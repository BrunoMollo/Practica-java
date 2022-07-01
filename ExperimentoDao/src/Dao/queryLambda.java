package Dao;

import java.sql.SQLException;

@FunctionalInterface
public interface queryLambda {
	public void apply() throws SQLException;
}
