package practice.datastruc.obj;

/**
 * @Author Nathan Wang
 * @Date 2021/9/25 23:52
 * @Version 1.0
 */
public class Haski extends Dog {
    public static void main(String[] args) {
        Haski haski = new Haski("2","二哈","false",37,true);
        System.out.println(haski);
    }



    private int iq;
    private boolean beautiful;

    public Haski(int iq) {
        this.iq = iq;
    }

    public Haski(String age, String name, String gender, int iq, boolean beautiful) {
        super(age, name, gender);
        this.iq = iq;
        this.beautiful = beautiful;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("haski run.");
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public boolean isBeautiful() {
        return beautiful;
    }

    public void setBeautiful(boolean beautiful) {
        this.beautiful = beautiful;
    }

    @Override
    public String toString() {
        return "Haski{" +
                "iq=" + iq +
                ", beautiful=" + beautiful +
                '}';
    }
}
