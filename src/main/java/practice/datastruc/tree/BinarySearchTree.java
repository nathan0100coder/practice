package practice.datastruc.tree;

public class BinarySearchTree {

    private Node root; // 树根节点

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insert(int key)
    {
        Node p = new Node(); // 待插入的节点
        p.data = key;

        if (root == null)
        {
            root = p;
        }
        else
        {
            Node parent = new Node();
            Node current = root;
            while (true) {
                parent = current;
                if (key > current.data) {
                    current = current.right; // 右子树
                    if (current == null)
                    {
                        parent.right = p;
                        return;
                    }
                }
                else // 本程序没有做key出现相等情况的处理，暂且假设用户插入的节点值都不同
                {
                    current = current.left; // 左子树
                    if (current == null)
                    {
                        parent.left = p;
                        return;
                    }
                }
            }
        }
    }

    public void preOrder(Node root)
    { // 前序遍历,"中左右"
        if (root != null)
        {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void inOrder(Node root)
    { // 中序遍历,"左中右"
        if (root != null)
        {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    public void postOrder(Node root)
    { // 后序遍历,"左右中"
        if (root != null)
        {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public void traverse(int traverseType)
    { // 选择以何种方式遍历
        switch (traverseType)
        {
            case 1:
                System.out.print("preOrder traversal ");
                preOrder(root);
                System.out.println();
                break;
            case 2:
                System.out.print("inOrder traversal ");
                inOrder(root);
                System.out.println();
                break;
            case 3:
                System.out.print("postOrder traversal ");
                postOrder(root);
                System.out.println();
                break;
        }
    }

    public Node search(int key) { // 从树中按照关键值查找元素
        Node current = root;
        while (current.data != key) {
            if (key > current.data)
                current = current.right;
            else
                current = current.left;
            if (current == null)
                return null;
        }
        return current;
    }

    public void show(Node node)
    {    //输出节点的数据域
        if(node!=null)
            System.out.println(node.data);
        else
            System.out.println("null");
    }

    private Node getSuccessor(Node delNode)    //寻找要删除节点的中序后继结点
    {
        Node successorParent=delNode;
        Node successor=delNode;
        Node current=delNode.right;

        //用来寻找后继结点
        while(current!=null)
        {
            successorParent=successor;
            successor=current;
            current=current.left;
        }

        //如果后继结点为要删除结点的右子树的左子，需要预先调整一下要删除结点的右子树
        if(successor!=delNode.right)
        {
            successorParent.left=successor.right;
            successor.right=delNode.right;
        }
        return successor;
    }

    public boolean delete(int key)
    // 删除结点
    {
        Node current = root;
        Node parent = new Node();
        boolean isRightChild = true;
        while (current.data != key)
        {
            parent = current;
            if (key > current.data)
            {
                current = current.right;
                isRightChild = true;
            }
            else
            {
                current = current.left;
                isRightChild = false;
            }
            if (current == null) return false; // 没有找到要删除的结点
        }
        // 此时current就是要删除的结点,parent为其父结点
        // 要删除结点为叶子结点
        if (current.right == null && current.left == null)
        {
            if (current == root)
            {
                root = null; // 整棵树清空
            }
            else
            {
                if (isRightChild)
                    parent.right = null;
                else
                    parent.left = null;
            }
            return true;
        }
        //要删除结点有一个子结点
        else if(current.left==null)
        {
            if(current==root)
                root=current.right;
            else if(isRightChild)
                parent.right=current.right;
            else
                parent.left=current.right;
            return true;
        }
        else if(current.right==null)
        {
            if(current==root)
                root=current.left;
            else if(isRightChild)
                parent.right=current.left;
            else
                parent.left=current.left;
            return true;
        }
        //要删除结点有两个子结点
        else
        {
            Node successor=getSuccessor(current);    //找到要删除结点的后继结点

            if(current==root)
                root=successor;
            else if(isRightChild)
                parent.right=successor;
            else
                parent.left=successor;

            successor.left=current.left;
            return true;
        }
    }

    public static void main(String[] args) // unit test
    {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(39);
        tree.insert(24);
        tree.insert(64);
        tree.insert(23);
        tree.insert(30);
        tree.insert(53);
        tree.insert(60);
        tree.insert(90);
        tree.insert(80);
        tree.insert(88);
        tree.insert(99);

//
//        tree.traverse(1);
//        tree.traverse(2);
//        tree.traverse(3);
        tree.show(tree.search(23));
        tree.show(tree.search(60));
        tree.show(tree.search(800));
//
//        tree.delete(23);
//        tree.delete(60);
//        tree.delete(64);
//
//        tree.show(tree.search(23));
//        tree.show(tree.search(60));
//        tree.show(tree.search(64));
    }
    // 二叉搜索树类
    public static class Node
    { // 节点类
        int data; // 数据域
        Node right; // 右子树
        Node left; // 左子树

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }
    }
}

