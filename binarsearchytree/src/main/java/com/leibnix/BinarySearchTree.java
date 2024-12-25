package com.leibnix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BinarySearchTree<T> implements BST<T> {

    private Node<T> root;
    /**
     * private helper class used only by BinaryTree.
     */
    private class Node<T> {
    
        private T value;

        private Node leftNode;

        private Node rightNode;
    
        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }
    
        public T getValue() {
            return value;
        }
    
        public void setValue(T value) {
            this.value = value;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }
        
    }

    @Override
    public void add(T value) {
        if (root == null) {
            root = new Node(value);
        } else {
            add(value, root);
        }
    }

    private void add(T value, Node<T> node) {
        if (value.hashCode() < node.getValue().hashCode()) {
            if (node.getLeftNode() == null) {
                node.setLeftNode(new Node(value));
            } else {
                add(value, node.getLeftNode());
            }
        } else {
            if (node.getRightNode() == null) {
                node.setRightNode(new Node(value));
            } else {
                add(value, node.getRightNode());
            }
        }
    }

    @Override
    public int getHeight() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(height(node.getLeftNode()), height(node.getRightNode()));
        }
    }

    @Override
    public boolean contains(T value) {
        return contains(value, root);
    }

    private boolean contains(T value, Node<T> node) {
        if (node == null) {
            return false;
        } else if (value.hashCode() == node.getValue().hashCode()) {
            return true;
        } else if (value.hashCode() < node.getValue().hashCode()) {
            return contains(value, node.getLeftNode());
        } else {
            return contains(value, node.getRightNode());
        }
    }

    @Override
    public void print() {
        print(root);
    }

    private void print(Node<T> node) {
        if (node != null) {
            print(node.getLeftNode());
            System.out.println(node.getValue());
            print(node.getRightNode());
        }
    }

    @Override
    public boolean remove(T value) {
        return remove(value, root);
    }

    private boolean remove(T value, Node<T> node) {
        if (node == null) {
            return false;
        } else if (value.hashCode() == node.getValue().hashCode()) {
            if (node.getLeftNode() == null && node.getRightNode() == null) {
                node = null;
            } else if (node.getLeftNode() == null) {
                node = node.getRightNode();
            } else if (node.getRightNode() == null) {
                node = node.getLeftNode();
            } else {
                Node<T> temp = node.getRightNode();
                while (temp.getLeftNode() != null) {
                    temp = temp.getLeftNode();
                }
                node.setValue(temp.getValue());
                remove(temp.getValue(), node.getRightNode());
            }
            return true;
        } else if (value.hashCode() < node.getValue().hashCode()) {
            return remove(value, node.getLeftNode());
        } else {
            return remove(value, node.getRightNode());
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean isLeaf() {
        return root.getLeftNode() == null && root.getRightNode() == null;
    }

    @Override
    public int getQuantity() {
        return getQuantity(root);
    }

    private int getQuantity(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + getQuantity(node.getLeftNode()) + getQuantity(node.getRightNode());
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isFull() {
        return isFull(root);
    }

    private boolean isFull(Node<T> node) {
        if (node == null) {
            return true;
        } else if (node.getLeftNode() == null && node.getRightNode() == null) {
            return true;
        } else if (node.getLeftNode() != null && node.getRightNode() != null) {
            return isFull(node.getLeftNode()) && isFull(node.getRightNode());
        } else {
            return false;
        }
    }

    /**
     * a list with root, left, rigth.
     */
    @Override
    public ArrayList< Node<T> > preorder() {
        ArrayList< Node<T> > list = new ArrayList< Node<T> >();
        preorder(root, list);
        return list;
    }

    private void preorder(Node<T> node, ArrayList< Node<T> > list) {
        if (node != null) {
            list.add(node);
            preorder(node.getLeftNode(), list);
            preorder(node.getRightNode(), list);
        }
    }

    /**
     * a list with left, root, rigth.
     */
    @Override
    public ArrayList< Node<T> > inorder() {
        ArrayList< Node<T> > list = new ArrayList< Node<T> >();
        inorder(root, list);
        return list;
    }

    private void inorder(Node<T> node, ArrayList< Node<T> > list) {
        if (node != null) {
            inorder(node.getLeftNode(), list);
            list.add(node);
            inorder(node.getRightNode(), list);
        }
    }

    /**
     * a list with left, rigth, root.
     */
    @Override
    public ArrayList< Node<T> > postorder() {
        ArrayList< Node<T> > list = new ArrayList< Node<T> >();
        postorder(root, list);
        return list;
    }

    private void postorder(Node<T> node, ArrayList< Node<T> > list) {
        if (node != null) {
            postorder(node.getLeftNode(), list);
            postorder(node.getRightNode(), list);
            list.add(node);
        }
    }

    //@Override
    public Node<T> getRoot() {
        return root;
    }

    @Override
    public HashSet<T> asHashSet() {
        HashSet<T> set = new HashSet<T>();
        getSet(root, set);
        return set;
    }

    public void getSet(Node<T> node, HashSet<T> set) {
        if (node != null) {
            set.add(node.getValue());
            getSet(node.getLeftNode(), set);
            getSet(node.getRightNode(), set);
        }
    }

    @Override
    public HashMap<T, Boolean> asHashMap() {
        HashMap<T, Boolean> map = new HashMap<T, Boolean>();
        getMap(root, map);
        return map;
    }

    private void getMap(Node<T> node, HashMap<T, Boolean> map) {
        if (node != null) {
            map.put(node.getValue(), true);
            getMap(node.getLeftNode(), map);
            getMap(node.getRightNode(), map);
        }
    }

    @Override
    public T[] toArray() {
        ArrayList<T> list = new ArrayList<T>();
        toArray(root, list);
        return (T[]) list.toArray();
    }

    private void toArray(Node<T> node, ArrayList<T> list) {
        if (node != null) {
            toArray(node.getLeftNode(), list);
            list.add(node.getValue());
            toArray(node.getRightNode(), list);
        }
    }

    @Override
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node<T> node) {
        if (node == null) {
            return true;
        } else {
            int left = height(node.getLeftNode());
            int right = height(node.getRightNode());
            return Math.abs(left - right) <= 1 && isBalanced(node.getLeftNode()) && isBalanced(node.getRightNode());
        }
    }

    @Override
    public boolean isPerfect() {
        return isPerfect(root);
    }

    private boolean isPerfect(Node<T> node) {
        if (node == null) {
            return true;
        } else {
            return isFull(node) && isBalanced(node);
        }
    }

    @Override
    public boolean isDegenerate() {
        return isDegenerate(root);
    }

    private boolean isDegenerate(Node<T> node) {
        if (node == null) {
            return true;
        } else {
            return (node.getLeftNode() == null && node.getRightNode() != null) || (node.getLeftNode() != null && node.getRightNode() == null);
        }
    }

    @Override
    public boolean isComplete() {
        return isComplete(root);
    }

    private boolean isComplete(Node<T> node) {
        if (node == null) {
            return true;
        } else {
            return isComplete(node.getLeftNode()) && isComplete(node.getRightNode()) && height(node.getLeftNode()) == height(node.getRightNode());
        }
    }
    
}
