package practice.topics.arr;

import javax.swing.*;
import java.net.URL;

/**
 * 该类描述了方块对象中主要的属性和方法。
 * 该类是抽象的，将在之后被继承，被继承的抽象方法需要被实现。
 * @author joe finney
 */
public abstract class GameSquare extends JButton
{
    /** 方块的 x 坐标 **/
    protected int xLocation;

    /** 方块的 y 坐标 **/
    protected int yLocation;

    /** 方块所在的游戏窗口 **/
    protected GameBoard board;

    /**
     * 创建一个会被放在游戏窗口的方块对象。
     * @param x 方块相对于游戏窗口的 x 坐标。
     * @param y 方块相对于游戏窗口的 y 坐标。
     * @param filename 图片文件所在位置。
     * @param board 游戏窗口。
     */
    public GameSquare(int x, int y, URL filename, GameBoard board)
    {
        super(new ImageIcon(filename));

        this.board = board;
        xLocation = x;
        yLocation = y;
    }

    /**
     * 根据所给的文件地址更改当前方块渲染的图像。
     *
     * @param filename 需要更新的图片的地址，
     */
    public void setImage(URL filename)
    {
        this.setIcon(new ImageIcon(filename));
    }

    /**
     * 用户点击调用的方法。
     */
    public abstract void clicked();
}