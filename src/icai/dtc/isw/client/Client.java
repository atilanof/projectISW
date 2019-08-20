package icai.dtc.isw.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import icai.dtc.isw.message.Message;

public class Client {
	private String host;
	private int port;

	public static void main(String args[]) {
		String host = "127.0.0.1";
		int port = 8081;
		Client cliente=new Client(host, port);
		HashMap<String,Object> session=new HashMap<String, Object>();
		session.put("Nombre","Soy el nombre");
		
		Message mensajeEnvio=new Message();
		mensajeEnvio.setContext("/getNombre");
		mensajeEnvio.setSession(session);
		cliente.sent(mensajeEnvio);
		System.out.println("3.- En Main.- El valor devuelto es: "+((String)mensajeEnvio.getSession().get("Nombre")));
	}
	
	public Client(String host, int port) {
		this.host=host;
		this.port=port;
	}
	

	public void sent(Message message) {
		try {

			System.out.println("Connecting to host " + host + " on port " + port + ".");

			Socket echoSocket = null;
			OutputStream out = null;
			InputStream in = null;

			try {
				echoSocket = new Socket(host, port);
				in = echoSocket.getInputStream();
				out = echoSocket.getOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
				
				//Create the objetct to send
				objectOutputStream.writeObject(message);
				
				// create a DataInputStream so we can read data from it.
		        ObjectInputStream objectInputStream = new ObjectInputStream(in);
		        message= (Message)objectInputStream.readObject();
		        System.out.println("\n1.- El valor devuelto es: "+message.getContext());
		        String cadena=(String) message.getSession().get("Nombre");
		        System.out.println("\n2.- La cadena devuelta es: "+cadena);
				
			} catch (UnknownHostException e) {
				System.err.println("Unknown host: " + host);
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