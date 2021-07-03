package ch.zhaw.ads;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class KgvServerTest {

    KgvServer server;

    @Before
    public void setUp() throws Exception {
        server = new KgvServer();
    }

    @Test
    public void testKgv() {
        assertEquals(12,server.kgv(3,4));
        assertEquals(4,server.kgv(2,4));
        assertEquals(35,server.kgv(5,7));
        assertEquals(12,server.kgv(4,6));
    }
}
