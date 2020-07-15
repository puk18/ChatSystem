package gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class FileSender {
	public static String sendFile(int serverPort,String text) {
		DatagramSocket fileSocket = null;
		String s=null;
        
		try {
		      byte sdata[] = new byte[1024];
		      fileSocket = new DatagramSocket();
		        InetAddress ip = InetAddress.getByName("230.1.1.5");
		        FileInputStream inputStream = new FileInputStream(text);
		        int nRead = 0;
		        while ((nRead = inputStream.read(sdata)) != -1) {
		        	fileSocket.send(new DatagramPacket(sdata, sdata.length, ip, 1314));
		           }		        
		} catch (SocketException e) {

			System.out.println("Socket: " + e.getMessage());

		} catch (IOException e) {

			e.printStackTrace();

			System.out.println("IO: " + e.getMessage());

		} finally {

			if (fileSocket != null)

				fileSocket.close();

		}
		return null;

	}

}
