package ch.zhaw.ads;
/**
 * @(#)TreeTest.java
 * @author
 * @version 1.00 2018/3/17
 */



import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AVLSearchTreeTest {
    Tree<String> tree;

    @Before
    public void setUp() {
        tree = new AVLSearchTree<>();
        tree.add("E");
        tree.add("F");
        tree.add("G");
        tree.add("H");
        tree.add("J");
        tree.add("A");
        tree.add("B");
        tree.add("C");
        tree.add("D");
    }

    @Test
    public void testInorder() {
        Visitor<String> v = new MyVisitor<>();
        tree.traversal().inOrder(v);
        assertEquals("inorder", "ABCDEFGHJ", v.toString());
    }

    @Test
    public void testPreorder() {
        Visitor<String> v = new MyVisitor<>();
        tree.traversal().preOrder(v);
        assertEquals("preorder", "FBADCEHGJ", v.toString());
    }

    @Test
    public void testPostorder() {
        Visitor<String> v = new MyVisitor<>();
        tree.traversal().postOrder(v);
        assertEquals("postorder", "ACEDBGJHF", v.toString());
    }

    @Test
    public void testLevelorder() {
        Visitor<String> v = new MyVisitor<>();
        tree.traversal().levelOrder(v);
        assertEquals("levelorder", "FBHADGJCE", v.toString());
    }

    @Test
    public void testHeight() {
        assertEquals(4, tree.height());
    }

    @Test
    public void testBalanced() {
        assertTrue(tree.balanced());
    }

    // @TODO Implement the "remove" operation. Allow duplicate indexes. Then enable this test.
    @Test
    @Ignore
    public void testMixed() {
        for (int i = 0; i < 1000; i++) {
            Character c = (char) ('A' + (Math.random() * 26));
            int op = (int) (Math.random() * 2);
            switch (op) {
                case 0:
                    tree.add(c.toString());
                    break;
                case 1:
                    tree.remove(c.toString());
                    break;
            }
        }
        assertTrue(tree.balanced());
    }

    class MyVisitor<T> implements Visitor<T> {
        StringBuilder output;

        MyVisitor() {
            output = new StringBuilder();
        }

        public void visit(T s) {
            output.append(s);
        }

        public String toString() {
            return output.toString();
        }
    }
}

