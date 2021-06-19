package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import commons.Constants;
import commons.Part;
import commons.Piece;

public class PartRepositoryImplement extends UnicastRemoteObject implements PartRepository{

	private static final long serialVersionUID = 1L;

	protected PartRepositoryImplement() throws RemoteException {
		super();
	}
	
	public static ArrayList <Piece> pieces = new ArrayList<Piece>();
	@Override
	public Piece getPart(int code) throws RemoteException{
		for (Piece piece : pieces) {
			if (piece.getCode() == code) {
				log("PIECE CODE " + code + " FOUND INTO REPOSITORY!"
							+ "\nRETURNED OBJECT\n" + piece.toString());
				return piece;
			}
		}
		
		log("PIECE CODE " + code + " NOT FOUND INTO REPOSITORY!"
		 		  	+ "\nRETURNED NULL");
		return null;
	}

	@Override
	public String addPart(Part part) throws Exception{
		Piece newPiece = (Piece) part;
		String name = newPiece.getName();
		int pieceCode = getPieceCodeFromName(name);
		if (pieceCode == -1) {
			//TODO: Verificar se o codigo da peca eh repetido
			pieces.add(newPiece);
			log("PIECE ADDED TO RESPOSITORY!\n" + newPiece.toString());
		} else {
			log("TRIED TO ADD PIECE " + name + ". BUT THE NAME ALREADY EXISTS ON REPOSITORY.");
			return "The piece " + name + " name already exists on repository with code " + pieceCode;
		}
		return Constants.SUCESS_MESSAGE;
	}

	@Override
	public int getPieceCodeFromName(String pieceName) throws Exception{
		for (Piece piece : pieces) {
			if (piece.getName().equals(pieceName)) {
				int code = piece.getCode();
				log("PIECE " + pieceName + " FOUND INTO REPOSITORY!"
							 + "\nRETURNED CODE " + code);
				return code;
			}
		}
		log("PIECE " + pieceName + " NOT FOUND INTO REPOSITORY!"
					 + "\nRETURNED CODE -1");
		return -1;
	}

	@Override
	public ArrayList<Piece> getPartList() throws Exception{
		log("PIECE LIST REQUESTED!");
		return pieces;
	}

	public static void log(String message) {
//		Calendar now = Calendar.getInstance();
//		String time = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND); 
//		System.out.println("[" + time + "] " + message + "\n");
	}
}
