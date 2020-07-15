package gui;

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
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JTextArea;

public class MessageReceiver {
	public static ChatBox chat;
 public JTextArea jaT;
 static Logger logger = Logger.getLogger(MessageReceiver.class.getName());
 static private FileHandler fh;
 static private SimpleFormatter sf;
 static String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
 
public static void receive(JTextArea ja) {
	
	
	MulticastSocket aSocket = null;

		try {
			Path currentRelativePath = Paths.get("");
			String path = currentRelativePath.toAbsolutePath().toString()+"\\logs\\"+fileSuffix+".logs";
			System.out.println("Current relative log path is: "+path);
			//String path="G:\\workspace\\Chats.log";
	    	
	        fh= new FileHandler(path);
	        sf = new SimpleFormatter();
	            fh.setFormatter(sf);
	            logger.addHandler(fh);

			aSocket = new MulticastSocket(1313);

			aSocket.joinGroup(InetAddress.getByName("230.1.1.5"));

			System.out.println(" Started............");
			
			while (true) {
				
				byte[] buffer = new byte[1000];

				DatagramPacket request = new DatagramPacket(buffer, buffer.length);

				aSocket.receive(request);
				
				String stringdata=new String (request.getData());
				System.out.println(stringdata);
				if(stringdata!=null) {
					logger.info(stringdata+"\n"); 
				//	System.out.println("1");
				ja.append(stringdata +"\n");
				

				}
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length, request.getAddress(),

						request.getPort());

				aSocket.send(reply);
				//return stringdata;
	
}
		

} catch (SocketException e) {
	System.out.println("Socket: " + e.getMessage());

} catch (IOException e) {

	System.out.println("IO: " + e.getMessage());

} finally {

	if (aSocket != null)
		System.out.println("xyz");
		aSocket.close();

}
	//	return null;

}
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
public static void main(String... s) {
	//Server.startServer();
}
}