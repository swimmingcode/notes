<%--
  Created by IntelliJ IDEA.
  User: 17567
  Date: 2020/3/9
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/role.js"></script>
    <style>
        #dialog #myform, .panel-header{
            height: 25px;
        }

        #dialog #myform .panel-title{
            color: #171e24;
            margin-top: -5px;
        }
    </style>
</head>
<body>

<%--角色页面与员工页面进入后都是列表--%>
<table id="rol_dg"></table>

<div id="toolbar">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="remove">删除</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" id="reload">刷新</a>
</div>

<div id="dialog">
    <form id="myform">
        <table align="center" style="border-spacing: 5px 20px">
            <input type="hidden" name="rid">
            <tr align="center">
                <td>角色类型: <input type="text" name="rnum"  class="easyui-validatebox" data-options="required:true"></td>
                <td>角色名称: <input type="text" name="rname" class="easyui-validatebox" data-options="required:true" ></td>
            </tr>
            <tr>
                <td><div id="role_data1"></div></td>
                <td><div id="role_data2"></div></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
