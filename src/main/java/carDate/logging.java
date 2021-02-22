package carDate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import carDate.emp.Employee;
import carDate.emp.EmployeeDao;

@Aspect
@Component
public class logging {
	
	Logger log = LoggerFactory.getLogger(logging.class);
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@After ("execution(public String login())")
	public void AfterLogging() {
		log.info("=====> UserController /login ");
	}

	@Before ("execution(public String logout())")
	public void BeforeLogout() {
		log.info("=====> UserController /logout ");
	}

	@Before ("execution(public String errorPage())")
	public void BeforeErrorPage() {
		log.warn("=====> UserController /403 ");
	}


//	@Around("execution(public String DoSomething())")
//	public void AroundLogging() {
//		System.out.println(" ====>  Around Logged");
//	}

	@Before ("execution(public String showNewEmpForm(..))")
	public void EmployeeController_empNew() {
		log.warn("=====> EmployeeController /emp/new ");
	}

//	// error if uncomment this
//	@Before ("execution(public ModelAndView editEmployee(..))")
//	public void EmployeeController_empEditId() {
//		log.warn("=====> EmployeeController /emp/edit/{empId}:");
//	}
	
	@Before ("execution(public String saveEmp(..))")
	public void EmployeeController_empSave(JoinPoint jp) {
		Object obj = jp.getArgs()[0];
		log.warn("=====> EmployeeController /emp/save:  " + obj.toString());
	}
	
	@Before ("execution(public String deleteEmplopyee(..))")
	public void EmployeeController_empDelete(JoinPoint jp) {
		Object obj = jp.getArgs()[0];
		Long l = Long.parseLong(obj.toString());
//		System.out.println(" ===> employee id: " + l);
		
		Employee employee = employeeDao.getEmployeeById(l);
		log.warn("=====> EmployeeController /emp/delete/{empId}: userName: " 
		+ employee.getEmpName() + ", with Id: " + l);
	}
}