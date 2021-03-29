package carDate;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class logging implements BeanPostProcessor {
	
//	Logger log = LoggerFactory.getLogger(logging.class);
	private final Logger log = LoggerFactory.getLogger(getClass());

	/* To log out all new beans 
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {

		// Log the names and types of all non inner-beans created
		if (!beanName.contains("inner bean")) {
			log.info("NEW " + bean.getClass().getSimpleName() + " -> " + beanName);
		}

		return bean;
	}*/

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

}