package server;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Server{
	
	Server(){
		try {
			System.setProperty("java.rmi.server.hostname", "192.168.15.37");
			LocateRegistry.createRegistry(1099);
			PartRepository partRepo = new PartRepositoryImplement();
			Naming.bind("PartService", (Remote) partRepo);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main (String [] args){
		new Server();
	}
	
}
