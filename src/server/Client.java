package server;

import java.rmi.Naming;

public class Client {
	public static void main (String [] args){
		try {
			PartRepository partRepo = (PartRepository) Naming.lookup("rmi://192.168.15.63:1099/PartService");
			partRepo.addPart("parafuso", "é um parafuso");
			System.out.println("peça" + partRepo.containsPart("parafuso"));
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
