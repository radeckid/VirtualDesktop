//Servlet służący do przemieszczania się głębiej w hierarchii folderów

package vs.api.serlvets;

import vs.api.helpers.InitializerSessionObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/DeeperFolderServlet")
public class DeeperFolderServlet extends HttpServlet {
    HttpSession _session;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        _session = request.getSession();
        String currentPath = InitializerSessionObject.initialize(_session.getAttribute("folderPath"));
        String folder = request.getParameter("param1");
        String folderPath = "";
        if (folder != null) {
            if (currentPath.equals("")) {
                folderPath = currentPath + folder;
            } else {
                folderPath = currentPath + "\\" + folder;
            }
        }

        _session.setAttribute("folderPath", folderPath);
        response.sendRedirect("mainpage.jsp");
    }
}
