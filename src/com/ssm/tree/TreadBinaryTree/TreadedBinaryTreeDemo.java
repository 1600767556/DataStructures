package com.ssm.tree.TreadBinaryTree;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/8 15:40
 */
public class TreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        //测试:以10号结点测试
        System.out.println("10号结点的前驱结点是: " + node5.getLeft());
        System.out.println("10号结点的后继结点是: " + node5.getRight());

        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList();
    }
}

//定义ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    //为了实现线索化,需要创建要给指向当前节点的前驱节点的指针
    // 在递归进行线索化时,pre总是保留前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载一把
    public void threadedNodes() {
        this.treadedNodes(root);
    }
    //遍历线索化 二叉树的方法
    public void threadedList(){
        //定义一个变量.存储当前遍历的结点,从root开始
        HeroNode node = root;
        while (node!=null){
            //循环的找到leftType == 1 的结点,第一个找到的就是8这个结点
            //后面随着遍历而变化,因为当leftType == 1 时,说明该结点是按照线索化处理后的结点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node);
            //如果当前结点的右指针指向的是后继节点,就一直输出
            while (node.getRightType() == 1){
                //获取当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node = node.getRight();
        }
    }
    //编写对二叉树进行中序线索化的方法

    /**
     * @param node 就是当前需要线索化的结点
     */
    public void treadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        //先线索化左子树
        treadedNodes(node.getLeft());
        //线索化当前结点

        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }
        //处理后继结点
        if ((pre != null) && pre.getRight() == null) {
            //让当前节点的右指针指向当前结点
            pre.setRight(node);
            //修改当前结点的右指针的类型
            pre.setRightType(1);
        }
        //!!! 每处理一个结点后,就让当前结点是下一个结点的前驱结点
        pre = node;
        //线索化右子树
        treadedNodes(node.getRight());
    }




}

//创建HeroNode 节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //说明
    // 1. 如果 leftType == 0 表示指向的是左子树,如果是1则表示指向前驱结点
    // 1. 如果 rightType == 0 表示指向的是右子树,如果是1则表示指向后驱结点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
