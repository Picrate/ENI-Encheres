<%
String title = request.getParameter("title");
String subtitle = request.getParameter("subtitle");
%>

<header class="row">
	<% if (title != "") { %>
	<h1><%= title %></h1>
	<% } %>
	
	<% if (subtitle != "" && subtitle != null) { %>
	<h2><%= subtitle %></h2>
	<% } %>
</header>