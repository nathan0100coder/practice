package practice.datastruc.graph;

import java.util.Arrays;

/*
* # --> 墙
* O --> 未走（可走）的路
* Y --> 走过的路
* N --> 死路
*  策略：下右上左 （！！！策略不唯一，策略不一样，路线也可能不一样。）
* */
public class Maze {
    static char[][] maze_map = new char[10][10];

    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            Arrays.fill(maze_map[i],'O');
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                maze_map[0][i] = '#';
                maze_map[9][i] = '#';
                maze_map[i][0] = '#';
                maze_map[i][9] = '#';
            }
        }
        maze_map[1][3] = maze_map[1][7] = maze_map[2][3] = maze_map[2][7] = '#';
        maze_map[3][5] = maze_map[3][6] = '#';
        maze_map[4][2] = maze_map[4][3] = maze_map[4][4] = '#';
        maze_map[5][4] = maze_map[6][2] = maze_map[6][6] = '#';
        maze_map[7][2] = maze_map[7][3] = maze_map[7][4] = maze_map[7][6] = maze_map[7][7] = '#';
        maze_map[8][1] = maze_map[8][2] = '#';
        System.out.println("迷宫地形图：");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(maze_map[i][j] + " ");
            }
            System.out.println();
        }

        walking(maze_map,1,1);
        System.out.println("---上帝视角迷宫可行路线地形图：---");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(maze_map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean walking(char[][] map, int x, int y) {
        if (map[8][8]=='Y'){
            //map[8][8] 为终点，即终点走过的时候，结束。
            return true;
        }else {
            if (map[x][y]=='O'){ // 为O即可走，
                map[x][y] ='Y'; //走过为Y。
                //判断下右上左四个方向是否可以走。
                if (walking(map,x+1,y)){
                    return true;
                }else if (walking(map,x,y+1)){
                    return true;
                }else if (walking(map,x-1,y)){
                    return true;
                }else if (walking(map,x,y-1)){
                    return true;
                }else {
                    //四个方向都不可走的话，说明这里是个死路，即赋值为N.
                    map[x][y]='N';
                    return false;
                }
            }else {
                // else的情况为路况不为O的情况,可能的情况为Y,N,#。这三种情况都不能走，返回false。
                return false;
            }
        }
    }
}


