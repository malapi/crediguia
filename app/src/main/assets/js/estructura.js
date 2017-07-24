//JavaScript Document	
function caargarEstructuraPagina(page){
/*	mp ="<div data-role='panel' id='menuPrincipal'> " +
			"    <h2>Menu</h2> " +
			"       <a href='index.html' data-ajax='false' class='ui-btn ui-btn-inline ui-corner-all ui-shadow' >Inicio</a>" +
			"       <a href='comite.html' data-ajax='false' class='ui-btn ui-btn-inline ui-corner-all ui-shadow' >Organizador</a>" +
			" 		<a href='informacion.html' data-ajax='false' class='ui-btn ui-btn-inline ui-corner-all ui-shadow' >Informacion</a> " +
			"		<a href='programa_1.html' data-ajax='false' class='ui-btn ui-btn-inline ui-corner-all ui-shadow' >Programa</a> " +
			"		<a href='auspiciantes.html' data-ajax='false' class='ui-btn ui-btn-inline ui-corner-all ui-shadow' >Auspiciantes</a> " +
			"</div> " +
			
			"";*/
	
	/*mp ="<div data-role='panel' id='menuPrincipal'> " +
			"		<ul data-role='listview' data-inset='true' data-theme='c'>" +
			"        	<li data-icon='custom' id='skull'><a href='index.html'>Inicio</a></li>" +
			"	        <li data-icon='delete'><a href='comite.html'>Organizador</a></li>" +
			"        	<li data-icon='info'><a href='programa_1.html'>Programa</a></li>" +
			"          <li data-icon='false'><a href='auspiciantes.html'>Auspiciantes</a></li> " +
					"</ul> " +
		"</div>";*/
	mp = "";
	
	bmp="<img style='float:left;width: 100%' alt='Banner' src='img/logo.jpg'>  ";
	
pie ="<marquee behavior='scroll'  >" +
  "<strong>Crediguia Pie</strong>" +
  "</marquee>"+
  " ";
$('.mp').html("").enhanceWithin();
$('.mp',page).html(mp).enhanceWithin();
$('.menusuperior',page).html(bmp).enhanceWithin();
$('.pie',page).html(pie).enhanceWithin();;
}
