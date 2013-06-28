<!DOCTYPE html>
<html lang="en">
<head>
<title>Ingresá - DocNet</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<script type="text/javascript" src="js/jquery-1.6.js" ></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script>
<script type="text/javascript" src="js/Swis721_Cn_BT_400.font.js"></script>
<script type="text/javascript" src="js/Swis721_Cn_BT_700.font.js"></script>
<script type="text/javascript" src="js/tabs.js"></script>

  <!--[if lt IE 9]>
  	<script type="text/javascript" src="js/html5.js"></script>
	<style type="text/css">
		.bg{ behavior: url(js/PIE.htc); }
	</style>
  <![endif]-->
	<!--[if lt IE 7]>
		<div style=' clear: both; text-align:center; position: relative;'>
			<a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/images/upgrade.jpg" border="0"  alt="" /></a>
		</div>
	<![endif]-->

</head>

<body id="page3">
	<div class="body1">
	<div class="body2">
	<div class="body5">
		<div class="main">
<!-- header -->
			<header>
				<div class="wrapper">
				<h1><a href="index.html" id="logo">Progress Business Company</a></h1>
				<nav>
					<ul id="menu">
						<li id="nav1"><a href="index.jsp">Inicio<span>Bienvenido!</span></a></li>
						<li id="nav2"><a href="registrate.jsp">Nuevo?<span>Registrate</span></a></li>
						<li id="nav3" class="active"><a href="Services.jsp">Ingrese<span>a doc.net</span></a></li>
						<li id="nav4"><a href="ingresaDoctor.jsp">Dudas?<span>FAQ</span></a></li>
						<li id="nav5"><a href="agregarDoctor2.jsp">Contactese<span>con nosotros</span></a></li>
					</ul>
				</nav>
				</div>
				<div class="wrapper">
					<div class="slider">
					  <ul class="items">
						<li><img src="images/img1.jpg" alt=""></li>
						<li><img src="images/img2.jpg" alt=""></li>
						<li><img src="images/img3.jpg" alt=""></li>
						<li><img src="images/img4.jpg" alt=""></li>
					  </ul>
					</div>
				</div>
			</header><div class="ic">More Website Templates  at TemplateMonster.com!</div>
<!-- header end-->
		</div>
	</div>
	</div>
	</div>
	<div class="body3">
		<div class="main">
<!-- content -->
			<article id="content">
				<div class="wrapper">
					<h2 class="under">Ingresa a Doc.Net</h2>
					<div class="wrapper"><div id="content" align="center">

       <img src="images/page3_img3.gif" alt=""> <div id="message">

            <c:if test="${error != null}">
                <c:out value="Check username or password."/>
            </c:if>

        </div>

        <form method="post" action="j_security_check">
            <table class="Box">
                <tr>
                    <td>mail:</td>
                    <td><input id="j_username" name="j_username" placeholder="Inserta tu mail"></td>
                </tr>

                <tr>
                    <td>Password :</td>
                    <td><input id="j_password" name="j_password" type="password" placeholder="Inserta tu password"></td>
                </tr>

            </table>
            <table>
                <td><input type="submit" class="button" value="Ingresar"></td>
                <td>
                    <c:if test="${error != null}">
                        <a href="register.jsp" class="button">No tenes usuario? Registrate!</a>
                    </c:if>
                </td>
            </table>


        </form>

    </div>

					</div>
				</div>

			</article>
		</div>
	</div>
	<div class="body4">
		<div class="main">
			<article id="content2">
				<div class="wrapper">
					<section class="col3 pad_left2">
						<h4>Direcci�n</h4>
						<ul class="address">
							<li><span>Country:</span>Argentina</li>
							<li><span>City:</span>Buenos Aires</li>
							<li><span>Phone:</span>0 800 docnet</li>
						</ul>
					</section>
					<section class="col3 pad_left2">
						<h4>Seguinos</h4>
						<ul id="icons">
							<li><a href="http://www.facebook.com"><img src="images/icon1.jpg" alt="">Facebook</a></li>
							<li><a href="http://www.twitter.com"><img src="images/icon2.jpg" alt="">Twitter</a></li>
							<li><a href="http://www.linkedIn.com"><img src="images/icon3.jpg" alt="">LinkedIn</a></li>
							
						</ul>
					</section>
					
				</div>
			</article>
<!-- content end -->
		</div>
	</div>
		<div class="main">
<!-- footer -->
			<footer>
				Copyright 2012 - Doc.Net<br>
				Sitio Creado por Mateus, Stessens para la materia Lab1
			</footer>
<!-- footer end -->
		</div>
<script type="text/javascript"> Cufon.now(); </script>
<script>
	$(document).ready(function() {
		tabs.init();
	})
</script>
</body>
</html>