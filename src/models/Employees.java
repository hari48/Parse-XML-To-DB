package models;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employees")
public class Employees {
private List<Employee> employees;

@XmlElement(name = "Employee")
public List<Employee> getEmployees() {
	return employees;
}

public void setEmployees(List<Employee> employees) {
	this.employees = employees;
}
}
