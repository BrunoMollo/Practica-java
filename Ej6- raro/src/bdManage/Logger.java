package bdManage;

import java.sql.SQLException;

public class Logger {
	public static void log(SQLException ex) {//Todo esto no se que es, lo copie nomas
		System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	
	public static void log(ClassNotFoundException ex) {
		ex.printStackTrace();
	}
}
