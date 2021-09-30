package com.ssm.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/9/26 20:53
 */
public class PoLandNotation {

    public static void main(String[] args) {
        //将中缀表达式转成后缀表达式
        //直接对str操作不方便,因此将 "1+((2+3)*4)-5" => ArrayList [1,+,(,(,2,+,3,),*4,)-,5]
        //将得到的中缀表达式对应的list => 后缀表达式对应的list
        //即 ArrayList [1,+,(,(,2,+,3,),*4,)-,5]  => ArrayList [1,2,3,+,4,*+,5,-]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list:"+infixExpressionList);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的list:"+parseSuffixExpressionList);
        System.out.printf("expression=%d",calculate(parseSuffixExpressionList));
        System.out.println();
        //定义逆波兰表达式
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路: 1.先将"3 4 + 5 * 6-" => 放到ArrayList中
        //2.将ArrayList 传递给一个方法,遍历ArrayList 配合栈完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);

        int res = calculate(rpnList);
        System.out.println("计算的结果是:" + res);

    }

    // ArrayList [1,+,(,(,2,+,3,),*4,)-,5]  => ArrayList [1,2,3,+,4,*+,5,-]
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈 s2可以用ArrayList代替
        Stack<String> s1 = new Stack<String>();
        List<String> s2 = new ArrayList<String>();
        for (String item:ls) {
            //如果是一个数
            if (item.matches("\\d+")){
                s2.add(item);
            } else if (item.equals("(")){
                s1.push(item);
            } else if (item.equals(")")){
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//!!! 将( 弹出s1栈 消除小括号

            } else {
                //步骤第四步
                while (s1.size() !=0 && Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2; //因为是存放到 list中,因此按顺序输出就是对应的后缀表达式对应的list
    }

    //将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<String>();
        int i = 0;//指针 用于遍历中缀表达式字符串
        String str; //多位数拼接
        char c; //每遍历一个字符就放入到c
        do {
            //如果c是非数字,需要加入到ls中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else { //如果是一个数,需要考虑多位数
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c; //拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length()); {
            return ls;
        }
    }

    //将一个逆波兰表达式依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        // 将 suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls
        ) {
            //使用正则表达式取出数据
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                //pop 两个数,并运算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push("" + res);
            }
        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());

    }
}
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回对应的优先级数字
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}
