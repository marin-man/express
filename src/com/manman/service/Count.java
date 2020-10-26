package com.manman.service;

import com.manman.struct.TreeNode;
import com.manman.utils.OperatorPriority;

public class Count {


    /**
     * 后续遍历树并求值
     * @param treeNode
     */
    public static double postTraver(TreeNode treeNode){
        Double num1 = 0.0;
        Double num2 = 0.0;
        // 后续遍历
        if(treeNode.leftChild != null){
            num1 = postTraver(treeNode.leftChild);
        }
        if(treeNode.rightChild != null){
            num2 = postTraver(treeNode.rightChild);
        }
        if(OperatorPriority.isDigit(treeNode.str)){
            // 如果字符串是数字，则返回数字类型
            return Double.parseDouble(treeNode.str);
        }else{
            // 如果字符串是操作符，则运算
            return judge(treeNode.str, num1, num2);
        }
    }

    /**
     * 运算
     * @param str
     * @param num1
     * @param num2
     * @return
     */
    private static Double judge(String str, Double num1, Double num2){
        if("*".equals(str)){
            return num1 * num2;
        }else if("/".equals(str)){
            return num1 / num2;
        }else if("+".equals(str)){
            return num1 + num2;
        }else{
            return num1 - num2;
        }
    }
}


