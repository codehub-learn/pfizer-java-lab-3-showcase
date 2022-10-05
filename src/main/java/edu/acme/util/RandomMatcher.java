package edu.acme.util;

import edu.acme.model.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class RandomMatcher {
	public static void randomlyMatchStudentAndUnits(Student student, List<University> universities) {
		int randomUniversityIndex = ThreadLocalRandom.current().nextInt(0, universities.size());
		University university = universities.get(randomUniversityIndex);

		int randomDepartmentIndex = ThreadLocalRandom.current().nextInt(0, university.getDepartments().size());
		Department department = university.getDepartments().get(randomDepartmentIndex);
		List<Unit> units = department.getUnits();

		HashSet<Integer> uniqueUnitIndexes = new HashSet<>();

		int randomUnitSize = ThreadLocalRandom.current().nextInt(1, units.size());

		int unitsMatched = 0;

		while (unitsMatched != randomUnitSize) {
			int randomUnitIndex = ThreadLocalRandom.current().nextInt(0, units.size());
			if (uniqueUnitIndexes.add(randomUnitIndex)) {
				unitsMatched++;
				Unit chosenUnit = units.get(randomUnitIndex);
				int randomGrade = ThreadLocalRandom.current().nextInt(1, 100);
				student.getEnrollments().add(Enrollment.builder().unit(chosenUnit).grade(randomGrade).build());
			}
		}
	}
}
