package com.manman.struct;

public class BaseList {
    private BaseListNode head ;
    private BaseListNode tail;
    private int length;

    public BaseList(BaseListNode baseNode){
        if(baseNode instanceof StackNode) {
            this.head = (StackNode)baseNode;
            this.tail = new StackNode();

        }else if(baseNode instanceof TreeStructNode){
            this.head = (TreeStructNode) baseNode;
            this.tail = new TreeStructNode();
        }

        this.head.right = this.tail;
        this.tail.left = this.head;
        this.length = 0;
    }

    public BaseListNode getHead() {
        return head;
    }

    public void setHead(BaseListNode head) {
        this.head = head;
    }

    public BaseListNode getTail() {
        return tail;
    }

    public void setTail(BaseListNode tail) {
        this.tail = tail;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
