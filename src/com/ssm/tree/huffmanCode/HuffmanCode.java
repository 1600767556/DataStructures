package com.ssm.tree.huffmanCode;

import com.ssm.tree.HuffmanTree;
import com.sun.corba.se.pept.encoding.OutputObject;

import java.io.*;
import java.util.*;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/10 19:07
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果:" + Arrays.toString(huffmanCodeBytes) + "长度= " + huffmanCodeBytes.length);
        byte[] decode = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("解压后原来的字符串=" + new String(decode));

        String srcFile = "G://1.jpg";
        String dstFile = "G://dst.zip";
        zipFile(srcFile, dstFile);
        System.out.println("压缩成功");
        String zipFile = "G://dst.zip";
        String dstFile2 = "G://2.jpg";
        unZipFile(zipFile, dstFile2);
        System.out.println("解压成功");
    }

    // 使用一个方法将前面的方法封装起来,便于调用

    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据nodes 创建赫夫曼树
        Node huffmanTreeRoot = creatHuffmanTree(nodes);
        //对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        // 根据生成的赫夫曼编码,压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    //将字符串对应的byte[]数组,通过生成的赫夫曼编码表,返回一个赫夫曼码压缩后的byte[]

    /**
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用HuffmanCodes 将 bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes
        ) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //统计返回 byte[] huffmanCodeBytes 长度  一句话表示 int len = (StringBuilder.length+7)/8
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { //因为是每8位对应一个byte,所以步长+8
            String strByte;
            if (i + 8 > stringBuilder.length()) { //不够八位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte 转成一个byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;

        }
        return huffmanCodeBytes;
    }

    //完成数据的解压

    /**
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //转成赫夫曼编码对应的字符串 "101010..."
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //解码 把赫夫曼编码进行调换,因为反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建一个集合,存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count); // i 不动,count移动,直到匹配到一个字符
                b = map.get(key);
                if (b == null) { //没有匹配到
                    count++;
                } else {
                    flag = false;
                }
            }

            list.add(b);
            i += count;
        }
        //for 循环结束 list中就存放了所有的字符
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    //完成对文件的压缩
    private static void zipFile(String srcFile, String dstFile) {
        OutputStream os = null;
        ObjectOutputStream oos = null;
        FileInputStream is = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);
            byte[] huffmanBytes = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodes);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    //完成对文件的解压
    public static void unZipFile(String zipFile, String dstFile) {
        InputStream is = null;
        OutputStream os = null;
        ObjectInputStream ois = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入目标文件
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();

                is.close();


            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }


    /**
     * 将一个byte 转成一个二进制字符串
     *
     * @param flag 标志是否需要补高位 如果是true,表示需要 false 不需要 (最后一个字节)
     * @param b    传入的byte
     * @return 该b对应的二进制字符串(按补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b; // 将 b 转成int
        if (flag) {
            temp |= 256;
        }
        String string = Integer.toBinaryString(temp); //返回的是temp对应的二进制补码
        if (flag) {
            return string.substring(string.length() - 8);
        } else {
            return string;
        }

    }


    //生成赫夫曼树对应的赫夫曼编码
    //将赫夫曼编码存放在Map<Byte,String> 形式: 32->01 97->100
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //在生成的赫夫曼编码表示,需要去拼接路径,定义一个StringBuilder,存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //重载一下
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理左子树
        getCodes(root.left, "0", stringBuilder);
        //处理右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能:将传入的node结点的所有叶子结点的赫夫曼编码得到,并放入到huffmanCodes集合
     *
     * @param node          传入结点
     * @param code          路径:左子结点是0,右子结点1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            //判断当前节点是叶子节点还是非叶子结点
            if (node.data == null) { //非叶子结点
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //像右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { //说明是一个叶子结点
                huffmanCodes.put(node.data, stringBuilder2.toString());

            }
        }

    }

    // 前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼空树");
        }
    }

    /**
     * @param bytes 接收字节数组
     * @return 返回数是List 形式
     */
    private static List<Node> getNodes(byte[] bytes) {
        //创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        //存储每一个byte出现的次数 ->map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes
        ) {
            Integer count = counts.get(b);
            if (count == null) { //map 没有数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每一个键值对转成一个node对象,并加入到nodes集合  遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过list,创建对应的赫夫曼树
    private static Node creatHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            System.out.println("nodes = " + nodes);
            //取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            //取出根节点权值第二小的两颗二叉树
            Node rightNode = nodes.get(1);
            //构建一颗新的二叉树,他的根结点没有data,只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到nodes
            nodes.add(parent);
        }
        //最后的结点就是赫夫曼树的根结点
        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {
    Byte data; //存储数据本身.'a' => 97 ' ' =>32
    int weight; //权值,字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

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

}