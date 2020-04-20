//Servlet do przechwytywania folderów z dropnzone i umieszczania ich w folderze tymczasowym

package vs.api.serlvets;

import vs.api.helpers.FoldersController;
import vs.api.helpers.InitializerSessionObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet("/SendFilesServlet")
@MultipartConfig
public class SendFilesServlet extends HttpServlet {
    private HttpSession _session;
    private FoldersController _foldersController;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        _session = request.getSession();
        try {
            String id = InitializerSessionObject.initialize(_session.getAttribute("id"));
            String pathFolder = InitializerSessionObject.initialize(_session.getAttribute("pathFolder"));

            _foldersController = new FoldersController(id, pathFolder);
        } catch (Exception e) {
            e.getMessage();
        }
        Collection<Part> fileParts = request.getParts();
        copyFileToTempFolder(fileParts);
    }

    private void copyFileToTempFolder(Collection<Part> parts) throws IOException
    {
        InputStream src = null;

        for(Part part : parts)
        {
            String fileName = "tmp" + parts.size();
            for (String cd : part.getHeader("content-disposition").split(";"))
            {
                if (cd.trim().startsWith("filename")) {
                    String found = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                    fileName = found.substring(found.lastIndexOf('/') + 1).substring(found.lastIndexOf('\\') + 1);
                }
            }
            try {
                src = part.getInputStream();
                _foldersController.copyFileToTempFolder(src, fileName);
            } catch (IOException ex) {

            }
        }
    }
}
