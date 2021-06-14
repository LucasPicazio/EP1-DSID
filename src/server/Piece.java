package server;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Piece implements Part{
	public static Integer lastCode;
	private int code;
	private String name;
	private String description;
	private String serverName;
	private List<Subcomponent> subcomponents;
	
	public Piece(String name, String description, String serverName) {
		if (lastCode == null) {
			this.lastCode = 0;
		} else {
			lastCode++;
		}
		this.code = lastCode;
		this.name = name;
		this.description = description;
		this.serverName = serverName;
		this.subcomponents = new ArrayList<Subcomponent>();
		
	}
	
	
	@Override
	public void addSubcomponent(Piece piece) {
		
		Subcomponent subcomponent = findSubcomponent(piece.getName());
		
		if (subcomponent != null) {
			subcomponent.addQuantity();
		} else {
			Subcomponent newSubcomponent = new Subcomponent(piece);
			subcomponents.add(newSubcomponent);
		}
	}
	
	@Override
	public Subcomponent findSubcomponent (String pieceName) {
		
		for (Subcomponent subcomponent : subcomponents) {
			if (subcomponent.getSubcomponent().getName().equals(pieceName)) {
				return subcomponent;
			}
		}
		
		return null;
	}
	
	@Override
	public String getServerName() {
		return serverName;
	}

	@Override
	public List<Subcomponent> getSubcomponents() {
		return this.subcomponents;
	}

	@Override
	public String getNameAndDescription() {
	
		return "Code: " + code + "\nDescription: " + description;
	}

	@Override
	public boolean isAggregated() {
		return (subcomponents.size() != 0);
	}

	@Override
	public int countSubcomponents(int count) {
		
		for (Subcomponent subcomponent : subcomponents) {
			Piece subcomponentPiece = (Piece) subcomponent.getSubcomponent();
			
			//Stop condition: primitive piece
			if (!subcomponentPiece.isAggregated()) {
				return subcomponent.getQuantity() + count;
			}
			
			count += subcomponent.getQuantity() * subcomponentPiece.countSubcomponents(count);
		}
		
		return count;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getCode() {
		return code;
	}
	
}
