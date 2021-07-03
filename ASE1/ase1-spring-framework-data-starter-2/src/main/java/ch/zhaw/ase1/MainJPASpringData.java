package ch.zhaw.ase1;


import ch.zhaw.ase1.service.CarManager;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainJPASpringData {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-data-config-hi.xml");
        CarManager manager = context.getBean(CarManager.class);

        manager.manage();

        context.close();
    }
}
