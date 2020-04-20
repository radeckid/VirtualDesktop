//Servlet do rejestracji użytkownika

package vs.api.serlvets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vs.api.database.DatabaseManager;
import vs.api.database.IDBO;
import vs.api.database.UserDBO;
import vs.api.repository.User;

@WebServlet("/RegisterUser")
public class RegisterUserServlet extends HttpServlet {
    private DatabaseManager databaseManager;
    private IDBO userDBO;

    public RegisterUserServlet() {
        super();
    }

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

        session.removeAttribute("errorUserExist");

        User user = new User();
        user.setName(request.getParameter("inputFirstName"));
        user.setSurname(request.getParameter("inputLastName"));
        user.setEmail(request.getParameter("inputEmail"));
        user.setPassword(request.getParameter("inputPassword"));
        try {
            userDBO.add(user);
            response.sendRedirect("login.jsp");
        } catch (SQLException | AbstractMethodError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            session.setAttribute("errorUserExist", e.getMessage());
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @Override
    public void destroy() {
        userDBO.exit();
    }
}
