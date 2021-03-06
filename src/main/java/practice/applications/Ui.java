package practice.applications;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Created by hongjiayong on 16/4/10.
 */
public class Ui {
    public static JLabel[] labels = new JLabel[20];
    public static JComboBox[] up = new JComboBox[20];
    public static JComboBox[] down = new JComboBox[20];
    public static JButton[] forOne = new JButton[20];
    public static JButton[] forTwo = new JButton[20];
    public static JButton[] forThree = new JButton[20];
    public static JButton[] forFour = new JButton[20];
    public static JButton[] forFive = new JButton[20];
    public static TextArea logs = new TextArea();
    public static Elevator one;
    public static Elevator two;
    public static Elevator three;
    public static Elevator four;
    public static Elevator five;

    public static ArrayList<Elevator> elevators = new ArrayList<Elevator>();
    public static ArrayList[][] queue = new ArrayList[20][2];
    public static boolean queLock[][] = new boolean[20][2];

    private static String helpMessage = "<html><h2>使用说明</h2><ul><li>位于左侧的多选按钮可以选择要去的楼层，有需求的楼层会显示" +
            "绿色</li><li>红色按钮代表电梯的位置，电梯初始状态为“－”，在上升和下降时分别为“UP”和“DOWN”</li><li>电梯在下客时会呈现蓝" +
            "色，并滞留一段时间</li><li>第一排按钮表示电梯状态,点击可使电梯进入紧急状态使电梯暂停,再次点击即可恢复正常</li></ul><h2>注" +
            "意事项</h2><ul><li>上升的电梯在到达任何楼层时会一次性带走所有上升的乘客</li><li>下降" +
            "的电梯在到达任何楼层时会一次性带走所有下降的乘客</li></ul><h2>基本算法</h2><h3>乘客</h3><ul><li>在乘客提出需求时检索" +
            "当前所有电梯状态。优先考虑离乘客比较近且将要到达该楼层的移动电梯</li><li>在没有符合条件的移动电梯的情况下，调动离乘客最近" +
            "的静止电梯</li></ul><h3>电梯</h3><ul><li>电梯初始状态均为静止，在响应请求时会被设置为上升或下降状态</li><li>电梯自检" +
            "状态来变更行为</li><li>电梯每到一个楼层都会自检下客队列，判断是否下客</li><li>电梯每到一个楼层都会自检当前楼层对应自己状" +
            "态的上客队列，判断是否需要在该楼层停下来载客</li><li>当电梯在某一楼层下光乘客，并且该楼层有乘客要上电梯，电梯将自行变更状态" +
            "载客运行</li></ul></html>";


    public static void init() {
        // 应答队列控制锁初始化
        for (int i = 0; i < 20; i++){
            queLock[i][0] = true;
            queLock[i][1] = true;
        }
        // 楼号初始化
        for (int i = 0; i < 20; i++){
            labels[i] = new JLabel(String.valueOf(i + 1));
            labels[i].setBackground(Color.WHITE);
            labels[i].setOpaque(true);
        }
        // 应答队列初始化
        for (int i = 0; i < 20; i++) {
            // 上升等待队列
            queue[i][0] = new ArrayList<Integer>();
            // 下降等待队列
            queue[i][1] = new ArrayList<Integer>();
        }
        // 上升选择键初始化
        for (int i = 0; i < 20; i++) {
            up[i] = new JComboBox();
            up[i].addItem("-");
            for(int k = i + 2; k <= 20; k++){
                up[i].addItem(String.valueOf(k));
            }
            final int finalI = i;
            up[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (ItemEvent.SELECTED == e.getStateChange() && !up[finalI].getSelectedItem().toString().equals("-")) {
                        queue[finalI][0].add(Integer.parseInt(up[finalI].getSelectedItem().toString()));
                        labels[finalI].setBackground(Color.GREEN);
                        up[finalI].setSelectedIndex(0);
                        logs.append("第" + (finalI + 1) + "楼有人要去"
                                + queue[finalI][0] + "楼\n");
                    }
                }
            });
        }
        // 下降选择键初始化
        for (int i = 0; i < 20; i++) {
            down[i] = new JComboBox();
            down[i].addItem("-");
            for(int k = i; k > 0; k--){
                down[i].addItem(String.valueOf(k));
            }
            final int finalI = i;
            down[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (ItemEvent.SELECTED == e.getStateChange() && !down[finalI].getSelectedItem().toString().equals("-")) {
                        queue[finalI][1].add(Integer.parseInt(down[finalI].getSelectedItem().toString()));
                        labels[finalI].setBackground(Color.GREEN);
                        down[finalI].setSelectedIndex(0);
                        logs.append("第" + (finalI + 1) + "楼有人要去"
                                + queue[finalI][1] + "楼\n");
                    }
                }
            });
        }
        // 电梯1初始化
        for (int i = 0; i < 20; i++){
            forOne[i] = new JButton("-");
            forOne[i].setOpaque(true);
            forOne[i].setBackground(Color.WHITE);
        }
        forOne[0].setBackground(Color.RED);
        forOne[0].setText("正常");
        forOne[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(forOne[0].getText().equals("正常")){
                    forOne[0].setText("异常");
                    forOne[0].setBackground(Color.ORANGE);
                    one.setCurrentState(-2);
                    logs.append("电梯1发生故障,停止运行!\n");
                }else {
                    forOne[0].setText("正常");
                    forOne[0].setBackground(Color.WHITE);
                    one.setCurrentState(2);
                    logs.append("电梯1恢复运作!\n");
                }
            }
        });
        // 电梯2初始化
        for (int i = 0; i < 20; i++){
            forTwo[i] = new JButton("-");
            forTwo[i].setOpaque(true);
            forTwo[i].setBackground(Color.WHITE);
        }
        forTwo[0].setBackground(Color.RED);
        forTwo[0].setText("正常");
        forTwo[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(forTwo[0].getText().equals("正常")){
                    forTwo[0].setText("异常");
                    forTwo[0].setBackground(Color.ORANGE);
                    two.setCurrentState(-2);
                    logs.append("电梯2发生故障,停止运行!\n");
                }else {
                    forTwo[0].setText("正常");
                    forTwo[0].setBackground(Color.WHITE);
                    two.setCurrentState(2);
                    logs.append("电梯2恢复运作!\n");
                }
            }
        });
        // 电梯3初始化
        for (int i = 0; i < 20; i++){
            forThree[i] = new JButton("-");
            forThree[i].setOpaque(true);
            forThree[i].setBackground(Color.WHITE);
        }
        forThree[0].setBackground(Color.RED);
        forThree[0].setText("正常");
        forThree[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(forThree[0].getText().equals("正常")){
                    forThree[0].setText("异常");
                    forThree[0].setBackground(Color.ORANGE);
                    three.setCurrentState(-2);
                    logs.append("电梯3发生故障,停止运行!\n");
                }else {
                    forThree[0].setText("正常");
                    forThree[0].setBackground(Color.WHITE);
                    three.setCurrentState(2);
                    logs.append("电梯3恢复运作!\n");
                }
            }
        });
        // 电梯4初始化
        for (int i = 0; i < 20; i++){
            forFour[i] = new JButton("-");
            forFour[i].setOpaque(true);
            forFour[i].setBackground(Color.WHITE);
        }
        forFour[0].setBackground(Color.RED);
        forFour[0].setText("正常");
        forFour[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(forFour[0].getText().equals("正常")){
                    forFour[0].setText("异常");
                    forFour[0].setBackground(Color.ORANGE);
                    four.setCurrentState(-2);
                    logs.append("电梯4发生故障,停止运行!\n");
                }else {
                    forFour[0].setText("正常");
                    forFour[0].setBackground(Color.WHITE);
                    four.setCurrentState(2);
                    logs.append("电梯4恢复运作!\n");
                }
            }
        });
        // 电梯5初始化
        for (int i = 0; i < 20; i++){
            forFive[i] = new JButton("-");
            forFive[i].setOpaque(true);
            forFive[i].setBackground(Color.WHITE);
        }
        forFive[0].setBackground(Color.RED);
        forFive[0].setText("正常");
        forFive[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(forFive[0].getText().equals("正常")){
                    forFive[0].setText("异常");
                    forFive[0].setBackground(Color.ORANGE);
                    five.setCurrentState(-2);
                    logs.append("电梯5发生故障,停止运行!\n");
                }else {
                    forFive[0].setText("正常");
                    forFive[0].setBackground(Color.WHITE);
                    five.setCurrentState(2);
                    logs.append("电梯5恢复运作!\n");
                }
            }
        });

        JFrame frame = new JFrame("电梯");
        frame.setLayout(new GridLayout(1, 2));
        GridLayout grid = new GridLayout(21, 8);
        Container c = new Container();
        c.setLayout(grid);
        // 标签
        c.add(new JLabel("楼层"));
        c.add(new JLabel("上"));
        c.add(new JLabel("下"));
        c.add(new JLabel("电梯1"));
        c.add(new JLabel("电梯2"));
        c.add(new JLabel("电梯3"));
        c.add(new JLabel("电梯4"));
        c.add(new JLabel("电梯5"));
        // 按钮
        for (int i = 20; i > 0; i--){
            c.add(labels[i - 1]);
            c.add(up[i - 1]);
            c.add(down[i - 1]);
            for (int k = 0; k < 5; k++) {
                c.add(forOne[i - 1]);
                c.add(forTwo[i - 1]);
                c.add(forThree[i - 1]);
                c.add(forFour[i - 1]);
                c.add(forFive[i - 1]);
            }
        }

        logs.setEditable(false);
        logs.setFont(new Font("黑体",Font.BOLD,32));
        frame.add(c);
        JScrollPane pane = new JScrollPane(logs);
        frame.add(pane);

        frame.setSize(new Dimension(2000, 1500));
        frame.setVisible(true);

        // 初始化电梯
        one = new Elevator(1, 0, forOne);
        elevators.add(one);
        two = new Elevator(2, 0, forTwo);
        elevators.add(two);
        three = new Elevator(3, 0, forThree);
        elevators.add(three);
        four = new Elevator(4, 0, forFour);
        elevators.add(four);
        five = new Elevator(5, 0, forFive);
        elevators.add(five);

        // help提示框
        JOptionPane.showMessageDialog(null, helpMessage, "使用说明", JOptionPane.DEFAULT_OPTION);
    }

    static class lightManger extends Thread{
        lightManger(){
            start();
        }
        @Override
        public void run(){
            while (true) {
                for (int i = 0; i < 20; i++) {
                    if (queue[i][0].isEmpty() && queue[i][1].isEmpty()) {
                        labels[i].setBackground(Color.WHITE);
                    }
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class elevatorManager extends Thread{
        elevatorManager(){
            start();
        }

        public void adjust(int index, int i) throws InterruptedException {
            if (elevators.get(index).getCurrentFloor()< i){
                elevators.get(index).setCurrentState(1);
                elevators.get(index).addUp(i);
                elevators.get(index).setMaxUp(i);
                logs.append("电梯" + (index + 1) + "启动 上升\n");
                Thread.sleep(500);
                return;
            }
            // 最优停滞电梯位于当前楼层上方
            if (elevators.get(index).getCurrentFloor()> i){
                elevators.get(index).setCurrentState(-1);
                elevators.get(index).addDown(i);
                elevators.get(index).setMinDown(i);
                logs.append("电梯" + (index + 1) + "启动 下降\n");
                Thread.sleep(500);
                return;
            }
            // 最优停滞电梯位于当前楼层
            if (elevators.get(index).getCurrentFloor() == i){
                elevators.get(index).setCurrentState(1);
                logs.append("电梯" + (index + 1) + "启动\n");
                Thread.sleep(500);
                return;
            }
        }

        @Override
        public void run(){
            while (true){
                for (int i = 0; i < 20; i++){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 监测上升队列
//                    System.out.println(queLock[i][0]);
                    while (!queLock[i][0]) {
                        ;
                    }
                    if (!queue[i][0].isEmpty()){
                        int index = -1,  distance = 1000000;
                        for (int k = 0; k < 5; k++){
                            if (elevators.get(k).getCurrentState() == 0 && !queue[i][0].isEmpty()){
                                if (Math.abs(elevators.get(k).getCurrentFloor() - i) < distance){
                                    index = k;
                                    distance = Math.abs(elevators.get(k).getCurrentFloor() - i);
                                }
                            }
                            if (elevators.get(k).getCurrentFloor() >= i && elevators.get(k).getCurrentState() == -1
                                    && elevators.get(k).downMin() >= i){
                                if (Math.abs(elevators.get(k).getCurrentFloor() - i) < distance){
                                    index = -1;
                                    distance = Math.abs(elevators.get(k).getCurrentFloor() - i);
                                }
                            }
                            if (elevators.get(k).getCurrentFloor() <= i && elevators.get(k).getCurrentState() == 1
                                    && elevators.get(k).upMax() >= i){
                                if (Math.abs(elevators.get(k).getCurrentFloor() - i) < distance){
                                    index = -1;
                                    distance = Math.abs(elevators.get(k).getCurrentFloor() - i);
                                }
                            }
                        }
                        System.out.println(i + ":" + index);
                        if (index != -1 && !queue[i][0].isEmpty()){
                            try {
                                adjust(index, i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                for (int i = 0; i < 20; i++){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 监测下降队列
//                    System.out.println(queLock[i][1]);
                    while (!queLock[i][1]);
                    if (!queue[i][1].isEmpty()){
                        int index = -1,  distance = 1000000;
                        for (int k = 0; k < 5; k++){
                            if (elevators.get(k).getCurrentState() == 0 && !queue[i][1].isEmpty()){
                                if (Math.abs(elevators.get(k).getCurrentFloor() - i) < distance){
                                    index = k;
                                    distance = Math.abs(elevators.get(k).getCurrentFloor() - i);
                                }
                            }
                            if (elevators.get(k).getCurrentFloor() >= i && elevators.get(k).getCurrentState() == -1
                                    && elevators.get(k).downMin() <= i){
                                if (Math.abs(elevators.get(k).getCurrentFloor() - i) < distance){
                                    index = -1;
                                    distance = Math.abs(elevators.get(k).getCurrentFloor() - i);
                                }
                            }
                            if (elevators.get(k).getCurrentFloor() <= i && elevators.get(k).getCurrentState() == 1
                                    && elevators.get(k).upMax() <= i){
                                if (Math.abs(elevators.get(k).getCurrentFloor() - i) < distance){
                                    index = -1;
                                    distance = Math.abs(elevators.get(k).getCurrentFloor() - i);
                                }
                            }
                        }
//                        System.out.println(i + ":" + index);
                        if (index != -1 && !queue[i][1].isEmpty()){
                            try {
                                adjust(index, i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        init();
        // 初始化灯管理
        lightManger lightmanger = new lightManger();
        elevatorManager elevatormanager = new elevatorManager();
        one.start();
        two.start();
        three.start();
        four.start();
        five.start();
    }
}
