package ch.zhaw.ads;
/**
 * Implements an AVL tree.
 * Note that all "matching" is based on the compareTo method.
 *
 * @author Mark Allen Weiss
 * Generic K.Rege
 */


public class AVLSearchTree<E extends Comparable<E>> extends SortedBinaryTree<E> {

    public AVLSearchTree() {
        super(Comparable::compareTo);
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param e    the item to insert.
     * @param node the node that roots the tree.
     * @return the new root.
     */
    @Override
    protected TreeNode<E> insertAt(TreeNode<E> node, E e) {
        if (node == null) {
            node = new TreeNode<>(e);
        } else {
            final int comparision = e.compareTo(node.element);
            if (comparision <= 0) {
                node.left = insertAt(node.left, e);
                if (height(node.left) - height(node.right) == 2) {
                    if (height(node.left.left) > height(node.left.right)) {
                        node = rotateR(node);
                    } else {
                        node = rotateLR(node);
                    }
                }
            } else {
                node.right = insertAt(node.right, e);
                if (height(node.right) - height(node.left) == 2) {
                    if (height(node.right.right) > height(node.right.left)) {
                        node = rotateL(node);
                    } else {
                        node = rotateRL(node);
                    }
                }
            }
        }
        updateHeight(node);
        return node;
    }

    /**
     * Remove from the tree. Nothing is done if e is not found.
     *
     * @param e the item to remove.
     */
    @Override
    public E remove(E e) {
        // @TODO Support this operation.
        throw new UnsupportedOperationException();
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException();
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private TreeNode<E> rotateR(TreeNode<E> k2) {
        TreeNode<E> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private TreeNode<E> rotateL(TreeNode<E> k1) {
        TreeNode<E> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private TreeNode<E> rotateLR(TreeNode<E> k3) {
        k3.left = rotateL(k3.left);
        return rotateR(k3);
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private TreeNode<E> rotateRL(TreeNode<E> k1) {
        k1.right = rotateR(k1.right);
        return rotateL(k1);
    }
}
