<%--
  Created by IntelliJ IDEA.
  User: LeWis
  Date: 2017/9/28
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
    <title>电子处方</title>
    <style>
        .td50 {
            width: 50%;
        }

        table {
            width: 100%;

        }

        td {
            padding-top: 2px;
            vertical-align: bottom;
            text-align: left;
            font-size: 8px;
        }

        label {
            display: inline-block;
            padding-bottom: 5px;
            border-bottom: 1px solid #000
        }

        body{
            font-family: Helvetica, Georgia, Arial, sans-serif, 宋体;
            font-size: small;
        }

    </style>
</head>
<body>
<div style="width: 640px;margin-left: auto;margin-right: auto">
    <h3 style="text-align: center;">
        ${zhengheRx.rxName}
    </h3>
    <table>
        <tr>
            <td>费别：</td>
            <td>
                <input type="checkbox"/>公费
                <input type="checkbox" checked/>自费
                <br>
                <input type="checkbox"/>医保
                <input type="checkbox"/>其他
                <strong>自费病人</strong>
            </td>
            <td>处方编号：${zhengheRx.rxNo}</td>
        </tr>
        <tr>
            <td colspan="3">
                <hr/>
            </td>
        </tr>
    </table>
    <table>
        <tr>
            <td>姓名：<label style="width:60%;">${zhengheRx.patientName}</label></td>
            <td>姓别：<input type="checkbox" <c:if test="${zhengheRx.patientGender eq '男'}">checked</c:if>/>男<input type="checkbox" <c:if test="${zhengheRx.patientGender eq '女'}">checked</c:if>/>女</td>
            <td>年龄：<label style="width:60%;">${zhengheRx.patientAge}岁</label></td>
        </tr>
    </table>
    <table>
        <tr>
            <td class="td50">门诊/住院病例号：<label style="width:50%;"></label></td>
            <td class="td50">科别（病区/床位号）：<label style="width:40%;">${zhengheRx.category}</label></td>
        </tr>
    </table>
    <table>
        <tr>
            <td class="td50">临床诊断：<label style="width:60%;">${zhengheRx.clinicalDiagnosis}</label></td>
            <td class="td50" style="vertical-align: top">开具日期：<label style="width:50%;"><fmt:formatDate value="${zhengheRx.rxDate}" pattern="yyyy年MM月dd日"/></label></td>
        </tr>
    </table>
    <table>
        <tr>
            <td>住址/电话：<label style="width:80%;">${zhengheRx.patientAddress}  ${zhengheRx.patientPhone}</label></td>
        </tr>
    </table>
    <hr>
    <div><strong>Rp:</strong></div>
    <div style="min-height: 350px">
        <table>
            <c:forEach items="${zhengheRx.zhengheRxDetailList}" var="item" varStatus="i">

                <tr>
                    <td style="width: 30px;vertical-align: top"><c:out value="${(i.index + 1)}"></c:out>、</td>
                    <td><c:out value="${item.productName}"></c:out>&nbsp;x&nbsp;<c:out value="${item.num}"></c:out>&nbsp;&nbsp;<c:out value="${item.standard}"></c:out>
                        <br>Sig：<c:out value="${item.sig}"></c:out></td>
                </tr>
            </c:forEach>

           <%-- <tr>
                <td style="width: 30px;vertical-align: top">2、</td>
                <td>感冒清热颗粒 1盒（6包16g）<br>Sig：每日一包 3天服完</td>
            </tr>--%>
        </table>
    </div>

    <hr>
    <table>
        <tr>
            <td class="td50">医 师：<label style="width:50%;">${zhengheRx.doctor}</label></td>
            <td class="td50">药品金额：<label style="width:50%;">${zhengheRx.totalAmount}元</label></td>
        </tr>
        <tr>
            <td class="td50">审核 药师：<label style="width:50%;">&nbsp;${zhengheRx.approvalDoctor}</label></td>
            <td class="td50">调配药师/士：<label style="width:50%;">&nbsp;${zhengheRx.deployDoctor}</label></td>
        </tr>
        <tr>
            <td colspan="2">核对、发药药师：<label style="width:80%;">&nbsp;${zhengheRx.checkDoctor}</label></td>
        </tr>
        <tr>
            <td colspan="2">医师处方仅开具当日有效（医师证明除外）</td>
        </tr>
    </table>
</div>

</body>
</html>
