package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;



@FunctionalInterface
interface ParamterAdder{
	public void apply(Integer index,PreparedStatement st ) throws SQLException;
}

public class StatementWrapper {
		private String query;
		private LinkedList<ParamterAdder> paramterAdders;


		public StatementWrapper(String _query) {
			query=_query;
			paramterAdders= new LinkedList<ParamterAdder>();
		}
		
	
		
		public StatementWrapper push(String s) {
			paramterAdders.add((index, st)->{
				try { st.setString(index, s); } 
				catch (SQLException e) { throw e; }
			});
			return this;
		}
		
		public StatementWrapper push(Integer i) {
			paramterAdders.add((index, st)->{
				try { st.setInt(index, i); } 
				catch (SQLException e) { throw e; }
			});
			return this;
		}
		
		
		public PreparedStatement execute(Connection con) throws SQLException {
			PreparedStatement st= con.prepareStatement(query);
			int index=1;
			for(ParamterAdder lambda: paramterAdders) {
				lambda.apply(index, st);
				index++;
			}
			return st;
		}



		public StatementWrapper push(Boolean b) {
			paramterAdders.add((index, st)->{
				try { st.setBoolean(index, b); } 
				catch (SQLException e) { throw e; }
			});
			return this;
		}
		
		
		
		
}
		
		
		
	

