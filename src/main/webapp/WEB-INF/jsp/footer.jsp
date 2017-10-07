<%--
  Created by IntelliJ IDEA.
  User: Chaklader
  Date: 10/3/17
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
    <hr>
    <footer>
    </footer>
</div>



<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>

<spring:url value="${pageContext.request.contextPath}/resources/js/custom.js" var="coreJs"/>
<spring:url value="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"
            var="bootstrapJs"/>

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>



