package com.ssm.linkedlist;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/9/23 15:21
 */
public class DoubleLinkListDemo {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1, "易", "无极剑圣");
        HeroNode2 heroNode2 = new HeroNode2(2, "艾希", "寒冰射手");
        HeroNode2 heroNode3 = new HeroNode2(3, "瑞兹", "流浪法师");
        HeroNode2 heroNode4 = new HeroNode2(4, "提莫", "迅捷斥候");
        HeroNode2 heroNode5 = new HeroNode2(5, "拉莫斯", "披甲龙龟");

        DoubleLinkList doubleLinkList = new DoubleLinkList();
       /* doubleLinkList.add(heroNode1);
        doubleLinkList.add(heroNode2);
        doubleLinkList.add(heroNode3);
        doubleLinkList.add(heroNode4);
        doubleLinkList.add(heroNode5);*/

        doubleLinkList.addByOrder(heroNode1);
        doubleLinkList.addByOrder(heroNode4);
        doubleLinkList.addByOrder(heroNode3);
        doubleLinkList.addByOrder(heroNode2);
        doubleLinkList.addByOrder(heroNode5);

        doubleLinkList.list();
        //修改
        System.out.println("修改后链表情况");
        doubleLinkList.update(new HeroNode2(2,"ad希","寒冰"));
        doubleLinkList.list();
        //删除
        System.out.println("删除后链表情况");
        doubleLinkList.del(2);
        doubleLinkList.list();
    }
}

class DoubleLinkList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空,没有数据~~~");
            return;
        }
        //头节点不能动再次使用辅助变量temp来遍历
        HeroNode2 temp = head.next;
        //判断是否到最后
        while (temp != null) {
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }

    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动,所以需要一个辅助节点变量temp
        HeroNode2 temp = head;
        //遍历链表,找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        //当退出while循环时temp就指向了链表的最后
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void addByOrder(HeroNode2 heroNode) {
        //再次使用辅助节点来帮助我们找到添加的位置
        //因为单链表,因为我们找到temp 是位于添加位置的前一个节点,否则插入不了(1,,,4 若插入2的话temp要找的是1)
        HeroNode2 temp = head;
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
            temp.next.pre = heroNode;
            heroNode.pre = temp;
        }
    }

    //修改一个节点的内容 和单向链表一样
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //根据no编号,找到需要修改的节点 定义辅助变量
        HeroNode2 temp = head.next;
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

    //从双向链表中删除节点
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false; //标志是否找到删除节点
        while (temp != null) {
            if (temp.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if ((flag)) {
            temp.pre.next = temp.next;
            //如果是最后i一个节点。不需要执行这句话
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre;

    //构造器
    public HeroNode2(int no, String name, String nickname) {
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
