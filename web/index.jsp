<%--
  Created by IntelliJ IDEA.
  User: MAIBENBEN
  Date: 2020/9/20
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>表达式计算</title>
  <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
  <link type="text/css" rel="stylesheet" href="static/css/style.css" >

  <script type="text/javascript" src="static/js/index.js"></script>
</head>
<body>

<div class="body">
  <div class="head">表达式计算</div>
  <div class="content">
    <div class="left">
      <table class="left_table">
        <tr>
          <td colspan="4" class="input">
            <input class="top" disabled="disabled">
            </input>
            <input class="bottom" disabled="disabled">
            </input>
          </td>
        </tr>
        <tr>
          <td value="c"><img src="images/c.svg" class="image"></img></td>
          <td value="left"><img src="images/left.svg" class="image" ></img></td>
          <td value="right"><img src="images/right.svg" class="image"></img></td>
          <td value="cheng"><img src="images/cheng.svg" class="image"></img></td>
        </tr>
        <tr>
          <td value="seven"><img src="images/seven.svg" class="image seven"></img></td>
          <td value="eight"><img src="images/eight.svg" class="image eight"></img></td>
          <td value="nine"><img src="images/nine.svg" class="image nine"></img></td>
          <td value="chu"><img src="images/chu.svg" class="image"></img></td>
        </tr>
        <tr>
          <td value="four"><img src="images/four.svg" class="image four"></img></td>
          <td value="five"><img src="images/five.svg" class="image five"></img></td>
          <td value="six"><img src="images/six.svg" class="image six"></img></td>
          <td value="jia"><img src="images/jia.svg" class="image seven"></img></td>
        </tr>
        <tr>
          <td value="one"><img src="images/one.svg" class="image one"></img></td>
          <td value="two"><img src="images/two.svg" class="image two"></img></td>
          <td value="three"><img src="images/three.svg" class="image three"></img></td>
          <td value="jian"><img src="images/jian.svg" class="image"></img></td>
        </tr>
        <tr>
          <td value="delete"><img src="images/delete.svg" class="image"></img></td>
          <td value="zero"><img src="images/zero.svg" class="image"></img></td>
          <td value="dian"><img src="images/dian.svg" class="image"></img></td>
          <td value="deng" class="deng"><img src="images/deng.svg" class="image"></img></td>
        </tr>
      </table>
    </div>
    <div class="right" style="overflow-x:auto; overflow-y:auto">
      <div class="tran">
        <div class="operate">数据结构
        <div class="hover"></div>
        </div>
        <div class="history">历史记录
        <div></div>
        </div>
      </div>
      <div id="operate_content">
        <div class="first">
          <div class="title_tip">中缀表达式转为后缀表达式：</div>
          <div class="in_to_suf"></div>
        </div>
        <div class="change_tree">
          <div class="title_tip">后缀表达式转为二叉树：</div>
          <div class="tree"></div>
        </div>
      </div>
      <div id="history_content" class="hidden"></div>

    </div>
  </div>
</div>
</body>
</html>
