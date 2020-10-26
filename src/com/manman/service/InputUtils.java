package com.manman.service;

import com.manman.service.Count;
import com.manman.struct.TreeStruct;

import java.util.ArrayList;
import java.util.List;

public class InputUtils {

    /**
     * 将从页面传来的字符串解析为字符串链表
     * @param str
     * @return
     */
    public static List<String> inputStr(String str){
        List<String> list = new ArrayList<String>();
        char[] chars = str.toCharArray();
        boolean upIsNum = false;   // 判断是否是数字
        boolean isPreFu = false;   // 负数前提条件
        boolean isFu = false;      // 判断是否是负数
        int i=0;
        for(int j=0; j<chars.length; j++){
            if(chars[j] == '('){
                isPreFu = true;    // 负数前提条件达到
                list.add(chars[j]+"");  // 加入链表
                i++;
                continue;
            }
            if(isPreFu){
                // 负数前提条件
                if(chars[j] == '-'){
                    isFu = true;    // 是负数
                    isPreFu = false;   // 重置负数前提条件
                    list.add(chars[j]+"");  // 加入链表
                    continue;
                }
                // 不是负数
                isPreFu = false;
            }
            if(isFu){
                // 是负数
                if(chars[j] >= '0' && chars[j] <= '9'){
                    list.set(i, list.get(i) + chars[j]);   // 添加在 符号 的那个单元里
                    upIsNum = true;
                    continue;
                }
            }
            if(isFu && chars[j] == ')'){
                isFu = false;
                list.add(chars[j] + "");
                i+=2;
                upIsNum = false;
                continue;
            }

            if(chars[j] == '.'){
                // 小数点
                list.set(i, list.get(i) + chars[j]);  // 添加到 前一数字 的单元里。
            } else if(chars[j] >= '0' && chars[j] <= '9' && !upIsNum){
                // 数字，但前一个不是数字
                upIsNum = true;   // 将前一个是数字标志为真
                list.add(chars[j]+"");  // 加入链表
            }else if(chars[j] >= '0' && chars[j] <= '9' && upIsNum){
                // 数字，且前一个是数字
                list.set(i, list.get(i) + chars[j]);  // 添加到 前一数字 的单元里
            }else if(upIsNum && (chars[j] < '0' || chars[j] > '9')){
                // 不是数字且前一单元是数字
                list.add(chars[j]+"");
                i+=2;
                upIsNum = false;
            }else{
                // 不是数字且前一单元也不是数字，括号
                list.add(chars[j]+"");
                i+=1;
            }
        }
        return list;
    }

    /**
     * 获得表达式的结果并返回字符串
     * @param treeStruct
     * @param str
     * @return
     */
    public static String getExpressResult(TreeStruct treeStruct, String str){
        String s;
        // 判断结果是整数还是小数
        if ((int) Count.postTraver(treeStruct.getRoot()) == Count.postTraver(treeStruct.getRoot())){
            s = str.substring(0, str.length()-1) + "=" + (int)Count.postTraver(treeStruct.getRoot());
        }else{
            s = str.substring(0, str.length()-1) + "=" + Count.postTraver(treeStruct.getRoot());
        }
        return s;
    }
}
