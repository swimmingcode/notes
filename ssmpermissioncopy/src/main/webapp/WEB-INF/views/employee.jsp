<%--
  Created by IntelliJ IDEA.
  User: 17567
  Date: 2020/3/9
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/employee.js"></script>
</head>
<body>

    <div id="excelUpload">
        <form method="post" enctype="multipart/form-data" id="uploadForm">
            <tabel>
                <tr>
                    <td><input type="file" name="excel" style="width: 180px; margin-top: 20px; margin-left: 5px;"></td>
                    <td><a href="#" id="downloadTml">下载模板</a></td>
                </tr>
            </tabel>
        </form>
    </div>

    <div id="employee_dg"></div>
    <%--添加标签--%>
    <div id="tb">
        <shiro:hasPermission name="employee:add">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
       </shiro:hasPermission>
        
        <shiro:hasPermission name="employee:edit">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
        </shiro:hasPermission>
           
        <shiro:hasPermission name="employee:delete">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="delete">离职</a>
        </shiro:hasPermission>

        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" id="reload">刷新</a>
        <input type="text" name="keyword" style="width: 200px; height: 30px;padding-left: 5px;">
        <a class="easyui-linkbutton" iconCls="icon-search" id="searchbtn">查询</a>
        <a  class="easyui-linkbutton" iconCls="icon-edit" id="excelOut">导出</a>
        <a  class="easyui-linkbutton" iconCls="icon-edit" id="excelIn">导入</a>
    </div>

    <div id="dialog">
        <form id="myform" method="post">
            <table align="center" style="border-spacing: 0px 10px">
                <input type="hidden" name="empId">
                <input type="hidden" name="empState">
                <tr>
                    <td>用户名:</td>
                    <td><input type="text" name="empUsername" class="easyui-validatebox" data-options="required:true"></td>
                </tr>
                <tr id="password">
                    <td>密码:</td>
                    <td><input type="text"  name="empPassword" class="easyui-validatebox"></td>
                </tr>
                <tr>
                    <td>手机:</td>
                    <td><input type="text" name="empTel" class="easyui-validatebox" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>邮箱:</td>
                    <td><input type="text" name="empEmail" class="easyui-validatebox" data-options="required:true,validType:'email'" ></td>
                </tr>

                <tr>
                    <td>入职日期:</td>
                    <td><input type="text"  name="empInputtime" class="easyui-datebox" data-options="required:true"></td>
                </tr>

                <tr>
                    <td>部门:</td>
                    <td><select id="department" name="department.depId" class="easyui-datebox" data-options="required:true"></select></td>
                </tr>
                <tr>
                    <td>角色:</td>
                    <td><select id="role" name="empRole" class="easyui-datebox" data-options="required:true"></select></td>
                </tr>
                <tr>
                    <td>是否管理员:</td>
                    <td><select id="admin" name="empAdmin" class="easyui-datebox" data-options="required:true"></select></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
