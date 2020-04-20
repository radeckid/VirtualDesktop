//Servlet służący do dodania folderu

package vs.api.serlvets;

import vs.api.helpers.FoldersController;
import vs.api.helpers.InitializerSessionObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddDirectoryServlet")
public class AddDirectoryServlet extends HttpServlet {
    HttpSession _session;
    FoldersController _folderController;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        _session = request.getSession();
        String id = InitializerSessionObject.initialize(_session.getAttribute("id"));
        String currentPath = InitializerSessionObject.initialize(_session.getAttribute("folderPath"));
        String folderName = InitializerSessionObject.initialize(request.getParameter("folderName"));
        _folderController = new FoldersController(id, currentPath);

        _folderController.addFolder(folderName);
        response.sendRedirect("mainpage.jsp");
    }
}
