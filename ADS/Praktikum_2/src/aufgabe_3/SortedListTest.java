package aufgabe_3;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;


/**
 * ADS FS2019 
 * Praktikum 2
 * 
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */


public class SortedListTest {
    private List<Integer> sortedIntegerList;
    private Comparator<Integer> integerComparator;

    @Before
    public void setUp() {
        integerComparator = Integer::compare;
        sortedIntegerList = new SortedList<Integer>(integerComparator);
    }

    @Test
    public void testSortedList() {
        List<Integer> expected;

        final List<Integer> add = Arrays.asList(
                9, 75, 2, 11, 25, 5, 17, 33, 15, 3, 6, 1, 3, 2
        );
        expected = Arrays.asList(
                1, 2, 2, 3, 3, 5, 6, 9, 11, 15, 17, 25, 33, 75
        );
        for (Integer integer : add) {
            sortedIntegerList.add(integer);
            checkOrder(sortedIntegerList, integerComparator);
        }
        assertTrue(Objects.equals(sortedIntegerList, expected));


        final List<Integer> remove = Arrays.asList(
                2, 2, 1, 5, 11
        );
        expected = Arrays.asList(
                 3, 3, 6, 9, 15, 17, 25, 33 , 75
        );
        for (Integer integer : remove) {
            sortedIntegerList.remove(integer);
            checkOrder(sortedIntegerList, integerComparator);
        }
        assertTrue(Objects.equals(sortedIntegerList, expected));
    }

    private <E> void checkOrder(final List<E> sortedList, final Comparator<? super E> comparator) {
        final ListIterator<E> listIterator = sortedList.listIterator();

        E next = null;
        E previous = null;
        boolean previousIsSet = false;
        while (listIterator.hasNext()) {
            next = listIterator.next();

            if (previousIsSet) {
                assertTrue(comparator.compare(previous, next) <= 0);
            }

            previous = next;
            previousIsSet = true;
        }
    }
}

