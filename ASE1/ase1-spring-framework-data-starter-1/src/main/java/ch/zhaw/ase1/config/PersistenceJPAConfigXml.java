package ch.zhaw.ase1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@ComponentScan({ "edu.spring.database" })
@ImportResource({ "classpath:spring-data-config-hi.xml" })
public class PersistenceJPAConfigXml {

    public PersistenceJPAConfigXml() {
        super();
    }

}
