package vu.springhibernate.common;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
@Entity
@Table
public class Department {
	@Id
    @GeneratedValue
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="department",cascade=CascadeType.PERSIST)
	private List<Employee> employees = new ArrayList<Employee>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(String name) {
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", employees=" + employees + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getEmployees()=" + getEmployees() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
