package server;

import java.rmi.Naming;
import java.util.ArrayList;

import commons.Subcomponent;

public class Client {
	public static void main (String [] args){
		try {
			PartRepository partRepo = (PartRepository) Naming.lookup("rmi://192.168.15.37:1099/PartService");
			partRepo.addPart("parafuso", "um parafuso", "NONONO", new ArrayList<Subcomponent>());
			System.out.println("peca" + partRepo.getPieceCodeFromName("parafuso"));
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
