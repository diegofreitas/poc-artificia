<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<style type="text/css">
	@IMPORT url("<%= request.getContextPath() %>/resources/style/novandi.css");
	@IMPORT url("<%= request.getContextPath() %>/resources/style/jquery-ui-1.8.2.custom.css");
	@IMPORT url("<%= request.getContextPath() %>/resources/style/nivo-slider.css");
</style>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/resources/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript"
	src="<%= request.getContextPath() %>/resources/js/jquery.nivo.slider.pack.js"></script>

<script type="text/javascript">
document.write('<%= request.getParameter("error")%>');
$(function(){
$('#slider').nivoSlider({effect:'fade'});

$('#ajaxReq').click(function(){
	$.ajax({
		url:'frontend/j_security_check',
		type:'post',
		data:{
			'j_username':$('[name=j_username]').val(),
			'j_password':$('[name=j_password]').val()
		},
		success:function(data){
				alert(data.responseText);
			}
	});
});
});

</script>	
	



</head>
<body>
<div id="header" class="ui-state-default" style="height: 60px;">
</div>
<div id="content"">
<div id="slider"  style="float: left;">
	<img src="<%= request.getContextPath() %>/resources/images/slide1.jpg" alt="" />
	<a href="http://dev7studios.com"><img src="<%= request.getContextPath() %>/resources/images/slide2.jpg" alt="" /></a>
	<img src="<%= request.getContextPath() %>/resources/images/slide3.jpg" alt="" title="This is an example of a caption" />
	<img src="<%= request.getContextPath() %>/resources/images/slide4.jpg" alt="" />
</div>
<div  class="dragbox ui-layout-container ui-dialog ui-widget ui-widget-content ui-corner-all" id="item4"  style="float: right;">  
         <h2 class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">Handle 4</h2>  

         <div class="dragbox-content" >  
		<form method="post" action="j_security_check">
			<table>
				<tr>
					<td colspan="2">Login to the Tomcat-Demo application:</td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="j_username" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="j_password"/></td>
				</tr>
				<tr>
					<td ><input type="submit" value="Go" /></td>
					<td ><input id="ajaxReq" type="submit" value="Go ajax" /></td>
				</tr>
			</table>
		</form>

         </div>  
</div>
</div>
<div id="footer">

</div>
</body>
</html>