package practice.datastruc.list;

public class TestLoopLinkedList {
    public static void main(String[] args) {
        Loop_List<Integer> linkedList = new Loop_List<>();
 
        System.out.println("空链表：" + linkedList);
 
        linkedList.add(0,10);
        System.out.println("0索引处添加元素10：" + linkedList);
 
 
        for (int i = 2; i < 5; i++) {
            linkedList.add(1,i * 10);
            System.out.printf("1索引处添加元素%d：" + linkedList,i * 10);
            System.out.println();
        }
        for (int i = 5; i < 8; i++) {
            linkedList.add(3, i * 10);
            System.out.printf("3索引处添加元素%d：" + linkedList,i * 10);
            System.out.println();
        }
 
        linkedList.addFirst(80);
        System.out.printf("在链表头添加元素%d：" + linkedList,80);
        System.out.println();
 
        linkedList.addLast(90);
        System.out.printf("在链表尾添加元素%d：" + linkedList,90);
        System.out.println();
 
        System.out.printf("链表5索引处的元素为：%d%n",linkedList.get(5));
        System.out.printf("链表头的元素为：%d%n",linkedList.getFirst());
        System.out.printf("链表尾的元素为：%d%n",linkedList.getLast());
        System.out.println();
 
        System.out.println("链表中是否存在元素100：" + linkedList.contains(100));
        System.out.println("修改链表2索引位置元素为100");
        linkedList.set(2,100);
        System.out.println("修改链表2索引位置元素为100后：" + linkedList);
        System.out.println("链表中是否存在元素100：" + linkedList.contains(100));
 
        System.out.println("删除链表头的元素：" + linkedList.removeFirst());
        System.out.println("删除后：" + linkedList);
 
        System.out.println("删除链表尾的元素：" + linkedList.removeLast());
        System.out.println("删除后：" + linkedList);
 
        System.out.println();
 
        linkedList.removeElement(10);
        System.out.println("删除元素10之后：" + linkedList);
 
        linkedList.removeElement(20);
        System.out.println("删除元素20之后：" + linkedList);
    }
}