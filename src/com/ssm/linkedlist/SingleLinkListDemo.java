package com.ssm.linkedlist;
import java.util.Stack;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/9/16 11:59
 * <p>
 * 单向链表创建并直接添加到链表的尾部实现&&考虑排名将英雄添加到指定位置
 */
public class SingleLinkListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "易", "无极剑圣");
        HeroNode heroNode2 = new HeroNode(2, "艾希", "寒冰射手");
        HeroNode heroNode3 = new HeroNode(3, "瑞兹", "流浪法师");
        HeroNode heroNode4 = new HeroNode(4, "提莫", "迅捷斥候");
        HeroNode heroNode5 = new HeroNode(5, "拉莫斯", "披甲龙龟");
        //创建单向链表
        SingleLinkList singleLinkList = new SingleLinkList();
        /*singleLinkList.add(heroNode1);
        singleLinkList.add(heroNode2);
        singleLinkList.add(heroNode4);
        singleLinkList.add(heroNode3);*/

        singleLinkList.addByOrder(heroNode1);
        singleLinkList.addByOrder(heroNode2);
        singleLinkList.addByOrder(heroNode4);
        singleLinkList.addByOrder(heroNode5);
        singleLinkList.addByOrder(heroNode3);
        singleLinkList.addByOrder(heroNode3);
        singleLinkList.list();

        //测试修改节点的代码
        singleLinkList.update(new HeroNode(2, "ad希", "寒冰"));
        System.out.println("修改后的链表情况");
        singleLinkList.list();

        //测试删除节点的代码
        singleLinkList.del(1);

        System.out.println("删除后的链表情况");
        singleLinkList.list();

        //测试求单链表中节点个数
        System.out.println("有效的节点个数: " + getLength(singleLinkList.getHead()));

        //测试是否得到倒数第k个节点
        System.out.println("倒数第1个节点: " + findLastIndexNode(singleLinkList.getHead(), 1));

        //反转单链表
        System.out.println("反转单链表~~");
        reverseList(singleLinkList.getHead());
        singleLinkList.list();

        //逆序打印单链表
        System.out.println("逆序打印单链表~~");
        reversePrint(singleLinkList.getHead());
}

    //方法: 获取到单链表的节点个数(如果是带有头节点的,不统计头节点)
    public static int getLength(HeroNode head) {
        if (head.next == null) { //空链表
            return 0;
        }
        int lenght = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            lenght++;
            cur = cur.next;
        }
        return lenght;
    }

    //查找单链表中的倒数第k个节点

    /**
     * 思路:
     * 1.编写一个方法,接收head节点,同时接收一个index
     * 2.index 表示倒数第index个节点
     * 3.先把链表从头到尾遍历,得到链表的总长度getlength
     * 4.得到size后,从链表的第一个开始遍历(size-index)个,就可以得到
     * 5.如果找到了,则返回该节点,否则返回null
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        //第一个遍历得到链表的总长度(节点个数)
        int size = getLength(head);
        //第二次遍历 size-index 位置,就是我们倒数第k个节点
        //index 验证
        if (index <= 0 || index > size) {
            return null;
        }
        //定义一个辅助变量,for 循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //单链表反转
    public static void reverseList(HeroNode head) {
        //如果链表为空或只有一个节点 无需反转 直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助指针,帮助遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (cur != null) {
            next = cur.next; //先暂时保存当前节点的下一个节点,后面需要使用
            cur.next = reverseHead.next; //将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将cur连接到新的链表上
            cur = next; //cure 后移
        }
        //将head.next 指向 reverseHead.next,实现单链表反转
        head.next = reverseHead.next;
    }

    //从尾到头打印单链表
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        //创建一个栈,将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

}


//定义SingleLinkList 管理我们的英雄
class SingleLinkList {
    //先初始化一个头节点,不存放数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表
     * 思路:当不考虑编号顺序时
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next 指向新的节点
     */
    public void add(HeroNode heroNode) {
        //因为head节点不能动,所以需要一个辅助节点变量temp
        HeroNode temp = head;
        //遍历链表,找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        //当退出while循环时temp就指向了链表的最后
        temp.next = heroNode;

    }

    /**
     * 第二种添加节点的方式 根据英雄排名插入到指定位置
     * (如果有排名则添加失败,并给出提示)
     * 1.首先找到添加的新的节点的位置,通过辅助变量通过遍历来搞定
     * 2.新的节点.next = temp.next
     * 3.将temp.next=新的节点
     */
    public void addByOrder(HeroNode heroNode) {
        //再次使用辅助节点来帮助我们找到添加的位置
        //因为单链表,因为我们找到temp 是位于添加位置的前一个节点,否则插入不了(1,,,4 若插入2的话temp要找的是1)
        HeroNode temp = head;
        boolean flag = false; //flag 标志用于判断添加的编号是否已经存在,默认未false
        while (true) {
            if (temp.next == null) { //说明已经遍历到最后一个节点 直接在最后插入即可
                break;
            }
            if (temp.next.no > heroNode.no) { //位置找到,就在temp的后面插入 heroNode(2) 添加在temp和temp.next(4)之间
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true; //编号存在
                break;
            }
            temp = temp.next; //上面三个条件都不成立 后移 遍历当前链表
        }
        //判断flag 的值
        if (flag) {
            System.out.printf("准备插入英雄的编号 %d 已经存在,不能添加\n", heroNode.no);
        } else {
            //插入到temp后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息,根据no编号来修改,即no编号不修改
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //根据no编号,找到需要修改的节点 定义辅助变量
        HeroNode temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.no == newHeroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag 判断是否找到需要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号%d 的节点,不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 删除节点
     * 1. head 不能动,需要一个temp辅助节点找到待删除节点前一个节点
     * 2. 说明我们在比较时,是temp.next.no和需要删除节点的no比较
     */
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; //标志是否找到删除节点
        while (temp.next != null) {
            if (temp.next.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if ((flag)) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }


    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空,没有数据~~~");
            return;
        }
        //头节点不能动再次使用辅助变量temp来遍历
        HeroNode temp = head.next;
        //判断是否到最后
        while (temp != null) {
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }

    }
}

//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法 重写toString


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
