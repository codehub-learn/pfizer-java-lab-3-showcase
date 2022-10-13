package edu.acme.service;

import edu.acme.model.Department;
import edu.acme.model.Unit;
import edu.acme.model.University;
import edu.acme.repository.UniversityRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UniversityService implements CRUDService<University, Long> {

    private static final Logger log = LoggerFactory.getLogger(UniversityService.class);

    private final UniversityRepository universityRepository;

    @Override
    public void create(University university) throws Exception {
        log.debug("Creating university {}.", university);
        universityRepository.create(university);
        for (Department department : university.getDepartments()) {
            createDepartment(university, department);
        }
    }

    private void createDepartment(University university, Department department) throws Exception {
        log.debug("Creating department {}.", department);
        universityRepository.createDepartment(university, department);
        for (Unit unit : department.getUnits()) {
            createUnit(department, unit);
        }
    }

    private void createUnit(Department department, Unit unit) throws Exception {
        log.debug("Creating unit {}.", unit);
        universityRepository.createUnit(department, unit);
    }

    @Override
    public List<University> findAll() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<University> findByID(Long aLong) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(University university) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(University university) throws Exception {
        throw new UnsupportedOperationException();
    }
}
