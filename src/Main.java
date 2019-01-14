import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import models.Employee;
import models.Employees;

public class Main {
	private static String XML_LOCATION = "C:\\Users\\dell\\Desktop\\udemyWorkspace\\Parse-XML-to-DB\\xmls\\SampleEmployees.xml";
	private static String connectionURL = "jdbc:sqlserver://<server>:<port>;databaseName=AdventureWorks;user=<user>;password=<password>";
	private static String insertionQuery = "INSERT INTO Employees (ID, NAME) VALUES (?, ?)";

	public static void main(String[] args) {
		try {
			List<Employee> employees = getEmployeesFromXML(XML_LOCATION);
			insertEmployeesToDB(employees);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static List<Employee> getEmployeesFromXML(String fileLocation) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(Employees.class);
		Unmarshaller u = context.createUnmarshaller();
		Employees employees = (Employees) u.unmarshal(new File(fileLocation));

		return employees.getEmployees();
	}

	public static void insertEmployeesToDB(List<Employee> employees) {
		try (Connection con = DriverManager.getConnection(connectionURL);
				PreparedStatement stmt = con.prepareStatement(insertionQuery);) {
			Iterator<Employee> itr = employees.iterator();
			Employee emp;
			while (itr.hasNext()) {
				emp = itr.next();
				stmt.setInt(1, emp.getId());
				stmt.setString(2, emp.getName());
				stmt.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
