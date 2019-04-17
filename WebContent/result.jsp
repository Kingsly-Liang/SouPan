<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type = "text/css">
	#father{
		width:100%;
		height:100%;
		margin:auto;
	}
			
	#top{
		width:100%;
		height:5%;
		background: #fff;
	}
	
	#result_zone{
		width:100%;
		height:80%;
		background: #fff;
	}
	
	#bottom{
		width:100%;
		height:15%;
	}
</style>
<title>搜索结果</title>
</head>
<body>
	<div id = "father">
		<div id="top">
			<form id="getPan" action="SearchPan" method="post">
				<img src = "./smallsou.jpg"/>
				<input id="keyword" name="keyword" type="text" autocomplete="off" placeholder="请输入关键字" style="width:400px;height:35px;"/>
				<input type = "submit"  id = "search" value = "搜一下" style="width:100px;height:40px;">
			</form>
		</div>
		<hr>
		<%
			String [] links = (String [])request.getAttribute("links");
			String [] titles = (String [])request.getAttribute("titles");
			Integer size = (Integer)request.getAttribute("size");
		%>
		
		<div id="result-zone">
			<p>查询结果:<%=size %>个结果</p><br>
			<div id="resultList">
				<%
					int i = 0;
					while(i < size){
				%>
				<!-- <p><%=titles[i] %>  <%=links[i] %></p><hr>  -->
				<p> <a href = <%= links[i]%> > <%= titles[i] %></a> </p><hr>
				<% 	
					i++;
					}
				%>
			</div>
		</div>
			
		<div id = "bottom" align = "center">
			<p>声明：本站搜索结果来自Google自定义搜索，不存储任何工作内容，只提供信息检索服务。如果有侵犯的地方，联系我们及时整改。</p>
			<p>	Copyright©2009-2018 盘搜(JobSou.Com) </p>
		</div>	
	</div>

</body>
</html>