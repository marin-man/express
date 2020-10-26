package com.manman.utils;

import com.manman.struct.*;

public class CollectionUtils {

    /**
     * 向链表中添加节点
     * @param obj
     * @param list
     */
    public static void addListNode(Object obj, BaseList list){
        BaseListNode baseListNode;
        if(list instanceof StackList) {
            // 链表类型
            baseListNode = new StackNode((String)obj);
        }else{
            // 树类型
            baseListNode = new TreeStructNode(new TreeStruct((TreeNode)obj));
        }
        baseListNode.left = list.getTail().left;
        list.getTail().left.right = baseListNode;
        baseListNode.right = list.getTail();
        list.getTail().left = baseListNode;
        list.setLength(list.getLength() + 1);
    }

    /**
     * 压栈
     * @param obj
     * @param list
     */
    public static void push(Object obj, BaseList list){
        addListNode(obj, list);
    }


    /**
     * 获取栈尾的值
     * @return
     */
    public static Object getLast(BaseList list){
        if(list instanceof StackList) {
            // 链表类型
            StackNode stackNode = (StackNode)list.getTail().left;
            return stackNode.getItem();
        }else{
            // 树类型
            TreeStructNode treeStructNode = (TreeStructNode)list.getTail().left;
            return treeStructNode.getItem();
        }
    }


    /**
     * 出栈
     * @return
     */
    public static Object pop(BaseList list){
        Object obj;
        if(list instanceof StackList) {
            StackNode stackNode = (StackNode)list.getTail().left;
            obj = stackNode.getItem();
        }else{
            TreeStructNode treeStructNode = (TreeStructNode)list.getTail().left;
            obj = treeStructNode.getItem();
        }

        list.getTail().left = list.getTail().left.left;
        list.getTail().left.right = list.getTail();
        list.setLength(list.getLength() - 1);
        return obj;
    }


    /**
     * 将 list 链表的值合为字符串
     * @param stackList
     * @return
     */
    public static String listToString(StackList stackList){
        String str = "";
        StackNode stackNode= (StackNode) (stackList.getHead().right);
        while(stackNode.right != null){
            str += stackNode.getItem() + " ";
            stackNode = (StackNode) stackNode.right;
        }
        return str;
    }
}
