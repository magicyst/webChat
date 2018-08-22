webchat:web聊天室

1.功能
	-实现群聊功能
	-可并发

2.特点
	-session管理不使用servlet 提供的session机制，而是在servletContext里维护一个user Map结果
	-map
		-key:MD5(session+username)
		-value:userBean(持久对象：username,password,chatMessage,logs)

	-user内容采用 properties文件存储

3.环境 
	-tomcat jdk1.8

4.技术
	-前端 html+js+jquery+css ajax发请求
	-后台 servlet+jsp