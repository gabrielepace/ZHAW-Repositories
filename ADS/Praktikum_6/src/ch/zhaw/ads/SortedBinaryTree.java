package ch.zhaw.ads;
import java.util.Comparator;

public class SortedBinaryTree<E> implements Tree<E> {
    private final Comparator<? super E> comparator;
    protected TreeNode<E> root;
    private E removed;

    /**
     * A factory method. Creates a new SortedBinaryTree.
     *
     * @param comparator The comparator to use for sorting.
     * @param <E>        Type of elements in the list.
     * @return A new SortedBinaryTree.
     */
    public static <E> SortedBinaryTree<E> create(Comparator<? super E> comparator) {
        if (comparator == null) {
            throw new NullPointerException();
        }
        return new SortedBinaryTree<>(comparator);
    }

    /**
     * A factory method. Creates a new SortedBinaryTree sorted using the natural ordering.
     *
     * @param <E> The comparable type of elements in the list.
     * @return A new SortedBinaryTree.
     */
    public static <E extends Comparable<? super E>> SortedBinaryTree<E> create() {
        final Comparator<E> comparator = Comparable::compareTo;
        return create(comparator);
    }

    protected SortedBinaryTree(final Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    protected TreeNode<E> insertAt(TreeNode<E> node, E e) {
        if (node == null) {
            return new TreeNode<>(e);
        } else {
            if (comparator.compare(e, node.element) <= 0) {
                node.left = insertAt(node.left, e);
            } else {
                node.right = insertAt(node.right, e);
            }
            updateHeight(node);
            return node;
        }
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param e the item to insert.
     */
    @Override
    public void add(E e) {
        root = insertAt(root, e);
    }

    // find node to replace by
    private TreeNode<E> replacement;

    private TreeNode<E> findRepAt(TreeNode<E> node) {
        if (node.right != null) {
            node.right = findRepAt(node.right);
            return node;
        } else {
            replacement = node;
            return node.left;
        }
    }

    // remove node
    private TreeNode<E> removeAt(TreeNode<E> node, E e) {
        if (node == null) {
            return null;
        } else {
            if (comparator.compare(e, node.element) == 0) {
                // found
                removed = node.element;
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else {
                    node.left = findRepAt(node.left);
                    replacement.left = node.left;
                    replacement.right = node.right;
                    return replacement;
                }
            } else if (comparator.compare(e, node.element) <= 0) {
                // search left
                node.left = removeAt(node.left, e);
            } else {
                // search right
                node.right = removeAt(node.right, e);
            }
            updateHeight(node);
            return node;
        }
    }

    /**
     * Remove from the tree. Nothing is done if e is not found.
     *
     * @param e the item to remove.
     */
    @Override
    public E remove(E e) {
        removed = null;
        root = removeAt(root, e);
        return removed;
    }

    @Override
    public E removeLast() {
        if (root.right != null) {
            root.right = findRepAt(root.right);
        } else {
            replacement = root;
            root = root.left;
        }
        return replacement.element;
    }

    public String printTree() {
        StringBuilder out = new StringBuilder();
        if (root.right != null) {
            printTree(root.right, out, true, "");
        }
        out.append(root.element).append("\n");
        if (root.left != null) {
            printTree(root.left, out, false, "");
        }
        return out.toString();
    }

    private void printTree(TreeNode<E> node, StringBuilder out, boolean isRight, String indent) {
        if (node.right != null) {
            printTree(node.right, out, true,
                    indent + (isRight ? "        " : " |      "));
        }
        out.append(indent);
        if (isRight) {
            out.append(" /");
        } else {
            out.append(" \\");
        }
        out.append("----- ");
        out.append(node.element).append("\n");
        if (node.left != null) {
            printTree(node.left, out, false,
                    indent + (isRight ? " |      " : "        "));
        }
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return True if empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Traversal<E> traversal() {
        return new TreeTraversal<>(root);
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(TreeNode<E> node) {
        if (root == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    @Override
    public int height() {
        return height(root);
    }

    protected int height(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        if (node.heightCacheValid) {
            return node.height;
        }
        node.heightCacheValid = true;
        return node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Recomputes the height of a subtree whose height was changed. Only the height of the subtree's root node is updated.
     *
     * @param node The node whose height to update.
     */
    protected void updateHeight(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        node.heightCacheValid = false;
        node.height = height(node);
    }

    @Override
    public boolean balanced() {
        return balanced(root);
    }

    private boolean balanced(TreeNode<E> node) {
        if (node == null) {
            return true;
        }
        return Math.abs(height(node.left) - height(node.right)) <= 1 && balanced(node.left) && balanced(node.right);
    }
}
