package com.ssm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/13 18:18
 */
public class Graph {
    private ArrayList<String> vertexList; //存储顶点的集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdge; // 表示边的数目
    private boolean[] isVisited; //


    public static void main(String[] args) {
        int n = 8;
        //String[] vertexs = {"A", "B", "C", "D", "E"};
        String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(n);
        //添加顶点
        for (String vertex : vertexs
        ) {
            graph.insertVertex(vertex);
        }
        //添加边
        /*graph.insertEdge(0, 1, 1); // A-B
        graph.insertEdge(0, 2, 1); // A-C
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);*/
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        graph.showGarph();
        System.out.println("深度优先遍历");
        graph.dfs();
        System.out.println();
        System.out.println("广度优先遍历");
        graph.bfs();
    }

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdge = 0;

    }

    //得到第一个邻接点的下标
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //深度优先遍历 i 第一次是0
    private void dfs(boolean[] isVisited, int i) {
        System.out.print(getValByIndex(i) + "->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) { //说明有
                dfs(isVisited, w);
            }
            //如果w结点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs重载 遍历所有的结点
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //广度优先遍历
    private void bfs(boolean[] isVisited, int i) {
        int u; //队列头结点对应的下标
        int w; //邻接点w
        //记录结点访问顺序
        LinkedList linkedList = new LinkedList();
        System.out.print(getValByIndex(i) + "->");
        isVisited[i] = true;
        linkedList.addLast(i);
        while (!linkedList.isEmpty()) {
            //取出队列头结点的下标
            u = (Integer) linkedList.removeFirst();
            // 得到第一个邻接结点的下标
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValByIndex(w) + "->");
                    isVisited[w] = true;
                    linkedList.addLast(w);
                }
                //以u为前驱结点,找w后面的下一个邻接结点
                w = getNextNeighbor(u, w); //体现出广度优先
            }
        }

    }

    //bfs重载 遍历所有的结点
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }


    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的个数
    public int getNumOfEdge() {
        return numOfEdge;
    }

    //返回结点i(下标)对应的数据
    public String getValByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1 v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边

    /**
     * @param v1     表示点的下标即是第几个顶点
     * @param v2     第二个顶点对应的下标
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdge++;

    }

    //显示图对应的矩阵
    public void showGarph() {
        for (int[] link : edges
        ) {
            System.out.println(Arrays.toString(link));
        }
    }

}
