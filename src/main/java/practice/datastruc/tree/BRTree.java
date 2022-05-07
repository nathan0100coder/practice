package practice.datastruc.tree;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

public class BRTree<K, V> implements Serializable, Iterable<BRTree.Node<K, V>> {

    private Node<K, V> root;

    private int size;

    private int BLACK = 0;
    private int RED = 1;

    public BRTree() {
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return null;
    }

    public void put(K key, V value) {
        Node<K, V> z = new Node<>();
        z.setKey(key);
        z.setValue(value);
        z.setColor(RED);
        this.size++;

        Node<K, V> y = null;
        Node<K, V> x = root;
        while (x != null) {
            y = x;
            int sp = z.compareTo(x);
            if (sp < 0) {
                x = x.getLeft();
            } else if (sp > 0) {
                x = x.getRight();
            } else {
                x.setValue(value);
                this.size--;
                return;
            }
        }
        z.setPro(y);

        if (y == null) {
            root = z;
        } else if (z.compareTo(y) < 0) {
            y.setLeft(z);
        } else if (z.compareTo(y) > 0) {
            y.setRight(z);
        }

        //调整红黑树
        this.fixup(z);

    }

    private void fixup(Node<K, V> z) {

        while (z.getPro() != null && z.getPro().getColor() == RED) {
            if (z.getPro().getPro() != null) {
                if (z.getPro().equals(z.getPro().getPro().getLeft())) {
                    Node<K, V> y = z.getPro().getPro().getRight();
                    if (y != null && y.getColor() == RED) {
                        z.getPro().setColor(BLACK);
                        y.setColor(BLACK);
                        z.getPro().getPro().setColor(RED);
                        z = z.getPro().getPro();
                    } else {
                        if (z.equals(z.getPro().getRight())) {
                            z = z.getPro();
                            this.leftRotate(z);
                        }
                        z.getPro().setColor(BLACK);
                        z.getPro().getPro().setColor(RED);
                        this.rightRotate(z.getPro().getPro());
                    }
                } else if (z.getPro().equals(z.getPro().getPro().getRight())) {
                    Node<K, V> y = z.getPro().getPro().getLeft();
                    if (y != null && y.getColor() == RED) {
                        z.getPro().setColor(BLACK);
                        y.setColor(BLACK);
                        z.getPro().getPro().setColor(RED);
                        z = z.getPro().getPro();
                    } else {
                        if (z.equals(z.getPro().getLeft())) {
                            z = z.getPro();
                            this.rightRotate(z);
                        }
                        z.getPro().setColor(BLACK);
                        z.getPro().getPro().setColor(RED);
                        this.leftRotate(z.getPro().getPro());
                    }
                }
            }
        }
        this.root.setColor(BLACK);
    }

    private void leftRotate(Node<K, V> x) {
        Node<K, V> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null) {
            y.getLeft().setPro(x);
        }
        y.setPro(x.getPro());
        if (x.getPro() == null) {
            this.root = y;
        } else if (x.equals(x.getPro().getLeft())) {
            x.getPro().setLeft(y);
        } else {
            x.getPro().setRight(y);
        }
        y.setLeft(x);
        x.setPro(y);
    }

    private void rightRotate(Node<K, V> x) {
        Node<K, V> y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != null) {
            y.getRight().setPro(x);
        }
        y.setPro(x.getPro());
        if (x.getPro() == null) {
            this.root = y;
        } else if (x.equals(x.getPro().getLeft())) {
            x.getPro().setLeft(y);
        } else {
            x.getPro().setRight(y);
        }

        y.setRight(x);
        x.setPro(y);
    }

    public void inorder(Node<K, V> x) {
        if (x != null) {
            inorder(x.getLeft());
            System.out.println(x.getKey() + ":" + x.getValue());
            inorder(x.getRight());
        }
    }

    public Node<K, V> getMaximum(Node<K, V> node) {
        while (node.getLeft() != null) {
            node = node.getRight();
        }
        return node;
    }

    public Node<K, V> getMinimum(Node<K, V> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public V get(K key) {
        Node<K, V> node = getNode(key);
        return node != null ? node.getValue() : null;
    }

    private Node<K, V> getNode(K key) {
        if (this.root == null)
            return null;
        Node<K, V> x = this.root;
        while (x != null && !x.getKey().equals(key)) {
            if (key.hashCode() - x.getKey().hashCode() < 0) {
                x = x.getLeft();
            } else if (key.hashCode() - x.getKey().hashCode() > 0) {
                x = x.getRight();
            }
        }
        return x;
    }

    public void remove(K key) {
        Node<K, V> z = getNode(key);
        Node<K, V> y = z;
        Node<K, V> x;
        int oColor = y.getColor();
        if (z.getLeft() == null) {
            x = z.getRight();
            this.RBTransplant(z, z.getRight());
        } else if (z.getRight() == null) {
            x = z.getLeft();
            this.RBTransplant(z, z.getLeft());
        } else {
            y = this.getMinimum(z.getRight());
            oColor = y.getColor();
            x = y.getLeft();
            if (y.getPro().equals(z)) {
                x.setPro(y);
            } else {
                this.RBTransplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setPro(y);
            }
            this.RBTransplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setPro(y);
            y.setColor(z.getColor());
        }
        if (oColor == this.BLACK) {
            this.RBRemoveFixup(x);
        }
        this.size--;
    }

    private void RBTransplant(Node<K, V> u, Node<K, V> v) {
        if (u.getPro() == null) {
            this.root = v;
        } else if (u.equals(u.getPro().getLeft())) {
            u.getPro().setLeft(v);
        } else {
            u.getPro().setRight(v);
        }
        if (v != null)
            v.setPro(u.getPro());
    }

    private void RBRemoveFixup(Node<K, V> x) {
        while (x != null && !x.equals(this.root) && x.getColor() == this.BLACK) {
            if (x.equals(x.getPro().getLeft())) {
                Node<K, V> w = x.getPro().getRight();
                if (w.getColor() == this.RED) {
                    w.setColor(this.BLACK);
                    x.getPro().setColor(this.RED);
                    this.leftRotate(x.getPro());
                    w = x.getPro().getRight();
                }

                if (w.getLeft().getColor() == this.BLACK && w.getRight().getColor() == this.BLACK) {
                    w.setColor(RED);
                    x = x.getPro();
                } else if (w.getRight().getColor() == BLACK) {
                    w.getLeft().setColor(BLACK);
                    w.setColor(RED);
                    this.rightRotate(w);
                    w = x.getPro().getRight();
                }

                w.setColor(x.getPro().getColor());
                x.getPro().setColor(BLACK);
                w.getRight().setColor(BLACK);
                this.leftRotate(x.getPro());
                x = root;
            } else {
                Node<K, V> w = x.getPro().getLeft();
                if (w.getColor() == this.RED) {
                    w.setColor(this.BLACK);
                    x.getPro().setColor(this.RED);
                    this.rightRotate(x.getPro());
                    w = x.getPro().getLeft();
                }

                if (w.getRight().getColor() == this.BLACK && w.getLeft().getColor() == this.BLACK) {
                    w.setColor(RED);
                    x = x.getPro();
                } else if (w.getLeft().getColor() == BLACK) {
                    w.getRight().setColor(BLACK);
                    w.setColor(RED);
                    this.leftRotate(x);
                    w = x.getPro().getLeft();
                }

                w.setColor(x.getPro().getColor());
                x.getPro().setColor(BLACK);
                w.getLeft().setColor(BLACK);
                this.rightRotate(x.getPro());
                x = root;
            }
        }
        if (x != null)
            x.setColor(BLACK);
    }


    public static class Node<K, V> implements Serializable, Comparable<Node<K, V>> {

        private int color;
        private K key;
        private V value;
        private Node<K, V> pro;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(int color, K key, V value, Node<K, V> pro, Node<K, V> left, Node<K, V> right) {
            this.color = color;
            this.key = key;
            this.value = value;
            this.pro = pro;
            this.left = left;
            this.right = right;
        }

        public Node() {
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getPro() {
            return pro;
        }

        public void setPro(Node pro) {
            this.pro = pro;
        }

        public Node<K, V> getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node<K, V> getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public int compareTo(Node<K, V> o) {
            return this.hashCode() - o.hashCode();
        }
    }
}
