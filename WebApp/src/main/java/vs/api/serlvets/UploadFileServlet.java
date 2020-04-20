//Servlet do przesyłania plików na serwer i zapisywania ich pod odpowiednią lokalizacją

package vs.api.serlvets;

import vs.api.database.DatabaseManager;
import vs.api.database.IDBO;
import vs.api.database.UserDBO;
import vs.api.helpers.FoldersController;
import vs.api.helpers.InitializerSessionObject;
import vs.api.helpers.Mail;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
    HttpSession _session;
    FoldersController _foldersController;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        _session = request.getSession();
        try {
            String id = InitializerSessionObject.initialize(_session.getAttribute("id"));
            String pathFolder = InitializerSessionObject.initialize(_session.getAttribute("folderPath"));
            _foldersController = new FoldersController(id, pathFolder);
            _foldersController.saveFilesToUserDirectory();

            int freeSpace = 1024 - (int) Math.ceil(_foldersController.getFolderSize());

            saveFreeSpaceToDatabase(id, freeSpace);
            _session.setAttribute("space", freeSpace);
            // If free space is under 100mb send email notification

            if (freeSpace < 100) {
                sendEmailAlert();
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Upload not complete!");
        }
        response.sendRedirect("mainpage.jsp");
    }

    private void saveFreeSpaceToDatabase(String id, int freeSpace) {
        DatabaseManager databaseManager = new DatabaseManager();

        try {
            IDBO userDBO = new UserDBO(databaseManager.dataSource.getConnection());
            userDBO.setSpace(id, freeSpace);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void sendEmailAlert() {
        String recipient = (String) _session.getAttribute("email");
        String subject = "VirtualDesktop space running low!";
        String content = "You have under 100mb of free space left";

        Mail mail = new Mail(recipient, subject, content);
        try {
            mail.send();
        } catch (IllegalArgumentException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
