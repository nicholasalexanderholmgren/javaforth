package edu.mccc.cos210.ds.binarytree;

public interface IBinaryTreeNode<E> {
    public E getData();
    public void setData(E data);
    public IBinaryTreeNode<E> getParent();
    public IBinaryTreeNode<E> getLeft();
    public void setLeft(IBinaryTreeNode<E> child);
    public IBinaryTreeNode<E> getRight();
    public void setRight(IBinaryTreeNode<E> child);
    public void removeFromParent();
    public void traversePreorder(Visitor visitor);
    public void traversePostorder(Visitor visitor);
    public void traverseInorder(Visitor visitor);
    public interface Visitor {
        public <E> void visit(IBinaryTreeNode<E> node);
    }
}
