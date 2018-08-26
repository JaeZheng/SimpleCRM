<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- 导入css样式的时候引入绝对路径 -->
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/css/addContract.css" />
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">

        function isnull(val) {
            var str = val.replace(/(^\s*)|(\s*$)/g, '');//把val首尾的空格去掉。

            if (str == '' || str == undefined || str == null) {//输入框中输入空格也为空
                return true;
            } else {
                return false;
            }
        }

        function check(){
            //检查用户输入修改信息格式是否正确
            var contracttime = document.getElementById('contracttime');
            var invoicedetail = document.getElementById('invoicedetail');
            var contractname = document.getElementById('contractname');
            var invoicetime = document.getElementById('invoicetime');
            var invoicetitle = document.getElementById('invoicetitle');
            var invoicenumber = document.getElementById('invoicenumber');
            var address = document.getElementById('address');
            var invoiceamount = document.getElementById('invoiceamount');
            var contractcontent = document.getElementById('contractcontent');

            if (isnull(contracttime.value)) {
                alert("合同时间不能为空!");
                return false;
            } else if (isnull(invoicedetail.value)) {
                alert("发票明细不能为空!");
                return false;
            } else if (isnull(contractname.value)) {
                alert("合同名称不能为空!");
                return false;
            } else if (isnull(invoicetime.value)) {
                alert("开票日期不能为空!");
                return false;
            } else if (isnull(invoicetitle.value)) {
                alert("发票抬头不能为空!");
                return false;
            } else if (isnull(invoicenumber.value)) {
                alert("发票编号不能为空!");
                return false;
            } else if (isnull(address.value)) {
                alert("办公地址不能为空!");
                return false;
            } else if (isnull(invoiceamount.value)) {
                alert("发票金额不能为空!");
                return false;
            } else if (isnull(contractcontent.value)) {
                alert("发票内容不能为空!");
                return false;
            }
        }

        //检查重复发票编号
        $("#invoicenumber").blur( function() {
            var url = "${pageContext.request.contextPath}/AddCompanyServlet";
            $.post(url,{method : "checkInvoiceExist", invoiceNumber : this.value}, function(data) {
                if (data == "true") {
                    $("#result1").html("&nbsp;<font color='red'>此发票编号已存在</font>");
                } else {
                    $("#result1").html("&nbsp;<font color='blue'>此发票编号可用</font>");
                }
            });
        });
    </script>
</head>
<body>
<form method="post"
      action="<%=request.getContextPath()%>/AddContractServlet?method=addContract">
    <fieldset>
        <legend>合同信息</legend>
        <table align="center">
            <tr>
                <td>签合同时间(*):</td>
                <td><input type="text" name="contracttime" id="contracttime"><span
                        id="result"></span></td>
                <td>对应发票明细(*):</td>
                <td><input type="text" name="invoicedetail" id="invoicedetail"></td>
            </tr>
            <tr>
                <td>合同名称(*):</td>
                <td><input type="text" name="contractname" id="contractname"></td>
                <td>开票时间(*):</td>
                <td><input type="text" name="invoicetime" id="invoicetime"></td>
            </tr>
            <tr>
                <td>对应发票抬头(*):</td>
                <td><input type="text" name="invoicetitle" id="invoicetitle"><span
                        id="result1"></span></td>
                <td>发票编号(*):</td>
                <td><input type="text" name="invoicenumber" id="invoicenumber"></td>
            </tr>
            <tr>
                <td>办公地址(*):</td>
                <td><input type="text" name="address" id="address"></td>
                <td>开票金额(*):</td>
                <td><input type="text" name="invoiceamount" id="invoiceamount"></td>
            </tr>
            <tr>
                <td>合同内容(*):</td>
                <td colspan="3"><input type="text" name="contractcontent" id="contractcontent"></td>
            </tr>
            <%--<tr>--%>
            <%----%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%----%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%----%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%----%>
            <%--</tr>--%>
            <!-- <tr align="center">
                <td></td>
                <td></td>
                <td colspan="2"><br> <br> <input type="submit"
                                                 name="button" id="bubmitBtn" class="but" value="提交"></td>
            </tr> -->
        </table>
        <br>
        <input align="center" type="submit" name="button" onclick="return check()"
               id="bubmitBtn" class="but" value="提交">
    </fieldset>
</form>
</body>
</html>