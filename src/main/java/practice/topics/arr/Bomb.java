package practice.topics.arr;

/**
 * @author Hephaest
 * @since 3/21/2019 8:41 PM
 * 这个抽象类中的抽象方法会在被继承时实现。
 */
public abstract class Bomb
{
    /** 游戏窗口实例 **/
    protected GameBoard board;

    /** 实例的高度 **/
    protected int boardHeight;

    /** 实例的宽度 **/
    protected int boardWidth;

    /**
     * Create bombs, which can be placed on a GameBoard.
     * @param board the GameBoard upon which user clicks on.
     */
    public Bomb(GameBoard board)
    {
        this.board = board;
        // 真正加入计算的高和宽去需要减去填充边距的长度。
        boardHeight = (board.getHeight() - 20) / 20;
        boardWidth = (board.getWidth() - 20) / 20;
    }

    /**
     * 该方法将会被用于分布炸弹的位置。
     */
    protected abstract void reproduceBomb();

}