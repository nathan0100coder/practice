
package practice.methods.divideAndConque;

/**
 * @author bigyang
 * @date 2020/11/02
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }
 
    /**
     * 汉诺塔算法
     *
     * @param num 盘子个数
     * @param a   a位置
     * @param b   b位置
     * @param c   c位置
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有1个盘，直接从 a->c 即可
        if (num == 1) {
            System.out.println("第 1 个盘从 " + a + " -> " + c);
        } else {
            // 2、先把最上面的盘1从 (1)a -> (1)b，中间借助到c位置
            hanoiTower(num - 1, a, c, b);
            // 3、再把盘2从 (2)a -> (2)c
            System.out.println("第 " + num + " 个盘从 " + a + " -> " + c);
            // 4、最后将b位置处的盘1移动到c位置，(1)b -> (1)c，中间借助到a位置
            hanoiTower(num - 1, b, a, c);
        }
    }
}