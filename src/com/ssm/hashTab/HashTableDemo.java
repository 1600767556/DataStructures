package com.ssm.hashTab;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/4 21:29
 */

import java.util.Scanner;

/**
 * 用hashtable存放雇员信息
 */
public class HashTableDemo {
    public static void main(String[] args) {
        hashTab hashTab = new hashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del: 删除雇员");
            System.out.println("exit: 退出系统 ");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    hashTab.delEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class hashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size; //共有多少条链表
    //构造器

    public hashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //没这一步会报 NullPointerException  需要初始化链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工id,得到员工应该添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将 emp 放到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);

    }

    //遍历所有的链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i + 1);
        }
    }
    //根据id查找雇员
    public void findEmpById(int id){
        //使用散列函数确定哪条链表查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp!=null){ //找到
            System.out.printf("在第%d条链表中找到雇员id = %d\n",(empLinkedListNo+1),id);
        } else { //没找到
            System.out.println("在hash表中没有找到该雇员");
        }
    }

    //根据id删除雇员
    public void delEmpById(int id){
        //使用散列函数确定哪条链表查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].delEmpById(id);
        if (emp!=null){ //找到
            System.out.print("已删除");
        } else { //没找到
            System.out.println("在hash表中没有找到该雇员");
        }
    }
    //编写散列函数,使用简单的取模法
    public int hashFun(int id) {
        return id % size;
    }

}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;//默认为null

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList,表示链表
class EmpLinkedList {
    //头结点
    private Emp head; // 默认null

    //添加雇员到链表
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员,则使用一个辅助指针,帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        //退出时直接将emp 加入链表
        curEmp.next = emp;
    }


    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "链表为空");
            return;
        }
        System.out.print("第" + no + "链表信息为");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id = %d name = %s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;//后移 遍历
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){ //找到了
                break;
            }
            //退出
            if (curEmp.next == null){ //没找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }


    //根据id删除雇员
    public Emp delEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){ //找到了
                curEmp.id = 0;
                curEmp.name = "";
                break;
            }
            //退出
            if (curEmp.next == null){ //没找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}