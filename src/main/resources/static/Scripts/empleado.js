$(function() {
	
	$("form .ddl-persona").change(function() {
		var elemento = $(this);
		solicitudAjax("/api/persona/" + elemento.val(), "POST", null, function(data) {
			$("form [name='codtipodocumento']").val(data.codtipodocumento);
			$("form [name='numerodocumento']").val(data.numerodocumento);
			$("form [name='nombre']").val(data.nombre1 + ' ' + data.nombre2 + ' ' + data.apellido1 + ' ' + data.apellido2);
			$("form [name='telefono']").val(data.telefono);
			$("form [name='correo']").val(data.correo);
			$("form [name='genero']").val(data.genero);
			$("form [name='fechanacimiento']").val(data.fechanacimiento && data.fechanacimiento.length >= 10 ? data.fechanacimiento.substring(0, 10) : '');
		}, function(err, er, e) {
			debugger;
		});
	});
	
});