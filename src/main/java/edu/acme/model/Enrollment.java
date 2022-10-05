package edu.acme.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class Enrollment extends BaseModel {
	private Unit unit;
	private int grade;
}
