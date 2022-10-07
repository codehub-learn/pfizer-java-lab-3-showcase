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

            Statement statement = connection.createStatement();
            int i = statement.executeUpdate("CREATE TABLE UNIVERSITY");
            log.info("{}", i);
        }
    }
}
