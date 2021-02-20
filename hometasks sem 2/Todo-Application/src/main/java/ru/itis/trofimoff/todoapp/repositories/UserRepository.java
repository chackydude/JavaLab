package ru.itis.trofimoff.todoapp.repositories;

import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.repositories.utils.SqlJDBCTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserRepository implements CrudRepository<User> {
    //language=SQL
    private String SQL_INSERT_USER = "INSERT INTO users(name, email, password, role) VALUES(?, ?, ?, ?)";
    private String sqlSelectFormat = "SELECT %s FROM users";
    private String sqlDeleteFormat = "DELETE FROM users WHERE id = %d";
    private DataSource dataSource;
    private SqlJDBCTemplate sqlJDBCTemplate;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.sqlJDBCTemplate = new SqlJDBCTemplate(dataSource);
    }

    // adding user into the DB
    @Override
    public void save(User user){
        sqlJDBCTemplate.execute(SQL_INSERT_USER, user.getName(), user.getEmail(), user.getPassword(), user.getRole());
    }

//    // finding user in the DB
//    // user need to have password & email
//    public User checkUser(User user){
//        try {
//            DataBaseConnector connector = new DataBaseConnector();
//            conn = connector.getConnection();
//            preparedStatement = conn.prepareStatement(String.format(sqlSelectFormat, "users.email, users.password, users.name, users.id, users.role"));
//            result = preparedStatement.executeQuery();
//            while(result.next()) {
//                // emails
//                String email = result.getString(1);
//                // passwords
//                String password = result.getString(2);
//                if(user.getEmail().equals(email) &&  user.getPassword().equals(password)) {
//                    String name = result.getString(3);
//                    int userId = result.getInt(4);
//                    String role = result.getString(5);
//                    User currentUser = new User(name, email, password);
//                    currentUser.setId(userId);
//                    currentUser.setRole(role);
//                    preparedStatement.close();
//                    conn.close();
//                    return currentUser;
//                }
//            }
//        }  catch(SQLException se) {
//            System.out.println(se.getMessage());
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close statement.");
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close connection.");
//                }
//            }
//        }
//        return null;
//    }
//
//
//    // removing user from the table
//    @Override
//    public void deleteById(int id){
//        try {
//            DataBaseConnector connector = new DataBaseConnector();
//            conn = connector.getConnection();
//            preparedStatement = conn.prepareStatement(String.format(sqlDeleteFormat, id));
//            preparedStatement.executeUpdate();
//        }  catch(SQLException se) {
//            System.out.println(se.getMessage());
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close statement.");
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close connection.");
//                }
//            }
//        }
//    }
//
//    @Override
//    public User findById(int id) {
//        try {
//            DataBaseConnector connector = new DataBaseConnector();
//            conn = connector.getConnection();
//            preparedStatement = conn.prepareStatement(String.format(sqlSelectFormat, "users.id, users.email, users.password, users.name, users.alltodos, users.donetodos"));
//            result = preparedStatement.executeQuery();
//            while(result.next()){
//                int userId = result.getInt(1);
//
//                if (userId == id) {
//                    String email = result.getString(2);
//                    String password = result.getString(3);
//                    String name = result.getString(4);
//                    int allTodos = result.getInt(5);
//                    int doneTodos = result.getInt(6);
//                    preparedStatement.close();
//                    conn.close();
//                    return new User(name, email, password, allTodos, doneTodos);
//                }
//            }
//        }  catch(SQLException se) {
//            System.out.println(se.getMessage());
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close statement.");
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close connection.");
//                }
//            }
//        }
//        return null;
//    }
//
//    // get all users without passwords
//    @Override
//    public List<User> findAll() {
//        List<User> users = new ArrayList();
//        try {
//            DataBaseConnector connector = new DataBaseConnector();
//            conn = connector.getConnection();
//            preparedStatement = conn.prepareStatement(String.format(sqlSelectFormat, "users.id, users.email, users.name, users.role"));
//            result = preparedStatement.executeQuery();
//            while(result.next()) {
//                String role = result.getString(4);
//                if (role.equals("user")) {
//                    int id = result.getInt(1);
//                    String email = result.getString(2);
//                    String name = result.getString(3);
//                    users.add(new User(id, name, email));
//                }
//            }
//            preparedStatement.close();
//            conn.close();
//            return users;
//        }  catch(SQLException se) {
//            System.out.println(se.getMessage());
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close statement.");
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close connection.");
//                }
//            }
//        }
//        return null;
//    };

    @Override
    public void update(User entity) {}

    @Override
    public void delete(User entity) {}

    @Override
    public List<User> findAll() {
        return null;
    }
}