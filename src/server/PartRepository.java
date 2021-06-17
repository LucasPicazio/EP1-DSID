package server;

import java.util.List;
import java.rmi.*;

public interface PartRepository extends Remote{

	public Piece getPart(int code);
	
	public void addPart(String name, String description, List<Subcomponent> subcomponents);
	
	//Returns the piece code if contains or -1 if not
	public int containsPart(String pieceName);
	
	public List<Piece> getPartList();

}
