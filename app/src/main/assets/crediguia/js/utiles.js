//JavaScript Document
function cargarEstructuraPagina(){

	mp = "<div data-role='header'> " +
              		"<h1>Mi Tarjeta</h1>"+
              		"<marquee behavior='scroll'  >" +
              		    "<strong class='bar_Bienvenida' >3ª Encuentro Argentina - Seguridad y Salud Ocupacional</strong>" +
              		     "</marquee> "+
          			    "<div data-role='navbar'>"+
          			    "  <ul>"+
          			        "<li><a href=\"javascript:siguiente('#bienvenida');\" class='ui-btn-active' >Mi Cuenta</a></li>"+
          			        "<li><a href=\"javascript:siguiente('#resumenes');\" >Resumenes</a></li>"+
          			        "<li><a href=\"javascript:siguiente('#pagos');\" >Pagos</a></li>"+
          			      "</ul>"+
          			    "</div>"+
          "</div>	";

	bmp=" <div data-role=\"header\" data-theme=\"b\">"+
              		" <h1><a href=\"#\" data-theme=\"b\">Mi Tarjeta</a></h1>"+
              " <div data-role=\"navbar\"> "+
                " <ul>"+
                  "<li><a href=\"#\" data-rel=\"back\" >Volver</a></li> "+
                  "<li><a href=\"#\" class=\"TituloDetalle\"> Titulo </a></li> "+
                "</ul>"+
              " </div>"+
            "</div>";

    pie =" <div data-role='navbar' class='cabecera' style='float:left;width: 100%' >  " +
		"<a href='#' data-rel='back'  class='ui-btn ui-icon-back ui-btn-icon-left' >Volver</a>"+
		" </div>" +
  "<marquee behavior='scroll'  >" +
  "<strong>3ª Encuentro Argentina - Seguridad y Salud Ocupacional</strong>" +
  "</marquee>"+
  " ";
  //console.log("JS: cargarEstructuraPagina ");
$('.CabeceraPrincipal').html(mp).enhanceWithin();
$('.CabeceraDetalleVolver').html(bmp).enhanceWithin();
//$('.mp',page).html(mp).enhanceWithin();
//$('.menusuperior',page).html(bmp).enhanceWithin();
//$('.pie',page).html(pie).enhanceWithin();;
}

 function siguiente(nextPage){
    //console.log("JS: siguiente " + nextPage);
    $.mobile.changePage($(nextPage),{reload:true,transition: 'flip',});

}

  jQuery.fn.serializeObject = function() {
  var arrayData, objectData;
  arrayData = this.serializeArray();
  objectData = {};

  $.each(arrayData, function() {
    var value;

    if (this.value != null) {
      value = this.value;
    } else {
      value = '';
    }

    if (objectData[this.name] != null) {
      if (!objectData[this.name].push) {
        objectData[this.name] = [objectData[this.name]];
      }

      objectData[this.name].push(value);
    } else {
      objectData[this.name] = value;
    }
  });

  return objectData;
};