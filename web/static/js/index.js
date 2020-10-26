$(function(){
    let href = window.location.href;
    let s1 = "";     // 表达式字符串
    let leftNum = 0;
    let historyArr = [];   // 历史记录
    $("td").click(function(e){
        let str = getValue($(this).attr("value"));
        // 记录下左右括号的数量
        if(str == '('){
            leftNum ++;
        }
        if(str == ')'){
            if(leftNum <= 0){
                alert("左右括号未能完整匹配！");
                return;
            }
            leftNum --;
        }

        // 点击等号
        if(str == -1){
            if(leftNum){
                // 左右括号不匹配
                alert("表达式错误，左右括号未匹配上！");
                return;
            }
            // 发送 ajax 请求
            $.post(href + "expressServlet", {
                action:"express",
                str:s1+"#"
            }, function (e) {
                try{
                    // 获取传回值
                    let obj = JSON.parse(e);  // 解析 json 字符串
                    $(".top").val(obj.result);   // 得到结果
                    $(".top").scrollLeft($(".top")[0].scrollWidth);
                    $(".in_to_suf").text(obj.suf);  // 得到后缀表达式
                    createTree(obj.tree);   // 根据二叉树生成图像
                    let history = {
                        "str" : s1,
                        "result" : obj.result,
                        "suf" : obj.suf,
                        "tree" : obj.tree
                    };
                    historyArr.push(history);
                    showHistoryItem(historyArr);
                }catch(e){
                    alert("程序出错了！");
                }
            });


            return;
        }else if(str == 'd'){
            // 删除键
            if(s1.substring(s1.length-1) == ')'){
                // 删除的是右括号
                leftNum ++;
            }
            if(s1.substring(s1.length-1) == '('){
                // 删除的是左括号
                leftNum --;
            }
            // 截取字符串
            s1 = s1.substring(0, s1.length-1)
            $(".bottom").val(s1);
            $(".bottom").scrollLeft($(".top")[0].scrollWidth);
            return;
        }else if(str == -2){
            // 空白键，不执行
            return;
        }else if(str == 'c'){
            // 清空键
            leftNum = 0;
            s1 = "";
        }else if(str == '+' || str == '-' || str == '*' || str == '/'){
            if(s1 == ""){
                alert("表达式第一位不能为操作符，若是负数，可以用括号包住！");
                return;
            }
            // 操作符键
            let s = s1.substring(s1.length-1);
            if(s == '+' || s == '-' || s == '*' || s == '/'){
                // 如果原字符串中最后一个也是操作符，则是更改操作符
                s1 = s1.substring(0, s1.length-1) + str;
            }else{
                // 原字符串中最后一个不是操作符，则直接添加
                s1 += str;
            }
        }else if(str == '.'){
            // 小数点键
            let s = s1.substring(s1.length-1);
            if(isNaN(s)){  // 判断原字符串的最后一位是否为数字，不是则提醒
                alert("小数点得在数字后面！");
                return;
            }else{   // 最后一位是数字，直接添加
                s1 += str;
            }
        }else{
            // 其他键直接添加
            s1 += str;
        }
        $(".top").attr('value', "");   // 将结果显示出页面
        $(".bottom").attr('value', s1);
        $(".bottom").scrollLeft($(".top")[0].scrollWidth);
    });


    /**
     * 数据结构和操作历史的转换
     */
    $(".operate").click(function (e) {
        $("#operate_content").attr("class", "");
        $("#history_content").attr("class", "hidden");
        $(".operate").children("div").attr("class", "hover");
        $(".history").children("div").attr("class", "");
    })

    $(".history").click(function (e) {
        $("#operate_content").attr("class", "hidden");
        $("#history_content").attr("class", "");
        $(".operate").children("div").attr("class", "");
        $(".history").children("div").attr("class", "hover");
        showHistoryItem(historyArr);

    });

    $(document).on("click", ".history_recall", function (e) {
        let i = e.currentTarget.attributes[1].value;
        $(".top").val(historyArr[i].result);
        $(".bottom").val(historyArr[i].str);
        $(".in_to_suf").text(historyArr[i].suf);
        createTree(historyArr[i].tree);
    })

})


/**
 * 生成二叉树
 * @param tree
 */
function createTree(tree){
    let layer = tree.layers;   // 数的层数
    let row = layer;           // 表格的行数
    let col = Math.pow(2,layer) -1;   // 表格的列数 ： 2^layer-1
    let arr = new Array(row);    // 二维数组代表表格
    for(let i=0; i<row; i++){    // 创建二维数组
        arr[i] = new Array(col);
    }
    let node = tree.root;      // 根节点
    posTra(node, arr, 0, Math.round(col/2)-1, layer);   // 前序遍历二叉树
    let str = '<table class="tree">';
    // 拼接 html 的表格字符串
    let k = 0;
    for(let i=0; i<row; i++){
        str += "<tr>"
        for(let j=0; j<col; j++){
            if(arr[i][j] != undefined){
                // 二维数组的单元中有值
                str += "<td>" + arr[i][j] +"</td>";
            }else{
                // 二维数组的单元中没有值
                str += "<td></td>";
            }
        }
        str += "</tr>";
        str += "\n";
    }
    str += "</table>";
    $(".tree").html(str);   // 将二叉树显示到页面中
}

/**
 * 中序遍历二叉树，并填写二维数组
 * @param node
 * @param arr
 * @param i
 * @param pos
 * @param layer
 */
function posTra(node, arr, i, pos, layer) {
    if(i >= layer){
        // 递归出口
        return;
    }
    arr[i][pos] = node.str;  // 填写二维数组
    if(node.leftChild){
        posTra(node.leftChild, arr, i+1, pos-(layer-i-1), layer);
    }
    if(node.rightChild){
        posTra(node.rightChild, arr, i+1, pos+(layer-i-1), layer);
    }
}

/**
 * 显示历史记录条
 * @param historyArr
 */
function showHistoryItem(historyArr){
    $("#history_content").children().remove();
    for(let i=0; i<historyArr.length; i++) {
        let str = '<div class="history_recall" data-item="' + i + '"><div class="history_result">';
        str += historyArr[i].result + '</div><div class="history_str">';
        str += historyArr[i].str + "</div></div>";
        $("#history_content").prepend(str);
    }
}

/**
 * 获取点击的值
 * @param a
 * @returns {string|number}
 */
function getValue(a) {
    if(a == 'c'){
        return 'c';
    } else if(a == 'left'){
        return '(';
    }else if(a == 'right'){
        return ')';
    }else if(a == 'cheng'){
        return '*';
    }else if(a == 'chu'){
        return '/';
    } else if(a == 'jia'){
        return '+';
    }
    else if(a == 'jian'){
        return '-';
    }else if(a == 'nine'){
        return 9;
    }else if(a == 'eight'){
        return 8;
    }else if(a == 'seven'){
        return 7;
    }else if(a == 'six'){
        return 6;
    }else if(a == 'five'){
        return 5;
    }else if(a == 'four'){
        return 4;
    }else if(a == 'three'){
        return 3;
    }else if(a == 'two'){
        return 2;
    }else if(a == 'one'){
        return 1;
    }else if(a == 'zero'){
        return 0;
    }else if(a == 'delete'){
        return 'd';
    }else if(a == 'deng'){
        return -1;
    }else if(a == 'dian'){
        return '.';
    }
    else{
        return -2;
    }
}