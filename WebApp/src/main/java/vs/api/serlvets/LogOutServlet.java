//Servlet do wylogowywania się

package vs.api.serlvets;

import vs.api.database.DatabaseManager;
import vs.api.database.UserDBO;
import vs.api.helpers.Authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LogOutUser")
public class LogOutServlet extends HttpServlet {
    DatabaseManager databaseManager;
    UserDBO userDBO;

    public void init() throws ServletException {
        databaseManager = new DatabaseManager();
        try {
            userDBO = new UserDBO(databaseManager.dataSource.getConnection());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");

        Authentication.deleteToken(session.getAttribute("email").toString());
        session.removeAttribute("name");
        session.removeAttribute("surname");
        session.removeAttribute("email");
        session.removeAttribute("space");
        session.removeAttribute("key");
        response.sendRedirect("index.jsp");
    }

    @Override
    public void destroy() {
        userDBO.exit();
    }
}
