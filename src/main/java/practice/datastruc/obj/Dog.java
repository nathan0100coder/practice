package practice.datastruc.obj;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author shiLong
 * @date 8/20/2021
 * @desc dog类
 */
@Data
@Builder
public class Dog implements Serializable {
    private String age;
    private String name;
    private String gender;
    public Dog(){}

    public Dog(String age, String name, String gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }



    public void run(){
        System.out.println("dog run. ");
    }
}
