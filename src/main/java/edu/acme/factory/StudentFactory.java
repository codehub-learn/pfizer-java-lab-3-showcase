package edu.acme.factory;

import edu.acme.model.Student;
import edu.acme.model.University;
import edu.acme.util.DateTool;
import edu.acme.util.RandomMatcher;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class StudentFactory implements AbstractFactory<Student>{

    private final List<University> universities;

    private static final Logger log = LoggerFactory.getLogger(StudentFactory.class);

    @Override
    public List<Student> getSampleData() {
        return studentCreation(universities);
    }

    private List<Student> studentCreation(List<University> universities) {
        // @formatter:off
        List<Student> students = List.of(
                Student.builder().name("Torkel McDevitt").address("Monaco 15").dateOfBirth(DateTool.parseDate("1919-12-19")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Kay Napoletani").address("Andorra 12").dateOfBirth(DateTool.parseDate("1929-02-09")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Jun-Ho Jahn").address("Equatorial Guinea 1").dateOfBirth(DateTool.parseDate("1982-12-19")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Abd Porras").address("Pakistan 8").dateOfBirth(DateTool.parseDate("1986-02-09")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Maria Robles").address("Kazakhstan 22").dateOfBirth(DateTool.parseDate("1983-02-09")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Torsten Riso").address("Montserrat 89").dateOfBirth(DateTool.parseDate("1981-12-19")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Vladlena Bertrand").address("Djibouti 4").dateOfBirth(DateTool.parseDate("1982-12-09")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Venyamin Messmann").address("Cabo Verde 18").dateOfBirth(DateTool.parseDate("1984-12-19")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Deimante Aveskamp").address("Sint Maarten 182").dateOfBirth(DateTool.parseDate("1987-12-09")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Wilfrid Haberkorn").address("Barbados 22").dateOfBirth(DateTool.parseDate("1979-02-19")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Erdmann Lund").address("Saint Helena 3").dateOfBirth(DateTool.parseDate("1959-02-09")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Tomislav Poingdestre").address("Spain 27").dateOfBirth(DateTool.parseDate("1999-12-09")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Polydeukes Laterza").address("Sweden 89").dateOfBirth(DateTool.parseDate("1979-02-19")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Kalyani Moe").address("Jamaica 67").dateOfBirth(DateTool.parseDate("1980-02-09")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Hilde Nakashima").address("Mauritania 77").dateOfBirth(DateTool.parseDate("1980-02-19")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Merope Gold").address("Guyana 20").dateOfBirth(DateTool.parseDate("1951-12-09")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Bharat Bartalotti").address("Haiti 5").dateOfBirth(DateTool.parseDate("1982-12-19")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Christian Paterson").address("Falkland Islands 39").dateOfBirth(DateTool.parseDate("1983-12-09")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Blanch Rake").address("Tokelau 88").dateOfBirth(DateTool.parseDate("1999-02-09")).enrollments(new ArrayList<>()).build(),
                Student.builder().name("Evangeline Filipov").address("Ecuador 9").dateOfBirth(DateTool.parseDate("1987-02-19")).enrollments(new ArrayList<>()).build()
        );
        // @formatter:on
        log.info("Randomly associating students with departments(units/enrollments)");
        for (final Student student : students) {
            RandomMatcher.randomlyMatchStudentAndUnits(student, universities);
        }
        for (final Student studentWithRandomUnits : students) {
            log.debug("{}", studentWithRandomUnits);
        }
        return students;
    }
}
