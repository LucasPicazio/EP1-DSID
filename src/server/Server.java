package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Server implements PartRepository{
	
	public static String serverName;

	public static int port;
	public static List <Piece> pieces;
	
	public Server(String name, int port) {
		this.serverName = name;
		this.port = port;
	}
	
	public static String getServerName() {
		return serverName;
	}
	
	@Override
	public Piece getPart(int code) {
		for (Piece piece : pieces) {
			if (piece.getCode() == code) {
				return piece;
			}
		}
		return null;
	}
	
	@Override
	public int containsPart(String pieceName) {
		for (Piece piece : pieces) {
			if (piece.getName().equals(pieceName)) {
				return piece.getCode();
			}
		}
		return -1;
	}

	@Override
	public void addPart(String name, String description, List<Subcomponent> subcomponents) {
		
		int pieceCode = containsPart(name);
		if (pieceCode == -1) {
			Piece newPiece = new Piece (name, description, serverName, subcomponents);
			pieces.add(newPiece);
		} else {
			System.out.println("The piece " + name + " is already registered with code " + pieceCode);
		}
	}

	@Override
	public List<Piece> getPartList() {
		return pieces;
	}

	public static void test() {
		
		Piece ram = new Piece("Memoria RAM","NONONO", serverName);
		Piece processor = new Piece("Processador","NONONO", serverName);
		Piece vga = new Piece("Placa de Video","NONONO", serverName);
		Piece nucleo = new Piece("Nucleo","NONONO", serverName);
		Piece registrador = new Piece("Registrador","NONONO", serverName);
		Piece motherBoard = new Piece("Placa Mae","NONONO", serverName);
		nucleo.addSubcomponent(registrador);
		nucleo.addSubcomponent(registrador);
		processor.addSubcomponent(nucleo);
		processor.addSubcomponent(nucleo);
		processor.addSubcomponent(nucleo);
		motherBoard.addSubcomponent(ram);
		motherBoard.addSubcomponent(ram);
		motherBoard.addSubcomponent(processor);
		motherBoard.addSubcomponent(vga);
		
		System.out.println("Nucleo Subcomponents = " + nucleo.countSubcomponents(0));
		System.out.println("Processador Subcomponents = " + processor.countSubcomponents(0));
		System.out.println("MotherBoard Subcomponents = " + motherBoard.countSubcomponents(0));
		System.out.println("FIM DO TESTE");
	}
	
	
	public static void main (String [] args) {
		if (args.length < 2) {
			System.out.println("Insert the server name and port as parameters");
			return;
		}
		
		String name = args[0];
		int port = Integer.parseInt(args[1]);
		
		//Valida��o da porta
		try {
			Registry registry = LocateRegistry.getRegistry(port);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		Server server = new Server(name, port);
		server.test();
	}
}
