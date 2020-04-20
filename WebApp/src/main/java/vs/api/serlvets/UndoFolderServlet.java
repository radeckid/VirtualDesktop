//Servlet do cofania się w hierarchii folderów

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
import java.util.Arrays;

@WebServlet("/UndoFolderServlet")
public class UndoFolderServlet extends HttpServlet {
    HttpSession _session;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        _session = request.getSession();
        String currentUser = InitializerSessionObject.initialize(_session.getAttribute("id"));
        String currentPath = InitializerSessionObject.initialize(_session.getAttribute("folderPath"));

        FoldersController foldersController = new FoldersController(currentUser, currentPath);

        String[] paths = foldersController.getCurrentPath().split("\\\\");
        int folderNr = Integer.parseInt(request.getParameter("param1"));

        String shortenPath = shortenPath(folderNr, paths);

        _session.setAttribute("folderPath", shortenPath);
        response.sendRedirect("mainpage.jsp");
    }

    public String shortenPath(int folderNr, String[] paths) {
        StringBuilder temp = new StringBuilder();
        for (int i = 1; i <= folderNr; i++) {
            temp.append("\\").append(paths[i + 2]);
        }

        String result = temp.toString();

        if (result.length() == 0) {
            return result;
        } else {
            return result.substring(1);
        }
    }
}
