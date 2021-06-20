package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import commons.Constants;
import commons.Piece;
import commons.Subcomponent;

public class Client {
	private static Scanner in = new Scanner(System.in);;
	private static String currentRepository = "";
	private static Piece currentPiece;
	private static List <Subcomponent> currentSubcomponents;
	
	public static void showGreetings() {
		String greetings =
			"******************************************"
		+ "\n*                                        *"
		+ "\n*   WELCOME TO PART REPOSITORY CLIENT!   *"
		+ "\n*                                        *"
		+ "\n******************************************"
		+ "\n\nIf you dont know how to start, type "
		+ Constants.CMD_SHOW_COMMANDS + " to see all available commands\n\n> ";
		System.out.print(greetings);
	}

	public static String getCurrentRepository() {
		return currentRepository;
	}

	public static void setCurrentRepository(String currentRepository) {
		Client.currentRepository = currentRepository;
	}

	public static Piece getCurrentPiece() {
		return currentPiece;
	}

	public static void setCurrentPiece(Piece currentPiece) {
		Client.currentPiece = currentPiece;
	}

	public static List<Subcomponent> getCurrentSubcomponents() {
		return currentSubcomponents;
	}
	
	public static void cleanCurrentSubcomponents() {
		currentSubcomponents = new ArrayList<Subcomponent>();
	}

	public static void main (String [] args) {
		showGreetings();
		cleanCurrentSubcomponents();

		while (true) {
			String setence = in.nextLine();
			CommandsUtil.processCommand(setence);
			System.out.print(currentRepository + "> ");
		}
	}
	
}
