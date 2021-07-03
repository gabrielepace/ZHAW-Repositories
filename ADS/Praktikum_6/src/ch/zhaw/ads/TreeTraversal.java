package ch.zhaw.ads;
import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal<E> implements Traversal<E> {

    private TreeNode<E> root;

    public TreeTraversal(TreeNode<E> root) {
        this.root = root;
    }

    @Override
    public void inOrder(Visitor<E> vistor) {
        if (root == null) {
            return;
        }

        inOrder(root, vistor);
    }

    private void inOrder(TreeNode<E> node, Visitor<E> vistor) {
        if (node.getLeft() != null) {
            inOrder(node.getLeft(), vistor);
        }

        vistor.visit(node.getValue());

        if (node.getRight() != null) {
            inOrder(node.getRight(), vistor);
        }
    }

    @Override
    public void preOrder(Visitor<E> vistor) {
        if (root == null) {
            return;
        }

        final Stack<TreeNode<E>> stack = new ListStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            final TreeNode<E> node = stack.pop();

            vistor.visit(node.getValue());
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    @Override
    public void postOrder(Visitor<E> vistor) {
        if (root == null) {
            return;
        }

        postOrder(root, vistor);
    }

    private void postOrder(TreeNode<E> node, Visitor<E> vistor) {
        if (node.getLeft() != null) {
            postOrder(node.getLeft(), vistor);
        }

        if (node.getRight() != null) {
            postOrder(node.getRight(), vistor);
        }

        vistor.visit(node.getValue());
    }


    @Override
    public void levelOrder(Visitor<E> vistor) {
        if (root == null) {
            return;
        }

        final Queue<TreeNode<E>> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            final TreeNode<E> node = stack.remove();

            vistor.visit(node.getValue());
            if (node.getLeft() != null) {
                stack.add(node.getLeft());
            }
            if (node.getRight() != null) {
                stack.add(node.getRight());
            }
        }
    }
}
