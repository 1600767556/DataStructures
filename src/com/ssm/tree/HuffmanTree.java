package com.ssm.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/10 13:57
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = creatHuffmanTree(arr);
        preOrder(root);
    }

    // 前序遍历的方法
    public static void preOrder(Node root) {
        if (root!=null){
            root.preOrder();
        } else {
            System.out.println("空树");
        }
    }

    //创建赫夫曼树的方法

    /**
     *
     * @param arr 需要创建赫夫曼树的数组
     * @return 创建好后的赫夫曼树的root
     */
    public static Node creatHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr
        ) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            System.out.println("nodes = " + nodes);
            //取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            //取出根节点权值第二小的两颗二叉树
            Node rightNode = nodes.get(1);
            //构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入到nodes
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

//节点类
//为了让node 对象持续排序Collections集合排序 需要实现Comparable接口
class Node implements Comparable<Node> {
    int value; //节点权值
    Node left;
    Node right;

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排(从大到小 -(this.value - o.value))
        return this.value - o.value;
    }
}