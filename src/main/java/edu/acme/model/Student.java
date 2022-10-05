package edu.acme.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class Student extends BaseModel {
	private String name;
	private String address;
	private Date dateOfBirth;
	private String telephoneNumber;
	private List<Enrollment> enrollments;
}
