package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import server.Piece;

public class Client {
	private static Scanner in;
	private static String currentRepository;
	private static Piece currentPiece;
	private static List <Piece> currentSubcomponents;
	
	//Commands 
	private static final String CMD_CONECT_TO_SERVER = "bind";
	private static final String CMD_LIST_ALL_PIECES = "listp";
	private static final String CMD_GET_PIECE_BY_CODE = "getp";
	private static final String CMD_SHOW_PIECE_INFO = "showp";
	private static final String CMD_CLEAR_SUBCOMPONENTS = "clearlist";
	private static final String CMD_ADD_SUBCOMPONENTS = "addsubpart";
	private static final String CMD_ADD_PIECE = "addp";
	private static final String CMD_LEAVE_SESSION = "quit";
	private static final String CMD_SHOW_COMMANDS = "help";
	
	public Client() {
		in = new Scanner(System.in);
		currentSubcomponents = new ArrayList<Piece>();
	}
	
	public static void showGreetings() {
		String greetings =
			"**************************************"
		+ "\n* WELCOME TO PART REPOSITORY CLIENT! *"
		+ "\n**************************************"
		+ "\n\nIf you dont know how to start, type "
		+ CMD_SHOW_COMMANDS + "\n> ";
		System.out.println(greetings);
	}
	
	public static void processCommand(String setence) {
		String command = setence.split(" ")[0];
		String params = setence.substring(command.length()).trim();
		
		switch (command) {
			case CMD_ADD_PIECE:
				addPiece();
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
		}
		
	}
	
	private static void showPieceInfo() {
		// TODO Auto-generated method stub
		
	}

	private static void showCommands() {
		// TODO Auto-generated method stub
		
	}

	private static void listPieces() {
		// TODO Auto-generated method stub
		
	}

	private static void leaveSession() {
		// TODO Auto-generated method stub
		
	}

	private static void getPiece(String pieceCode) {
		// TODO Auto-generated method stub
		
	}

	private static void connectServer(String repositoryName) {
		// TODO Auto-generated method stub
		
	}

	private static void clearSubcomponents() {
		// TODO Auto-generated method stub
		
	}

	private static void addSubcomponents(String params) {
		// TODO Auto-generated method stub
		
	}

	private static void addPiece() {
		// TODO Auto-generated method stub
		
	}

	public static void main (String [] args) {
		showGreetings();
		Client client = new Client();
		
		while (true) {
			String setence = in.nextLine();
			processCommand(setence);
		}
	}
	
}
