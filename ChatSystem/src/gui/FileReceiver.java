package gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public class FileReceiver {
	public static void startReceiver(JTextArea ja) {

		try{
	    	
			
			Runnable task = () -> {

				receive(ja);

			};
		
			Thread thread = new Thread(task);
			
			thread.start();
		
	}
		catch (Exception re) {
	    System.out.println("Exception : " + re);
	   // logger.info("Exception in ServerComp: " + re);
		} 
		
	}
	public static void receive(JTextArea ja) {
		System.out.println("file server started ");
		 byte b[]=new byte[192000];
		 MulticastSocket fileSocket=null;
		 try{
			 fileSocket=new MulticastSocket(1314);
		 fileSocket.joinGroup(InetAddress.getByName("230.1.1.5"));
		 System.out.println("hello1");
		 String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		 //Path currentRelativePath = Paths.get("");
		//	String filePath = currentRelativePath.toAbsolutePath().toString()+"\\ReceivedFile"+fileSuffix+".txt";
			//System.out.println("Current relative File path is:"+filePath);
		 FileWriter fileWriter = new FileWriter("C:\\Users\\p_wadhwa\\Downloads\\ReceivedFile"+fileSuffix+".txt");
         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
         int i=1;
         while (b!=null) {
        	// System.out.println("hello2");
             DatagramPacket dp = new DatagramPacket(b, b.length);
             fileSocket.receive(dp);
            // System.out.println("received"+dp);
             String str = new String(new String(dp.getData(), 0, dp.getLength()));
//
//             if (str.trim().equals("END")) {
//            	 System.out.println("hELLO4");
//            	 break;
//             }
             if(i==1) {
            	  ja.append("C:\\Users\\p_wadhwa\\Downloads\\ReceivedFile"+fileSuffix+".txt \n");
            	 i++;
             }
                 if(b==null) {
                	
                	 break;
                 }
             bufferedWriter.write(str);
             
             //System.out.println("file receved");
             bufferedWriter.newLine();
         //    ja.append("C:\\Users\\p_wadhwa\\Downloads\\heelloowwww.txt");
            // bufferedWriter.close();

             
         }
       
         bufferedWriter.close();
        

         }
	
	 catch (SocketException e) {
		System.out.println("Socket: " + e.getMessage());

	} catch (IOException e) {

		System.out.println("IO: " + e.getMessage());

	} finally {

		if (fileSocket != null)
			System.out.println("xyz");
		fileSocket.close();

	}
	}

}