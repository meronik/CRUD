package employee.demo;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	  @Autowired
	  EmployeeRepository employeeRepository;


	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeRepository.deleteById(employee.getId());
	}

	@Override
	public List<Employee> listAllEmployee() {
        return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).get();
	}

}
