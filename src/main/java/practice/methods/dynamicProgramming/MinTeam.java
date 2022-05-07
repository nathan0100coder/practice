package practice.methods.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 诗龙
 * @date 8/18/2021
 * @desc 最少数量团队
 */
public class MinTeam {
    public static void main(String[] args) {

    }
    //输入：req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
    //输出：[0,2]
    //输入：req_skills = ["algorithms","math","java","reactjs","csharp","aws"],
    // people = [["algorithms","math","java"],["algorithms","math","reactjs"],
    // ["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
    //输出：[1,2]
    static int[] smallest_Sufficient_Team(String[] req_skills, List<List<String>> people) {
        int pNum = people.size();
        int skNum = req_skills.length;
        int[] dp = new int[1<<skNum];
        List<Integer>[] res = new ArrayList[1<<skNum];
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<skNum;i++){
            map.put(req_skills[i],1<<i);
        }
        for(int i=0;i<(1<<skNum);i++){
            dp[i]=-1;
            res[i] = new ArrayList<>();
        }
        dp[0]=0;
        for(int i=0;i<pNum;i++){
            int sk=0;
            for(String str:people.get(i))
                sk|=( map.get(str) == null ? 0 : map.get(str) );//获取当前人的技能类
            for(int st=0;st<(1<<skNum);st++){
                if(dp[st]==-1) continue;
                int newState = sk|st;
                if(dp[newState]==-1||dp[st]+1<dp[newState]){
                    dp[newState] = dp[st] + 1; //更新人员数量
                    res[newState].clear();
                    res[newState].addAll(res[st]);  //更新人员的编号
                    res[newState].add(i);
                }
            }
        }
        int[] result = new int[res[(1<<skNum)-1].size()];
        for(int i=0;i<result.length;i++){
            result[i] = res[(1<<skNum)-1].get(i);
        }
        return result;
    }
}
