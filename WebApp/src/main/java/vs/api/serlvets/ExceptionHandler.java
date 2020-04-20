//Servlet do przechwytywania exceptionów i zwracania odpowiedniej strony błędu

package vs.api.serlvets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ExceptionHandler")
public class ExceptionHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Analyze the servlet exception
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String servletName = (String) request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        String requestUri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        if (servletName == null) {
            servletName = "Unknown";
        }

        if (requestUri == null) {
            requestUri = "Unknown";
        }

        // Set response content type
        PrintWriter out = response.getWriter();

        if (requestUri.equals("/Upload")) {
            out.write("Błąd podczas przesyłania. Spróbuj ponownie później.");
        } else {
            response.sendRedirect("/error.jsp");
        }
    }
}
