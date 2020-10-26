package com.manman.struct;

public class TreeStructNode extends BaseListNode{

    public TreeStructNode(){}
    public TreeStructNode(TreeStruct treeStruct){
        this.item = treeStruct;
        this.left = null;
        this.right = null;
    }

    public TreeStruct getItem(){
        return (TreeStruct)this.item;
    }
}
