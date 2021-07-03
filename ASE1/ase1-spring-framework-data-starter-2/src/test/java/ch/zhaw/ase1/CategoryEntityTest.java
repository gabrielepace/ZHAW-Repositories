package ch.zhaw.ase1;


import ch.zhaw.ase1.config.PersistenceJPAConfigXml;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ch.zhaw.ase1.model.CategoryEntity;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/jpa-config-hi.xml")
@ContextConfiguration(classes = { PersistenceJPAConfigXml.class }, loader = AnnotationConfigContextLoader.class)
class CategoryEntityTest {

    @Test
    public final void whenContextIsBootstrapped_thenNoExceptions() {
        //
    }
}
