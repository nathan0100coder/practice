package practice.topics.arr;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

/**
 * 该类继承 JFrame 类。
 * 该类实现了 ActionListener 里对不用点击事件的反馈。
 * 该类提供4个选项供玩家选择。
 * 这4个选项分别是 "初级"，"中级"，"高级" 和 "自定义"。
 * 在点击 "New Game" 按钮之后，菜单窗口自动关闭。
 * @author Hephaest
 * @since 3/21/2019 8:41 PM
 */
public class Menu extends JFrame implements ActionListener
{
    private JButton start;
    private JRadioButton beginner, intermediate, advanced, custom;
    private JTextField width, height, mines;

    /**
     * 创建一个给定标题的菜单。
     * @param title 菜单上的标题。
     */
    public Menu(String title)
    {
        // 设置菜单标题。
        setTitle(title);

        // 创建菜单子标题。
        JLabel subtitle = new JLabel("Difficulty");
        subtitle.setBounds(100,10,100,20);
        add(subtitle);

        // 创建 "初级" 选择按钮。
        beginner = new JRadioButton("Beginner");
        beginner.setBounds(40,40,150,20);
        add(beginner);

        // 设置 "初级" 选择的描述。
        JLabel bDescFirstLine = new JLabel("10 mines");
        bDescFirstLine.setBounds(70,60,100,20);
        JLabel bDescSecondLine = new JLabel("10 x 10 tile grid");
        bDescSecondLine.setBounds(70,80,100,20);
        add(bDescFirstLine);
        add(bDescSecondLine);

        // 创建 "中级" 选择按钮。
        intermediate=new JRadioButton("Intermediate");
        intermediate.setBounds(40,100,150,20);
        add(intermediate);

        // 设置 "中级" 选择的描述。
        JLabel iDescFirstLine = new JLabel("40 mines");
        iDescFirstLine.setBounds(70,120,100,20);
        JLabel iDescSecondLine = new JLabel("16 x 16 tile grid");
        iDescSecondLine.setBounds(70,140,100,20);
        add(iDescFirstLine);
        add(iDescSecondLine);

        // 创建 "高级" 选择按钮。
        advanced=new JRadioButton("Advanced");
        advanced.setBounds(40,160,160,20);
        add(advanced);

        // 设置 "高级" 选择的描述。
        JLabel aDescFirstLine = new JLabel("100 mines");
        aDescFirstLine.setBounds(70,180,100,20);
        JLabel aDescSecondLine = new JLabel("30 x 25 tile grid");
        aDescSecondLine.setBounds(70,200,100,20);
        add(aDescFirstLine);
        add(aDescSecondLine);

        // 创建 "自定义" 选择按钮。
        custom = new JRadioButton("Custom");
        custom.setBounds(40,220,100,20);
        add(custom);

        // 设置 "自定义" 选择的描述。
        JLabel widthLabel = new JLabel("Width (10-30):");
        widthLabel.setBounds(70,240,80,20);
        add(widthLabel);

        width = new JTextField();
        width.setBounds(170,240,40,20);
        add(width);

        JLabel heightLabel = new JLabel("height (10-25):");
        heightLabel.setBounds(70,260,90,20);
        add(heightLabel);

        height = new JTextField();
        height.setBounds(170,260,40,20);
        add(height);

        JLabel mineLabel = new JLabel("Mines (10-100):");
        mineLabel.setBounds(70,280,90,20);
        add(mineLabel);

        mines = new JTextField();
        mines.setBounds(170,280,40,20);
        add(mines);

        // 创建 "开始游戏" 选择按钮。
        start = new JButton("New Game");
        start.setBounds(80,320,100,20);
        add(start);

        // 初始化每个文本框的编辑状态。
        width.setEditable(false);
        height.setEditable(false);
        mines.setEditable(false);

        // 在每个按键上添加监听事件。
        custom.addActionListener(this);
        beginner.addActionListener(this);
        intermediate.addActionListener(this);
        advanced.addActionListener(this);
        start.addActionListener(this);

        // 确保单选。
        ButtonGroup group = new ButtonGroup();
        group.add(beginner);
        group.add(intermediate);
        group.add(advanced);
        group.add(custom);

        // 初始化菜单实例。
        beginner.setSelected(true);
        setSize(280,400);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * 实现 ActionListener 接口。
     * @param e 点击事件。
     */
    public void actionPerformed(ActionEvent e)
    {
        // 如果用户选择 "自定义"，设置文本框为可编辑状态。
        if (e.getSource() == custom)
        {
            width.setEditable(true);
            height.setEditable(true);
            mines.setEditable(true);
        } else if (e.getSource() == start) {
            // 如果用户点击 "开始游戏" 按钮，获得相对应的炸弹总数，游戏窗口的长和宽。
            int boardWidth = 0;
            int boardHeight = 0;
            int bombs = 0;
            boolean errorFlag = false;

            if (beginner.isSelected())
            {
                boardWidth = 10;
                boardHeight = 10;
                bombs = 10;
            } else if (intermediate.isSelected()) {
                boardWidth = 16;
                boardHeight = 16;
                bombs = 40;
            } else if (advanced.isSelected()) {
                boardWidth = 30;
                boardHeight = 25;
                bombs = 100;
            } else {
                if(!checkValid(width.getText(), height.getText(), mines.getText()))
                {
                    // 设置标记并在窗口上弹出错误提示。
                    errorFlag = true;
                    JOptionPane.showMessageDialog(null, "Please enter correct numbers!");

                } else {
                    boardWidth = Integer.parseInt(width.getText());
                    boardHeight = Integer.parseInt(height.getText());
                    bombs = Integer.parseInt(mines.getText());
                }
            }

            if(!errorFlag)
            {

                // 关闭当前菜单窗口并弹出与之对应的游戏窗口。
                this.dispose();
                GameBoard b = new GameBoard("Minesweeper", boardWidth, boardHeight);
                new ProduceBombs(b, bombs);
                ((SmartSquare) b.getSquareAt(0, 0)).setStartTime(System.currentTimeMillis());
            }

        } else{
            // 如果玩家即没有选择 "Custom" 也没有点击 "New Game" 按钮，这些文本框要设置成不可编辑的状态。
            width.setEditable(false);
            height.setEditable(false);
            mines.setEditable(false);
        }
    }

    /**
     * 检查玩家的输入是否符合要求。
     * @param bWidth 游戏窗口的宽度。
     * @param bHeight 游戏窗口的高度。
     * @param bomb 炸弹的总数
     * @return 返回检查结果的布尔值。
     */
    private boolean checkValid(String bWidth, String bHeight, String bomb)
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        if (bWidth == null || bHeight== null || bomb == null)
            return false;
        else if (bWidth.isEmpty() || bHeight.isEmpty() || bomb.isEmpty())
            return false;
        else if (!pattern.matcher(bWidth).matches() || !pattern.matcher(bHeight).matches() || !pattern.matcher(bomb).matches())
            return false;
        else if (Integer.parseInt(bWidth) < 10 || Integer.parseInt(bWidth) > 30 || Integer.parseInt(bHeight) < 10 || Integer.parseInt(bHeight) > 25
                || Integer.parseInt(bomb) < 10 || Integer.parseInt(bomb) > 100)
            return false;
        else
            return Integer.parseInt(bWidth) * Integer.parseInt(bHeight) >= Integer.parseInt(bomb);
    }
}