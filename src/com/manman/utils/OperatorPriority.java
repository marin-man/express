package com.manman.utils;

public class OperatorPriority {
    // 初始化操作符的优先级
    static final int LEFT_BRACKET = 0;
    static final int ADD_OR_SUB = 1;
    static final int MUL_OR_DIV = 2;

    /**
     * 获取优先级
     * @param str
     * @return
     */
    public static int getPriority(String str){
        if(str.equals("(")){
            return LEFT_BRACKET;
        }else if(str.equals("+") || str.equals("-")){
            return ADD_OR_SUB;
        }else if(str.equals("*") || str.equals("/")){
            return MUL_OR_DIV;
        }else{
            return -1;
        }
    }

    /**
     * 正则判断字符串是否是能转换成数字
     * @param str
     * @return
     */
    public static boolean isDigit(String str){
        return str.matches("-*[0-9]+.*[0-9]*");
    }
}
