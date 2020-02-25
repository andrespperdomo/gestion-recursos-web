$(function() {
	
	/**
	 * Menú principal
	 */
	var clm = obtenerParametroUrl("clm"); // Parámetro estándar para menú activo
	if (!clm)
		clm = "/"; // Por defecto menú de inicio
	$(".cl-menu a[href$='" + clm + "']")
		.addClass("cl-menu-activo")
		.parents(".cl-menu-group")
			.addClass("show").each(function() {
				var element = $(this);
				$(".cl-menu").find("a[href='#" + element.attr("id") + "']").addClass("cl-menu-activo");
			});
	clTablasBotonConsultarMantenerMenu();
	
	/**
	 * Se activa validación de formularios
	 */
	$("form.validate").validate({
		errorPlacement: function ( error, element ) {	
	        if(element.parent().hasClass('input-group') || element.hasClass('selectpicker')){
	          error.insertAfter( element.parent() );
	        } else {
	          	error.insertAfter( element );
	        }	
		}
	});
	// Mantener el parámetro del menú a través de los formularios
	clFormMantenerMenu();
	
	jQuery.validator.addMethod("notEqual", function (value, element, param) {
	    return this.optional(element) || value != '0';
	});
	
	/**
	 * Se implementan los DatePicker
	 */
	$("input.cl-date-picker").each(function() {
		$(this).datepicker({
			locale: 'es-es',
	        uiLibrary: 'bootstrap4',
	        format: 'dd/mm/yyyy'
	    });
	});
	$("input.cl-datetime-picker").each(function() {
		$(this).datetimepicker({
			locale: 'es-es',
	        uiLibrary: 'bootstrap4',
	        format: 'dd/mm/yyyy hh:MM tt',
	        mode: 'ampm'
	    });
	});
		
	/**
	 * Miga de pan
	 */
	$(".cl-menu a.cl-menu-activo").each(function() {
		$("ol.breadcrumb").append('<li class="breadcrumb-item">' + $(this).find("span").text() + '</li>');
	});
	
	
	/* "Autocomplete" */
	$.fn.selectpicker.Constructor.BootstrapVersion = '4';

});

function obtenerParametroUrl(name){
   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
      return decodeURIComponent(name[1]);
}

function clTablasBotonConsultarMantenerMenu() {
	var clm = obtenerParametroUrl("clm"); // Parámetro estándar para menú activo
	// Se agrega el parámetro clm a los link "consultar" que se encuentran en las rejillas de resultados
	if (clm) {
		$(".cl-tabla tbody tr td:first-child a").each(function() {
			if ($(this).attr("href").indexOf("clm=") < 0) {
				$(this).attr("href", $(this).attr("href") + ($(this).attr("href").indexOf("?") >= 0 ? "&" : "?") + "clm=" + clm);
			}
		});
	}
}

function clFormMantenerMenu() {
	var clm = obtenerParametroUrl("clm"); // Parámetro estándar para menú activo
	// Se agrega el parámetro clm a los formularios
	if (clm) {
		$("form.validate").each(function() {
			if ($(this).attr("action").indexOf("clm=") < 0) {
				$(this).attr("action", $(this).attr("action") + ($(this).attr("action").indexOf("?") >= 0 ? "&" : "?") + "clm=" + clm);
			}
		});
	}
}
