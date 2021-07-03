package ch.zhaw.ads;
class TreeNode<E> {
    E element;
    TreeNode<E> left, right;
    int height = 1;
    boolean heightCacheValid = false;

    TreeNode(E element) {
        this.element = element;
    }

    TreeNode(E element, TreeNode<E> left, TreeNode<E> right) {
        this(element);
        this.left = left;
        this.right = right;
    }

    E getValue() {
        return element;
    }

    TreeNode<E> getLeft() {
        return left;
    }

    TreeNode<E> getRight() {
        return right;
    }
}