package practice.topics.maths.number_theory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 诗龙
 * @date 8/18/2021
 * @desc
 */
public class Count_Arithmetic_series {
    public static void main(String[] args) {
        int[] arr={2,3,5,7,11,13,17,31};
        System.out.println("count_Arithmetic_series(arr) = " + count_Arithmetic_series(arr));

    }

    //PI=(1 – 1/3 + 1/5 – 1/7 + …)*4
    //保留7位小数
    //1/2+1/3+1/5+1/7+1/11+...
    //等差数列7n+3中前1000个素数
    static  int count_Arithmetic_series(int[] nums) {
        int n = nums.length;
        // 每个 f[i] 均为哈希表，哈希表键值对为 {d : cnt}
        // d : 子序列差值
        // cnt : 以 nums[i] 为结尾，且差值为 d 的子序列数量
        List<Map<Long, Integer>> f = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Map<Long, Integer> cur = new HashMap<>();
            for (int j = 0; j < i; j++) {
                Long d = (long) nums[i] - nums[j];
                Map<Long, Integer> prev = f.get(j);
                int cnt = cur.getOrDefault(d, 0);
                cnt += prev.getOrDefault(d, 0);
                cnt ++;
                cur.put(d, cnt);
            }
            f.add(cur);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Map<Long, Integer> cur = f.get(i);
            for (Long key : cur.keySet()) {
                ans += cur.get(key);
            }
        }
        int a1 = 0, an = n - 1;
        int cnt = (a1 + an) * n / 2;
        return ans - cnt;
    }
//    static  string countOfAtoms(string formula) {
//        string res = "";
//        int pos = 0;
//        map<string, int> m = parse(formula, pos);
//        for (auto a : m) {
//            res += a.first + (a.second == 1 ? "" : to_string(a.second));
//        }
//        return res;
//    }
//    static map<string, int> parse(string& str, int& pos) {
//        map<string, int> res;
//        while (pos < str.size()) {
//            if (str[pos] == '(') {
//                ++pos;
//                for (auto a : parse(str, pos)) res[a.first] += a.second;
//            } else if (str[pos] == ')') {
//                int i = ++pos;
//                while (pos < str.size() && isdigit(str[pos])) ++pos;
//                string multipleStr = str.substr(i, pos - i);
//                int multiple = multipleStr.empty() ? 1 : stoi(multipleStr);
//                for (auto a : res) res[a.first] *= multiple;
//                return res;
//            } else {
//                int i = pos++;
//                while (pos < str.size() && islower(str[pos])) ++pos;
//                string elem = str.substr(i, pos - i);
//                i = pos;
//                while (pos < str.size() && isdigit(str[pos])) ++pos;
//                string cnt = str.substr(i, pos - i);
//                res[elem] += cnt.empty() ? 1 : stoi(cnt);
//            }
//        }
//        return res;
//    }




}
