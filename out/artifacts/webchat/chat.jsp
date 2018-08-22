<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>聊天室</title>
<link href="css/index.css"  rel="stylesheet"/>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="js/jquery-1.7.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/util.js"></script>

</head>

<body class="bank_body" onunload="window.location.href='${pageContext.request.contextPath}/logout?suid=${requestScope.suid}'">
	<!--banner container-->
	<div class="banner_container">
    	<div class="banner">
    		
            <div id="user">
            <table style="margin:15px auto 0px auto;">
            	<tr>
                	<td align="center" width="60"><span style="font-size:20px;display:block;width:60px; height:30px;color:#FFF;font-weight:bolder;margin-top:5px">${requestScope.username}</span><td>
                    <td align="center" width="80"><a href="${pageContext.request.contextPath}/logout?suid=${requestScope.suid}">注销</a><td>
                </tr>
            </table>
            
            </div>
        </div>
    </div>
    <div class="main">  
    	     
        <div id="chat">
            <span style="color: #ffffff; margin: 0px auto;">说明:点击右侧在线表可以私聊</span>
        	<form method="get" id="form">
                <input type="hidden" name="username" value="${requestScope.username}"/>
                <input type="hidden" name="domain" id="call_suid"/>
            	<input type="hidden" name="chat_flag" value="1"/>
                <input id="userid" type="hidden" name="suid" value="${requestScope.suid}"/>
                <div class="input-group" style="width:200px; height:20px; margin:0px auto">
                    <span class="input-group-addon" id="sizing-addon3">call</span>
                    <input id="call_name" name="domain_name" type="text" class="form-control" placeholder="Username" aria-describedby="sizing-addon1">
                </div>
                <div id="show">
                	
                </div>
                <textarea style="float:left; width:470px; height:100px; margin:0px auto 0px 15px; border-radius:10px; background-color:#8a8a8a75" name="send_message" id="input"></textarea>
        </form>
            <button id="send" onclick="send()"><span class="on">发 &nbsp; &nbsp; 送</span></button>
         </div>  
         <div id="users" style="list-style:none;">
         	<ul >
            </ul>
         </div>
    </div>
    
   
</body>
<script>

    window.onbeforeunload=function(e){
        var e=e||window.event;
        e.returnValue="请确认是否退出？";
        $.get("/wechat/logout", {"suid":"123"});
    };

    //异步发送信息
	function send(){
		$.ajax({
			type:"get",
			url:"/webchat/Chat",
			data:$("#form").serialize(),
			success: function(){
				alert("发送成功");
                $("#input").val("");
			},
			error:function(){
				alert("发送失败");
			}		
		})
	}
	/**
     * 页面加载结束开始询轮
     */
    $(document).ready(timer());

    /**
     * 询轮请求
     */
    function refresh(){
        $.ajax({
            type:"POST",
            url:"/webchat/refresh",
            async:true,
            dataType:"json",
            data:{
                suid:$("#userid").val(),
            },
            success:function(json){
                var list = json.list;
                var msg = json.msg.messages;
                var users = $("<ul></ul>")

                //创建用户表
                users.append($("<li></li>")
                        .attr("id","all")
                        .text("所有人")
                );
                for(l in list){

                    users.append($("<li></li>")
                            .attr("id",l)
                            .text(list[l])
                    );
                }
                //users区域添加用户列表
				$("#users").empty();
				$("#users").append(users);
				
				/*
				每一个li绑定点击事件
				点击用户可以发送信息
				*/
				$("#users").find("ul li").each(function() {
				   $(this).click(function() {
                       //获取发送suid
					   var suid = $(this).attr("id");
                       var name = $(this).text();
					   $("#call_name").val(name);
                       $("#call_suid").val(suid);
				   });
				});

                $("#show").empty();
                //显示聊天信息
                for(m in msg){
                    $("#show").append($("<span></span>")
                            .html(msg[m])
                            //.text(msg[m])
                    );
                }
				//再次定时发任务
                timer();
            },
            error:function(){
                console.log("请求失败");
                timer();
            }
        });

    }
    /**
     * 询轮定时器
     */
    function timer(){
        setTimeout(refresh,2000);
    }
	
	function test1(){
		
		var list = {"seid1":"yq","suid2":"yst"};
		var msg = ["123","456"];
		alert(typeof list);
        var users = $("<ul></ul>")
        var user = $("<li></li>")
                for(l in list){

                    users.append($("<li></li>")
                            .attr("id",l)
                            .text(list[l])
                    );
                }
				$("#users").empty();
				$("#users").append(users);
		for(m in msg){
			$("#show").append($("<span></span>")
						.text(msg[m])	
			);
		}
		
		$("#users").find("ul li").each(function() {
		   $(this).click(function() {
			   var value = $(this).attr("id");
			   $("#call").val(value);
		   });
		});
	}
			
</script>
</html>