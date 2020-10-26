package com.manman.struct;

public class StackNode extends BaseListNode {

    public StackNode(){}

    public StackNode(String str){
        this.item = str;
        this.left = null;
        this.right = null;
    }

    public String getItem(){
        return (String)this.item;
    }
}
