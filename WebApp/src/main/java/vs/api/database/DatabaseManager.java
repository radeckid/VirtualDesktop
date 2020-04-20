//Klasa do połaczenia się z bazą danych

package vs.api.database;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseManager 
{
	@Resource(name = "jdbc/VirtualDesktopDB")
	public DataSource dataSource;
	
	Context ctx;
	public DatabaseManager()
	{
		 try {
			 ctx = new InitialContext();
			 dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB");
		    } catch (NamingException e1) {
		        // TODO Auto-generated catch block
		        e1.printStackTrace();
		    }
	}
}
