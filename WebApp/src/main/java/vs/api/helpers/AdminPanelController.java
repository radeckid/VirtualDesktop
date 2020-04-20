//Klasa służąca do obsługi panela administratora

package vs.api.helpers;

import vs.api.database.DatabaseManager;
import vs.api.database.IDBO;
import vs.api.database.UserDBO;
import vs.api.repository.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPanelController {

    //Pobierania informacji nt. wszystkich użytkowników
    public static String LoadAllUsersTable() {
        DatabaseManager databaseManager = new DatabaseManager();
        IDBO<User> userDBO;

        ArrayList<User> allUsers = new ArrayList<>();

        try {
            userDBO = new UserDBO(databaseManager.dataSource.getConnection());
            allUsers = (ArrayList<User>) userDBO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (User user : allUsers) {
            stringBuilder.append("<tr>");
            stringBuilder.append("<td>").append(user.getName()).append("</td>");
            stringBuilder.append("<td>").append(user.getSurname()).append("</td>");
            stringBuilder.append("<td>").append(user.getEmail()).append("</td>");
            stringBuilder.append("<td>").append(user.getDataFreeAmount()).append("</td>");

            if (user.getUserType() == 1) {
                stringBuilder.append("<td style=\"color: red;\"><b><b>Administrator</b></b></td>");
            } else if (user.getUserType() == 2) {
                stringBuilder.append("<td>Użytkownik</td>");
            } else {
                stringBuilder.append("<td style=\"color: red\"><b><b>ERROR</b></b></td>");
            }

            if (user.getUserType() != 1) {
                stringBuilder.append("<td style=\"width: 140px;\"><a class=\"btn btn-primary\" href=\"#\" data-toggle=\"modal\" data-target=\"#editModal\" data-name=\"")
                        .append(user.getName())
                        .append("\" data-surname=\"")
                        .append(user.getSurname())
                        .append("\" data-email=\"")
                        .append(user.getEmail())
                        .append("\" >Edytuj</a>");

                stringBuilder.append("<a style=\"margin-left: 4px;\" class=\"btn btn-danger\" href=\"#\" data-toggle=\"modal\" data-target=\"#deleteModal\" data-id=\"")
                        .append(user.getId())
                        .append("\" data-name=\"")
                        .append(user.getName())
                        .append("\" data-surname=\"")
                        .append(user.getSurname())
                        .append("\">Usuń</a></td>");
            } else {
                stringBuilder.append("<td></td>");
            }

            stringBuilder.append("</tr>");
        }

        return stringBuilder.toString();
    }
}
