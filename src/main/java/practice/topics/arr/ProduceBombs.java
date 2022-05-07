package practice.topics.arr;

import java.util.Random;

/**
 * 该类用于在游戏窗口生成炸弹。
 * 该类主要用尾递归算法来分配炸弹的位置。
 * @version V1.0
 * @author Hephaest
 * @since 2019-03-12 20:18
 */
public class ProduceBombs extends Bomb
{
    /**
     * 在给定游戏窗口创建该类的实例。
     * 使用递归函数避免炸弹位置重叠。
     * @param board 用户点击的游戏窗口。
     * @param number 炸弹的总数。
     */
    public ProduceBombs(GameBoard board, int number)
    {

        super(board);

        int count =0;

        do {
            reproduceBomb();
            count++;
        }while (count < number);
    }

    /**
     * 该类用于在游戏窗口随机生成炸弹的位置。如果该位置已被占，则通过调用自己重新生成新的位置，以此类推。
     */
    public void reproduceBomb()
    {
        Random r = new Random();

        int xLocation = r.nextInt(boardWidth);
        int yLocation = r.nextInt(boardHeight);

        SmartSquare square = (SmartSquare) board.getSquareAt(xLocation, yLocation);

        if (!square.getBombExist())
        {
            // 标记该方块含有炸弹并被遍历过了。
            square.setBombExist(true);
            square.setTraverse(true);
        } else {
            reproduceBomb();
        }
    }
}