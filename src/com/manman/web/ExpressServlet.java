package com.manman.web;

import com.google.gson.Gson;
import com.manman.struct.StackList;
import com.manman.struct.TreeNode;
import com.manman.struct.TreeStruct;
import com.manman.service.ChangeTree;
import com.manman.service.Transformation;
import com.manman.utils.CollectionUtils;
import com.manman.service.InputUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressServlet extends BaseServlet{

    /**
     * 解析传递过来的表达式
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void express(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String str = req.getParameter("str");
        // 将字符串转为中缀链表
        List<String> list = InputUtils.inputStr(str);
        // 中缀转为后缀
        StackList stackList = Transformation.infixToSuffix(list);
        // 将后缀转为二叉树
        TreeStruct treeStruct = ChangeTree.SufChangeTree(stackList);
        // 将后缀转为字符串
        String strStackList = CollectionUtils.listToString(stackList);
        // 得到计算后的表达式答案字符串
        String s = InputUtils.getExpressResult(treeStruct, str);
        // 把计算到的数据传到页面
        writeToHtml(s, strStackList, treeStruct, resp);
    }

    /**
     * 把计算到的数据传到页面
     * @param s
     * @param strStackList
     * @param treeStruct
     * @param resp
     * @throws IOException
     */
    public static void writeToHtml(String s, String strStackList, TreeStruct treeStruct, HttpServletResponse resp) throws IOException{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", s);
        map.put("suf", strStackList);
        map.put("tree", treeStruct);

        // 将 Map 解析为 json 格式的字符串
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        // 传递给前端
        PrintWriter out = resp.getWriter();
        out.println(jsonStr);
        out.flush();
    }


    /**
     * 测试二叉树
     * @param treeNode
     */
    public static void test(TreeNode treeNode){
        System.out.print(treeNode.str);
        if(treeNode.leftChild != null) {
            test(treeNode.leftChild);
        }
        if(treeNode.rightChild != null) {
            test(treeNode.rightChild);
        }
    }
}


