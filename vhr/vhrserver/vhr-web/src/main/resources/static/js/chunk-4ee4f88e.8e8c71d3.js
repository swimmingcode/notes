(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4ee4f88e"],{"0aaf":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v("综合信息统计")])},n=[],r={name:"StaAll"},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"16d82b4e",null);t["default"]=o.exports},1937:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v("工资表查询")])},n=[],r={name:"SalSearch"},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"cda973ce",null);t["default"]=o.exports},"21c7":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v("工资表管理")])},n=[],r={name:"SalTable"},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"32a9ca91",null);t["default"]=o.exports},"2bc5":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("div",{staticStyle:{display:"flex","justify-content":"left"}},[a("el-button",{attrs:{icon:"el-icon-plus",type:"primary"},on:{click:e.addSalary}},[e._v("添加工资账套")]),a("el-button",{attrs:{icon:"el-icon-refresh",type:"success"},on:{click:e.initSalaries}},[e._v("刷新")])],1),a("div",{staticStyle:{"margin-top":"10px"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.salares,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),a("el-table-column",{attrs:{width:"150",prop:"name",label:"账套名称"}}),a("el-table-column",{attrs:{width:"100",prop:"basicsalary",label:"基本工资"}}),a("el-table-column",{attrs:{width:"100",prop:"trafficsalary",label:"交通补助"}}),a("el-table-column",{attrs:{width:"100",prop:"lunchsalary",label:"午餐补助"}}),a("el-table-column",{attrs:{width:"100",prop:"bonus",label:"奖金"}}),a("el-table-column",{attrs:{width:"130",prop:"createdate",label:"启用时间"}}),a("el-table-column",{attrs:{label:"养老金",align:"center"}},[a("el-table-column",{attrs:{width:"120",prop:"pensionper",label:"比率"}}),a("el-table-column",{attrs:{width:"120",prop:"pensionbase",label:"基数"}})],1),a("el-table-column",{attrs:{label:"医疗保险",align:"center"}},[a("el-table-column",{attrs:{width:"120",prop:"medicalper",label:"比率"}}),a("el-table-column",{attrs:{width:"120",prop:"medicalbase",label:"基数"}})],1),a("el-table-column",{attrs:{label:"公积金",align:"center"}},[a("el-table-column",{attrs:{width:"120",prop:"accumulationfundper",label:"比率"}}),a("el-table-column",{attrs:{width:"120",prop:"accumulationfundbase",label:"基数"}})],1),a("el-table-column",{attrs:{label:"操作",align:"center",width:"200px"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"small"},on:{click:function(a){return e.editSalary(t.row)}}},[e._v("编辑")]),a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(a){return e.deleteSalary(t.row)}}},[e._v("删除")])]}}])})],1)],1),a("el-dialog",{attrs:{title:e.title,visible:e.dialogVisible,width:"50%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("div",{staticStyle:{display:"flex","justify-content":"space-around","align-items":"center"}},[a("el-steps",{attrs:{direction:"vertical",active:e.activeItemIndex}},e._l(e.salaryItemName,(function(e,t){return a("el-step",{key:t,attrs:{title:e}})})),1),e._l(e.salary,(function(t,l,n){return a("el-input",{directives:[{name:"show",rawName:"v-show",value:e.activeItemIndex==n,expression:"activeItemIndex == index "}],key:n,staticStyle:{width:"250px"},attrs:{placeholder:"请输入"+e.salaryItemName[n]+"...."},model:{value:e.salary[l],callback:function(t){e.$set(e.salary,l,t)},expression:"salary[title]"}})}))],2),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:e.preStep}},[e._v(e._s(10==e.activeItemIndex?"取消":"上一步"))]),a("el-button",{attrs:{type:"primary"},on:{click:e.nextStep}},[e._v(e._s(10==e.activeItemIndex?"完成":"下一步"))])],1)])],1)},n=[],r=(a("b0c0"),{name:"SalSob",data:function(){return{title:null,salares:[],dialogVisible:!1,activeItemIndex:0,salaryItemName:["基本工资","交通补助","午餐补助","奖金","养老金比率","养老金基数","医疗保险比率","医疗保险基数","公积金比率","公积金基数","账套名称"],salary:{basicsalary:0,trafficsalary:0,lunchsalary:0,bonus:0,pensionper:0,pensionbase:0,medicalper:0,medicalbase:0,accumulationfundper:0,accumulationfundbase:0,name:null}}},mounted:function(){this.initSalaries()},methods:{addSalary:function(){this.title="添加工资套账",this.dialogVisible=!0,this.activeItemIndex=0,this.salary={basicsalary:null,trafficsalary:null,lunchsalary:null,bonus:null,pensionper:null,pensionbase:null,medicalper:null,medicalbase:null,accumulationfundper:null,accumulationfundbase:null,name:null}},initSalaries:function(){var e=this;this.getRequest("/salary/sob/").then((function(t){e.salares=t}))},nextStep:function(){var e=this;if(this.activeItemIndex++,11==this.activeItemIndex)return this.salary.id?void this.putRequest("/salary/sob/",this.salary).then((function(t){t&&(e.dialogVisible=!1,e.initSalaries(),e.activeItemIndex=0)})):(delete this.salary.id,void this.postRequest("/salary/sob/",this.salary).then((function(t){e.initSalaries(),e.dialogVisible=!1,e.activeItemIndex=0})))},preStep:function(){if(0!=this.activeItemIndex)return 10==this.activeItemIndex?(this.dialogVisible=!1,void(this.activeItemIndex=0)):void this.activeItemIndex--},deleteSalary:function(e){var t=this;this.$confirm("此操作将永久删除【"+e.name+"】, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){t.deleteRequest("/salary/sob/"+e.id).then((function(e){t.initSalaries()}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))},editSalary:function(e){this.title="修改工资套账",this.dialogVisible=!0,this.salary={basicsalary:e.basicsalary,trafficsalary:e.trafficsalary,lunchsalary:e.lunchsalary,bonus:e.bonus,pensionper:e.pensionper,pensionbase:e.pensionbase,medicalper:e.medicalper,medicalbase:e.medicalbase,accumulationfundper:e.accumulationfundper,accumulationfundbase:e.accumulationfundbase,name:e.name,id:e.id}}}}),s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,null,null);t["default"]=o.exports},5865:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v("员工资料")])},n=[],r={name:"PerEmp"},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"18ce4408",null);t["default"]=o.exports},"5a4a":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v("员工调薪")])},n=[],r={name:"PerSalary"},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"a5756de0",null);t["default"]=o.exports},"6ac98":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("div",[a("el-table",{staticStyle:{width:"95%"},attrs:{data:e.emps,border:"",stripe:"",size:"mini"}},[a("el-table-column",{attrs:{type:"selection",align:"left",width:"55"}}),a("el-table-column",{attrs:{prop:"name",label:"姓名",width:"150"}}),a("el-table-column",{attrs:{prop:"workID",label:"工号",width:"150"}}),a("el-table-column",{attrs:{prop:"email",label:"电子邮件",width:"200"}}),a("el-table-column",{attrs:{prop:"phone",label:"电话号码",width:"180"}}),a("el-table-column",{attrs:{prop:"department.name",label:"所属部门",width:"120"}}),a("el-table-column",{attrs:{label:"工资套账",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.salary?a("el-tooltip",{attrs:{placement:"right"}},[a("div",{attrs:{slot:"content"},slot:"content"},[a("table",[a("tr",[a("td",[e._v("基本工资")]),a("td",[e._v(e._s(t.row.salary.basicsalary))])]),a("tr",[a("td",[e._v("交通补助")]),a("td",[e._v(e._s(t.row.salary.trafficsalary))])]),a("tr",[a("td",[e._v("午餐补助")]),a("td",[e._v(e._s(t.row.salary.lunchsalary))])]),a("tr",[a("td",[e._v("奖金")]),a("td",[e._v(e._s(t.row.salary.bonus))])]),a("tr",[a("td",[e._v("养老金比率")]),a("td",[e._v(e._s(t.row.salary.pensionper))])]),a("tr",[a("td",[e._v("养老金基数")]),a("td",[e._v(e._s(t.row.salary.pensionbase))])]),a("tr",[a("td",[e._v("公积金比率")]),a("td",[e._v(e._s(t.row.salary.accumulationfundper))])]),a("tr",[a("td",[e._v("公积金基数")]),a("td",[e._v(e._s(t.row.salary.accumulationfundbase))])]),a("tr",[a("td",[e._v("医疗保险比率")]),a("td",[e._v(e._s(t.row.salary.medicalper))])]),a("tr",[a("td",[e._v("医疗保险基数")]),a("td",[e._v(e._s(t.row.salary.medicalbase))])]),a("tr",[a("td",[e._v("启用时间")]),a("td",[e._v(e._s(t.row.salary.createdate))])])])]),a("el-tag",[e._v(e._s(t.row.salary.name))])],1):a("el-tag",[e._v("暂未设置")])]}}])}),a("el-table-column",{attrs:{label:"操作",align:"center",width:"200px"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-popover",{attrs:{placement:"left",title:"工资账套",width:"200",trigger:"click"},on:{show:function(a){return e.showPop(t.row.salary)},hide:function(a){return e.hidePop(t.row)}}},[a("el-select",{attrs:{placeholder:"请选择",size:"mini"},model:{value:e.selectSalary,callback:function(t){e.selectSalary=t},expression:"selectSalary"}},e._l(e.salaries,(function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1),a("el-button",{attrs:{slot:"reference",size:"small",type:"danger"},slot:"reference"},[e._v("修改工资套账")])],1)]}}])})],1),a("div",{staticStyle:{display:"flex","justify-content":"left"}},[a("el-pagination",{attrs:{background:"",layout:"sizes, prev, pager, next, jumper, ->, total, slot",total:e.total},on:{"current-change":e.currentChange,"size-change":e.sizeChange}})],1)],1)])},n=[],r={name:"SalSobCfg",data:function(){return{emps:[],salaries:[],selectSalary:null,page:1,size:10,total:10}},mounted:function(){this.initSalSobCfg(),this.initSalaries()},methods:{sizeChange:function(e){this.size=e,this.initSalSobCfg()},currentChange:function(e){this.page=e,this.initSalSobCfg()},initSalaries:function(){var e=this;this.getRequest("/salary/sobcfg/salaries").then((function(t){t&&(e.salaries=t)}))},initSalSobCfg:function(){var e=this;this.getRequest("/salary/sobcfg/?page="+this.page+"&size="+this.size).then((function(t){t&&(e.emps=t.data,e.total=t.total)}))},showPop:function(e){this.selectSalary=e?e.id:null},hidePop:function(e){var t=this;if(this.selectSalary){var a=e.id,l=this.selectSalary,n="/salary/sobcfg/?eid="+a+"&sid="+l;this.putRequest(n).then((function(e){e&&t.initSalSobCfg()}))}}}},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"f71d741e",null);t["default"]=o.exports},"768a":function(e,t,a){},"83ed":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v("员工培训")])},n=[],r={name:"PerTrain"},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"2320c55d",null);t["default"]=o.exports},a49a:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v("人事记录统计")])},n=[],r={name:"StaRecord"},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"47089697",null);t["default"]=o.exports},b419:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v("人事信息统计")])},n=[],r={name:"StaPers"},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"23b5e040",null);t["default"]=o.exports},bb51:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-container",[a("el-container",[a("el-header",{staticClass:"homeHeader"},[a("div",{staticClass:"title"},[e._v("微人事")]),a("el-dropdown",{staticClass:"userInfo",on:{command:e.commandHandler}},[a("span",{staticClass:"el-dropdown-link"},[e._v(" "+e._s(e.user.name)),a("i",[a("img",{attrs:{src:e.user.userface,alt:""}})])]),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("el-dropdown-item",{attrs:{command:"setting"}},[e._v(" 设置")]),a("el-dropdown-item",{attrs:{command:"logout",divided:""}},[e._v("注销登录")])],1)],1)],1),a("el-container",[a("el-aside",{attrs:{width:"200px"}},[a("el-menu",{attrs:{router:"","unique-opened":""}},e._l(e.routes,(function(t,l){return t.hidden?e._e():a("el-submenu",{key:l,attrs:{index:l+""}},[a("template",{slot:"title"},[a("i",{class:t.iconcls,staticStyle:{color:"#409eff","margin-right":"5px"}}),a("span",[e._v(e._s(t.name))])]),e._l(t.children,(function(t,l){return a("el-menu-item",{key:l,attrs:{index:t.path}},[e._v(" "+e._s(t.name)+" ")])}))],2)})),1)],1),a("el-main",["/home"!=this.$router.currentRoute.path?a("el-breadcrumb",{attrs:{"separator-class":"el-icon-arrow-right"}},[a("el-breadcrumb-item",{attrs:{to:{path:"/home"}}},[e._v("首页")]),a("el-breadcrumb-item",[e._v(e._s(this.$router.currentRoute.name))])],1):e._e(),"/home"==this.$router.currentRoute.path?a("div",{staticClass:"homeWelcome"},[e._v(" 欢迎来到微人事！ ")]):e._e(),a("router-view",{staticClass:"homeRouterView"})],1)],1)],1)],1)],1)},n=[],r=(a("ac1f"),a("5319"),{name:"Home",data:function(){return{user:JSON.parse(window.sessionStorage.getItem("user"))}},computed:{routes:function(){return this.$store.state.routes}},methods:{commandHandler:function(e){var t=this;"logout"==e?this.$confirm("此操作将注销登录, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){t.getRequest("/logout"),window.sessionStorage.removeItem("user"),t.$store.commit("initRoutes",[]),t.$router.replace("/")})).catch((function(){t.$message({type:"info",message:"已取消操作"})})):"userinfo"==e&&this.$router.push("/hrinfo")},menuClick:function(e,t){this.$router.push(e)}}}),s=r,i=(a("c6d4"),a("2877")),o=Object(i["a"])(s,l,n,!1,null,"0a3aa464",null);t["default"]=o.exports},c1d3:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v("员工积分统计")])},n=[],r={name:"StaScore"},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"35258b6d",null);t["default"]=o.exports},c6d4:function(e,t,a){"use strict";var l=a("768a"),n=a.n(l);n.a},cfbb:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v("员工调动")])},n=[],r={name:"PerMv"},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"5770ddae",null);t["default"]=o.exports},d9ac:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v(" 员工奖惩 ")])},n=[],r={name:"PerEc"},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"6d980ed6",null);t["default"]=o.exports},fbac:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e._v("月末处理")])},n=[],r={name:"SalMont"},s=r,i=a("2877"),o=Object(i["a"])(s,l,n,!1,null,"5d028d64",null);t["default"]=o.exports},feca:function(e,t,a){var l={"./Home.vue":"bb51","./Login.vue":"a55b","./emp/EmpAdv.vue":"8759","./emp/EmpBasic.vue":"58da","./per/PerEc.vue":"d9ac","./per/PerEmp.vue":"5865","./per/PerMv.vue":"cfbb","./per/PerSalary.vue":"5a4a","./per/PerTrain.vue":"83ed","./sal/SalMonth.vue":"fbac","./sal/SalSearch.vue":"1937","./sal/SalSob.vue":"2bc5","./sal/SalSobCfg.vue":"6ac98","./sal/SalTable.vue":"21c7","./sta/StaAll.vue":"0aaf","./sta/StaPers.vue":"b419","./sta/StaRecord.vue":"a49a","./sta/StaScore.vue":"c1d3","./sys/SysBasic.vue":"8d67","./sys/SysCfg.vue":"1fe7","./sys/SysData.vue":"d1e3","./sys/SysHr.vue":"418a","./sys/SysInit.vue":"8608","./sys/SysLog.vue":"864e"};function n(e){var t=r(e);return a(t)}function r(e){if(!a.o(l,e)){var t=new Error("Cannot find module '"+e+"'");throw t.code="MODULE_NOT_FOUND",t}return l[e]}n.keys=function(){return Object.keys(l)},n.resolve=r,e.exports=n,n.id="feca"}}]);
//# sourceMappingURL=chunk-4ee4f88e.8e8c71d3.js.map