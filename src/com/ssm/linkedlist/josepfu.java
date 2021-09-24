package com.ssm.linkedlist;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/9/24 13:56
 */
public class josepfu {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1, 2, 5);//5个人,1开始报数,数2下
    }
}

class CircleSingleLinkedList {
    //创建一个first节点,当前没有编号
    private Boy first = null;

    //添加小孩节点,构成环形列表
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针,用来构建环形列表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;
            } else {
                curBoy.setNext(boy); //指向下一个boy 上面的线连上了
                boy.setNext(first); // 指到第一个节点  下面的线连上了
                curBoy = boy; //指向下一个boy 指针指上去了
            }
        }

    }

    //遍历当前环形链表
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //first 不能动,仍然使用辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) { //遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

    /**
     * 根据用户输入计算出圈的顺序
     *
     * @param startNo  从第几个小孩开始数数
     * @param countNum 表示数记下
     * @param nums     最初有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误,清重新输入");
            return;
        }
        //创建辅助指针,帮助完成小孩出圈 事先应指向环形链表的最后这个节点
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前,先让first和helper移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时,让first和helper指针同时移动m-1次
        while (true) {
            if (helper == first) { //圈中只有一人
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //first指向的节点就是小孩出圈的节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n", helper.getNo());
    }
}


class Boy {
    private int no;
    private Boy next;//指向下一个节点 默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
