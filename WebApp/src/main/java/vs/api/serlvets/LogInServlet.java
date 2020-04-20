//Servlet do logowania się

package vs.api.serlvets;

import vs.api.database.DatabaseManager;
import vs.api.database.UserDBO;
import vs.api.helpers.Authentication;
import vs.api.helpers.FoldersController;
import vs.api.helpers.VerifyRecaptcha;
import vs.api.repository.User;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LogInUser")
public class LogInServlet extends HttpServlet {
    vs.api.database.DatabaseManager databaseManager;
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

        session.removeAttribute("errorLogIn");
        String email = "";
        String password = "";
        User user;
        try {
            String recaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean verify = VerifyRecaptcha.verify(recaptchaResponse);
            verify = true; //TODO usunac na sprinta
            if (verify) {
                email = request.getParameter("inputEmail");
                password = request.getParameter("inputPassword");
                user = userDBO.get(email, password);
            } else {
                throw new IllegalArgumentException("Are you a robot?");
            }

            String rememberMe = request.getParameter("checkboxRememberMe");
            if ("remember-me".equals(rememberMe)) {
                Cookie cookieEmail = new Cookie("cookieEmail", email);
                Cookie cookiePassword = new Cookie("cookiePassword", password);
                Cookie cookieRememberMe = new Cookie("cookieRememberMe", rememberMe);
                cookieEmail.setMaxAge(7 * 24 * 60 * 60);  //Pliki cookies dla danego konta będą istniały przez tydzień
                cookiePassword.setMaxAge(7 * 24 * 60 * 60);
                cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(cookieEmail);
                response.addCookie(cookiePassword);
                response.addCookie(cookieRememberMe);
            } else {
                Cookie cookieEmail = new Cookie("cookieEmail", "");
                Cookie cookiePassword = new Cookie("cookiePassword", "");
                Cookie cookieRememberMe = new Cookie("cookieRememberMe", null);
                response.addCookie(cookieEmail);
                response.addCookie(cookiePassword);
                response.addCookie(cookieRememberMe);
            }

            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getName());
            session.setAttribute("surname", user.getSurname());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("space", user.getDataFreeAmount());
            session.setAttribute("usertype", user.getUserType());
            session.setAttribute("key", Authentication.createToken(user.getEmail()));
            session.setAttribute("folderPath", "");
            response.sendRedirect("/mainpage.jsp");
        } catch (IllegalArgumentException e) {

            session.setAttribute("errorLogIn", e.getMessage());
            response.sendRedirect(request.getHeader("Referer"));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    public void destroy() {
        userDBO.exit();
    }
}
