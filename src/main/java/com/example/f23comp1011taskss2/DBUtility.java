package com.example.f23comp1011taskss2;

import javafx.scene.chart.XYChart;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBUtility {
    //Add in YOUR user name and password.  For my account, I created a user called student
    //with a password of student.
    private static String dbUser = "student";
    private static String password = "student";

    /**
     * jdbc:mysql -> the database driver
     * 127.0.0.1 -> IP address of the MySQL server
     * 3306 -> port number of the MySQL Server
     * F23COMP1011Monday -> database name
     */
    private static String connectURL = "jdbc:mysql://127.0.0.1:3306/F23COMP1011Monday";

    public static String addUserToDB(User user){
        String rspMessage = "broken";

        String sql = "INSERT INTO users VALUES (?,?,?)";

        //"try with resources" will automatically close anything that was defined inside
        //the () brackets
        try(
                Connection conn = DriverManager.getConnection(connectURL,dbUser,password);
                PreparedStatement ps = conn.prepareStatement(sql);
                )
        {
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getUserName());
            ps.setString(3,user.getPhone());

            ps.executeUpdate();

            rspMessage = "User Added";
        }
        catch (SQLIntegrityConstraintViolationException e)  //this is a duplicate key
        {
            rspMessage = "User with "+user.getEmail()+ " already defined";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return rspMessage;
    }

    /**
     * This method will return a list of users from the database
     */
    public static ArrayList<User> getUsersFromDB()
    {
        ArrayList<User> users = new ArrayList<>();

        //use a try with resources block to access the database and automatically close the connection, statement
        //and result set
        String sql = "SELECT * FROM users";

        try (
                Connection conn = DriverManager.getConnection(connectURL,dbUser,password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            //loop over the results returned and create new user objects
            while (resultSet.next())
            {
                User newUser = new User(resultSet.getString("email"),
                                        resultSet.getString("fullName"),
                                        resultSet.getString("phone"));

                users.add(newUser);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return users;
    }

    public static String addTaskToDB(Task task){
        String rspMessage = "broken";

        String sql = "INSERT INTO tasks (title, taskDescription, dueDate, creationDate, estimatedLengthInMin," +
                     " email,status) VALUES (?,?,?,?,?,?,?)";

        ResultSet resultSet = null;

        //"try with resources" will automatically close anything that was defined inside
        //the () brackets
        try(
                Connection conn = DriverManager.getConnection(connectURL,dbUser,password);
                PreparedStatement ps = conn.prepareStatement(sql, new String[] {"taskID"});
        )
        {
            ps.setString(1,task.getTitle());
            ps.setString(2,task.getDescription());
            ps.setDate(3,Date.valueOf(task.getDueDate()));
            ps.setDate(4,Date.valueOf(task.getCreationDate()));
            ps.setInt(5,task.getEstimatedLengthInMin());
            ps.setString(6,task.getUser().getEmail());
            ps.setString(7,task.getStatus().toString());

            ps.executeUpdate();

            int taskID = -1;

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next())
                taskID = resultSet.getInt(1);

            rspMessage = "Task saved with ID: "+taskID;
        }
        catch (SQLIntegrityConstraintViolationException e)  //this is a duplicate key
        {
            rspMessage = "user not defined in system, task not saved";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return rspMessage;
    }

    private static User getUserFromEmail(String email, ArrayList<User> users)
    {
        for (User user : users)
        {
            if (user.getEmail().equalsIgnoreCase(email))
                return user;
        }
        return null;
    }

    /**
     * This method will return a list of tasks from the database
     */
    public static ArrayList<Task> getTasksFromDB()
    {
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<User> users = DBUtility.getUsersFromDB();

        //use a try with resources block to access the database and automatically close the connection, statement
        //and result set
        String sql = "SELECT * FROM tasks";

        try (
                Connection conn = DriverManager.getConnection(connectURL,dbUser,password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            //loop over the results returned and create new user objects
            while (resultSet.next())
            {
                int taskID = resultSet.getInt("taskID");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDate dueDate = resultSet.getDate("dueDate").toLocalDate();
                LocalDate creationDate = resultSet.getDate("creationDate").toLocalDate();
                int estimatedLengthInMin = resultSet.getInt("estimatedLengthInMinutes");
                String email = resultSet.getString("email");
                User user = getUserFromEmail(email, users);
                Status status = Status.valueOf(resultSet.getString("status"));

                Task newTask = new Task(taskID, title, description, dueDate, creationDate,
                                         estimatedLengthInMin, user, status);

                tasks.add(newTask);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * This method will return a list of tasks from the database
     *
     */
    public static XYChart.Series<String, Integer> getBarChartData(Status status)
    {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName(status.toString());

        //use a try with resources block to access the database and automatically close the connection, statement
        //and result set
        String sql = String.format("SELECT MONTHName(creationDate) as month, COUNT(title) AS count " +
                "FROM tasks " +
                "WHERE status= '%s' " +
                "GROUP BY MONTHName(creationDate), status " +
                "ORDER BY MONTH(creationDate), status; ", status.toString());

        try (
                Connection conn = DriverManager.getConnection(connectURL,dbUser,password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            //loop over the results returned and create new user objects
            while (resultSet.next())
            {
                series.getData().add(new XYChart.Data<>(resultSet.getString("month"),
                                                            resultSet.getInt("count")));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return series;
    }
}
