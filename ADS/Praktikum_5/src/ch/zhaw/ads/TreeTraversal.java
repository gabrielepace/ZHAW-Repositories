package ch.zhaw.ads;

import java.util.*;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ADS FS2019
 * 
 * Praktikum 5 
 * Aufgabe 2 – Traversierung von Bäumen & 
 * Aufgabe 4 – Ausschnitt der Elemente aus einem binären Suchbaum
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

	private TreeNode<T> root;
	private TreeNode<T> node;

	public TreeTraversal(TreeNode<T> root) {
		this.root = root;
	}

	@SuppressWarnings("unchecked")
	public void inorder(TreeNode<T> node, Visitor<T> vis) {
		// to be done
		if (node != null) {
			inorder(node.left, vis);
			vis.visit(node.element);
			inorder(node.right, vis);
		}
	}

	public void inorder(Visitor<T> vis) {
		inorder(root, vis);
	}

	@SuppressWarnings("unchecked")
	public void preorder(TreeNode<T> node, Visitor<T> vis) {
		// to be done
		if (node != null) {
			vis.visit(node.element);
			preorder(node.left, vis);
			preorder(node.right, vis);
		}
	}

	public void preorder(Visitor<T> visitor) {
		preorder(root, visitor);
	}

	@SuppressWarnings("unchecked")
	public void postorder(TreeNode<T> node, Visitor<T> vis) {
		// to be done
		if (node != null) {
			postorder(node.left, vis);
			postorder(node.right, vis);
			vis.visit(node.element);
		}
	}

	public void postorder(Visitor<T> visitor) {
		postorder(root, visitor);
	}

	@SuppressWarnings("unchecked")
	void levelorder(TreeNode<T> node, Visitor<T> vis) {
		Queue<TreeNode<T>> q = new LinkedBlockingQueue<TreeNode<T>>();
		if (node != null)
			q.add(node);
		while (!q.isEmpty()) {
			node = q.remove();
			vis.visit(node.element);
			if (node.left != null)
				q.add(node.left);
			if (node.right != null)
				q.add(node.right);
		}
	}

	@Override
	public void levelorder(Visitor<T> vistor) {
		levelorder(root, vistor);
	}

	// Aufgabe 4
	@SuppressWarnings("unchecked")
	private void inorderInterval(TreeNode<T> node, T min, T max, Visitor<T> vistor) {
		if (node != null) {
			if (node.element.compareTo(min) < 0) {
				inorderInterval(node.right, min, max, vistor);
			} else if (node.element.compareTo(max) > 0) {
				inorderInterval(node.left, min, max, vistor);
			} else {
				inorderInterval(node.left, min, max, vistor);
				vistor.visit(node.element);
				inorderInterval(node.right, min, max, vistor);
			}
		}
	}

	@Override
	public void interval(T min, T max, Visitor<T> vistor) {
		inorderInterval(root, min, max, vistor);
	}
}