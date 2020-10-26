package com.manman.struct;


/**
 * 树形节点
 * @x 值
 * @parent 父节点
 * @leftChild 左节点
 * @rightChild 右节点
 */
public class TreeNode{
    public String str;
    public TreeNode rightChild;
    public TreeNode leftChild;

    public TreeNode(String str){
        this.str = str;
        rightChild = null;
        leftChild = null;
    }
}
