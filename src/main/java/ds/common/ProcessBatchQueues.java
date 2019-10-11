package ds.common;

import ds.fileparse.IncrementalStg;
import ds.fileparse2.EntityXml;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 *  Copyright  2017  ThomsonReuters all right reserved
 *
 * @author Tiger Shi
 *
 * @version Nov 3, 2017 9:33:37 PM
 *
 */
public class ProcessBatchQueues {

	public static BlockingQueue<EntityXml> EntityQueue = new LinkedBlockingQueue<EntityXml>();
	public static AtomicInteger parseNum = new AtomicInteger(0);
	public static AtomicInteger insertNum = new AtomicInteger(0);
	public final static IncrementalStg DUMMY = new IncrementalStg();
	public static BlockingQueue<IncrementalStg> IncrementalQueue2 = new LinkedBlockingQueue<IncrementalStg>();
	public final static BlockingQueue<List<IncrementalStg>> IncrementalQueue = new LinkedBlockingQueue<List<IncrementalStg>>();

}
