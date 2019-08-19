package icai.dtc.isw.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import icai.dtc.isw.message.Message;

public class Client {

	public static void main(String args[]) {
		String host = "127.0.0.1";
		int port = 8081;
		new Client(host, port);
	}

	public Client(String host, int port) {
		try {
			String serverHostname = new String("127.0.0.1");

			System.out.println("Connecting to host " + serverHostname + " on port " + port + ".");

			Socket echoSocket = null;
			OutputStream out = null;
			InputStream in = null;

			try {
				echoSocket = new Socket(serverHostname, 8081);
				in = echoSocket.getInputStream();
				out = echoSocket.getOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
				
				//Create the objetct to send
				Message message=new Message();
				message.setContext("/prueba");
				objectOutputStream.writeObject(message);
				
				// create a DataInputStream so we can read data from it.
		        ObjectInputStream objectInputStream = new ObjectInputStream(in);
		        Message returnMessage= (Message)objectInputStream.readObject();
		        System.out.println("\nEl valor devuelto es: "+returnMessage.getContext());
				
			} catch (UnknownHostException e) {
				System.err.println("Unknown host: " + serverHostname);
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Unable to get streams from server");
				System.exit(1);
			}

			

			/** Closing all the resources */
			out.close();
			in.close();			
			echoSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}