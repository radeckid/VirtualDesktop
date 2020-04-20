//Klasa do obsługi głównej strony użytkowej, strony mainpage.jsp

package vs.api.helpers;

import vs.api.database.DatabaseManager;
import vs.api.database.IDBO;
import vs.api.database.UserDBO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class MainPageController {

    //Metoda do obsługi progrssBar
    public static String getProgressBar(HttpServletRequest request) {
        DatabaseManager databaseManager = new DatabaseManager();
        IDBO userDBO = null;
        try {
            userDBO = new UserDBO(databaseManager.dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Object email = null;
        Object size = null;

        try {
            email = request.getSession().getAttribute("email");
            size = request.getSession().getAttribute("space");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();

        if (size != null && email != null) {
            assert userDBO != null;
            int availableSpace = userDBO.getSpace(request.getSession().getAttribute("email").toString());

            double allSpace = 1024;
            double percentSpace = (availableSpace / allSpace) * 100;

            stringBuilder.append("<h6 class=\"mb-0 text-white lh-100\" style=\"padding-bottom: 6px;\">Ilość dostępnej przestrzeni:" +
                    "</h6><div class=\"progress mb-auto\" style=\"height: 20px;\">");

            if (percentSpace <= 25) {
                stringBuilder.append("<div class=\"progress-bar progress-bar-animated progress-bar-striped bg-danger\" role=\"progressbar\" ");
            } else if (percentSpace <= 50) {
                stringBuilder.append("<div class=\"progress-bar progress-bar-animated progress-bar-striped bg-warning\" role=\"progressbar\" ");
            } else if (percentSpace <= 75) {
                stringBuilder.append("<div class=\"progress-bar progress-bar-animated progress-bar-striped bg-info\" role=\"progressbar\" ");
            } else if (percentSpace <= 100) {
                stringBuilder.append("<div class=\"progress-bar progress-bar-animated progress-bar-striped \" role=\"progressbar\" ");
            }
            stringBuilder.append("style=\"width: ")
                    .append((int) percentSpace)
                    .append("%; font-size: 15px; text-shadow: 2px 2px 4px #000000; " +
                            "font-weight: bold; text-align: center;\" aria-valuenow=\"")
                    .append((int) percentSpace).append("\" aria-valuemin=\"0\" " + "aria-valuemax=\"100\">")
                    .append(availableSpace)
                    .append(" / ")
                    .append((int)allSpace)
                    .append(" MB</div></div>");
        }
        return stringBuilder.toString();
    }

}
