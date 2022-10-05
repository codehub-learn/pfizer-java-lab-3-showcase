package edu.acme.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class University extends BaseModel {
	private String name;
	private List<Department> departments;
}
