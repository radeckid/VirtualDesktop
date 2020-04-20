//Servlet do edytowania pliku

package vs.api.serlvets;

import vs.api.helpers.FoldersController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@WebServlet("/EditTextServlet")
public class EditTextServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
  
        String user = String.valueOf((int) session.getAttribute("id"));

        String filePath = request.getParameter("fileName") + ".txt";
        List<String> fileContent = Arrays.asList(request.getParameter("fileTextArea").split("\n"));

        FoldersController foldersController = new FoldersController(user, null);
        String userPath = foldersController.getUserPath();

        String fileFullPath = userPath + "\\" + filePath;

        File file = new File(fileFullPath);

        try(PrintWriter out = new PrintWriter(file)){
            for(String line : fileContent){
                out.println(line);
            }
            out.println(fileContent);
        }

        response.sendRedirect("/mainpage.jsp");
    }
}
