package commons;

import java.io.Serializable;
import java.util.List;

public interface Part extends Serializable{
	public String getServerName();
	
	public String getNameAndDescription();
	
	public boolean isAggregated(); 
	
	public List<Subcomponent> getSubcomponents();
	
	//Recursive method. Should be called passing zero as parameter for index and count
	public int countSubcomponents(int count);
	
	public void addSubcomponent(Piece piece);
	
	public Subcomponent findSubcomponent(String pieceName);
	
	public int getCode();

	public String getName();
	
	public String getDescription();
	
	public void setSubcomponents(List<Subcomponent> subcomponents);
}
