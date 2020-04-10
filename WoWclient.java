import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class WoWclient {
	public static void main (String argv[]) throws Exception {

		if (argv.length < 2) {
			System.out.println("Syntax: WoWclient <hostname> <port>");
			return;
		}

		String file = argv[0];
		int port = Integer.parseInt(argv[1]);
  

		
		int socketClose = 0;     // will close socket after 1 min.

		//	BufferedReader inFromUser = new BufferedReader (new InputStreamReader(System.in));
		byte[] sendData = new byte[1];
		byte[] receiveData = new byte[1204];
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName(file);
		
		//System.out.println(IPAddress);
		
		//	String sentence = inFromUser.readLine();
		//	sendData = sentence.getBytes();
		while(true)
		{
			socketClose=socketClose+5;
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			clientSocket.send(sendPacket);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());
			System.out.println("FROM SERVER: "+modifiedSentence.trim());
			TimeUnit.SECONDS.sleep(5);
			if(socketClose==60)                                 // remove it to infinitely send and receive requests and response.
			{
				break;
			}
		}
		clientSocket.close();    // closing socket after 1 min.	

	}
}