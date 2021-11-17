import java.sql.Connection;
import java.sql.SQLException;

import dao.UserDao;
import dao.UserDaoImpl;

public class TestHelper {
	
	private static Connection connection;
	
	private static class TestUserDao extends UserDaoImpl {
		TestUserDao(String str) throws SQLException {
			super(str);
			TestHelper.connection = this.conn;
		}
	}
	
	
	private static TestUserDao dao = null;
	
	public static UserDao createUserDao() throws SQLException {
		if ( dao != null ) return dao;
		String resource = UserDao.SQLBDD +"BddUsers.db";
		dao = new TestUserDao( resource );
		return dao;
	}
	
	public static int updateDb(String sql) throws SQLException {
		
		if ( connection == null ) createUserDao();
		
		return connection
			.createStatement()
			.executeUpdate(sql);
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
