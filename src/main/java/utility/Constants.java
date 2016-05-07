package utility;

public class Constants {

	public static final int ERROR_CODE_FILE_NOT_FOUND = 1000;
	public static final int ERROR_CODE_SERVER_DATA_NULL = 1001;
	public static final int ERROR_CODE_NO_SERVER_IN_FILE = 1002;
	public static final int ERROR_CODE_PARSING_ERROR = 1003;
	public static final int ERROR_CODE_DATABASE_ERROR = 1004;
	public static final int MSG_CODE_SERVER_SAVED = 1005;
	
	public static final int MSG_CODE_SERVER_DELETED = 1006;
	public static final int MSG_CODE_SERVER_NOT_DELETED = 1007;
	
	public static final int ERROR_CODE_DUPLICATE_SERVER = 1008;
	
	public static final int ERROR_CODE_SERVER_NOT_UPDATED = 1009;
	public static final int MSG_CODE_SERVER_UPDATED = 1010;
	
	
	
	public static final String dbUrl = "jdbc:mysql://localhost:3306/cmd_example";
	public static final String dbUrl_test = "jdbc:mysql://localhost:3306/cmd_example_test";
    
	
	
	public static String getMessage(int code) {
		
		String msg;
		
		switch(code) {
		
		case(ERROR_CODE_FILE_NOT_FOUND):
			msg = "File not found. Please check file name !!!";
			break;
		case(MSG_CODE_SERVER_DELETED):
			msg = "server deleted successfully";
			break;
		case(MSG_CODE_SERVER_NOT_DELETED):
			msg = "server could not be deleted. please check server id";
			break;
		case(MSG_CODE_SERVER_SAVED):
			msg = "server saved successfully";
			break;
		case(ERROR_CODE_DATABASE_ERROR):
			msg = "database error. please try later !!!";
			break;
		case(ERROR_CODE_PARSING_ERROR):
			msg = "error while parsing the xml file !!!";
			break;
		case(ERROR_CODE_SERVER_DATA_NULL):
			msg = "id or name of server is null or empty";
			break;
		case(ERROR_CODE_NO_SERVER_IN_FILE):
			msg = "server information not found in file";
			break;
		case(ERROR_CODE_DUPLICATE_SERVER):
			msg = "server with this id already exists";
			break;
		case(MSG_CODE_SERVER_UPDATED):
			msg = "server updated successfully";
			break;
		case(ERROR_CODE_SERVER_NOT_UPDATED):
			msg = "server could not be updated. please check provided id";
			break;
		default:
			msg = "";
			break;
		}
		
		return msg;
		
		
	}
}
