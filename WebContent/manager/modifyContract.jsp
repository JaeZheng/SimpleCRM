<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- 导入css样式的时候引入绝对路径 -->
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/css/modifyContract.css" />
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#buttonbin").click(function() {
                //检查输入信息是否不为空钩
                //alet("--");
                var $cindexEle = $("#index");
                var reg = /\s/;
                if (!reg.test($cindexEle)) {
                    alert("输入信息为空，请重新输入");
                    //$cindexEle[0].focus();
                    return false;
                }
                return true;
            });

            $("#buttonbin1")
                .click(
                    function() {
                        //检查用户输入修改信息格式是否正确
                        //检查姓名: 姓名只能输入中文且不能为空
                        //alert("--");
                        /*
                        var $nameEle = $("#linkman");
                        var reg = /^[\u4e00-\u9fa5]{1,5}$/;
                        if (!reg.test($nameEle.val())) {
                            alert("姓名只能输入中文且不能为空,最少1个汉字最多5个汉字");
                            $nameEle[0].focus();
                            return false;
                        }

                        //手机号码必须为11位
                        var $phoneEle = $("#linkphone");
                        reg = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^0?[1][358][0-9]{9}$)/;
                        if (!reg.test($phoneEle.val())) {
                            $phoneEle[0].focus();
                            alert("手机格式不正确!");
                            return false;
                        }
                        */
                        return true;
                    });

        });
    </script>
</head>
<body>
<form action="<%=request.getContextPath()%>/GetContractServlet"
      method="post">
    <fieldset>
        <legend>
            <font color="#FFFFFF">合同信息查找栏</font>
        </legend>
        <table>
            <tr>
                <td><input type="text" class="input"
                           placeholder="请输入你要修改的合同发票编号" id="index" name="cindex">
                    <input type="submit" class="but" id="buttonbin" value="搜索"></td>
            </tr>
        </table>
    </fieldset>
</form>
<br>
<br>
<fieldset>
    <legend>
        <font color="#FFFFFF">合同信息显示栏</font>
    </legend>
    <c:choose>
        <c:when test="${empty Contract}">
            <font color="#FFFFFF">您输入的是:${requestScope.result}<br> <br>无此合同信息，请检查您是否已搜索或发票编号填写是否正确。
            </font>
        </c:when>
        <c:when test="${!empty Contract}">
            <table class="bordered" bgcolor="#FFFFFF">
                <tr>
                    <td width="60px">签合同时间</td>
                    <td width="180px">合同名称</td>
                    <td width="180px">对应发票抬头</td>
                    <td width="50px">公司地址</td>
                    <td width="50px">合同内容</td>
                    <td width="80px">对应发票明细</td>
                    <td width="50px">开票时间</td>
                    <td width="50px">发票编号</td>
                    <td width="50px">开票金额</td>
                </tr>
                <tr>
                    <td width="60px">${Contract.contracttime}</td>
                    <td width="180px">${Contract.contractname}</td>
                    <td width="180px">${Contract.invoicetitle}</td>
                    <td width="50px">${Contract.address}</td>
                    <td width="50px">${Contract.contractcontent}</td>
                    <td width="80px">${Contract.invoicedetail}</td>
                    <td width="50px">${Contract.invoicetime}</td>
                    <td width="50px">${Contract.invoicenumber}</td>
                    <td width="50px">${Contract.invoiceamount}</td>
                </tr>
            </table>
        </c:when>
    </c:choose>

</fieldset>
<br>
<br>
<form action="<%=request.getContextPath()%>/ModifyContractServlet"
      method="post">
    <fieldset>
        <legend>
            <font color="#FFFFFF">合同信息修改栏</font>
        </legend>
        <c:choose>
            <c:when test="${empty Contract}">
                <font color="#FFFFFF">请先进行查询操作。</font>
            </c:when>
            <c:when test="${!empty Contract}">
                <table class="bordered" bgcolor="#FFFFFF">
                    <tr>
                        <td>签合同时间(*)</td>
                        <td>合同名称(*)</td>
                        <td>对应发票抬头(*)</td>
                        <td>公司地址(*)</td>
                        <td>合同内容(*)</td>
                        <td>对应发票明细(*)</td>
                        <td>开票时间(*)</td>
                        <td>发票编号(*)</td>
                        <td>开票金额(*)</td>
                    </tr>
                    <tr>
                        <td width="50px"><input type="text" class="input1"
                                                name="contracttime" id="contracttime" value="${Contract.contracttime}"></td>
                        <td width="180px"><input type="text" class="input1"
                                                 name="contractname" id="contractname" value="${Contract.contractname}"></td>
                        <td width="180px"><input type="text" class="input1"
                                                 name="invoicetitle" id="invoicetitle" value="${Contract.invoicetitle}"></td>
                        <td width="80px"><input type="text" class="input1"
                                                name="address" id="address" value="${Contract.address}"></td>
                        <td width="50px"><input type="text" class="input1"
                                                name="contractcontent" id="contractcontent" value="${Contract.contractcontent}"></td>
                        <td width="60px"><input type="text" class="input1"
                                                name="invoicedetail" id="invoicedetail" value="${Contract.invoicedetail}"></td>
                        <td width="50px"><input type="text" class="input1"
                                                name="invoicetime" id="invoicetime" value="${Contract.invoicetime}"></td>
                        <td width="50px"><input type="text" class="input1"
                                                name="invoicenumber" id="invoicenumber" value="${Contract.invoicenumber}"></td>
                        <td width="50px"><input type="text" class="input1"
                                                name="invoiceamount" id="invoiceamount" value="${Contract.invoiceamount}"></td>
                    </tr>
                </table>
                <br>
                <font color="#FFFFFF">Tips：直接在上方表格中直接输入对应的修改内容，带(*)的一定要有，其他信息无需修改的可以不输入。</font>
                <table>
                    <tr>
                        <td><input type="hidden" id="id" name="id"
                                   value="${Contract.id}"> <input type="submit"
                                                                  id="buttonbin1" class="but1" value="提交"></td>
                    </tr>
                </table>
            </c:when>
        </c:choose>
    </fieldset>
</form>
</body>
</html>