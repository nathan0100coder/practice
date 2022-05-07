package practice.topics.arr;
/**
 * @author Hephaest
 * @since 3/21/2019 8:41 PM
 * 该类用于计算指定方块周围的炸弹总数。
 */
public class CheckSquare
{
    /** 游戏窗口实例 **/
    private GameBoard board;

    /** 实例的高度**/
    private int boardHeight;

    /** 实例的宽度 **/
    private int boardWidth;

    private static final int[] distantX = {-1, 0, 1};
    private static final int[] distantY = {-1, 0, 1};

    /**
     * 在游戏窗口中创建该类的实例。
     * @param board 玩家点击的游戏窗口。
     */
    public CheckSquare(GameBoard board)
    {
        this.board = board;
        // 长宽都要减去边距的长度。
        boardHeight = (board.getHeight() - 20) / 20;
        boardWidth = (board.getWidth() - 20) / 20;
    }

    /**
     * 返回指定位置方块的检查结果。
     * @param x 指定方块的 x 坐标。
     * @param y 指定方块的 y 坐标。
     * @return 指定方块的查询结果，用布尔类型表示。
     */
    private boolean hasKickedBoundary(int x, int y)
    {
        return x < 0 || x >= boardWidth || y < 0 || y >= boardHeight;
    }

    /**
     * 返回玩家是否挑战成功的确认结果。
     * @return 确认结果用布尔值表示。
     */
    protected boolean isSuccess()
    {
        // 确保调用该方法时计数器从0开始。
        int count = 0;

        for (int y = 0; y < boardHeight; y++)
        {
            for (int x = 0; x < boardWidth; x++)
            {
                if (((SmartSquare) board.getSquareAt(x, y)).getTraverse())
                    count++;
            }
        }

        return count == boardHeight * boardWidth;
// 也可以写成这种形式。
//        for (int y = 0; y < boardHeight; y++)
//        {
//            for (int x = 0; x < boardWidth; x++)
//            {
//                if (!((SmartSquare) board.getSquareAt(x, y)).getTraverse()) return false;
//            }
//        }
//        return true;    
    }

    /**
     * 该方法会显示所有炸弹的位置，检验用户猜测是否正确。
     * @param currentX 该方块的 x 坐标。
     * @param currentY 该方块的 y 坐标。
     */
    protected void showBomb(int currentX, int currentY)
    {
        for (int y = 0; y < boardHeight; y++)
        {
            for (int x = 0; x < boardWidth; x++)
            {
                if (currentX == x && currentY == y){}
                else if (((SmartSquare) board.getSquareAt(x, y)).getBombExist())
                    board.getSquareAt(x, y).setImage(CheckSquare.class.getResource("/bomb.png"));
                else if(((SmartSquare) board.getSquareAt(x, y)).getGuessThisSquareIsBomb())
                    board.getSquareAt(x, y).setImage(CheckSquare.class.getResource("/flagWrong.png")); // Wrong guess!
            }
        }
    }

    /**
     * 该方法计算指定方块周围的炸弹总数。
     * 如果该方块周围没有炸弹，把该方块绘制成白色并扩大检测范围。
     * 直到周围的炸弹总数不为0。该方法用递归算法实现。
     * @param currentX 该方块的 x 坐标
     * @param currentY 该方块的 y 坐标。
     */
    protected void countBomb(int currentX, int currentY)
    {
        // 确保每次调用时计数器从0开始计数。
        int count = 0;
        SmartSquare currentObject;

        if (hasKickedBoundary(currentX, currentY))
            return; // 无需往下检验，直接跳出循环。
        else if(((SmartSquare)board.getSquareAt(currentX, currentY)).getTraverse())
            return; // 无需往下检验，直接跳出循环。
        else {
            // 声明 SmartSquare 实例。
            SmartSquare squareObject;

            // 获取当前方块对象。
            currentObject = (SmartSquare)board.getSquareAt(currentX, currentY);
            currentObject.setTraverse(true);

            /*
             * 检测周围 8 个方块:
             * 如果所指定的方块位置超出游戏窗口边界，跳出本次循环。
             * 如果所指定的方块位置恰恰是自己，跳出本次循环。
             * 否则检验该方块周围是否含有炸弹。如果有，计算机累加。
             */
            for (int x : distantX)
            {
                for (int y: distantY)
                {
                    if (hasKickedBoundary(currentX + x, currentY + y)){}
                    else if (x == 0 && y == 0){}
                    else{
                        squareObject = (SmartSquare)board.getSquareAt(currentX + x, currentY + y);
                        count = squareObject.getBombExist() ? count + 1 : count;
                    }
                }
            }
        }

        /*
         * 如果循环后计数器仍为0，用该方块周围的方块们作为中心继续探测。
         */
        if (count != 0)
            currentObject.setImage(CheckSquare.class.getResource( "/" + count + ".png"));
        else {
            // 将当前方块渲染为空白。
            currentObject.setImage(CheckSquare.class.getResource("/0.png"));
            countBomb(currentX - 1, currentY -1); // 左上。
            countBomb(currentX, currentY -1); // 正上。
            countBomb(currentX + 1, currentY -1); // 右上。
            countBomb(currentX - 1, currentY); // 正左。
            countBomb(currentX + 1, currentY); // 正右。
            countBomb(currentX - 1, currentY + 1); // 左下。
            countBomb(currentX, currentY + 1); // 正下。
            countBomb(currentX + 1, currentY + 1); // 右下。
        }
    }
}