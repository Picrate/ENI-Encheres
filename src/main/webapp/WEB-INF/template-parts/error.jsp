<%-- Librairies --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="error" class="row">
	<c:if test="${ !empty listeErreurs }">
		<c:forEach items="${listeErreurs}" var="erreur">
			<p><c:out value="${erreur}"/></p>
		</c:forEach>
	
		
	</c:if>
</div>