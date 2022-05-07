package practice.datastruc.tree;

import java.util.ArrayList;

//二叉树
public class BiTree {

    private final Node root;

    public Node getRoot() {
        return root;
    }

    public BiTree(ArrayList<Integer> inputList) {
        this.root = createBinaryTree(inputList);
    }

    //创建二叉树
    private Node createBinaryTree(ArrayList<Integer> inputList) {
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }
        Node node = null;
        Integer value = inputList.remove(0);
        if (value != null) {
            node = new Node(value);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    //先序遍历
    public void preOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value);
        preOrderTraversal(node.leftChild);
        preOrderTraversal(node.rightChild);
    }

    //中序遍历
    public void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.leftChild);
        System.out.print(node.value);
        inOrderTraversal(node.rightChild);
    }

    //后序遍历
    public void postOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.leftChild);
        postOrderTraversal(node.rightChild);
        System.out.print(node.value);
    }
    //二叉树结点
    public class Node {
        Integer value;
        Node leftChild;
        Node rightChild;

        public Node(Integer value) {
            this.value = value;
        }
    }
}