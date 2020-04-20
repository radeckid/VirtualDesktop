//Klasa abstrakcyjna zawierająca połączenie do bazy danych

package vs.api.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class DBO<T> implements vs.api.database.IDBO<T>
{
	protected Connection _connection;
	protected List<T> dataList;
	
	protected DBO(Connection connection)
	{
		_connection = connection;
	}
	
	public void exit()
	{
        try
        {
            _connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
	}
}
