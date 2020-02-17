$(function() {
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
	$("input.cl-date-picker").datepicker({
		locale: 'es-es',
        uiLibrary: 'bootstrap4',
        format: 'dd/mm/yyyy'
    });
		
});