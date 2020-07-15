package gui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MessageSender {
	public static String sendMessage(int serverPort,String text) {
//		System.out.println("serverport " +serverPort);
	//	System.out.println("here");
		DatagramSocket aSocket = null;
        
		try {
            
			aSocket = new DatagramSocket();
            String mess=text;
            
			byte[] message = mess.getBytes();

			InetAddress aHost = InetAddress.getByName("230.1.1.5");

			DatagramPacket request = new DatagramPacket(message, message.length, aHost, serverPort);
			aSocket.send(request);

			System.out.println(" message sent is: "

					+ new String(request.getData()));
		} catch (SocketException e) {

			System.out.println("Socket: " + e.getMessage());

		} catch (IOException e) {

			e.printStackTrace();

			System.out.println("IO: " + e.getMessage());

		} finally {

			if (aSocket != null)

				aSocket.close();

		}
		return null;

	}
	

}
