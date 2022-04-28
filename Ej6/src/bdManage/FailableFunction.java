package bdManage;

import java.sql.SQLException;

@FunctionalInterface  
public interface FailableFunction<Transaction,T_OUT>{  
	T_OUT invoke(Transaction t)throws SQLException;  
}  