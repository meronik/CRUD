package employee.demo;

import java.util.List;

public interface EmployeeService {
	
	public Employee saveEmployee(Employee employee);
	
	public Employee updateEmployee(Employee employee);
	
	public void deleteEmployee(Employee employee);

	public List<Employee> listAllEmployee();

	public Employee getEmployee(Integer id);


}
