package server;

import java.util.ArrayList;

import commons.Part;
import commons.Piece;

import java.rmi.*;

public interface PartRepository extends Remote{

	public Part getPart(int code) throws Exception;

	//Returns "OK" on sucess
	public String addPart(Part part) throws Exception;
	
	//Returns the piece code if exists or -1 otherwise
	public int getPieceCodeFromName(String pieceName) throws Exception;
	
	public ArrayList<Piece> getPartList() throws Exception;
	
	public String getName();

}
