<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>web聊天</title>
<link href="css/index.css"  rel="stylesheet"/>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="js/jquery-1.7.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>

<body class="bank_body">
	<!--banner container-->
	<div class="banner_container">
    	<div class="banner">
            <div id="user">
            </div>
        </div>
    </div>
    <div id="img_show">
    	<!--login_div-->
    	<div id="login_div">
            <form method="post" action="${pageContext.request.contextPath}/Login">
                <input type="hidden" name="flag" value="1"/>
           	  <table style="width:90%; height:50%; margin: 0px auto;">
              		<tr>
                    	<td height="70" align="center"></td>
                        <td width="248" align="center"><label><h3 style="font-weight:bolder">账号密码登录</h3></label></td>
                    </tr>
                	<tr>
                    	<td width="10" height="50" align="center"><label></label></td>
                        <td align="center"><input class="form-control" name="username" type="text" placeholder="username"/></td>
                    </tr>
                    <tr>
                    	<td height="50" align="center"><label></label></td>
                        <td align="center"><input class="form-control" name="password" type="password" placeholder="password"/> </td>
                    </tr>
                    <tr>
                    	<td height="60" align="center"><label></label></td>
                        <td align="center">
                      	
                            <div class="input-group">
                              <span class="input-group-addon">
                                <input type="checkbox" name="remuber" value="1">
                                <span>记住密码</span>
                              </span>
                              
                            </div>
                          
                      </td>
                    </tr>
                    <tr>
                    	<td height="60" align="center"><label></label></td>
                        <td align="center"> 
							<button style="width:270px; height:40px" type="submit" class="btn btn-primary btn-lg btn-block">登录</button>
                        </td>
                    </tr>
                    <tr>     
                </table>
            </form>
        </div>
    </div>

</body>

</html>