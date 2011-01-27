<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
	@IMPORT url("<%= request.getContextPath() %>/resources/style/novandi.css");
	@IMPORT url("<%= request.getContextPath() %>/resources/style/jquery-ui-1.8.2.custom.css");
</style>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/resources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/resources/js/jquery-ui-1.8.2.custom.min.js"></script>
	<script type="text/javascript"
	src="<%= request.getContextPath() %>/resources/js/jquery.scrollpane.js"></script>
<script type="text/javascript">
var Event = function (sender) {
	this._sender = sender;
	this._listeners = [];
};

Event.prototype = {
	attach : function (listener) {
		this._listeners.push(listener);
	},
	notify : function (args) {
		for (var i = 0; i < this._listeners.length; i++) {
			this._listeners[i](this._sender, args);
		}
	}
};
function CustomerModel(){
	this.list = {};
	this.listChanged = new Event(this);
}
CustomerModel.prototype.load = function(){
	var _this = this;
	$.ajax({
		  url: '/frontend/customer/list.action',
		  dataType: 'json',
		  success: function(data){ 
		  	_this.setList(data.customers);
		  }
	});
};

CustomerModel.prototype.setList = function(list){
	this.list = list;
	this.listChanged.notify();
};

function CustomerView(model){
	this.model = model;
};

CustomerView.prototype.init = function(model){
	var _this = this;
	this.model.listChanged.attach(function(){
		_this.updateList();
	});
	this.list.selectable({
		selected:function(){alert('asdfasdf');}
	});
	this.model.load();
	$('button[type!=icon]').button();
	$('#refresh').button({
        icons: {
        	primary: 'ui-icon-refresh'
    	},
    	text: false
	});
	$('#next').button({
        icons: {
        	primary: 'ui-icon-seek-next'
    	},
    	text: false
	});
	$('#previous').button({
        icons: {
        	primary: 'ui-icon-seek-prev'
    	},
    	text: false
	});
};
CustomerView.prototype.updateList = function(){
	var _list = this.model.list;
	for(i in _list){
		this.list.append('<li class="ui-state-default ui-corner-all">' + _list[i].name + '</li>');
	}
	$('#ui-content-scroll').jScrollPane({scrollbarWidth:5, showArrows:false});
};
$(function(){
	var view = new CustomerView(new CustomerModel());
	$.extend(view,{
		'list':$('#customers')
	});
	view.init();
});
</script>
</head>
<body style="width: 1024px;height: 768px; margin: 0px;">
<div class=" ui-layout-container ui-dialog ui-widget ui-widget-content ui-corner-all">
   <div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
      <span id="ui-dialog-title-dialog" class="ui-dialog-title">Dialog title</span>
     <a class="ui-state-default ui-dialog-titlebar-close ui-corner-all" href="#"><span class="ui-icon ui-icon-search">close</span></a>
   </div>
   <div style="height: 100%; width: 100%;" class="ui-dialog-content ui-widget-content">
	<div class="ui-nvd-commnad-pane" style="float: left;width: 100%;padding-bottom: 5px;">
		<button type="button">
			Function
		</button>
		<button type="button">
			New
		</button>
	</div>
	<div class="ui-nvd-filters-pane" style="width: 100%; padding: 0px 0px 5px 0px">
		<select class="ui-button ui-widget ui-state-default ui-corner-all">
			<option>All</option>
			<option>OUTRO</option>
		</select> 
		<select class="ui-button ui-widget ui-state-default ui-corner-all">
			<option>All</option>
			<option>OUTRO</option>
		</select> 
	</div>
	<div id="ui-nvd-list-pane">
	    <div style="padding-bottom: 5px; float: right;">
	    	<button id="refresh" style=""></button>
	    </div>
		<div style="clear: both;"></div>
		<div id="ui-content-scroll" class="scroll-pane">
   			<ol id="customers">
			</ol>
   		</div>
   		<ul id="pagination" style="padding-left:0px; margin: 0px; float: left;">
   		    <li><span>Show</span></li>
   			<li class="ui-state-default ui-corner-all"><span >10</span></li>
   			<li class="ui-state-default ui-corner-all"><span >30</span></li>
   			<li class="ui-state-default ui-corner-all"><span >50</span></li>   			 
   			<li><span>entries</span></li>
   			<li><button id="previous"></button></li>
   			<li><button id="next"></button></li>
   			<li><span>1/3</span></li>
   		</ul>
   		<div style="clear: both;"></div>
   	</div>
   </div>
</div>
<div style="width: 690px; min-height: 591px;" class="ui-layout-container ui-dialog ui-widget ui-widget-content ui-corner-all">
   <div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
      <span id="ui-dialog-title-dialog" class="ui-dialog-title">Dialog title</span>
      <a class="ui-state-default ui-dialog-titlebar-close ui-corner-all" href="#"><span class="ui-icon ui-icon-search">close</span></a>
   </div>
   <div style="width: 100%;" class="ui-dialog-content ui-widget-content">
   		<form action="">
   			<input />
   		</form>
   </div>
</div>
</body>
</html>