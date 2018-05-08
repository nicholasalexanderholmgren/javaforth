package edu.mccc.cos210.ds.binarytree;

public class LinkedBinaryTreeNode<E> implements IBinaryTreeNode<E> {
    protected E data;
    protected LinkedBinaryTreeNode<E> parent;
    protected LinkedBinaryTreeNode<E> left;
    protected LinkedBinaryTreeNode<E> right;
    public LinkedBinaryTreeNode(E data) {
        this.data = data;
    }
    @Override
    public E getData() {
        return data;
    }
    @Override
    public void setData(E data) {
        this.data = data;
    }
    @Override
    public IBinaryTreeNode<E> getParent() {
      return parent;
    }
    @Override
    public IBinaryTreeNode<E> getLeft() {
      return left;
    }
    @Override
    public void setLeft(IBinaryTreeNode<E> child) {
        for (LinkedBinaryTreeNode<E> n = this; n != null; n = n.parent) {
            if (n == child) {
                throw new IllegalArgumentException();
            }
        }
        LinkedBinaryTreeNode<E> childNode = (LinkedBinaryTreeNode<E>) child;
        if (this.left != null) {
            left.parent = null;
        }
        if (childNode != null) {
            childNode.removeFromParent();
            childNode.parent = this;
        }
        this.left = childNode;
    }
    @Override
    public IBinaryTreeNode<E> getRight() {
      return right;
    }
    @Override
    public void setRight(IBinaryTreeNode<E> child) {
         for (LinkedBinaryTreeNode<E> n = this; n != null; n = n.parent) {
            if (n == child) {
                throw new IllegalArgumentException();
            }
        }
        LinkedBinaryTreeNode<E> childNode = (LinkedBinaryTreeNode<E>) child;
        if (right != null) {
            right.parent = null;
        }
        if (childNode != null) {
            childNode.removeFromParent();
            childNode.parent = this;
        }
        this.right = childNode;
    }
    @Override
    public void removeFromParent() {
        if (parent != null) {
            if (parent.left == this) {
                parent.left = null;
            } else {
            	if (parent.right == this) {
            		parent.right = null;
            	}
            }
            this.parent = null;
        }
    }
    @Override
    public void traversePreorder(IBinaryTreeNode.Visitor visitor) {
        visitor.visit(this);
        if (left != null) {
        	left.traversePreorder(visitor);
        }
        if (right != null) {
        	right.traversePreorder(visitor);
        }
    }
    @Override
    public void traversePostorder(Visitor visitor) {
        if (left != null) {
        	left.traversePostorder(visitor);
        }
        if (right != null) {
        	right.traversePostorder(visitor);
        }
        visitor.visit(this);
    }
    @Override
    public void traverseInorder(Visitor visitor) {
        if (left != null) {
        	left.traverseInorder(visitor);
        }
        visitor.visit(this);
        if (right != null) {
        	right.traverseInorder(visitor);
        }
    }
}