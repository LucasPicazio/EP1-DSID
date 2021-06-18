package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PartRepositoryImplement extends UnicastRemoteObject implements PartRepository{

	private static final long serialVersionUID = 1L;

	protected PartRepositoryImplement() throws RemoteException {
		super();
	}
	
	public static String serverName;
	public static int port;
	public static List <Piece> pieces = new ArrayList<Piece>();
	
	public static String getServerName() {
		return serverName;
	}
	
	public Piece getPart(int code) throws RemoteException{
		for (Piece piece : pieces) {
			if (piece.getCode() == code) {
				return piece;
			}
		}
		return null;
	}

	public void addPart(String name, String description) throws RemoteException{
		
		int pieceCode = containsPart(name);
		if (pieceCode == -1) {
			Piece newPiece = new Piece (name, description, serverName);
			pieces.add(newPiece);
		} else {
			System.out.println("The piece " + name + " is already registered with code " + pieceCode);
		}
	}


	public int containsPart(String pieceName) throws RemoteException{
		for (Piece piece : pieces) {
			if (piece.getName().equals(pieceName)) {
				return piece.getCode();
			}
		}
		return -1;
	}

	
	public List<Piece> getPartList() throws RemoteException{
		return pieces;
	}

}
