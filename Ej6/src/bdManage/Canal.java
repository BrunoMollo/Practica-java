package bdManage;



public class Canal {
	
	private String url;
	private String user;
	private String psw;
	
	public Canal(String url, String user, String psw) {
		this.url = url;
		this.user = user;
		this.psw = psw;
	}

///ESTOS DUPLICADOS LOS TENGO QUE ARREGLAR
	public <T_OUT> T_OUT executeTransaction(FailablePretparedFunction<T_OUT> func){
		
		try {
			PreparedTransaction tr=new PreparedTransaction(url, user, psw);
	
			T_OUT output=func.invoke(tr);
			
			tr.close();
			
			return output;
			
				
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		return null;
	}
	
public <T_OUT> T_OUT executeTransaction(FailableInstantFunction<T_OUT> func){
		
		try {
			InstantTransaction tr=new InstantTransaction(url, user, psw);
	
			T_OUT output=func.invoke(tr);
			
			tr.close();
			
			return output;
			
				
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		return null;
	}
}
