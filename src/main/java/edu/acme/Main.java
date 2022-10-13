package edu.acme;

import edu.acme.factory.StudentFactory;
import edu.acme.factory.UniversityFactory;
import edu.acme.model.Student;
import edu.acme.model.University;
import edu.acme.repository.DatabaseSource;
import edu.acme.repository.DatabaseSourceConnectionPooling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        UniversityFactory universityFactory = new UniversityFactory();
        List<University> universities = universityFactory.getSampleData();
        universities.forEach(university -> log.info("University: {}", university));

        StudentFactory studentFactory = new StudentFactory(universities);
        List<Student> students = studentFactory.getSampleData();
        students.forEach(student -> log.info("Student: {}", student));

        try (Connection connection = DatabaseSourceConnectionPooling.getConnection()){
            String productVersion = connection.getMetaData().getDatabaseProductVersion();
            log.info(productVersion);
        }

        List<String> sqlCreateQueries = getTableCreationQueries();

        sqlCreateQueries.forEach(query -> {
            try {
                createTable(query);
            } catch (SQLException e) {
                log.error("Something went wrong during the creation of the tables");
            }
        });

    }

    private static void createTable(String sql) throws SQLException {
        try (Connection connection = DatabaseSourceConnectionPooling.getConnection();
             Statement statement = connection.createStatement()){
            int i = statement.executeUpdate(sql);
            log.info("{}", i);
        }
    }

    private static List<String> getTableCreationQueries(){
        String universitySql = "CREATE TABLE UNIVERSITY " +
                "(id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR2(50) NOT NULL)";

        String departmentSql = "CREATE TABLE DEPARTMENT " +
                "(id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR2(50) NOT NULL, " +
                "university_id INTEGER NOT NULL, " +
                "foreign key (university_id) references UNIVERSITY(id))";

        String unitSql = "CREATE TABLE UNIT (" +
                "  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "  name VARCHAR2(100), " +
                "  tutor_name VARCHAR2(100), " +
                "  department_id INTEGER NOT NULL, " +
                "  foreign key (department_id) references DEPARTMENT(id)" +
                "  )";

        String studentSql = "CREATE TABLE STUDENT (" +
                "  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "  name VARCHAR2(100) NOT NULL, " +
                "  address VARCHAR2(100) NOT NULL, " +
                "  dateOfBirth DATE NOT NULL " +
                "  )";

        String enrollmentSql = "CREATE TABLE ENROLLMENT (" +
                "  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "  grade NUMBER(3), " +
                "  student_id INTEGER NOT NULL," +
                "  unit_id INTEGER NOT NULL," +
                "  foreign key (student_id) references STUDENT(id), " +
                "  foreign key (unit_id) references UNIT(id) " +
                "  )";

        return List.of(universitySql, departmentSql, unitSql, studentSql, enrollmentSql);
    }
}
