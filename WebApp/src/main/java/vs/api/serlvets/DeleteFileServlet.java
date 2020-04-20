//Servlet służący do usuwania pliku

package vs.api.serlvets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import vs.api.database.DatabaseManager;
import vs.api.database.IDBO;
import vs.api.database.UserDBO;
import vs.api.helpers.FoldersController;
import vs.api.helpers.InitializerSessionObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteFileServlet")
public class DeleteFileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("file");
        String id = InitializerSessionObject.initialize(request.getSession().getAttribute("id"));
        String pathFolder = InitializerSessionObject.initialize(request.getSession().getAttribute("folderPath"));

        FoldersController foldersController = new FoldersController(id, pathFolder);

        String filepath = foldersController.getCurrentPath();

        File file = new File(filepath + "\\" + filename);

        if (file.isDirectory()) {
            FileUtils.deleteDirectory(file);
        } else {
            file.delete();
        }

        int freeSpace = 1024 - (int) foldersController.getFolderSize();
        DatabaseManager databaseManager = new DatabaseManager();
        try {
            IDBO userDBO = new UserDBO(databaseManager.dataSource.getConnection());
            userDBO.setSpace(id, freeSpace);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("mainpage.jsp");
    }
}
