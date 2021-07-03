package ch.zhaw.ase1;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import ch.zhaw.ase1.config.PersistenceJPAConfigXml;
import ch.zhaw.ase1.model.Address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ch.zhaw.ase1.model.PersonEntity;
import ch.zhaw.ase1.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/jpa-config-hi.xml")
@ContextConfiguration(classes = { PersistenceJPAConfigXml.class }, loader = AnnotationConfigContextLoader.class)
public class PersonServicePersistenceIntegrationTest {

    @Autowired
    private PersonService service;

    // tests

    @Test
    public final void whenContextIsBootstrapped_thenNoExceptions() {
        //
    }

    @Test
    public final void whenEntityIsCreated_thenNoExceptions() {
        service.save(new PersonEntity(randomAlphabetic(6),new Address("Musterstrasse", 2), null));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public final void whenInvalidEntityIsCreated_thenDataException() {
        service.save(new PersonEntity(randomAlphabetic(2048),new Address("Musterstrasse", 2), null));
    }

    @Test //(expected = InvalidDataAccessApiUsageException.class)
    public final void whenSameEntityIsCreatedTwice_thenDataException() {
        final PersonEntity entity = new PersonEntity(randomAlphabetic(8),new Address("Musterstrasse", 2), null);
        service.save(entity);
        service.save(entity);
    }

    @Test(expected = DataAccessException.class)
    public final void temp_whenInvalidEntityIsCreated_thenDataException() {
        service.save(new PersonEntity(randomAlphabetic(2048),new Address("Musterstrasse", 2), null));
    }

    @Test
    public final void whenEntityIsCreated_thenFound() {
        final PersonEntity personEntity = new PersonEntity("abc",new Address("Musterstrasse", 2), null);
        service.save(personEntity);
        final PersonEntity found = service.findOne(personEntity.getId());
        Assert.assertNotNull(found);
    }

}
