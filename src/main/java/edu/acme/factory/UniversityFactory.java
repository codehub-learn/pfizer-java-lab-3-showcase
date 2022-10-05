package edu.acme.factory;

import edu.acme.Main;
import edu.acme.model.Department;
import edu.acme.model.Unit;
import edu.acme.model.University;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UniversityFactory implements AbstractFactory<University>{

    private static final Logger log = LoggerFactory.getLogger(UniversityFactory.class);

    @Override
    public List<University> getSampleData() {
        List<Unit> units = unitCreation();
        List<Department> departments = departmentCreation(units);
        return universityCreation(departments);
    }

    private List<Unit> unitCreation() {
        // @formatter:off
        List<Unit> units = List.of(
                // cs
                Unit.builder().name("Data Science").tutorName("John Dew").build(),
                Unit.builder().name("Operating Systems").tutorName("Walter White").build(),
                Unit.builder().name("Databases").tutorName("Krysten Odenkirk").build(),
                // biology
                Unit.builder().name("Genetics").tutorName("Nicole Miker").build(),
                Unit.builder().name("Biochemistry").tutorName("Bryan Gunn").build(),
                Unit.builder().name("Molecular Biology").tutorName("Aaron Cranston").build(),
                Unit.builder().name("Biodiversity").tutorName("Anna Paul").build(),
                // economics
                Unit.builder().name("Macroeconomics").tutorName("Severus Snape").build(),
                Unit.builder().name("Microeconomics").tutorName("Bob Ritter").build(),
                Unit.builder().name("Economics and Policies").tutorName("Dean Norris").build(),
                Unit.builder().name("Business and Operations").tutorName("James Robert").build(),
                // maritime studies
                Unit.builder().name("Maritime Human Resource Management").tutorName("John Michael").build(),
                Unit.builder().name("Environmental Administration").tutorName("David William").build(),
                Unit.builder().name("Ocean Sustainability").tutorName("Richard Joseph").build(),
                Unit.builder().name("Maritime Law & Policy").tutorName("Thomas Charles").build(),
                Unit.builder().name("Shipping and Transport Management").tutorName("Mary Patricia").build(),
                // arts
                Unit.builder().name("Ceramics").tutorName("Jennifer Linda").build(),
                Unit.builder().name("Drawing").tutorName("John Joseph").build(),
                Unit.builder().name("Foreign Languages").tutorName("Jessica Susan").build(),
                Unit.builder().name("History of Art").tutorName("Barbara Linda").build(),
                Unit.builder().name("Visual Arts").tutorName("Karen Sarah").build());
        // @formatter:on
        for (final Unit unit : units) {
            log.trace("{}", unit);
        }
        return units;
    }

    private List<Department> departmentCreation(List<Unit> units) {
        // @formatter:off
        List<Department> departments = List.of(
                // mit
                Department.builder().name("Computer Science").units(new ArrayList<>(units.subList(0, 3))).build(),
                Department.builder().name("Biology").units(new ArrayList<>(units.subList(3, 7))).build(),
                Department.builder().name("Economics").units(new ArrayList<>(units.subList(7, 11))).build(),
                // sheffield
                Department.builder().name("Maritime Studies").units(new ArrayList<>(units.subList(11, 16))).build(),
                Department.builder().name("Major Arts").units(new ArrayList<>(units.subList(16, 21))).build());
        // @formatter:on

        for (final Department departmentWithUnits : departments) {
            log.trace("{}", departmentWithUnits);
        }
        return departments;
    }

    private List<University> universityCreation(final List<Department> departments) {
        // @formatter:off
        List<University> universities = List.of(
                University.builder().name("University of Sheffield").departments(new ArrayList<>(departments.subList(0, 3))).build(),
                University.builder().name("MIT").departments(new ArrayList<>(departments.subList(3, 5))).build());
        // @formatter:on

        for (final University universityWithDepartments : universities) {
            log.trace("{}", universityWithDepartments);
        }
        return universities;
    }
}
