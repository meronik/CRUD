package employee.demo;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);      //to find error

	
	@PostMapping("")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		try {          
			Employee res = employeeService.saveEmployee(employee);
            return new ResponseEntity<Employee>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	 
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
		try {   
			Employee res = employeeService.updateEmployee(employee);
            return new ResponseEntity<Employee>(res, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.error(e.toString());
            return new ResponseEntity<>("Invalid Employee Id", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
		try {
			employeeService.deleteEmployee(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.error(e.toString());
            return new ResponseEntity<>("Invalid Employee Id", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping("/all")
    public ResponseEntity<?> getAllEmployees() {
        try {
            return new ResponseEntity<List<Employee>>(employeeService.listAllEmployee(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Employee>(employeeService.getEmployee(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.error(e.toString());
            return new ResponseEntity<>("Invalid Employee Id", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
