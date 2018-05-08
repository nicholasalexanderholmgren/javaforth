package edu.mccc.cos210.ds.demo.binarytree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.mccc.cos210.ds.binarytree.IBinaryTreeNode;
import edu.mccc.cos210.ds.binarytree.LinkedBinaryTreeNode;

public class BinaryTreeTest {
	public static void main(String... args) {
		BinaryTreeTest btt = new BinaryTreeTest();
		IBinaryTreeNode<Scrobus> root = btt.initTree();
		EventQueue.invokeLater(() -> btt.initSwing(root));
	}
	private void initSwing(IBinaryTreeNode<Scrobus> root) {
		JFrame jf = new JFrame("Binary Tree Test");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new BinaryTreePanel(root, 100, 100);
		jf.add(jp, BorderLayout.CENTER);
		jf.setSize(800, 600);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	private IBinaryTreeNode<Scrobus> initTree() {
		IBinaryTreeNode<Scrobus> root = new MyLinkedBinaryTreeNode<>(new Scrobus("Dog", 0.0), Color.YELLOW);
		IBinaryTreeNode<Scrobus> l = new MyLinkedBinaryTreeNode<>(new Scrobus("Cat", 1.0), Color.RED);
		IBinaryTreeNode<Scrobus> r = new MyLinkedBinaryTreeNode<>(new Scrobus("Mouse", 2.0), Color.BLUE);
		IBinaryTreeNode<Scrobus> ll = new MyLinkedBinaryTreeNode<>(new Scrobus("Horse", 1.1), Color.YELLOW);
		IBinaryTreeNode<Scrobus> lr = new MyLinkedBinaryTreeNode<>(new Scrobus("Rabbit", 1.2), Color.YELLOW);
		IBinaryTreeNode<Scrobus> rl = new MyLinkedBinaryTreeNode<>(new Scrobus("Skunk", 2.1), Color.YELLOW);
		root.setLeft(l);
		root.setRight(r);
		l.setLeft(ll);
		l.setRight(lr);
		r.setLeft(rl);
		return root;
	}
	public class MyLinkedBinaryTreeNode<E> extends LinkedBinaryTreeNode<E> {
		public Color color;
		public MyLinkedBinaryTreeNode(E data, Color color) {
			super(data);
			this.color = color;
		}
	}
	private class Scrobus {
		private String name;
		private Double number;
		public Scrobus(String name, Double d) {
			this.name = name;
			this.number = d;
		}
		@Override
		public String toString() {
			return name + " : " + number;
		}
	}
}
