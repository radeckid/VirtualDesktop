//Servlet do wysyłania wiadomości email

package vs.api.serlvets;

import vs.api.helpers.Mail;

import javax.mail.MessagingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        request.removeAttribute("mailSentInfo");

        request.setCharacterEncoding("UTF-8");

        String sender = request.getParameter("inputEmail");
        String recipient = Mail.ADMIN_MAIL;
        String subject = "CONTACT/HELP: " + request.getParameter("inputSubject");
        String content = "<div style=\"font-size: 20px;\"><span style=\"font-weight:bold;\">Person Name: </span>" + subject +
                "</div><br><div style=\"font-size: 20px;\"><span style=\"font-weight:bold;\">Content: </span>" + request.getParameter("inputContent") +
                "</div><br><div style=\"font-size: 20px;\"><span style=\"font-weight:bold;\">Response adres: </span><a href = \"mailto: " + sender + "\">" + sender + "</a></div>";

        Mail mail = new Mail(recipient, subject, content);
        try {
            mail.send();
        } catch (IllegalArgumentException e) {
            session.setAttribute("mailSentInfo", "<div style=\"color:green; font-size: 30px;\" >" + e.getMessage() + "</div>");
            response.sendRedirect(request.getHeader("Referer"));
        } catch (MessagingException e) {
            session.setAttribute("mailSentInfo", "<div style=\"color:red; font-size: 30px;\" >Mail server error.</div>");
            response.sendRedirect(request.getHeader("Referer"));
            e.printStackTrace();
        }
    }
}
