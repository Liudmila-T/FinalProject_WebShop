<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
	<fmt:message key="list_products_jsp.title" var="title" />
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<!-- CSS styles	 -->
	<link href="resources/css/bootstrap.css" rel="stylesheet">
	<link id="switcher" href="resources/css/theme-color/lite-blue-theme.css" rel="stylesheet">
	<link href="resources/css/sequence-theme.modern-slide-in.css" rel="stylesheet" media="all">
	<link href="resources/css/style.css" rel="stylesheet">

	<body>
		<%@ include file="WEB-INF/jspf/header.jspf"%>
  <%--===========================================================================
	This is the CONTENT, containing the main part of the page.
	===========================================================================--%>
			<div id="aa-contact">
            <div class="container">

					<!-- Contact address -->

					<div class="aa-contact-address">
						<div class="row">
							<div class="col-md-16">
								<div class="aa-contact-address-left">
								<br> <br><br><br>
									<p>
										<fmt:message key="contact_jsp_info" />
										<br>

										<fmt:message key="contact_jsp_admin" />
										<br>
										095-812-82-92
										<br>
										timlv@gmail.com

									</p>

									<br> <br> <br> <br>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

		</div>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
		<!-- jQuery library -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<script src="resources/js/jquery.smartmenus.js" type="text/javascript"></script>
	<script src="resources/js/jquery.smartmenus.bootstrap.js" type="text/javascript"></script>
	<script src="resources/js/sequence.js"></script>
	<script src="resources/js/sequence-theme.modern-slide-in.js"></script>
	<script src="resources/js/cartbox.js"></script>


</body>

