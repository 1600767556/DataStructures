package com.ssm.recursion;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/9/30 14:30
 */
public class MiGong {
    public static void main(String[] args) {
        //创建二维数组 模拟二维数组
        int[][] map = new int[8][7];
        //上下左右全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板,1表示
        map[3][1] = 1;
        map[3][2] = 1;
        // 输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------");
        //使用递归回溯给小球找路
        setWay(map, 1, 1);
        //输出新地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 出发点map[1][1]
     * 若小球能到map[6][5] 则找到通路
     * 约定:当map[i][j]为0的点表示改点没有走过,当1表示墙,2表示可以走,3表示此路以走过,但走不通
     * 在走迷宫时,制定一个策略:  下->右->上->左,如果改点走不通,再回溯
     *
     * @param map 地图
     * @param i   从哪个位置找
     * @param j   从哪个位置找
     * @return 若找到通路, 返回true, 否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { //已找到
            return true;
        } else {
            if (map[i][j] == 0) { //这个点没走过
                // 按照策略 下->右->上->左
                map[i][j] = 2; // 假定该点可有走通
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }

            } else {
                return false;
            }
        }
    }

}
