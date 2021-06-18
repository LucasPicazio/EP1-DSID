package commons;

public class Constants {
	
	//Commands 
	public static final String CMD_CONECT_TO_SERVER = "bind";
	public static final String CMD_CREATE_PIECE = "newp";
	public static final String CMD_LIST_ALL_PIECES = "listp";
	public static final String CMD_GET_PIECE_BY_CODE = "getp";
	public static final String CMD_SHOW_PIECE_INFO = "showp";
	public static final String CMD_CLEAR_SUBCOMPONENTS = "clearlist";
	public static final String CMD_ADD_SUBCOMPONENTS = "addsubpart";
	public static final String CMD_ADD_PIECE = "addp";
	public static final String CMD_LEAVE_SESSION = "quit";
	public static final String CMD_SHOW_COMMANDS = "help";

	//Messages
	public static final String SUCESS_MESSAGE = "OK";
	
	//Adrresses
	public static final String DEFAULT_SERVER_IP = "localhost";
	public static final String DEFAULT_SERVER_PORT = "1099";
	
	//Intervals
	public static final int MIN_PORT_NUMBER = 0;
	public static final int MAX_PORT_NUMBER = 9999;
	public static final String PORT_INTERVAL_ERROR = "The port number must be greater than " + Constants.MIN_PORT_NUMBER 
						        				   + " and greater than " + Constants.MAX_PORT_NUMBER;
}
