package bdManage;

import java.sql.SQLException;

@FunctionalInterface  
public interface FailableInstantFunction<T_OUT>{  
	T_OUT invoke(InstantTransaction t)throws SQLException;  
}