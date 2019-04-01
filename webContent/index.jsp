<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
	<fmt:message key="list_products_jsp.title" var="title" />
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<!-- CSS styles	 -->
	<link href="resources/css/bootstrap.css" rel="stylesheet">
	<link id="switcher" href="resources/css/theme-color/lite-blue-theme.css" rel="stylesheet">
	<link href="resources/css/sequence-theme.modern-slide-in.css" rel="stylesheet" media="all">
	<link href="resources/css/style.css" rel="stylesheet">

	<body>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<br> <br>
               <div id="aa-slider">
	<div class="aa-slider-area">
		<div id="sequence" class="seq">
			<div class="seq-screen">

				<!-- start slider body -->
				<ul class="seq-canvas">

					<li>
						<div class="seq-model">
							<a href="/controller?command=listProducts">
							<img data-seq src="resources/images/slider/11.jpg"  alt="SLIDE IMAGE NUMBER 1"/>
							</a>

						</div>
					</li>

					<li>
						<div class="seq-model">
							<a href="/controller?command=listProducts">
							<img data-seq src="resources/images/slider/222.jpg" alt="SLIDE IMAGE NUMBER 2" />
							</a>
						</div>

					</li>

				</ul>
				<!-- end slider body -->

			</div>

			<!-- slider navigation btn -->
			<fieldset class="seq-nav" aria-controls="sequence" aria-label="Slider buttons">
				<a type="button" class="seq-prev" aria-label="Previous">
				</a>
				<a type="button" class="seq-next" aria-label="Next">
					<span class="fa fa-angle-right"></span>
				</a>
			</fieldset>

		</div>
	</div>
</div>

<br> <br>
<div class="container">
		<p class="text-muted">

Kids Toys™ <br>
		</p>
</div>
<br> <br>
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

