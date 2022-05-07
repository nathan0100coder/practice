package practice.topics.arr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * 该类为游戏窗口提供图形模型。
 * 该类创建了可点击的矩形面板
 * 如果玩家点击了小方块，会在响应的 SmartSquare 实例种调用回调函数。
 * 该类是基于平铺的游戏的基础。
 * @author joe finney
 */
public class GameBoard extends JFrame implements ActionListener
{
    private JPanel boardPanel = new JPanel();

    private int boardHeight;
    private int boardWidth;
    private GameSquare[][] board;

    /**
     * 创建给定大小的游戏窗口。
     * 一旦该类实例被创建，窗口将可视化。
     *
     * @param title 窗口栏的标题。
     * @param width 以方块作单位的窗口的宽。
     * @param height 以方块作单位的窗口的高。
     */
    public GameBoard(String title, int width, int height)
    {
        super();

        this.boardWidth = width;
        this.boardHeight = height;

        // 创建游戏初始方块。
        this.board = new GameSquare[width][height];

        // 新建窗口。
        setTitle(title);
        setSize(20 + width * 20,20 + height * 20);
        setContentPane(boardPanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel.setLayout(new GridLayout(height,width));

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                board[x][y] = new SmartSquare(x, y, this);
                board[x][y].addActionListener(this);

                boardPanel.add(board[x][y]);
            }
        }

        // 使窗口可视化。
        setVisible(true);

    }

    /**
     * 返回给定位置的方块。
     * @param x 给定方块的 x 的坐标。
     * @param y 给定方块的 y 的坐标。
     * @return 返回给定位置的方块。
     * 如果 x 和 y 的位置都在边界范围内，则给出响应的方块对象，否则返回 null.
     */
    public GameSquare getSquareAt(int x, int y)
    {
        if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight)
            return null;

        return board[x][y];
    }

    public void actionPerformed(ActionEvent e)
    {
        // 被点击的方块，需要处理点击情况。
        GameSquare b = (GameSquare)e.getSource();
        b.clicked();
    }
}