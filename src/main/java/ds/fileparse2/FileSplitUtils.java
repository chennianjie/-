package ds.fileparse2;

import ds.common.ProcessBatchQueues;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * 
 * @author U6063165
 *
 */
public class FileSplitUtils {
private static Logger logger = Logger.getLogger(FileSplitUtils.class);


 private FileSplitUtils(){
 }

 

public static void splitZipFile(File file,String uuid, String beginEleTag, String endEleTag) throws IOException{
       long allNumPerFile =0;
	    long startTime=System.currentTimeMillis();  
    
      ZipInputStream Zin=new ZipInputStream(new FileInputStream(file));//输入�?zip路径  
      
      BufferedInputStream Bin=new BufferedInputStream(Zin);  
      BufferedReader bReader = new BufferedReader(new InputStreamReader(Bin));

      ZipEntry entry;  
      
          while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
       
            String lineStr = null;
   		   boolean flag = false;
   		   StringBuilder sBuilder = null;
   		  
   		   while ((lineStr=bReader.readLine())!=null){
   		    
   		   // System.out.println(lineStr);
   		    
   		    if(flag){
   				// return lineStr;
   		    	if (!(lineStr.contains(endEleTag))) {
   		    		if(!lineStr.trim().equals("")){
		    			sBuilder.append(lineStr);
		    		}
   				//	System.out.println("------use line ------"+lineStr);
   				}else{
   					sBuilder.append(lineStr);
   					//System.out.println(sBuilder.toString());
   				/*	long permId = ParserQuoteXmlUtil.getPermid(sBuilder.toString(),1);
   				    bqXml.add(new Quote(permId,sBuilder.toString()));
   				    */
   				    
   				     ProcessBatchQueues.EntityQueue.add(new EntityXml(file.getName(),uuid, sBuilder.toString()));
   				    
   				    allNumPerFile++;
   				    flag = false;
   				    sBuilder = null;
   				}
   			 }else{
   				 if(lineStr.contains(beginEleTag)){
   					sBuilder = new StringBuilder();
   					sBuilder.append(lineStr);
   					//System.err.println("------no user line start------"+lineStr);
   					flag = true;
   				 }else if((lineStr.contains(endEleTag))){
   					sBuilder.append(lineStr);
   					ProcessBatchQueues.EntityQueue.add(new EntityXml(file.getName(),uuid, sBuilder.toString()));
   				    
   				   allNumPerFile++;;
   				    flag = false;
   				    sBuilder = null;
   				 }else{
   				    logger.info("------no user line end------"+lineStr);
   				 }
        	  
     
            
   			 }
              
          }  
   		   
   		long endTime=System.currentTimeMillis();  
   		   
   		 logger.info("the entity--"+entry.getName()+" have entities uuid-- "+uuid+"_____"+allNumPerFile+"--cost time： "+(endTime-startTime)+" ms");
          }
          bReader.close();
          Bin.close();  
          Zin.close();  
   
  long endTime=System.currentTimeMillis();  
  logger.info("the file"+file.getName()+" have Numbers  "+allNumPerFile+"cost time： "+(endTime-startTime)+" ms");

	}
 
 
 
 
	
 public static void splitFile(File file,String beginEleTag, String endEleTag, String uuid) throws IOException, InterruptedException{
	 FileReader fr = null;
	 BufferedReader bf = null;
	 long allNumPerFile =0;
	 long startTime=System.currentTimeMillis();  
	 try {
		  fr = new FileReader(file);
		   bf= new BufferedReader(fr);
		   String lineStr = null;
		   boolean flag = false;
		   StringBuilder sBuilder = null;
		   while ((lineStr=bf.readLine())!=null){
		    
		   // System.out.println(lineStr);
		    
		    if(flag){
				// return lineStr;
		    	if (!(lineStr.contains(endEleTag))) {
		    		
		    		if(!lineStr.trim().equals("")){
		    			sBuilder.append(lineStr);
		    		}
				//	System.out.println("------use line ------"+lineStr);
				}else{
					sBuilder.append(lineStr);
					//System.out.println(sBuilder.toString());
					ProcessBatchQueues.EntityQueue.add(new EntityXml(file.getName(),uuid, sBuilder.toString()));
				    allNumPerFile++;
				    flag = false;
				    sBuilder = null;
				}
			 }else{
				 if(lineStr.contains(beginEleTag)){
					sBuilder = new StringBuilder();
					
					
					sBuilder.append(lineStr);
					//System.err.println("------no user line start------"+lineStr);
					flag = true;
				 }else if((lineStr.contains(endEleTag))){
					sBuilder.append(lineStr);
				
				    
				    
					ProcessBatchQueues.EntityQueue.add(new EntityXml(file.getName(),uuid, sBuilder.toString()));
				    
				    allNumPerFile++;
				    flag = false;
				    sBuilder = null;
				 }else{
				    //System.err.println("------no user line end------"+lineStr);
					 logger.info("------no user line end------"+lineStr);
				 }
			 }
		   }
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		bf.close();
		fr.close();
	}
	 
	 
	 long endTime=System.currentTimeMillis();  
	  logger.info("the file----"+file.getName()+"-----uuid--- "+uuid+"--- have entities --"+allNumPerFile+"--cost time： "+(endTime-startTime)+" ms");
 }
 
 
 
 

 
 
 public static void main(String[] args) {
	
	
}
}
