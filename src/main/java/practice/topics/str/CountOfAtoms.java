package practice.topics.str;

import java.util.Stack;
import java.util.TreeMap;

/**
 * @author shiLong
 * @date 8/14/2021
 * @desc 化学分子式解析
 */
public class CountOfAtoms {
    public static void main(String[] args) {
        String formula="Ca(OH)2";
        System.out.println("countOfAtoms(formula) = " + countOfAtoms(formula));

    }
    static  String countOfAtoms(String formula) {
        Stack<TreeMap<String,Integer>> stack = new Stack<>();
        //curMap对字符串进行遍历
        TreeMap<String , Integer> curMap = new TreeMap<>();
        int len = formula.length();
        for(int i=0 ; i<len ;){
            char c = formula.charAt(i);
            if(c=='('){
                stack.push(curMap);
                //新建map对括号里面字符串进行操作
                curMap = new TreeMap<>();
                i++;
            }else if(c==')'){
                //两个treemap合并
                //当前treemap保存下来
                TreeMap<String , Integer> temp = curMap;
                curMap = stack.pop();
                i++;
                int istart = i;
                int quantity = 1;
                while(i<len && Character.isDigit(formula.charAt(i))) i++;
                if(istart<i){
                    quantity = Integer.parseInt(formula.substring(istart,i));
                }
                for(String name : temp.keySet()){
                    curMap.put(name , curMap.getOrDefault(name,0) + temp.get(name)*quantity);
                }
            }else{
                int istart = i;
                i++;
                while(i<len && Character.isLowerCase(formula.charAt(i))) i++;
                String name = formula.substring(istart,i);
                istart = i;
                int quantity = 1;
                while(i<len && Character.isDigit(formula.charAt(i))) i++;
                if(istart<i){
                    quantity = Integer.parseInt(formula.substring(istart,i));
                }
                curMap.put(name , curMap.getOrDefault(name,0) + quantity);
            }
        }
        StringBuilder sb =new StringBuilder();
        for(String name : curMap.keySet()){
            int quantity = curMap.get(name);
            sb.append(name);
            if(quantity>1){
                sb.append(quantity);
            }
        }
        return sb.toString();
    }
}
