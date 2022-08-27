package derakhshani.arad.controller.dbcontroller;

import derakhshani.arad.controller.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static derakhshani.arad.controller.dbcontroller.StudentController.getCountNotDeletedStudentByYear;

public class CapacityController {
    public static Integer getTotalCountOfYear(int year) throws SQLException, ClassNotFoundException {

        int count = 0;
        Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        String query = "SELECT cnt FROM public.capacity WHERE \"year\"=" + year + ";";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next())
            count = resultSet.getInt(1);
        statement.close();
        connection.close();
        return count;

    }
    public static void showCapacity() throws SQLException, ClassNotFoundException {
        LocalDate date = LocalDate.now();
        int count = getTotalCountOfYear(date.getYear()) - getCountNotDeletedStudentByYear(date.getYear());
        System.out.println("Remain Register Capacity in Year " + date.getYear() + " Is " + count);
    }

}
