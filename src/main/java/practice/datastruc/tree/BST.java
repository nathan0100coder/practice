package practice.datastruc.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BST<Value> {

    private Node root;

    private class Node{
        private String key;
        private Value value;
        private Node left;
        private Node right;

        Node(String key,Value value){
            this.key=key;
            this.value=value;
        }
    }

    //添加节点
    public void add(String key,Value value){
        root=add(root,key,value);
    }

    public Node add(Node x,String key,Value value){
        //在以x节点为根节点的树中插入节点
        if(x==null) return new Node(key,value);         //x为空，表示已经到达树的叶子节点
                                                        //则直接新键节点并挂在空链接上

        int cmp=key.compareTo(x.key);                   //比较将要插入的节点和节点x的key
        if(cmp>0)       x.right=add(x.right,key,value); //在x的右子树中插入节点
        else if(cmp<0)  x.left=add(x.left,key,value);   //在x的左子树中插入节点
        else            x.value=value;                  //树已经有key对应的节点，则直接更新其值
        return x;                                       //更新节点x;
    }

    //根据键来获取值
    public Value get(String key){
        return get(root,key);
    }

    private Value get(Node x,String key){
        //在以x节点为根节点的树中查找key
        //如果能找到，则返回键对应的值，如果找不到则返回null
        if(x==null) return null;                //如果当前节点为null，则直接返回null

        int cmp=key.compareTo(x.key);           //比较当前节点和待找节点的key
        if(cmp>0) return get(x.right,key);      //在当前节点的右子树中继续查找
        else if(cmp<0) return get(x.left,key);  //在当前节点的右子树中继续查找
        else return x.value;                    //两者的key相等，则查找命中
    }

    //获取键最小的那个节点
    public Node min(){
        return min(root);
    }

    private Node min(Node x){
        if(x.left==null) return x;
        return min(x.left);
    }

    //删除键最小的那个节点
    public void deleteMin(){
        if(root==null) return;
        root=deleteMin(root);
    }

    private Node deleteMin(Node x){
        if(x.left==null) x=x.right;
        else x.left=deleteMin(x.left);
        return x;
    }

    //获取键最大的那个节点
    public Node max(){
        return null;
    }

    //删除节点
    public void delete(String key){
        root=delete(root,key);
    }

    private Node delete(Node x,String key){

        if(x==null) return null;                    //未找到键值为key的节点
        int cmp=key.compareTo(x.key);
        if(cmp>0) x.right=delete(x.right,key);      //从x的左节点删除节点
        else if(cmp<0) x.left=delete(x.left,key);   //从x的右节点删除节点
        else{
            if(x.right==null) return x.left;
            if(x.left==null) return x.right;
            Node t=x;
            x=min(t.right);
            x.right=deleteMin(t.right);
            x.left=t.left;
        }
        return x;
    }

    //对二叉树进行中序遍历
    public List<Node> inorderTraverse(){

        List<Node> list=new ArrayList<>();
        inorderTraverse(root,list);
        return list;
    }

    private void inorderTraverse(Node x,List<Node> list){
        if(x==null) return;
        inorderTraverse(x.left,list);
        list.add(x);
        inorderTraverse(x.right,list);
    }

    public void print(){
        System.out.println("***************************************");
        print(root,0);
        System.out.println("***************************************");
    }

    private void print(Node x,int floor){
        if(x==null) return ;
        print(x.left,floor+1);

        for (int i = 0; i < floor; i++) {
            System.out.print("----");
        }
        System.out.println(x.key+" "+x.value);

        print(x.right,floor+1);
    }

    public static void main(String[] args) {
        BST<Integer> sibst = new BST<>();

        int i=0;
        String str;
        boolean flag=true;
        Scanner in = new Scanner(System.in);
        String[] words;

        while(flag){
            str=in.nextLine();
            if(str.equals("")) continue;
            words = str.split(" ");
            switch (words[0]){
                case "$exit": {
                    flag=false;
                    break;
                }
                case "$add":{
                    sibst.add(words[1],i++);
                    break;
                }
                case "$del":{
                    sibst.delete(words[1]);
                    break;
                }
                case "$show":{
                    sibst.print();
                    break;
                }
                default:{
                    System.out.println("错误命令");
                }
            }
        }
    }
}
