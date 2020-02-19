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
	
	/**
	 * Se activa validación de formularios
	 */
	$("form.validate").validate();
	
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
		
	/**
	 * Miga de pan
	 */
	$(".cl-menu a.cl-menu-activo").each(function() {
		$("ol.breadcrumb").append('<li class="breadcrumb-item">' + $(this).find("span").text() + '</li>');
	});
});

function obtenerParametroUrl(name){
   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
      return decodeURIComponent(name[1]);
}