package client;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import com.sun.tools.javac.util.List;

import commons.Constants;
import commons.Piece;
import commons.Subcomponent;
import server.PartRepository;

public class CommandsUtil {
	
	private static PartRepository partRepo;
	
	public static void processCommand(String setence) {
		String command = setence.split(" ")[0];
		String params = setence.substring(command.length()).trim();
		
		switch (command) {
			case Constants.CMD_ADD_PIECE:
				addPiece();
				break;
			case Constants.CMD_CREATE_PIECE:
				createPiece(params);
				break;
			case Constants.CMD_ADD_SUBCOMPONENTS:
				addSubcomponents(params);
				break;
			case Constants.CMD_CLEAR_SUBCOMPONENTS:
				clearSubcomponents();
				break;
			case Constants.CMD_CONECT_TO_SERVER:
				connectServer(params);
				break;
			case Constants.CMD_GET_PIECE_BY_CODE:
				getPiece(params);
				break;
			case Constants.CMD_LEAVE_SESSION:
				leaveSession();
				break;
			case Constants.CMD_LIST_ALL_PIECES:
				listPieces();
				break;
			case Constants.CMD_SHOW_COMMANDS:
				showCommands();
				break;
			case Constants.CMD_SHOW_PIECE_INFO:
				showPieceInfo();
				break;
			default:
				showUnknowCommandError(command);
		}
	}
	
	private static void createPiece(String params) {

		if (params.isEmpty()) {
			showMessage("Please enter the piece name and description");
			return;
		}
		String [] splitedParams = params.split(" ");
		String pieceName = splitedParams[0];
		String pieceDescription = "";
		if (splitedParams.length > 1) {
			pieceDescription = params.substring(pieceName.length()).trim();
		}
		
		Piece currentPiece = new Piece(pieceName, pieceDescription, Client.getCurrentRepository());
		Client.setCurrentPiece(currentPiece);
		
		showMessage("Piece " + pieceName + " created with code " + currentPiece.getCode() + " and selected");
	}

	private static void showUnknowCommandError(String wrongCommand) {
		
		showMessage(wrongCommand + " : command not found. Type " + 
		Constants.CMD_SHOW_COMMANDS + " to see all available commands.");
	}

	private static void showPieceInfo() {
		
		Piece currentPiece = Client.getCurrentPiece();
		if (currentPiece == null) {
			showMessage(NO_SELECTED_PIECE_ERROR);
			return;
		}
		showMessage("PIECE INFO\n" + currentPiece.toString());
	}

	private static void showCommands() {
		
		String commandsList = 
		  Constants.CMD_CONECT_TO_SERVER +  " <respositoryName>    connect to a repository server\n"
		+ Constants.CMD_LIST_ALL_PIECES +    "                     lists all pieces from current repository\n"
		+ Constants.CMD_CREATE_PIECE +      " <name> <description> creates a new piece and selects it\n"
		+ Constants.CMD_GET_PIECE_BY_CODE + " <pieceCode>          searchs for the piece on repository and selects it when found\n"
		+ Constants.CMD_SHOW_PIECE_INFO +    "                     show info from selected piece\n"
		+ Constants.CMD_CLEAR_SUBCOMPONENTS +    "                 empty the local subcomponents list\n"
		+ Constants.CMD_ADD_SUBCOMPONENTS +       " <quantity>     adds n selected pieces to the local subcomponents list\n"
		+ Constants.CMD_ADD_PIECE +         "                      add selected piece to the server repository with subcomponents from local list\n"
		+ Constants.CMD_LEAVE_SESSION +     "                      logout from current repository server\n"
		+ Constants.CMD_SHOW_COMMANDS +     "                      show this message";
		showMessage(commandsList);
	}

	private static void listPieces() {
		
		if (partRepo == null) {
			showMessage(NO_SERVER_COMM_ERROR);
			return;
		}
		
		try {
			ArrayList<Piece> pieces = partRepo.getPartList();
			String piecesInfo = "";
			for (Piece piece : pieces) {
				piecesInfo = piecesInfo.concat("Nome:"+piece.getName().toString() + "\n"+
								"Code:"+piece.getCode()+"\n"+"Description:"+ piece.getDescription().toString()+"\n"+"\n");
			}
			showMessage(piecesInfo);
		} catch (Exception e) {
			showMessage("Failed to get pieces list from repository");
		}
	}

	private static void leaveSession() {
		
		if (partRepo == null) {
			showMessage("You arent connected to any repository");
		} else {
			Client.setCurrentRepository("");
			partRepo = null;
			showMessage("Leaving connection from repository");
		}
	}

	private static void getPiece(String pieceCode) {
		
		if (partRepo == null) {
			showMessage(NO_SERVER_COMM_ERROR);
			return;
		}
		
		try {
			int code = Integer.parseInt(pieceCode);
			Piece piece = (Piece) partRepo.getPart(code);
			
			if (piece == null) {
				showMessage("Piece code " + piece.getCode() + " not found on server repository");
			} else {
				showMessage("Piece " + piece.getName() + " selected!");
				Client.setCurrentPiece(piece);
			}
		} catch (NumberFormatException e){
			showMessage(pieceCode + " isnt a number!");
			return;
		} catch (Exception e) {
			showMessage("Failed to get piece from repository");
		}
	}

	private static void connectServer(String params) {

		if (params.isEmpty()) {
			showMessage("Please enter the respository name and port.");
			return;
		}
		
		String [] splitedParams = params.split(" ");
		String serverName = splitedParams[0];
		String port = splitedParams[1]; //TODO: criar método para pegar port do server

		//Verify the port
		if (splitedParams.length > 1) {
			try {
				port = splitedParams[1]; 
				int portNumber = Integer.parseInt(port);
				if (portNumber < Constants.MIN_PORT_NUMBER || portNumber > Constants.MAX_PORT_NUMBER) {
			        showMessage(Constants.PORT_INTERVAL_ERROR);
			        return;
			    }
			} catch (NumberFormatException e){
				showMessage(splitedParams[1] + " isnt a number!");
				return;
			}
		}
		
		try {
			String serverAddress = "rmi://" + Constants.DEFAULT_SERVER_IP + ":" + port + "/" + serverName;
			partRepo = (PartRepository) Naming.lookup(serverAddress);
		} catch (Exception e) {
			showMessage("Cant connect to repository " + serverName + " on port " + port);
		}
		
		Client.setCurrentRepository(serverName);
		showMessage("Connected succesfully to repository " + serverName + " on port " + port);
	}

	private static void clearSubcomponents() {
		
		int size = Client.getCurrentSubcomponents().size();
		Client.getCurrentSubcomponents().clear();
		showMessage(size + " pieces removed from local subcomponents list");
	}

	private static void addSubcomponents(String params) {
		
		Piece currentPiece = Client.getCurrentPiece();
		if (currentPiece == null) {
			showMessage(NO_SELECTED_PIECE_ERROR);
			return;
		}
		if (params.isEmpty()) {
			showMessage("Please enter the quantity to be added.");
			return;
		}

		String firstParam = params.split(" ")[0];
		
		try {
			Integer quantity = Integer.parseInt(firstParam);
			if (quantity < 1) {
				showMessage("The quantity must be greater than 0");
				return;
			}
			Subcomponent subcomponent = new Subcomponent(currentPiece, quantity);
			Client.getCurrentSubcomponents().add(subcomponent);
			showMessage(quantity + "x " + currentPiece.getName() + " added to local subcomponents list");
		} catch (NumberFormatException e){
			showMessage(firstParam + " isnt a number!");
			return;
		}
	}

	private static void addPiece() {
		if (partRepo == null) {
			showMessage(NO_SERVER_COMM_ERROR);
			return;
		}
		Piece currentPiece = Client.getCurrentPiece();
		if (currentPiece == null) {
			showMessage(NO_SELECTED_PIECE_ERROR);
			return;
		}
		currentPiece.setSubcomponents(Client.getCurrentSubcomponents());
		
		try {
			String response = partRepo.addPart(currentPiece);
			if (response.equals(Constants.SUCESS_MESSAGE)) {
				showMessage("Piece " + currentPiece.getName() + " added to server repository");
			} else {
				showMessage(response);
			}
		} catch (Exception e) {
			showMessage("Failed to add piece " + currentPiece.getName() + " to server repository");
		}
	}
	
	public static void showMessage(String message) {
		System.out.println("\n" + message + "\n");
	}
	
	//Log Messages
	public static final String NO_SELECTED_PIECE_ERROR = "You should select a piece before you proceed.\n"
			+ "Use " + Constants.CMD_GET_PIECE_BY_CODE + " command to select one from respository "
			+ "or " + Constants.CMD_CREATE_PIECE + " to create a new one.";
	
	public static final String NO_SERVER_COMM_ERROR = "You should connect to a repository server before you proceed.\n" 
													+ "Use " + Constants.CMD_CONECT_TO_SERVER + " command for this";
}
