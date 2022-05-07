package practice.topics.maths.probability;

import java.util.Random;
import java.io.*;

class SphericalSampling{
    public static void main(String[] args){
        Random rnd = new Random();
        try{
            PrintWriter writer = new PrintWriter("sphere.txt", "UTF-8");
            for(int i = 0; i < 1000; i++){
                double x = rnd.nextGaussian();
                double y = rnd.nextGaussian();
                double z = rnd.nextGaussian();
                double r = Math.sqrt(x*x + y*y + z*z);
                writer.println(x/r + " " + y/r + " " + z/r);
            }            
        }catch (Exception e) {
               e.printStackTrace(System.out);
        }
    }
}