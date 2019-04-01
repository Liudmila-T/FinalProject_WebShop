<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
	<fmt:message key="list_users_jsp.title" var="title" />
	<%@ include file="/WEB-INF/jspf/head.jspf"%>

	<body>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		
		<div class="container">
		<form id="users" action="controller">
						<input type="hidden" name="command" value="changeRole" />
						<table id="list_users_table" class="table table-hover">
							<thead>
								<tr>
									<td>№</td>
									<td><fmt:message key="list_users_jsp.table.userId" /></td>
									<td><fmt:message key="list_users_jsp.table.login" /></td>
									<td><fmt:message key="list_users_jsp.table.name" /></td>
									<td><fmt:message key="list_users_jsp.table.email" /></td>
									<td><fmt:message key="list_users_jsp.table.roleId" /></td>
									<td><fmt:message key="list_users_jsp.table.roleId_change" /></td>
									<td><input type="submit" class="btn btn-sm btn-warning"
									value="<fmt:message key="list_users_jsp.table.button.execute" />"></td>
								</tr>
							</thead>

							<c:set var="k" value="0" />
							<c:forEach var="item" items="${userBeans}">
								<c:set var="k" value="${k+1}" />
								<tr>
								<td>${k}</td>
								<td>${item.userId}</td>
								<td>${item.userLogin}</td>
								<td>${item.userName}</td>
								<td>${item.userEmail}</td>
								<td>${item.roleName}</td>
									<td><select name="roleName" class="form-control input-sm">
										<option value="${item.roleName}">
												${item.roleName}
										</option>
										<option value="admin">
											<fmt:message key="list_users_jsp.table.admin" />
										</option>
										<option value="client">
											<fmt:message key="list_users_jsp.table.client" />
										</option>
										<option value="blocked_user">
											<fmt:message key="list_users_jsp.table.blocked_user" />
										</option>
									</select></td>
								</tr>
							</c:forEach>
						</table>
					</form>
					

						
		</div>

</body>
</html>