<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/resources/js/jquery-ui-1.8.2.custom.min.js"></script>
	
<script type="text/javascript">

$(function(){
	$('.dragbox')
	.each(function(){
		$(this).hover(function(){
			$(this).find('h2').addClass('collapse');
		}, function(){
			$(this).find('h2').removeClass('collapse');
		})
		.find('h2').hover(function(){
			$(this).find('.configure').css('visibility', 'visible');
		}, function(){
			$(this).find('.configure').css('visibility', 'hidden');
		})
		.click(function(){
			$(this).siblings('.dragbox-content').toggle();
		})
		.end()
		.find('.configure').css('visibility', 'hidden');
	});
	$('.column').sortable({
		connectWith: '.column',
		handle: 'h2',
		cursor: 'move',
		placeholder: 'placeholder',
		forcePlaceholderSize: true,
		opacity: 0.4,
		stop: function(event, ui){
			var sortorder='';
			$('.column').each(function(){
				var itemorder=$(this).sortable('toArray');
				var columnId=$(this).attr('id');
				sortorder+=columnId+'='+itemorder.toString()+'&';
			});
			//alert('SortOrder: '+sortorder);
			/*Pass sortorder variable to server using ajax to save state*/
		}
	})
	.disableSelection();
});

</script>
	
<style type="text/css">
.nav {
	height: 35px;
	background: url(<%= request.getContextPath() %>/resources/style/images/bg.gif) repeat-x;
	position: relative;
	font-family: arial, verdana, sans-serif;
	font-size: 11px;
	width: 100%;
	z-index: 100;
	margin: 0;
	padding: 0;
}

.nav .table {
	display: table;
	margin: 0 auto;
}

.nav .select,.nav .current {
	margin: 0;
	padding: 0;
	list-style: none !important;
	display: table-cell;
	white-space: nowrap;
}

.nav li {
	margin: 0;
	padding: 0;
	height: auto;
	float: left;
}

.nav .select a {
	display: block;
	height: 35px;
	float: left;
	font-weight: bold;
	background: url(images/bg.gif);
	padding: 0 30px 0 30px;
	text-decoration: none;
	line-height: 35px;
	white-space: nowrap;
	color: #2b3238;
}

.nav .select a:hover,.nav .select li:hover a {
	background: url(<%= request.getContextPath() %>/resources/style/images/hover.gif);
	padding: 0 0 0 15px;
	cursor: pointer;
	color: #2b3238;
}

.nav .select a b {
	font-weight: bold;
}

.nav .select a:hover b,.nav .select li:hover a b {
	display: block;
	float: left;
	padding: 0 30px 0 15px;
	background: url(<%= request.getContextPath() %>/resources/style/images/hover.gif) right top;
	cursor: pointer;
}

.nav .select_sub {
	display: none;
}

/* IE6 only */
.nav table {
	border-collapse: collapse;
	margin: -1px;
	font-size: 1em;
	width: 0;
	height: 0;
}

.nav .sub {
	display: table;
	margin: 0 auto;
	padding: 0;
	list-style: none;
}

.nav .sub_active .current_sub a,.nav .sub_active a:hover {
	background: transparent;
	color: #2b3238;
}

.nav .select :hover .select_sub,.nav .current .show {
	display: block;
	position: absolute;
	width: 100%;
	top: 35px;
	background: url(<%= request.getContextPath() %>/resources/style/images/back.gif);
	padding: 0;
	z-index: 100;
	left: 0;
	text-align: center;
}

.nav .current .show {
	z-index: 10;
}

.nav .select :hover .sub li a,.nav .current .show .sub li a {
	display: block;
	float: left;
	background: transparent;
	padding: 0 10px 0 10px;
	margin: 0;
	white-space: nowrap;
	border: 0;
	color: #2b3238;
}

.nav .current .sub li.sub_show a {
	color: #2b3238;
	cursor: default;
}

.nav .select .sub li a {
	font-weight: normal;
}

.nav .select :hover .sub li a:hover,.nav .current .sub li a:hover {
	visibility: visible;
	color: #73a0d2;
}

		



.column{
	width:49%;
	margin-right:.5%;
	min-height:300px;
	background:#fff;
	float:left;
}
.column .dragbox{
	margin:5px 2px  20px;
	position:relative;
	width: 98% !important;
}
.column .dragbox h2{
	margin:0;
	font-size:12px;
	padding:5px;
	cursor:move;
}
.dragbox-content{
	min-height:100px; margin:5px;
}
.column  .placeholder{
	background: #f0f0f0;
	border:1px dashed #ddd;
}
.dragbox h2.collapse{

}
.dragbox h2 .configure{
	margin-right:30px; float:right;
}
</style>
<style type="text/css">
	@IMPORT url("<%= request.getContextPath() %>/resources/style/novandi.css");
	@IMPORT url("<%= request.getContextPath() %>/resources/style/jquery-ui-1.8.2.custom.css");
</style>
</head>
<body >

<div class="nav">
<div class="table">

<ul class="select">
	<li><a href="#"><b>Sample</b></a></li>
</ul>

<ul class="select">
	<li><a href="#"><b>Sample Menu</b></a>
	<div class="select_sub">
	<ul class="sub">
		<li><a href="#">Sample Menu</a></li>
		<li><a href="#">Sample Menu</a></li>
		<li><a href="#">Sample Menu</a></li>

		<li><a href="#">Sample Menu</a></li>
		<li><a href="#">Sample Menu</a></li>
	</ul>
	</div>
	</li>
</ul>

<ul class="select">
	<li><a href="#"><b>Sample Menu</b></a>
	<div class="select_sub">
	<ul class="sub">

		<li><a href="#">Sample Menu 2</a></li>
		<li><a href="#">Sample Menu 2</a></li>
		<li><a href="#">Sample Menu 2</a></li>
		<li><a href="#">Sample Menu 2</a></li>
		<li><a href="#">Sample Menu 2</a></li>
	</ul>

	</div>
	</li>
</ul>


<ul class="select">
	<li><a href="#"><b>Sample Menu</b></a>
	<div class="select_sub">
	<ul class="sub">
		<li><a href="#">Sample Menu 3</a></li>
		<li><a href="#">Sample Menu 3</a></li>
		<li><a href="#">Sample Menu 3</a></li>

		<li><a href="#">Sample Menu 3</a></li>
		<li><a href="#">Sample Menu 3</a></li>
	</ul>
	</div>
	</li>
</ul>


<ul class="select">
	<li><a href="#"><b>Sample Menu</b></a>
	<div class="select_sub">
	<ul class="sub">

		<li><a href="#">Sample Menu 4</a></li>
		<li><a href="#">Sample Menu 4</a></li>
		<li><a href="#">Sample Menu 4</a></li>
		<li><a href="#">Sample Menu 4</a></li>
		<li><a href="#">Sample Menu 4</a></li>
	</ul>

	</div>
	</li>
</ul>

</div>
</div>


<div style="width: 1024px; padding-left: 190px;">
 <div class="column" id="column1">  
     <div class="dragbox ui-layout-container ui-dialog ui-widget ui-widget-content ui-corner-all" id="item1" >  
         <h2 class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">Handle 1</h2>  
         <div class="dragbox-content" >  
             <!-- Panel Content Here -->  
         </div>  
     </div>  
      <div class="dragbox ui-layout-container ui-dialog ui-widget ui-widget-content ui-corner-all" id="item2" >  
         <h2 class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span class="configure" ><a href="#" >Configure</a></span>Handle 2</h2>  
         <div class="dragbox-content" >  
             <!-- Panel Content Here -->  
         </div>  
     </div>  
      <div class="dragbox ui-layout-container ui-dialog ui-widget ui-widget-content ui-corner-all" id="item3" >  
         <h2 class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">Handle 3</h2>  
         <div class="dragbox-content" >  
             <!-- Panel Content Here -->  
         </div>  
     </div>  
 </div>  
 <div class="column" id="column2" >  
      <div class="dragbox ui-layout-container ui-dialog ui-widget ui-widget-content ui-corner-all" id="item4" >  
         <h2 class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">Handle 4</h2>  

         <div class="dragbox-content" >  
             <!-- Panel Content Here-->  
         </div>  
    </div>  
      <div class="dragbox ui-layout-container ui-dialog ui-widget ui-widget-content ui-corner-all" id="item5" >  
         <h2 class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">Handle 5</h2>  
         <div class="dragbox-content" >  
             <!--Panel Content Here-->  
  
           </div>  
       </div>  
   </div>  
   </div>
</body>
</html>