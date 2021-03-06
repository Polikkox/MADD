package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AuthenticationDAO {

    private Connection connection;

//    private static final String GET_USER_BY_LOGIN =
//            "SELECT id_user " +
//                    "FROM authentication " +
//                    "WHERE login = ? " +
//                    "AND password = ?;";

    private static final String GET_USER_BY_LOGIN = "select * from authentication where login = ? and password = ?;";



    public AuthenticationDAO()  {
        connection = new ConnectionProvider().getConnection();
    }

    public Integer getUserIdByInputs(Map<String, String> formData) {
        System.out.println(formData);
        String login = formData.get("login");
        String password = formData.get("password");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Integer userId = resultSet.getInt("id_user");
                return userId;
            } else {
                return null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
