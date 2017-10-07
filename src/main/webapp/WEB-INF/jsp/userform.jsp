<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>--%>
<%--
  Created by IntelliJ IDEA.
  User: Chaklader
  Date: 10/5/17
  Time: 2:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Form Update</title>
</head>

<jsp:include page="header.jsp"/>

<body>
<div class="container">
    <c:choose>
        <c:when test="${userForm['new']}">
            <h1>Add User</h1>
        </c:when>
        <c:otherwise>
            <h1>Update User</h1>
        </c:otherwise>
    </c:choose>
    <br/>

    <%--@elvariable id="updateUser" type="java"--%>
    <form:form class="form-horizontal" method="post" action="/users" modelAttribute="updateUser">

        <form:hidden path="id"/>

        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Name</label>
                <div class="col-sm-10">
                    <form:input path="name" type="text" class="form-control " id="name" placeholder="Name"/>
                    <form:errors path="name" class="control-label"/>
                </div>
            </div>
        </spring:bind>


        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                    <form:input path="email" class="form-control" id="email" placeholder="Email"/>
                    <form:errors path="email" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <form:password path="password" class="form-control" id="password" placeholder="password"/>
                    <form:errors path="password" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">confirm Password</label>
                <div class="col-sm-10">
                    <form:password path="confirmPassword" class="form-control" id="password" placeholder="password"/>
                    <form:errors path="confirmPassword" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="address">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Address</label>
                <div class="col-sm-10">
                    <form:textarea path="address" id="address" placeholder="address"
                                   class="form-control" rows="5"/>
                    <form:errors path="address" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="newsletter">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Newsletter</label>
                <div class="col-sm-10">
                    <div class="checkbox">
                        <label> <form:checkbox path="newsletter" id="newsletter"/>
                        </label>
                        <form:errors path="newsletter" class="control-label"/>
                    </div>
                </div>
            </div>
        </spring:bind>

        <%--frameworks list--%>
        <spring:bind path="framework">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Web frameworks</label>
                <div class="col-sm-10">
                    <form:checkboxes path="framework" items="${frameworks}"/>
                    <form:errors path="framework" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="sex">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Sex</label>
                <div class="col-sm-10">
                    <label class="radio-inline">
                        <form:radiobutton path="sex" value="M"/>Male
                    </label>
                    <label class="radio-inline">
                        <form:radiobutton path="sex" value="F"/>Female
                    </label>
                    <form:errors path="sex" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="number">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Number</label>
                <div class="col-sm-10">
                    <form:radiobuttons path="number" items="${numbers}"/>
                    <br/>
                    <form:errors path="number" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="country">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Country</label>
                <div class="col-sm-5">
                    <form:select path="country" class="form-control">
                        <form:option value="NONE" label="--- Select ---"/>
                        <form:options items="${countries}"/>
                    </form:select>
                    <form:errors path="country" class="control-label"/>
                </div>
                <div class="col-sm-5"></div>
            </div>
        </spring:bind>

        <spring:bind path="skill">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Java Skills</label>
                <div class="col-sm-5">
                    <form:select path="skill" items="${skills}" multiple="true" size="5" class="form-control"/>
                    <form:errors path="skill" class="control-label"/>
                </div>
                <div class="col-sm-5"></div>
            </div>
        </spring:bind>

        <%--submit the profile info--%>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${userForm['new']}">
                        <button type="submit" class="btn-lg btn-primary pull-right">Add</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn-lg btn-primary pull-right">Update</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

    </form:form>
</div>
<jsp:include page="footer.jsp"/>
</body>

</html>
