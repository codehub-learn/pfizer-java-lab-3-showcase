package edu.acme.repository;

import edu.acme.Main;
import edu.acme.model.Department;
import edu.acme.model.Unit;
import edu.acme.model.University;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UniversityRepository implements CRUDRepository<University, Long>{

    private static final Logger log = LoggerFactory.getLogger(UniversityRepository.class);

    @Override
    public void create(University university) throws SQLException {
        String insertSql = "INSERT INTO UNIVERSITY(name) VALUES (?)";

        try (Connection connection = DatabaseSourceConnectionPooling.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, university.getName());
            preparedStatement.executeUpdate();
            log.trace("Created university {}.", university);
        } catch (SQLException e) {
            throw new SQLException("Could not create university.", e);
        }
    }

    public void createDepartment(University university, Department department) throws SQLException {
        String insertSql = "INSERT INTO DEPARTMENT(name, university_id) VALUES (?, ?)";

        try (Connection connection = DatabaseSourceConnectionPooling.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

            preparedStatement.setString(1, department.getName());
            preparedStatement.setLong(2, university.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Could not create department.", e);
        }
    }

    public void createUnit(Department department, Unit unit) throws SQLException {
        String insertSql = "INSERT INTO UNIT(name, tutor_name, department_id) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseSourceConnectionPooling.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

            preparedStatement.setString(1, unit.getName());
            preparedStatement.setString(2, unit.getTutorName());
            preparedStatement.setLong(3, department.getId());
            preparedStatement.executeUpdate();
            log.trace("Created unit {} with department id:{}.", unit, department.getId());

        } catch (SQLException e) {
            throw new SQLException("Could not create unit.", e);
        }
    }

    @Override
    public List<University> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<University> findByID(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(University university) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(University university) {
        throw new UnsupportedOperationException();
    }
}
