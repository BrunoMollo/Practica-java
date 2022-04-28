package bdManage;

import java.sql.SQLException;

@FunctionalInterface  
public interface FailablePretparedFunction<T_OUT>{  
	T_OUT invoke(PreparedTransaction t)throws SQLException;  
}