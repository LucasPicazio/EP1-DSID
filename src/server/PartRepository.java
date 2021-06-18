package server;

import java.util.List;
import java.rmi.*;

public interface PartRepository extends Remote{

	public Piece getPart(int code) throws Exception;
	
	public void addPart(String name, String description) throws Exception;
	
	//Returns the piece code if contains or -1 if not
	public int containsPart(String pieceName) throws Exception;
	
	public List<Piece> getPartList() throws Exception;

}
