package dao;

import model.User;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{
    private String jdbcURL ="jdbc:mysql://localhost:8080";
    private String jdbcUsername = "Phong";
    private String jdbcPassword = "123456";

    private static final String INSERT_USER_SQL = "INSERT INTO users" + "(id,name,email,country) value"+ "?,?,?,?";
    private static final String SELECT_ALL_USERS ="select*from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
    private User users;


    public UserDAO(){
    }

    protected Connection getConnection()  {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcPassword,jdbcUsername);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USER_SQL);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)){
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException  e){
            printSQLException (e);
        }
    }

    private void printSQLException(Exception e) {
    }

    public User selectUser (int id){
        User user= null;
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                id = resultSet.getInt("id");
                String name =resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                users.add(new User(id, name,email,country));
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return users;
    }
    public List<User> selectAllUsers(){
        List<User> users = new ArrayList<>();

        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String email = resultSet.getString("email");
                users.add(new User(id,name,email,country));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public boolean deleteUser(int id) throws SQLException{
        boolean rowDeleted;
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);){
            statement.setInt(1,id);
            rowDeleted = statement.executeUpdate()>0;
        }
        return rowDeleted;
    }
    private void printSQLException(SQLException ex){
        for (Throwable e:ex){
            if (e instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQLState: "+((SQLException)e).getSQLState());
                System.err.println("ErrorCode: "+((SQLException)e).getErrorCode());
                System.err.println("Message: "+e.getMessage());
                Throwable throwable = ex.getCause();

                while (throwable != null){
                    System.out.println("Cause: "+throwable);
                    throwable = throwable.getCause();
                }
            }
        }
    }




    @Override
    public List<User> selectAllUser() {
        return null;
    }


    @Override
    public boolean updateUser(User user) throws SQLException {
        return false;
    }


    public void insertUser() {
    }
}
