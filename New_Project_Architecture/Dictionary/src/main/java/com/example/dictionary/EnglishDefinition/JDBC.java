package com.example.dictionary.EnglishDefinition;

import javafx.scene.Node;

import java.sql.*;
import java.util.ArrayList;

public class JDBC {
    private static final String url = "jdbc:mysql://localhost:3306/entries";
    private static final String user = "root";
    private static final String password = "12345678";

    private static Connection connection = null;
    public JDBC() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String executeQuery(String query) {
        StringBuilder result = new StringBuilder();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
//                String word = resultSet.getString("word");
                String wordType = resultSet.getString("wordtype");
                String definition = resultSet.getString("definition");

//                System.out.println("Word: " + word);
                result.append("WordType: ").append(wordType).append("\n").
                        append("Definition: ").append(definition).append("\n").
                        append("-----------------------\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(statement, resultSet);
        }
        return result.toString();
    }

    public ArrayList<String> lookUpWords(String query) {
        ArrayList<String> lookUp = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                lookUp.add(resultSet.getString("word"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(statement, resultSet);
        }
        return lookUp;
    }

    public void removeWord(String query) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResourcesNonResultSet(statement);
        }
    }

    private void closeResources(Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeResourcesNonResultSet(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
