package ds.fileparse2;


import ds.common.OracleConnection;
import ds.common.ProcessBatchQueues;
import ds.common.PropertyUtil;
import ds.common.PropsStr;
import ds.fileparse.IncrementalStg;
import oracle.jdbc.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.JAVA_STRUCT;
import oracle.sql.StructDescriptor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MergeProduceTask implements Runnable {
	private Connection conn;
	private static int batchNum = Integer.parseInt(PropertyUtil
			.getPropValue(PropsStr.BatchNumber));


	public MergeProduceTask(Connection conn) {
		this.conn = conn;
	}

	public static void setStoptask() {
	}

	private static ARRAY tran2Oracle(Connection con,
									 List<List<IncrementalStg>> istgList, Integer batchIndex)
			throws SQLException {
		int listCount = 0;
		for (int j = 0; j < istgList.size(); j++) {
			for (int i = 0; i < istgList.get(j).size(); i++) {
				listCount++;
			}
		}

		 StructDescriptor structDesc = StructDescriptor.createDescriptor(
		 "RDC_COLLECTED.RDC_INCR_TYPE", con);
//		StructDescriptor structDesc = StructDescriptor.createDescriptor(
//				"RDC_INCR_TYPE", con);
		JAVA_STRUCT[] structs = new JAVA_STRUCT[listCount];
		int listSeq = 0;
		for (int j = 0; j < istgList.size(); j++) {

			List<IncrementalStg> istgListList = istgList.get(j);

			for (int i = 0; i < istgListList.size(); i++) {

				Object[] objeArry = new Object[19];
				objeArry[0] = istgListList.get(i).getNda_pi();
				objeArry[1] = istgListList.get(i).getVersion();
				objeArry[2] = istgListList.get(i).getEntity_type();
				objeArry[3] = istgListList.get(i).getEntity_sub_type();
				objeArry[4] = istgListList.get(i).getEntity_rcs_sub_type();
				objeArry[5] = istgListList.get(i).getEntity_event();

				objeArry[6] = istgListList.get(i).getProperty_id();
				// System.out.println("istgListList.get(i).getProperty_id() --->"
				// +istgListList.get(i).getProperty_id());

				objeArry[7] = istgListList.get(i).getCurrent_value();
				objeArry[8] = istgListList.get(i).getClassifier_type();
				objeArry[9] = istgListList.get(i).getValid_from();
				objeArry[10] = istgListList.get(i).getValid_from_inc_time();
				objeArry[11] = istgListList.get(i).getValid_to();
				objeArry[12] = istgListList.get(i).getValid_to_inc_time();

				objeArry[13] = istgListList.get(i).getLanguage();
				objeArry[14] = istgListList.get(i).getBpm_batch_guid();
				objeArry[15] = batchIndex;
				objeArry[16] = istgListList.get(i).getCreate_date();
				objeArry[17] = istgListList.get(i).getCreate_by();
				objeArry[18] = istgListList.get(i).getReference_flag();
				structs[listSeq] = new JAVA_STRUCT(structDesc, con, objeArry);
				++listSeq;
			}

		}
		 ArrayDescriptor arryDesc = ArrayDescriptor.createDescriptor(
		 "RDC_COLLECTED.RDC_INCR_COL_TYPE", con);
//		ArrayDescriptor arryDesc = ArrayDescriptor.createDescriptor(
//				"RDC_INCR_COL_TYPE", con);

		ARRAY list = new ARRAY(arryDesc, con, structs);
//		System.out.println(" list " + list.length());
		con.close();
		return list;

	}

	public void run() {
		// System.out.println("insert into incremental table ");

		List<List<IncrementalStg>> istgList = new ArrayList<List<IncrementalStg>>();
	
		
		while (!ProcessBatchQueues.IncrementalQueue.isEmpty()) {

			try {
				List<IncrementalStg>

				istg = ProcessBatchQueues.IncrementalQueue.poll(10,
						TimeUnit.MILLISECONDS);

				istgList.add(istg);
				if (istgList.size() == batchNum) {
//					 System.out.println("while-batchIndexCount-->" +
//					 batchNum);

					OracleCallableStatement proc = null;
					try {

						proc = (OracleCallableStatement) conn
								.prepareCall("{ call RDC_COLLECTED.RDC_INSERT_INC_PDP_PRC(?,?,?,?) }");

						proc.setString(1, SDIFileProcessor.CurrFileName);
						proc.setString(2, SDIFileProcessor.UUid);
						int bat_index = SDIFileProcessor.batch_index
								.getAndIncrement();
						proc.setInt(3, bat_index);
						Connection connection = OracleConnection.getConnection();
						ARRAY resultArr = tran2Oracle(connection, istgList,
								bat_index);
						proc.setARRAY(4, resultArr);

						proc.execute();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					} finally {
						try {
							proc.close();
							istgList.clear();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (istgList.size() > 0) {

			OracleCallableStatement proc = null;
			try {
				proc = (OracleCallableStatement) conn
						.prepareCall("{ call RDC_COLLECTED.RDC_INSERT_INC_PDP_PRC(?,?,?,?) }");

				proc.setString(1, SDIFileProcessor.CurrFileName);
				proc.setString(2, SDIFileProcessor.UUid);
				int bat_index = SDIFileProcessor.batch_index.getAndIncrement();
				proc.setInt(3, bat_index);
				Connection connection = OracleConnection.getConnection();
				ARRAY resultArr = tran2Oracle(connection, istgList, bat_index);

				proc.setARRAY(4, resultArr);

				proc.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} finally {
				try {
					proc.close();
					istgList.clear();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			  
				 
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;

		}

	}

}
