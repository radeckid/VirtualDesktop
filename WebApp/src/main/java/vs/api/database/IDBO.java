//Interface zawierajacy główne metody do obsługi bazy danych

package vs.api.database;

import java.sql.SQLException;
import java.util.List;

public interface IDBO <T>
{
	List<T> getAll() throws SQLException;
	T get(String email, String password) throws SQLException, IllegalArgumentException;
	int getSpace(String email);
	void setSpace(String id, int freeSpace);
	void add(T data) throws SQLException, IllegalArgumentException;
	void edit(String name, String surname, String email, String oldEmail);
	boolean find(String email) throws SQLException;
	void save();
	void delete(int id);
	void exit();
}
