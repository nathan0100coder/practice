package practice.design_pattern;

/**
 * @author shiLong
 * @version 1.0
 * @desc dog run实现
 * @date 2022/3/22
 */
public class DogRun implements Run {
    @Override
    public void run() {
        System.out.println("dog run... ");
    }


    void test(){
        System.out.println("dog test... ");

    }

}
