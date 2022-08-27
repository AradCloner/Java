package derakhshani.arad.controller.dbcontroller;

import derakhshani.arad.controller.ConnectionManager;
import derakhshani.arad.entity.Student;
import derakhshani.arad.exception.StudentNotFoundException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static derakhshani.arad.MainApp.*;
import static derakhshani.arad.util.IOUtil.getInput;

public class StudentController {
    public static void showStudentInformation() throws IOException, StudentNotFoundException, SQLException, ClassNotFoundException {
        Student student = getStudentNationalCode();
        print(student.toString());
    }
    public static void cancelRegistration() throws IOException, StudentNotFoundException, SQLException, ClassNotFoundException {
        Student student = getStudentNationalCode();
        student.setDeleted(true);
        updateStudentData(student);
    }
    private static void insertToDB(Student student) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);

        String query = "INSERT INTO public.students(" +
                "\"firstName\", \"lastName\", \"score\", \"studentNumber\", \"address\", \"nationalCode\", \"isDeleted\", \"year\")" +
                " VALUES ('" + student.getFirstName() + "', '" + student.getLastName() + "', '" + student.getScore() + "'," +
                "'" + student.getStudentNumber() + "', '" + student.getAddress() + "', '" + student.getNationalCode() + "'," +
                " '" + student.getDeleted() + "', '" + student.getYear() + "');";
        statement.executeUpdate(query);
        connection.commit();
        statement.close();
        connection.close();
    }

    public static void registerStudent() throws IOException, SQLException, ClassNotFoundException {
        Student student = new Student();
        print("Enter First Name :");
        student.setFirstName(getInput());
        print("Enter Last Name :");
        student.setLastName(getInput());
        print("Enter Score :");
        student.setScore(Float.parseFloat(getInput()));
        print("Enter Student Number :");
        student.setStudentNumber(Integer.parseInt(getInput()));
        print("Enter Address :");
        student.setAddress(getInput());
        print("Enter National Code :");
        student.setNationalCode(getInput());
        student.setDeleted(false);
        LocalDate date = LocalDate.now();
        student.setYear(date.getYear());
        insertToDB(student);
        print("Student Added Successful");
    }


    public static Integer getCountNotDeletedStudentByYear(int year) throws SQLException, ClassNotFoundException {
        Integer count = 0;
        Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        String query = "SELECT count(*) FROM public.students WHERE \"year\"=" + year + " AND \"isDeleted\"=false;";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next())
            count = resultSet.getInt("count");
        statement.close();
        connection.close();
        return count;

    }

    private static Student loadStudentByNationalCode(String nationalCode) throws SQLException, ClassNotFoundException {
        Student student = new Student();
        Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        String query = "SELECT * FROM public.students WHERE \"nationalCode\"='"+nationalCode+"';";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            student.setId(resultSet.getInt("id"));
            student.setFirstName(resultSet.getString("firstName"));
            student.setLastName(resultSet.getString("lastName"));
            student.setScore(resultSet.getFloat("score"));
            student.setStudentNumber(resultSet.getInt("studentNumber"));
            student.setAddress(resultSet.getString("address"));
            student.setNationalCode(resultSet.getString("nationalCode"));
            student.setDeleted(resultSet.getBoolean("isDeleted"));
            student.setYear(resultSet.getInt("year"));
        }
        statement.close();
        connection.close();
        return student;
    }

    public static void updateStudentInformation() throws IOException, StudentNotFoundException, SQLException, ClassNotFoundException {
        print("Please Enter Student National Code");
        Student student = getStudentNationalCode();
        if (student == null) return;
        print("For Editing Information Enter New Data Or Press Enter To Ignore");
        print("First Name: " + student.getFirstName());
        String firstName = getInput();
        if (!firstName.equals(""))
            student.setFirstName(firstName);
        print("Last Name: " + student.getLastName());
        String lastName = getInput();
        if (!lastName.equals(""))
            student.setLastName(lastName);
        print("Score: " + student.getScore());
        Float score = Float.parseFloat(getInput());
        if (!score.equals(""))
            student.setScore(score);
        print("Address: " + student.getAddress());
        String address = getInput();
        if (!firstName.equals(""))
            student.setAddress(address);
        updateStudentData(student);
    }

    private static void updateStudentData(Student student) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        String query ="UPDATE public.students" +
                " SET \"firstName\"='"+student.getFirstName()+"', \"lastName\"='"+student.getLastName()+"', score="+student.getScore()+" , \"isDeleted\"="+student.getDeleted()+", address='"+student.getAddress()+"'" +
                " WHERE \"nationalCode\"='"+student.getNationalCode()+"';";
        statement.executeUpdate(query);
        connection.commit();
        statement.close();
        connection.close();

    }

    public static Student getStudentNationalCode() throws IOException, StudentNotFoundException, SQLException, ClassNotFoundException {
        print("Enter nationalCode :");
        Student student = loadStudentByNationalCode(getInput());
        if (student == null) {
            print("Student Not Found");
            throw new StudentNotFoundException();
        }
        return student;
    }

}
