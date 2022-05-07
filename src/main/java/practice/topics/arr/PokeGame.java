package practice.topics.arr;
 
import java.util.*;
 
public class PokeGame {
    public static void main(String[] args) {
        //键是编号,值是牌
        HashMap<Integer,String> hashMap=new HashMap<Integer, String>();
 
        //花色
        String[] colors={"♥","♣","♦","♠"};
        //点数
        String[] points={"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
 
        ArrayList<Integer> arr=new ArrayList<Integer>();
        int index=0;
        for(String point:points){
            for(String color:colors){
                String poker=color+point;
                arr.add(index);
                hashMap.put(index,poker);
                index++;
            }
        }
        arr.add(index);
        hashMap.put(index,"小王");
        index++;
        arr.add(index);
        hashMap.put(index,"大王");
 
        //检验是否正确
//        System.out.println(arr);
//        System.out.println(hashMap);
 
        //洗牌
        Collections.shuffle(arr);
        //检验是否洗牌成功
        //System.out.println(arr);
 
        TreeSet<Integer> dparr=new TreeSet<Integer>();
        TreeSet<Integer> lcyarr=new TreeSet<Integer>();
        TreeSet<Integer> yhyarr=new TreeSet<Integer>();
        TreeSet<Integer> ysbarr=new TreeSet<Integer>();
 
        //给他们发牌,注意:发的是索引,然后再去hashSet里面调
        for(int i=0;i<arr.size();i++){
            int x=arr.get(i);
            if(i>=arr.size()-3){
                dparr.add(x);
            }else if(i%3==0){
                lcyarr.add(x);
            }else if(i%3==1){
                yhyarr.add(x);
            }else if(i%3==2){
                ysbarr.add(x);
            }
        }
 
        //检验发牌是否成功,切是否为自然排序:是
//        System.out.println(dparr);
//        System.out.println(lcyarr);
//        System.out.println(yhyarr);
//        System.out.println(ysbarr);
 
        //看牌
        lookPokers("eve",lcyarr,hashMap);
        lookPokers("lisa",yhyarr,hashMap);
        lookPokers("ben",ysbarr,hashMap);
        lookPokers("Community_Cards",dparr,hashMap);
 
    }
    public static void lookPokers(String name,TreeSet<Integer> arr,HashMap<Integer,String> hs){
        System.out.print(name+": ");
        for(Integer a:arr){
            String poker=hs.get(a);
            System.out.print(poker+" ");
        }
        System.out.println();
    }
}