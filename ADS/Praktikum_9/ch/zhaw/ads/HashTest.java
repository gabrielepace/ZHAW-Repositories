package ch.zhaw.ads;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.*;

class Town {
	int hashCode;
	String name;
	String nb;
	Town (int hashCode, String name, String nb) {
		this.name = name; this.hashCode = hashCode; this.nb = nb;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Town)) return false;
		return ((Town)o).name.equals(this.name);
	}
	
	@Override
	public int hashCode() {
		return hashCode;
	}
	
	@Override 
	public String toString() {
		return name+" "+hashCode;
	}
}

public class HashTest {
	Map<Town,Town> hashmap;
	List<Town> towns;
	
	@Before
	public void setUp() throws Exception {
		towns = new LinkedList<>();
		towns.add(new Town(5,"Bari","BA"));
		towns.add(new Town(8,"Bologna","BO"));
		towns.add(new Town(3,"Catania","CA"));
		towns.add(new Town(9,"Firenze","FI"));
		towns.add(new Town(0,"Genova","GV"));
		towns.add(new Town(12,"Milano","MI"));
		towns.add(new Town(7,"Napoli","NA"));
		towns.add(new Town(7,"Palermo","PA"));
		towns.add(new Town(7,"Roma","RM"));
		towns.add(new Town(5,"Torino","TO"));
		hashmap = new MyHashtable<>(100);
	}
    
	@Test
	public void testAdd() {
		hashmap.clear();
		Town t0 = towns.get(0);
		hashmap.put(t0,t0);
		Town t1 = hashmap.get(t0);
		assertEquals(t0,t1);
	}	

	@Test
	public void testAdd2() {
		hashmap.clear();
		Town t0 = towns.get(0);
		Town t1 = towns.get(1);
		hashmap.put(t0,t0);
		hashmap.put(t1,t1);
		Town t2 = hashmap.get(t0);
		assertEquals(t0,t2);
		t2 = hashmap.get(t1);
		assertEquals(t1,t2);
	}
	
	@Test
	public void testAdd3() {
		hashmap.clear();
		Town t0 = towns.get(0);
		hashmap.remove(t0);
		hashmap.put(t0,t0);
		hashmap.put(t0,t0);
		assertEquals(1, hashmap.size());
		Town t1 = hashmap.get(t0);
		assertEquals(t0,t1);
	}	
		
	@Test
	public void testAdd4() {
		hashmap.clear();
		Town t0 = towns.get(0);
		hashmap.put(t0,t0);
		hashmap.put(t0,t0);
		assertEquals(1, hashmap.size());
	}
		
	
	@Test
	public void testSize() {
		hashmap.clear();
		assertEquals(0, hashmap.size());
		testAdd2();
		assertEquals(2, hashmap.size());
	}
	
	@Test
	public void testRemove() {
		hashmap.clear();
		Town t0 = towns.get(0);
		Town t1 = towns.get(1);
		hashmap.put(t0,t0);
		hashmap.remove(t0);
		assertEquals(0, hashmap.size());
		hashmap.put(t0,t0);
		hashmap.remove(t1);
		assertEquals(1, hashmap.size());		
		hashmap.remove(t0);
		assertEquals(0, hashmap.size());
	}
	
	@Test
	public void testCompetitor() {
		try {
			Competitor c1 = new Competitor(1,"Röthlin Viktor",74,"Magglingen/Macolin","2:11:05.0");
			Competitor c2 = new Competitor(1,"Röthlin Viktor",74,"Magglingen/Macolin","2:11:05.0");
			Competitor c3 = new Competitor(1,"Röthlin Viktor",75,"Magglingen/Macolin","2:11:05.0");
			Competitor c4 = new Competitor(1,"Röthlin Pavel",74,"Magglingen/Macolin","2:11:05.0");
			assertEquals("test1",c1,c2);
			assertTrue("test2",c1.compareTo(c2) == 0);
			assertFalse("test3",c1.compareTo(c3) == 0);
			assertTrue("test4",c1.hashCode() == c2.hashCode());
			assertFalse("test5",c1.hashCode() == c3.hashCode());
			assertFalse("test6",c1.hashCode() == c4.hashCode());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testMixed() {
		hashmap.clear();
		Map<Town,Town> hashmap2 = new HashMap<>();
		for (int i = 0; i < 1000; i++) {
			Town c = towns.get((int)(Math.random()*towns.size()));
			// System.out.println(""+ c);
			int op = (int)(Math.random()*2);
			switch (op) {
				case 0 : hashmap.put(c,c); hashmap2.put(c,c); break;
				case 1 : hashmap.remove(c); hashmap2.remove(c); break;
			}	
		}
		assertEquals(hashmap2.size(), hashmap.size());	
		for (Town t : towns) {
			Town c1 = hashmap.get(t);
			Town c2 = hashmap2.get(t);	
			assertEquals(c1,c2);			
		}
	}
	
		@Test
	public void testMixedWithOveflow() {
		Map<Town,Town> hashmap = new MyHashtable<>(4);
		Map<Town,Town> hashmap2 = new HashMap<>();
		for (int i = 0; i < 1000; i++) {
			Town c = towns.get((int)(Math.random()*towns.size()));
			// System.out.println(""+ c);
			int op = (int)(Math.random()*2);
			switch (op) {
				case 0 : hashmap.put(c,c); hashmap2.put(c,c); break;
				case 1 : hashmap.remove(c); hashmap2.remove(c); break;
			}	
		}
		assertEquals(hashmap2.size(), hashmap.size());	
		for (Town t : towns) {
			Town c1 = hashmap.get(t);
			Town c2 = hashmap2.get(t);	
			assertEquals(c1,c2);			
		}
	}
	

}
