package server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import commons.Constants;

public class Server{
	
	public static String name;
	
	Server(String serverName, int port){
		try {
			name = serverName;
			System.setProperty("java.rmi.server.hostname", "localhost");
			PartRepository partRepo = new PartRepositoryImplement();
			Registry register = LocateRegistry.createRegistry(port);
			register.rebind(name, (Remote) partRepo);
			System.out.println("Initializing server " + name + " on port " + port + "...");
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public static String checkParams(String [] params) {
		
		//Verify if exists two params
		if (params.length < 2) {
			return "Insert the port and server name as parameters";
		}
		
		try {
			//Verify if the second param is a number
			//Throws NumberFormatException on fail
			Integer port = Integer.parseInt(params[1]);
			
			//Verify if the port is into allowed range
			if (port < Constants.MIN_PORT_NUMBER || port > Constants.MAX_PORT_NUMBER) {
		        return Constants.PORT_INTERVAL_ERROR;
		    }
			
			//Verify if the port is available
			//Throws IOException on fail
			ServerSocket ss = null;
		    DatagramSocket ds = null;
		    ss = new ServerSocket(port);
		    ss.setReuseAddress(true);
		    ds = new DatagramSocket(port);
		    ds.setReuseAddress(true);
		    if (ds != null) ds.close();
		    if (ss != null) ss.close();
			
		} catch (NumberFormatException e){
			return "The second param must be a number.\n" + params[1] + " isnt a number!";
		} catch (IOException e) {
			return "Cant open socket on port " + params[1];
		}
		
		return Constants.SUCESS_MESSAGE;
	}
	
	public static void main (String [] args){
		
		String paramsTestMessage = checkParams(args);
		
		if (paramsTestMessage.equals(Constants.SUCESS_MESSAGE)) {
			String serverName = args[0];
			Integer port = Integer.parseInt(args[1]);
			new Server(serverName, port);
		} else {
			System.out.println(paramsTestMessage);
		}
		
	}
	
}
