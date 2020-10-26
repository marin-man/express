package com.manman.service;

import com.manman.struct.*;
import com.manman.utils.CollectionUtils;
import com.manman.utils.OperatorPriority;

public class ChangeTree {

    /**
     * 将后缀表达式转为二叉树
     * @param stackList
     * @return
     */
    public static TreeStruct SufChangeTree(StackList stackList){
        BaseList treeStructList = new TreeStructList();             // 二叉树链表
        StackNode stackNode = (StackNode)stackList.getHead().right;  // 后缀表达式的第一个元素

        for(int i=0; i<stackList.getLength(); i++){
            // 将每个元素单独作为一个树节点并添加到二叉树链表上
            CollectionUtils.addListNode(new TreeNode(stackNode.getItem()), treeStructList);
            stackNode = (StackNode)stackNode.right;
        }

        TreeStructNode node = (TreeStructNode) treeStructList.getHead().right;   // 二叉树链表的第一个元素
        while(treeStructList.getLength() != 1){
            String str = node.getItem().getRoot().str;   // 获取二叉树链表元素的字符串
            while(OperatorPriority.isDigit(str)){        // 如果字符串是数字类型，则继续遍历
                node = (TreeStructNode)node.right;
                str = node.getItem().getRoot().str;
            }
            // 把三个二叉树链表元素整合成一个：操作符作为根节点，前面两个节点作为左右孩子节点
            createNewTree(node);
            treeStructList.setLength(treeStructList.getLength() - 2);  // 二叉树链表个数需减 2
            node = (TreeStructNode)node.right;
        }

        return ((TreeStructNode) treeStructList.getHead().right).getItem();  // 返回二叉树结构
    }

    /**
     * 把三个二叉树链表元素整合成一个：操作符作为根节点，前面两个节点作为左右孩子节点
     * @param node
     * @return
     */
    private static TreeStructNode createNewTree(TreeStructNode node){
        TreeStructNode treeStructNode1 = (TreeStructNode) (node.left.left);  // 第一个元素在 操作符元素的左边左边
        TreeStructNode treeStructNode2 = (TreeStructNode) node.left;   // 第二个元素在 操作符元素的左边
        // 将两个元素添加到 操作符元素 的左右孩子上
        node.getItem().getRoot().leftChild = treeStructNode1.getItem().getRoot();
        node.getItem().getRoot().rightChild = treeStructNode2.getItem().getRoot();
        treeStructNode1.left.right = node;
        node.left = treeStructNode1.left;
        node.getItem().setLayers(1 + Math.max(treeStructNode1.getItem().getLayers(), treeStructNode2.getItem().getLayers()));
        return node;
    }


}
