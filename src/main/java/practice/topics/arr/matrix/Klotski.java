package practice.topics.arr.matrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Klotski {
    /**
     * 数字华容道的大小
     */
    private Integer size;
    /**
     * 使用一维数组表示数字华容道
     */
    private int[] klotski;
    /**
     * key:数字
     * value：数字对应的索引
     */
    Map<Integer, Integer> numberIndex = new HashMap<>();
    /**
     * key:数字
     * value：数字对应坐标位置
     */
    Map<Integer, int[]> numberCoordinate = new HashMap<>();


    public Klotski() {
    }

    public Klotski(Integer size) {
        this.size = size;
        init();
    }

    /**
     * 初始化数字华容道
     * 在排序好的数字华容道的基础上随机移动300次
     * 保证生成的华容道是有解的
     */
    public void init() {
        int totalSize = (int) Math.pow(size, 2);
        klotski = new int[totalSize];

        //初始化成员属性
        for (int i = 1; i < totalSize; i++) {
            klotski[i - 1] = i;
            numberIndex.put(i, i - 1);
            numberCoordinate.put(i, getCoordinate(i));
        }
        numberIndex.put(0, totalSize - 1);
        numberCoordinate.put(0, getCoordinate(0));

        /*
         * 初始化随机移动300次
         * 并保证生成的数字华容道不是已排序好的
         */
        while (check()) {
            for (int i = 0; i < 300; i++) {
                Random random = new Random();
                move(random.nextInt((int) Math.pow(size, 2)));
            }
        }

    }

    /**
     * 移动某一个数字
     *
     * @param number 数字
     * @return 是否移动成功
     */
    public Boolean move(Integer number) {
        /*
         *如果要移动的数字是0，即空格，则返回失败
         */
        if (number == 0) {
            return false;
        }
        int[] zeroCoordinate = numberCoordinate.get(0);
        int[] currentCoordinate = numberCoordinate.get(number);
        /*
         * 如果输入的数字，不在数字华容道的范围内，则返回失败
         */
        if (zeroCoordinate == null || currentCoordinate == null) {
            return false;
        }

        if (zeroCoordinate[0] != currentCoordinate[0] && zeroCoordinate[1] != currentCoordinate[1]) {
            return false;
        }
        /*
         * 点击的数字并不在0即空格的旁边
         * 则移动点击数字到空格之间的所有数字
         * 如果点击数字跟空格不在同一个横轴或纵轴上，则移动失败
         */
        if (zeroCoordinate[0] == currentCoordinate[0]) {
            if (zeroCoordinate[1] > currentCoordinate[1]) {
                for (int i = zeroCoordinate[1] - 1; i >= currentCoordinate[1]; i--) {
                    int moveNumber = klotski[coordinate2Index(new int[]{zeroCoordinate[0], i})];
                    move(0, moveNumber);
                }
            }
            if (zeroCoordinate[1] < currentCoordinate[1]) {
                for (int i = zeroCoordinate[1] + 1; i <= currentCoordinate[1]; i++) {
                    int moveNumber = klotski[coordinate2Index(new int[]{zeroCoordinate[0], i})];
                    move(0, moveNumber);
                }
            }
        }

        if (zeroCoordinate[1] == currentCoordinate[1]) {
            if (zeroCoordinate[0] > currentCoordinate[0]) {
                for (int i = zeroCoordinate[0] - 1; i >= currentCoordinate[0]; i--) {
                    int moveNumber = klotski[coordinate2Index(new int[]{i, zeroCoordinate[1]})];
                    move(0, moveNumber);
                }
            }
            if (zeroCoordinate[0] < currentCoordinate[0]) {
                for (int i = zeroCoordinate[0] + 1; i <= currentCoordinate[0]; i++) {
                    int moveNumber = klotski[coordinate2Index(new int[]{i, zeroCoordinate[1]})];
                    move(0, moveNumber);
                }
            }
        }
        return true;
    }

    /**
     *  移动交互某两个相邻数字的位置
     *
     * @param number1 数字1 在这里就是0
     * @param number2 数字2 根0相邻的一个数字（上下左右）
     */
    private void move(Integer number1, Integer number2) {

        int indexNumber1 = numberIndex.get(number1);
        int indexNumber2 = numberIndex.get(number2);
        klotski[indexNumber1] = number2;
        klotski[indexNumber2] = number1;
        numberIndex.put(number1, indexNumber2);
        numberIndex.put(number2, indexNumber1);

        numberCoordinate.put(number1, getCoordinate(number1));
        numberCoordinate.put(number2, getCoordinate(number2));


    }

    /**
     * 将坐标数据转变为在数组中的索引下标
     *
     * @param coordinate 在二维数组中的坐标位置
     * @return 在一维数组中的索引值
     */
    private Integer coordinate2Index(int[] coordinate) {
        return (coordinate[0] - 1) * size + coordinate[1] - 1;
    }

    /**
     * 获取某个数字的坐标
     *
     * @param number 数字
     * @return 数字所在的位置表座
     */
    private int[] getCoordinate(Integer number) {
        int[] coordinate = new int[2];
        int index = numberIndex.get(number);
        coordinate[0] = (index / size) + 1;
        coordinate[1] = index % size + 1;
        return coordinate;
    }

    /**
     *  检查华容道是否已经完成
     *
     * @return 已完成或未完成
     */
    public Boolean check() {
        for (int i = 1; i < klotski.length; i++) {
            if (i != klotski[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印华容道的题面
     * @param str 提示语
     */
    public void println(String str) {
        for (int i = 0; i < klotski.length; i++) {
            if (i % size == 0) {
                System.out.println();
            }
            System.out.print(klotski[i] + "\t");
        }
        System.out.println();
        System.out.println(str);
        System.out.println("---------------");
    }


    public void setKlotski(int[] klotski) {
        this.klotski = klotski;
    }


    public static void main(String[] args) {
        System.out.println("请输入一个大于等于3的数字，用于初始化数字华容道游戏");
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();

        Klotski klotski = new Klotski(size);
        klotski.println("请开始你的表演");
        while (!klotski.check()) {
            int num = input.nextInt();
            klotski.move(num);
            if (klotski.check()) {
                klotski.println("恭喜你已逃出数字华容道");
                break;
            } else {
                klotski.println("还没完，请继续你的表演");
            }
        }
    }
}