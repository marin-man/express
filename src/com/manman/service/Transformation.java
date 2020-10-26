package com.manman.service;

import com.manman.struct.StackList;
import com.manman.utils.CollectionUtils;
import com.manman.utils.OperatorPriority;

import java.util.List;

public class Transformation {

    private static final int ZERO = '0' - 0;
    private static final int NINE = '9' - 9;
    public static StackList stackList;
    public static StackList operateList;


    /**
     * 中缀转为后缀
     * @param list
     */
    public static StackList infixToSuffix(List<String> list){
        stackList = new StackList();   // 初始化后缀的栈结构
        operateList = new StackList();  // 初始化操作符的栈结构
        CollectionUtils.addListNode("#", operateList);   // 操作符的栈底为 #
        for(String str : list){
            // 遍历中缀链表
            if(OperatorPriority.isDigit(str)){
                CollectionUtils.addListNode(str, stackList);   // 数字直接进入链表
            }else{
                addOperator(str);    // 添加操作符
            }
        }
        clearOperateList();    // 清除操作符栈中的符号，将符号添加到后缀栈中
        return stackList;
    }


    /**
     * 清除操作符栈中的符号，将符号添加到后缀栈中
     */
    private static void clearOperateList(){
        while(operateList.getLength() != 1){
            String pop = (String)CollectionUtils.pop(operateList);
            CollectionUtils.addListNode(pop, stackList);
        }
    }


    /**
     * 将操作符入操作栈或入后缀链表
     * @param str
     */
    private static void addOperator(String str){
        if(str.equals("(")){
            // 如果字符串是 "(" ，直接压栈
            CollectionUtils.push(str, operateList);
            return;
        }
        String s;
        if(str.equals(")")){
            // 如果字符串是 ")"，循环出栈直到找到 "("
            s = (String)CollectionUtils.pop(operateList);
            while(!s.equals("(")){
                CollectionUtils.addListNode(s, stackList);
                s = (String)CollectionUtils.pop(operateList);
            }
            return;
        }
        // 其他字符串，比较大小
        s = (String)CollectionUtils.getLast(operateList);
        int priority1 = OperatorPriority.getPriority(s);   // 获取优先级
        int priority2 = OperatorPriority.getPriority(str);  // 获取优先级
        while(priority1 >= priority2){
            // 优先级底，出栈
            if(priority1 == -1){
                return;
            }
            CollectionUtils.pop(operateList);
            CollectionUtils.addListNode(s, stackList);
            s = (String)CollectionUtils.getLast(operateList);
            priority1 = OperatorPriority.getPriority(s);
        }
        if(priority1 < priority2){
            // 优先级高，入栈
            CollectionUtils.push(str, operateList);
        }
    }
}
