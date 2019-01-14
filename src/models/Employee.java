package models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Employee")
@XmlType(propOrder = { "id", "name"})
public class Employee {
private int id;
private String name;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public String toString(){
	return "id: " + this.id + "  name: " + this.name;
}
}
