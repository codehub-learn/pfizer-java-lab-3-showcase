package edu.acme;

import edu.acme.factory.StudentFactory;
import edu.acme.factory.UniversityFactory;
import edu.acme.model.Student;
import edu.acme.model.University;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        final UniversityFactory universityFactory = new UniversityFactory();
        final List<University> universities = universityFactory.getSampleData();
        universities.forEach(university -> log.info("University: {}", university));

        final StudentFactory studentFactory = new StudentFactory(universities);
        final List<Student> students = studentFactory.getSampleData();
        students.forEach(student -> log.info("Student: {}", student));

        try (Connection conn = DriverManager.getConnection ("jdbc:h2:mem:university", "sa","")){
            final String productVersion = conn.getMetaData().getDatabaseProductVersion();
            log.info(productVersion);
        }
    }
}
