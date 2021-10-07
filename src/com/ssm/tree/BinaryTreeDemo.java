package com.ssm.tree;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/5 12:56
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "易");
        HeroNode node2 = new HeroNode(2, "艾希");
        HeroNode node3 = new HeroNode(3, "奶妈");
        HeroNode node4 = new HeroNode(4, "蛮王");
        HeroNode node5 = new HeroNode(5, "霞");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        //测试遍历
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();
        //测试查找
        System.out.println("前序查找");
        HeroNode resnode = binaryTree.preOrderSearch(5);
        if (resnode != null) {
            System.out.printf("找到了,信息为 no=%d name=%s", resnode.getNo(), resnode.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        }
        System.out.println("中序查找");
        HeroNode resnode2 = binaryTree.preOrderSearch(5);
        if (resnode2 != null) {
            System.out.printf("找到了,信息为 no=%d name=%s", resnode2.getNo(), resnode2.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        }
        System.out.println("后序查找");
        HeroNode resnode3 = binaryTree.preOrderSearch(5);
        if (resnode3 != null) {
            System.out.printf("找到了,信息为 no=%d name=%s", resnode3.getNo(), resnode3.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        }

        //删除节点
        binaryTree.delNode(5);
        System.out.println("删除节点");
        binaryTree.preOrder();
    }
}

//定义BinaryTree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //删除节点
    public void delNode(int no){
        if (root != null){
            if (root.getNo() == no){
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树,不能删除~~~");
        }
    }

}

//创建HeroNode 节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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

    //编写前序遍历
    public void preOrder() {
        System.out.println(this); //输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //编写中序遍历
    public void infixOrder() {
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this); //输出父节点
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //编写后序遍历
    public void postOrder() {
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this); //输出父节点
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        //不是的话,则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) { //说明找到
            return resNode;
        }
        //左递归前序查找，找到结点，则返回，否继续判断,当前的结点的右子节点是否为空，如果不空，则继续向右递归
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {

        // 判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) { //说明找到
            return resNode;
        }
        //如果没有找到，就和当前结点比较
        if (this.no == no) {
            return this;
        }
        //否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {

        // 判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) { //说明找到
            return resNode;
        }
        //如果没有找到，就右递归的后序查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) { //说明找到
            return resNode;
        }
        //左右子树都没有找到,就比较当前节点
        if (this.no == no) {
            return this;
        }
        return resNode;
    }


    //递归删除节点
    //如果删除的节点是叶子结点,则删除该结点  如果删除的节点是非叶子结点,则删除该子树
    public void delNode(int no) {
        if (this.left!=null && this.left.no == no){
            this.left = null;
            return;
        }
        if (this.right!=null && this.right.no == no){
            this.right = null;
            return;
        }
        if (this.left!=null){
            this.left.delNode(no);
        }
        if (this.right!=null){
            this.right.delNode(no);
        }
    }
}
