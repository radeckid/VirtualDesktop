//Servlet do pobierania pliku

package vs.api.serlvets;

import vs.api.helpers.FoldersController;
import vs.api.helpers.InitializerSessionObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("file");
        String id = InitializerSessionObject.initialize(request.getSession().getAttribute("id"));
        String pathFolder = InitializerSessionObject.initialize(request.getSession().getAttribute("folderPath"));

        FoldersController foldersController = new FoldersController(id, pathFolder);

        String filepath = foldersController.getCurrentPath();

        response.setContentType("application/octet-stream");
        String disHeader = "Attachment; Filename=\"" + filename + "\"";
        response.setHeader("Content-Disposition", disHeader);
        File fileToDownload = new File(filepath + "\\" + filename);

        InputStream in = null;
        ServletOutputStream outs = response.getOutputStream();

        try {
            in = new BufferedInputStream(new FileInputStream(fileToDownload));
            int ch;
            while ((ch = in.read()) != -1) {
                outs.print((char) ch);
            }
        } finally {
            if (in != null) in.close();
        }

        outs.flush();
        outs.close();
        in.close();

        response.sendRedirect("mainpage.jsp");
    }
}
