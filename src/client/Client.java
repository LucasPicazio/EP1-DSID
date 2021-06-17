package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import server.Piece;
import server.Subcomponent;

public class Client {
	private static Scanner in;
	private static String currentRepository = "";
	private static Piece currentPiece;
	private static List <Subcomponent> currentSubcomponents;
	
	//Commands 
	private static final String CMD_CONECT_TO_SERVER = "bind";
	private static final String CMD_CREATE_PIECE = "newp";
	private static final String CMD_LIST_ALL_PIECES = "listp";
	private static final String CMD_GET_PIECE_BY_CODE = "getp";
	private static final String CMD_SHOW_PIECE_INFO = "showp";
	private static final String CMD_CLEAR_SUBCOMPONENTS = "clearlist";
	private static final String CMD_ADD_SUBCOMPONENTS = "addsubpart";
	private static final String CMD_ADD_PIECE = "addp";
	private static final String CMD_LEAVE_SESSION = "quit";
	private static final String CMD_SHOW_COMMANDS = "help";
	private static final String NO_SELECTED_PIECE_ERROR = "\nYou should select a piece before you proceed.\nqui"
							   + "Use " + CMD_GET_PIECE_BY_CODE + " command to select one from respository "
							   + "or " + CMD_CREATE_PIECE + " to create a new one.\n";
	
	public Client() {
		in = new Scanner(System.in);
		currentSubcomponents = new ArrayList<Subcomponent>();
	}
	
	public static void showGreetings() {
		String greetings =
			"******************************************"
		+ "\n*                                        *"
		+ "\n*   WELCOME TO PART REPOSITORY CLIENT!   *"
		+ "\n*                                        *"
		+ "\n******************************************"
		+ "\n\nIf you dont know how to start, type "
		+ CMD_SHOW_COMMANDS + "to see all available commands\n\n> ";
		System.out.print(greetings);
	}
	
	public static void processCommand(String setence) {
		String command = setence.split(" ")[0];
		String params = setence.substring(command.length()).trim();
		
		switch (command) {
			case CMD_ADD_PIECE:
				addPiece();
				break;
			case CMD_CREATE_PIECE:
				createPiece(params);
				break;
			case CMD_ADD_SUBCOMPONENTS:
				addSubcomponents(params);
				break;
			case CMD_CLEAR_SUBCOMPONENTS:
				clearSubcomponents();
				break;
			case CMD_CONECT_TO_SERVER:
				connectServer(params);
				break;
			case CMD_GET_PIECE_BY_CODE:
				getPiece(params);
				break;
			case CMD_LEAVE_SESSION:
				leaveSession();
				break;
			case CMD_LIST_ALL_PIECES:
				listPieces();
				break;
			case CMD_SHOW_COMMANDS:
				showCommands();
				break;
			case CMD_SHOW_PIECE_INFO:
				showPieceInfo();
				break;
			default:
				showUnknowCommandError(command);
		}
		
	}
	
	private static void createPiece(String params) {

		if (params.isEmpty()) {
			System.out.println("\nPlease enter the piece name and description\n");
			return;
		}
		String [] splitedParams = params.split(" ");
		String pieceName = splitedParams[0];
		String pieceDescription = "";
		if (splitedParams.length > 1) {
			pieceDescription = params.substring(pieceName.length());
		}
		currentPiece = new Piece(pieceName, pieceDescription, currentRepository);
		
		System.out.println("\n" + currentPiece.getCode() + ":" 
		+ pieceName + " created and selected locale\n");
	}

	private static void showUnknowCommandError(String wrongCommand) {
		System.out.println("\n" + wrongCommand + " : command not found. Type "
				+ CMD_SHOW_COMMANDS + " to see all available commands.\n");
	}

	private static void showPieceInfo() {
		if (currentPiece == null) {
			System.out.println(NO_SELECTED_PIECE_ERROR);
			return;
		}
		System.out.println("\n" + currentPiece.toString() + "\n");
	}

	private static void showCommands() {
		
		String commandsList = "\n"
		+ CMD_CONECT_TO_SERVER +  " <respositoryName>    connect to a repository server\n"
		+ CMD_LIST_ALL_PIECES +    "                     lists all pieces from current repository\n"
		+ CMD_CREATE_PIECE +      " <name> <description> creates a new piece and selects it\n"
		+ CMD_GET_PIECE_BY_CODE + " <pieceCode>          searchs for the piece on repository and selects it when found\n"
		+ CMD_SHOW_PIECE_INFO +    "                     show info from selected piece\n"
		+ CMD_CLEAR_SUBCOMPONENTS +    "                 empty the local subcomponents list\n"
		+ CMD_ADD_SUBCOMPONENTS +       " <quantity>     adds n selected pieces to the local subcomponents list\n"
		+ CMD_ADD_PIECE +         "                      add selected piece to the server repository with subcomponents from local list\n"
		+ CMD_LEAVE_SESSION +     "                      logout from current repository server\n"
		+ CMD_SHOW_COMMANDS +     "                      show this message\n";
		System.out.println(commandsList);
	}

	private static void listPieces() {
		//TODO
		System.out.println("\nlistPieces() called!\n");
	}

	private static void leaveSession() {
		//TODO: fechar conexao com servidor
		currentRepository = "";
		System.out.println("\nleaveSession() called!\n");
	}

	private static void getPiece(String pieceCode) {
		//TODO: atualizar currentPiece
		System.out.println("\ngetPiece() called!\n");
	}

	private static void connectServer(String repositoryName) {
		//TODO
		System.out.println("\nconnectServer() called!\n");
	}

	private static void clearSubcomponents() {
		int size = currentSubcomponents.size();
		currentSubcomponents.clear();
		System.out.println("\n" + size + " pieces removed from local subcomponents list\n");
	}

	private static void addSubcomponents(String params) {
		if (currentPiece == null) {
			System.out.println(NO_SELECTED_PIECE_ERROR);
			return;
		}
		if (params.isEmpty()) {
			System.out.println("\nPlease enter the quantity to be added.\n");
			return;
		}

		String firstParam = params.split(" ")[0];
		
		try {
			Integer quantity = Integer.parseInt(firstParam);
			if (quantity < 1) {
				System.out.println("\nThe quantity must be greater than 0\n");
				return;
			}
			Subcomponent subcomponent = new Subcomponent(currentPiece, quantity);
			currentSubcomponents.add(subcomponent);
		} catch (NumberFormatException e){
			System.out.println("\n" + firstParam + " isnt a number!\n");
			return;
		}
		System.out.println("\n" + currentPiece.getCode() + ":" 
		+ currentPiece.getName() + " added to local subcomponents list\n");
	}

	private static void addPiece() {
		if (currentRepository.isEmpty()) {
			System.out.println("\nYou should connect to a repository server before you proceed."
					+ " Use " + CMD_CONECT_TO_SERVER + " command for this\n");
			return;
		}
		if (currentPiece == null) {
			System.out.println(NO_SELECTED_PIECE_ERROR);
			return;
		}
		currentPiece.setSubcomponents(currentSubcomponents);
		
		//TODO: invocar RMI addPiece()
		
		System.out.println("\n" + currentPiece.getCode() + ":" 
		+ currentPiece.getName() + " added to server repository\n");
	}

	public static void main (String [] args) {
		showGreetings();
		Client client = new Client();
		
		while (true) {
			String setence = in.nextLine();
			processCommand(setence);
			System.out.print(currentRepository + "> ");
		}
	}
	
}
