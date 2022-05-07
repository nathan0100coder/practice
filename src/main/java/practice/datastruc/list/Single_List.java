package practice.datastruc.list;

import java.util.Objects;

/**
 * @author shiLong
 * @date 8/12/2021
 * @desc 单向链表
 */
public class Single_List {
    private Node head = new Node();
    public Node getHead() {
        return head;
    }
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * 向链表添加数据
     *
     * @param value 要添加的数据
     */
    public void add(int value) {
        //初始化要加入的节点
        Node newNode = new Node(value);
        //临时节点
        Node temp = head;

        // 找到尾节点
        while (temp.next != null) {
            temp = temp.next;
        }
        // 已经包括了头节点.next为null的情况了～
        temp.next = newNode;
    }

    /**
     * 遍历链表
     *
     * @param head 头节点
     */
    public void traverse(Node head) {
        //临时节点，从首节点开始
        Node temp = head.next;
        while (temp != null) {
            if (temp.data != null) {
                System.out.println(temp.data);
            }
            //继续下一个
            temp = temp.next;
        }
    }

    /**
     * 插入节点
     *
     * @param head  头指针
     * @param index 要插入的位置
     * @param value 要插入的值
     */
    public void insert(Node head, int index, int value) {
        //首先需要判断指定位置是否合法，
        if (index < 1 || index > length(head)) {
            System.out.println("插入位置不合法。");
            return;
        }
        //临时节点，从头节点开始
        Node temp = head;
        //记录遍历的当前位置
        int currentPos = 0;
        //初始化要插入的节点
        Node insertNode = new Node(value);
        while (temp.next != null) {
            //找到上一个节点的位置了
            if ((index - 1) == currentPos) {
                //temp表示的是上一个节点
                //将原本由上一个节点的指向交由插入的节点来指向
                insertNode.next = temp.next;
                //将上一个节点的指针域指向要插入的节点
                temp.next = insertNode;
                return;
            }
            currentPos++;
            temp = temp.next;
        }
    }

    /**
     * 获取链表的长度
     *
     * @param head 头指针
     */
    public int length(Node head) {
        int length = 0;
        //临时节点，从首节点开始
        Node temp = head.next;
        // 找到尾节点
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 根据位置删除节点
     *
     * @param head  头指针
     * @param index 要删除的位置
     */
    public void delete(Node head, int index) {
        //首先需要判断指定位置是否合法，
        if (index < 1 || index > length(head) + 1) {
            System.out.println("删除位置不合法。");
            return;
        }
        //临时节点，从头节点开始
        Node temp = head;
        //记录遍历的当前位置
        int currentPos = 0;
        while (temp.next != null) {
            //找到上一个节点的位置了
            if ((index - 1) == currentPos) {
                //temp表示的是上一个节点
                //temp.next表示的是想要删除的节点
                //将想要删除的节点存储一下
                Node deleteNode = temp.next;
                //想要删除节点的下一个节点交由上一个节点来控制
                temp.next = deleteNode.next;
                //Java会回收它，设置不设置为null应该没多大意义了(个人觉得,如果不对请指出哦～)
                return;
            }
            currentPos++;
            temp = temp.next;
        }
    }

    public void list_sort(Node head) {
        Node cur;
        Node next;
        for (cur = head.next; cur.next != null; cur = cur.next) {//n-1趟   控制趟次循环
            for (next = head.next; next.next != null; next = next.next) {//n-1次
                if (next.data > next.next.data) {
                    next.data ^= next.next.data;
                    next.next.data ^= next.data;
                    next.data ^= next.next.data;
                }
            }
        }
    }


    public Node last_Kth_node(Node head, int k) {
        if (k < 1 || k > length(head))
            return null;

        Node p1 = head;
        Node p2 = head;
        // p2比怕p1快k个节点
        for (int i = 0; i < k - 1; i++)
            p2 = p2.next;
        // 只要p2为null，那么p1就是倒数第k个节点了
        while (p2.next != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        return p1;
    }

    //外循环当前遍历的结点为p，内循环从表头开始遍历至p
    public void delete_dup(Node head) {
        Node p = head;
        while (p != null) {
            Node q = head;
            while (q.next != p && q.next != null){

                if (Objects.equals(q.next.data, p.data)) {
                    q.next = q.next.next;
                }
                else{
                    q = q.next;
                }

            }
            p = p.next;
        }
    }

    /**
     * 查询单链表的中间节点
     */
    public Node get_mid(Node head) {

        Node p1 = head;
        Node p2 = head;
        // 一个走一步，一个走两步，直到为null，走一步的到达的就是中间节点
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;
    }

    /**
     * 通过递归从尾到头输出单链表
     * @param head 头节点
     */
    public void reversely_print(Node head) {
        if (head != null) {
            reversely_print(head.next);//此处递归
            if (head.data != null) {
                System.out.println(head.data);
            }
        }
    }


    public Node reverseList(Node head) {
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }









    // 翻转完，使用下面的代码进行遍历吧：
    public  void traverse_Reverse(Node head) {
        //临时节点，从首节点开始
        Node temp = head;

        while (temp != null) {

            if (temp.data != null) {
                System.out.println("关注公众号Java3y：" + temp.data);
            }

            //继续下一个
            temp = temp.next;
        }
    }

    public static class Node {
        //数据域
        public Integer data;
        //指针域，指向下一个节点
        public Node next;

        public Node() {
        }
        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
        public Integer getData() {
            return data;
        }
        public void setData(Integer data) {
            this.data = data;
        }
    }

}
