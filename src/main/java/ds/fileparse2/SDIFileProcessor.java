package ds.fileparse2;


import ds.common.*;
import ds.exception.DBFeedException;
import ds.exception.FeedAbortException;
import ds.exception.FeedException;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SDIFileProcessor implements IFeedFileProcessor {
	public static String UUid = null;
	public static AtomicInteger batch_index = null;
	public static List<Long> propertyIdList = new ArrayList<Long>();
	public static String CurrFileName = null;

	String DIR;

	int FileSplit;

	private Connection DBConnection;
	private int collectThread;
	private FeedInfo feedInfo;

	public SDIFileProcessor(String work, int order, int split)
			throws SQLException {
		new File(work);
		DIR = work;
		this.setExecutionOrder(order);
		this.FileSplit = split;

	}

	public SDIFileProcessor() {
	}

	private void setExecutionOrder(int order) {
	}

	public static String getFileType(String fileName) {
		String[] strArray = fileName.split("\\.");
		int suffixIndex = strArray.length - 1;
		System.out.println(strArray[suffixIndex]);
		return strArray[suffixIndex];
	}

	@Override
	public void process() throws FeedException {

		this.init();
		System.out.println("wait for file");
		try {
			List<File> files = getLocalAbsFiles(PropertyUtil
					.getPropValue(PropsStr.WorkPath));
			if (files != null) {
				//Collections.sort(files);
				for (File insertFile : files) {
					String fileType = getFileType(insertFile.getName());
					if (fileType.equals(PropertyUtil
							.getPropValue(PropsStr.FileType))) {
						initBatchIdUUID(insertFile.getName());
						System.out.println("insertFile.getName() : "
								+ insertFile.getName());

						insertFileStatus(this.DBConnection, UUid,
								insertFile.getName(), "StartPDP");
						try {
							FileSplitUtils.splitFile(insertFile, "<entity",
									"</entity>", UUid);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						while (ProcessBatchQueues.EntityQueue.isEmpty()) {

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						}
						System.out.println("merge file  : "
								+ insertFile.getName());
						ExecutorService serviceParse = Executors
								.newFixedThreadPool(16);

						for (int i = 0; i < Integer.parseInt(PropertyUtil
								.getPropValue(PropsStr.PaserXMLThreadNum)); i++) {
							serviceParse.submit(new ParserXmlTask());

						}
						serviceParse.shutdown();

						while (ProcessBatchQueues.IncrementalQueue.isEmpty()) {

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						ExecutorService serviceMerge = Executors
								.newFixedThreadPool(4);

						for (int i = 0; i < Integer.parseInt(PropertyUtil
								.getPropValue(PropsStr.PaserXMLThreadNum)); i++) {
							Connection changeCon = ds.common.OracleConnection.getConnection();
							serviceMerge
									.submit(new MergeProduceTask(changeCon));

						}

						serviceMerge.shutdown();

						while (true) {
							if (serviceParse.isTerminated()) {

								break;
							}
							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						while (true) {
							if (serviceMerge.isTerminated()) {
								this.DBConnection.commit();
								break;
							}
							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						moveFile(insertFile,
								PropertyUtil
										.getPropValue(PropsStr.ArchievePath),
								UUid);
						insertFileStatus(this.DBConnection, UUid,
								insertFile.getName(), "EndPDP");
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FeedAbortException(e.getMessage());
		}

	}

	private void insertFileStatus(Connection dbConnection2, String uuid_file,
			String name, String string) {
		CallableStatement cs = null;
		try {
			cs = dbConnection2.prepareCall("call RDC_FILE_status_PRC(?,?,?)");

			cs.setString(1, uuid_file);

			cs.setString(2, name);
			cs.setString(3, string);

			cs.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBFeedException(e.getMessage());
		} finally {
			try {
				if (cs != null && !cs.isClosed())
					cs.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBFeedException(e.getLocalizedMessage());
			}
		}
	}

	@Override
	public void setFeedInfo(FeedInfo feedInfo) {
		this.feedInfo = feedInfo;
	}

	@Override
	public FeedInfo getFeedInfo() {
		// TODO Auto-generated method stub
		return this.feedInfo;
	}

	@Override
	public int getExecutionOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void setDBConnection() {
		try {
			if (this.DBConnection == null || this.DBConnection.isClosed()) {
				this.DBConnection = ds.common.OracleConnection.getConnection();
			} else if (DBConnection != null && !DBConnection.isValid(10)) {
				this.DBConnection.close();
				this.DBConnection = ds.common.OracleConnection.getConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBFeedException(e.getLocalizedMessage());
		}
	}

	public void setCollectThread(int threadNumber) {
		this.collectThread = threadNumber;
	}

	public int getCollectThread() {
		return this.collectThread;
	}

	private void init() {
		this.setDBConnection();
		propertyIdList.clear();
		propertyIdList = PropertyUtil.getPropertyIds();
	}

	private static void initBatchIdUUID(String fileName) {
		batch_index = new AtomicInteger(1);
		UUid = UUID.randomUUID().toString();
		CurrFileName = fileName;

	}

	private static List<File> getLocalAbsFiles(String localPath) {
		File localfile = new File(localPath);
		File[] iFiles = localfile.listFiles();
		if (iFiles != null) {
			List<File> filelist = Arrays.asList(iFiles);
			 Collections.sort(filelist, new Comparator<File>() {
			        @Override
			        public int compare(File o1, File o2) {
			            if (o1.isDirectory() && o2.isFile())
			                return -1;
			            if (o1.isFile() && o2.isDirectory())
			                return 1;
			            return o1.getName().compareTo(o2.getName());
			        }
			    });
			
			return filelist;
		} else {
			return null;
		}

	}

	private static boolean moveFile(File srcFile, String destPath, String uuid) {

		// Destination directory
		File dir = new File(destPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// Move file to new directory
		boolean success = srcFile.renameTo(new File(dir, srcFile.getName()
				+ "__" + uuid));

		return success;
	}

}
