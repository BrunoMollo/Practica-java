package bdManage;

import java.sql.SQLException;

@FunctionalInterface
public interface FailableFunction<T, R> {
    R apply(T t) throws SQLException;
}
