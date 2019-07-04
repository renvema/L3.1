package dbService.dao;

import database.DBHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UsersDaoImpl implements UsersDao {
    private DBHandler dbHandler;
    private final String REQUEST_SELECT = "select * from users where login=? and password=?";
    private final String REQUEST_INSERT = "insert into users(login, password) values(?, ?)";

    public UsersDaoImpl() {
        dbHandler = new DBHandler();
    }

    @Override
    public void addUser(String login, String password) throws SQLException {
        PreparedStatement preparedStat = dbHandler.getConnection().prepareStatement(REQUEST_INSERT);
        preparedStat.setString(1, login);
        preparedStat.setString(2, password);
        preparedStat.execute();
        preparedStat.close();
    }

    @Override
    public boolean isUserPresent(String login, String password) throws SQLException {
        boolean isUserPresent = false;
        PreparedStatement preparedStat = dbHandler.getConnection().prepareStatement(REQUEST_SELECT);
        preparedStat.setString(1, login);
        preparedStat.setString(2, password);
        ResultSet result = preparedStat.executeQuery();
        if (Objects.nonNull(result)) {
            isUserPresent = true;
        }
        result.close();
        preparedStat.close();
        return isUserPresent;
    }
}
