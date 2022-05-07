package practice.datastruc.list;

public class Loop_List<E> {
    private Node dummyHead;
    private int size;

    public Loop_List() {
        dummyHead = new Node(null, null, null);
        size = 0;
    }

    /**
     * 获取链表容量
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }


    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed.Illegal index");
        }
        /*if (index == size) {
            Node prevNode = dummyHead;
            for (int i = 0; i < index; i++) {
                prevNode = prevNode.next;
            }
 
        }*/

        Node prevNode = dummyHead;
        if (prevNode.next == null) {
            Node newNode = new Node(e, dummyHead, dummyHead);
            dummyHead.next = newNode;
            dummyHead.prev = newNode;
        } else {
            for (int i = 0; i < index; i++) {
                prevNode = prevNode.next;
            }
            Node nextNode = prevNode.next;
            Node newNode = new Node(e, prevNode, nextNode);
            prevNode.next = newNode;
            nextNode.prev = newNode;
        }

        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获得链表index（0-based）位置的元素
     * 链表中不常用
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed.Illegal index");
        }
        Node curNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改链表index（0-based）位置的元素
     * 链表中不常用
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed.Illegal index");
        }
        Node curNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        curNode.e = e;
    }

    /**
     * 查找链表是否存在元素e
     */
    public boolean contains(E e) {
        Node curNode = dummyHead.next;
        while (curNode != null && curNode != dummyHead) {
            if (curNode.e == e) {
                return true;
            } else {
                curNode = curNode.next;
            }
        }
        return false;
    }

    /**
     * 删除索引位置的元素
     *
     * @param index
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Delete failed.Illegal index");
        }

        Node prevNode = dummyHead;
        for (int i = 0; i < index; i++) {
            prevNode = prevNode.next;
        }
        Node delNode = prevNode.next;
        Node nextNode = delNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        E ret = delNode.e;
        delNode = null;
        size--;
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        Node curNode = dummyHead.next;
        while (curNode != null && curNode != dummyHead) {

            if (curNode.e == e) {
                Node prevNode = curNode.prev;
                Node nextNode = curNode.next;
                nextNode.prev = prevNode;
                prevNode.next = nextNode;
            }
            curNode = curNode.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node curNode = dummyHead.next;
        while (curNode != null && curNode != dummyHead) {
            res.append(curNode.e + "->");
            curNode = curNode.next;
        }

        res.append("NULL");
        return res.toString();
    }
    /**
     * 节点类
     * 私有的内部类
     */
    private class Node {
        public E e;
        public Node prev;
        public Node next;

        public Node(E e, Node prev, Node next) {
            this.e = e;
            this.prev = prev;
            this.next = next;
        }

        public Node(E e) {
            this(e, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
}