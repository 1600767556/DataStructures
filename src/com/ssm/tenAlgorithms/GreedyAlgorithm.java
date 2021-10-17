package com.ssm.tenAlgorithms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/10/17 12:40
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("杭州");
        hashSet3.add("上海");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("天津");
        hashSet4.add("上海");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("大连");
        hashSet5.add("杭州");

        broadcasts.put("k1", hashSet1);
        broadcasts.put("k2", hashSet2);
        broadcasts.put("k3", hashSet3);
        broadcasts.put("k4", hashSet4);
        broadcasts.put("k5", hashSet5);

        //存放所有的地区
        HashSet<String> allArea = new HashSet<String>();
        allArea.add("北京");
        allArea.add("上海");
        allArea.add("深圳");
        allArea.add("杭州");
        allArea.add("广州");
        allArea.add("天津");
        allArea.add("成都");
        allArea.add("大连");

        //存放选择的电台的集合
        ArrayList<String> selects = new ArrayList<String>();
        //临时集合,存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<String>();
        //保存在一次遍历中能够覆盖最大未覆盖的地区对应的电台key  如果maxKey不为null,则加入到selects
        String maxKey = null;
        while (allArea.size() != 0) {
            maxKey = null;
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //取tempSet 和 areas 的交集 赋给 tempSet
                tempSet.retainAll(allArea);
                //如果当亲这个集合包含未覆盖地区的数量,比maxKey指向的集合地区还多,就需要重置maxKey  这里体现贪心算法的特点
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxkey指向的广播电台重allArea中去除掉
                allArea.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果" + selects);
    }
}
