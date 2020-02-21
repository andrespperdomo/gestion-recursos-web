$(function() {
	cargarEventosTareas();
});

function cargarEventosTareas() {
	
	$("select.cl-tarea-ddl-proyecto").unbind("change").change(function() {
		$(".cl-tarea-item select[name='codigoproyecto']").val($(this).val());
	});
	
	$(".cl-tarea-item select[name='codtareatipo']").unbind("change").change(function() {
		$(this).parents(".cl-tarea-item").css("background-color", $(this).find(":selected").attr("data-color"));
	});
}