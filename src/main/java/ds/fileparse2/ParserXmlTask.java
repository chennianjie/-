package ds.fileparse2;


import ds.common.ProcessBatchQueues;
import ds.fileparse.IncrementalStg;

import java.util.List;

public class ParserXmlTask implements Runnable {

	 
	 
	public void run() {
		// TODO Auto-generated method stub
		
	
			while(!ProcessBatchQueues.EntityQueue.isEmpty()){
			
			EntityXml xmlEntity = ProcessBatchQueues.EntityQueue.poll();
			
			
			if(!(xmlEntity==null)){
				
			List<IncrementalStg> incrstgList = DomXmlUtils.parserEntity(xmlEntity.getFileName(), xmlEntity.getUuid(), xmlEntity.getXmlContent());
			if(incrstgList !=null){
			        try {
						ProcessBatchQueues.IncrementalQueue.put(incrstgList);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				
			
			}
			
			
		}
		
		
	}

}
