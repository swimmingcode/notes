<%--
  Created by IntelliJ IDEA.
  User: 游建成
  Date: 2020/3/17
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/menu.js"></script>
</head>
<body>

<!-- 数据表格 -->
<table id="menu_datagrid">
</table>
<!-- 数据表格CRUD按钮 -->
<div id="menu_toolbar">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" id="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="del">刪除</a>
    </div>
</div>

<div id="menu_dialog">
    <form id="menu_form" method="post">
        <table align="center" style="margin-top: 15px;">
            <input type="hidden" name="id">
            <tr>
                <td>名称</td>
                <td><input type="text" name="text" class="easyui-validatebox" data-options="required:true"></td>
            </tr>
            <tr>
                <td>url</td>
                <td><input type="text" name="url"></td>
            </tr>
            <tr>
                <td>父菜单</td>
                <td><input type="text" id="parentSelect" name="parent.id" class="easyui-combobox" placeholder="请选择父菜单"/></td>
            </tr>
        </table>
    </form>
</div>

<!-- 对话框保存取消按钮 -->
<div id="menu_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>

</body>
</html>
