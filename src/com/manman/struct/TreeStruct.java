package com.manman.struct;

/**
 * 树结构
 * @TreeNode 根节点
 * @size 树的大小
 * @layers 层数
 */
public class TreeStruct {
    private TreeNode root;
    private Integer size;
    private Integer layers;

    public TreeStruct(TreeNode treeNode){
        root = treeNode;
        size = 1;
        layers = 1;
    }


    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getLayers() {
        return layers;
    }

    public void setLayers(Integer layers) {
        this.layers = layers;
    }

    @Override
    public String toString() {
        return "TreeStruct{" +
                "root=" + root +
                ", size=" + size +
                ", layers=" + layers +
                '}';
    }
}
