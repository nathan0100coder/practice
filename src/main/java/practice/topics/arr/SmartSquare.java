package practice.topics.arr;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 该类继承 GameSquare 类。
 * 该类实现了 ActionListener 和 MouseListener 的方法，对不同的点击事件有不同的响应。
 * 每一个方块有自己独一无二的二维坐标和属性值。
 * 一旦玩家鼠标左击点击该类的实例，会马上显示出该方块周围存在几个炸弹。
 * 该类提供一个弹出窗口无论玩家挑战成功或失败。
 * @author Hephaest
 * @since 3/21/2019 8:41 PM
 */
public class SmartSquare extends GameSquare implements MouseListener, TimeChecker
{
    /** 炸弹在该方块的存在与否 **/
    private boolean thisSquareHasBomb;

    /** 玩家是否有在该方块上设置红旗 **/
    private boolean guessThisSquareIsBomb;

    /** 该方块是否被遍历过 **/
    private boolean thisSquareHasTraversed;

    /** 该方块的 x 坐标 **/
    private int xLocation;

    /** 该方块的 y 坐标 **/
    private int yLocation;

    /** 该方块记录的游戏开始时间 **/
    private long startTime;

    /**
     * 创建该类的新实例并放到游戏窗口上去。
     * @param x 该方块相对于游戏窗口的 x 的坐标。
     * @param y 该方块相对于游戏窗口的 y 的坐标。
     * @param board 该方块所在的游戏窗口。
     */
    public SmartSquare(int x, int y, GameBoard board)
    {
        // 初始化时将方块变成灰色。
        super(x, y, SmartSquare.class.getResource("/block.png"), board);

        // 赋值地址。
        xLocation = x;
        yLocation = y;

        // 初始化属性。
        thisSquareHasBomb = false;
        thisSquareHasTraversed = false;
        guessThisSquareIsBomb = false;
        startTime = 0;

        // 添加右键监听器。
        addMouseListener(this);
    }

    /**
     * 为炸弹是否存在于该方块设定值。
     * @param result 给定的布尔值。
     */
    protected void setBombExist(boolean result)
    {
        thisSquareHasBomb = result;
    }

    /**
     * 获取炸弹是否存在于该方块的结果。
     * @return 布尔结果。
     */
    protected boolean getBombExist()
    {
        return thisSquareHasBomb;
    }

    /**
     * 返回该方块是否遍历过的状态。
     * @return 该方块的状态。
     */
    protected boolean getTraverse()
    {
        return thisSquareHasTraversed;
    }

    /**
     * 根据给定值设置该方块当前的状态。
     * @param result 布尔值表示当前的状态。
     */
    protected void setTraverse(boolean result)
    {
        thisSquareHasTraversed = result;
    }

    /**
     * 返回该方块是否插上小红旗的查询结果。
     * @return 返回查询状态。
     */
    protected boolean getGuessThisSquareIsBomb()
    {
        return guessThisSquareIsBomb;
    }

    /**
     * 记录游戏开始的时间戳。
     * @param time 以毫秒表示的时间。
     */
    protected void setStartTime(long time)
    {
        startTime = time;
    }

    /**
     * 返回游戏刚开始的时间。
     * @return 返回以毫秒表示的时间。
     */
    protected long getStartTime()
    {
        return startTime;
    }

    /**
     * 从 GameSquare 实现的抽象方法。
     * 一旦获得点击事件，检测炸弹的存在和扩大空白的面积。
     */
    public void clicked()
    {

        CheckSquare cq = new CheckSquare(board);

        guessThisSquareIsBomb = false;

        if(thisSquareHasBomb)
        {
            /*
             * 如果该方块包含炸弹，显示炸弹。
             * 弹出失败窗口。
             */
            setImage(SmartSquare.class.getResource("/bombReveal.png"));
            long costTime = System.currentTimeMillis() - ((SmartSquare) board.getSquareAt(0, 0)).getStartTime();
            cq.showBomb(xLocation, yLocation);
            window("You used " + TimeChecker.calculateTime(costTime) +". Do you want to try again?", "Game Over",
                    new ImageIcon(SmartSquare.class.getResource("/failFace.png")));
        } else{
            thisSquareHasTraversed = false;
            /*
             * 如果该方块不包含炸弹，计算它周围8个格子里炸弹的总数。
             * 如果周遭也没有炸弹，扩大空白区域直到检测到炸弹或者越界。
              */
            cq.countBomb(xLocation, yLocation);

            if (cq.isSuccess()) {
                long costTime = System.currentTimeMillis() - ((SmartSquare) board.getSquareAt(0, 0)).getStartTime();
                cq.showBomb(xLocation, yLocation);
                window("You win this game in " + TimeChecker.calculateTime(costTime) +
                        "! Do you want to try again?","Congratulations",
                        new ImageIcon(SmartSquare.class.getResource("/passFace.jpg")));
            }
        }
    }

    /**
     * 一个实现弹出窗口的方法。
     * @param msg 要显示在窗口上的信息。
     * @param title 窗口的标题。
     * @param img the 窗口的图标。
     */
    public void window(String msg, String title, Icon img)
    {

        int choose = JOptionPane.showConfirmDialog(board, msg, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,img);

        if (choose == JOptionPane.YES_OPTION)
        {
            new Menu("Mine sweeper");
        }

        // 关闭弹出窗口并重返菜单。
        board.dispose();
    }

    /**
     * 实现对右键鼠标的反馈。
     * @param e 玩家点击方块的事件。
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        // 如果玩家右击方块。
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            int clickCount = e.getClickCount();

            // 显示小红旗。
            if (clickCount == 1)
            {
                setImage(SmartSquare.class.getResource("/redFlag.png"));
                guessThisSquareIsBomb = true;
            }

            // 显示问号。
            if (clickCount == 2)
            {
                setImage(SmartSquare.class.getResource("/questionMark.png"));
                guessThisSquareIsBomb = false;
            }
        }
    }

    // 下列鼠标事件不处理。
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}