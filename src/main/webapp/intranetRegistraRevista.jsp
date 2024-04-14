<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>

<title>Sistemas - Jorge Jacinto Gutarra</title>
</head>
<body>

<jsp:include page="intranetCabecera.jsp" />

<div class="container" style="margin-top: 5%">
	<h4>Registro Revista</h4>
</div>

<div class="container" style="margin-top: 2%">
<form id="id_form"> 
	<div class="row">
		<div class="form-group col-md-6">
				<label class="control-label" for="id_nombre">Nombres</label>
				<input class="form-control" type="text" id="id_nombre" name="nombre" placeholder="Ingrese el nombre">
		</div>
		<div class="form-group col-md-6">
				<label class="control-label" for="id_frecuencia">Frecuencia</label>
				<input class="form-control" type="text" id="id_frecuencia" name="frecuencia" placeholder="Ingrese la frecuencia">
		</div>
	</div>	
	<div class="row">
		<div class="form-group col-md-6">
				<label class="control-label" for="id_fecha">Fecha Creación</label>
				<input class="form-control" type="date" id="id_fecha" name="fechaCreacion">
		</div>
		<div class="form-group col-md-6">
				<label class="control-label" for="id_modalidad"> Modalidad </label> <select
					class="form-control" id="id_modalidad" name="modalidad" >
					<option value=" ">[Seleccione]</option>
				</select>
		</div>
	</div>
	<div class="row" align="center" style="margin-top: 2%">				
			<button type="button" class="btn btn-primary" id="id_btn_registra">Crea Revista</button>
	</div>	
</form>			
</div>


<script type="text/javascript">
$(document).ready(function() {
    $('#id_form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        
        fields:{
        	nombre : {  
        		selector: "#id_nombre",
        		validators : {
        			notEmpty: {
                        message: 'El nombre es requerido'
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: 'El nombre tiene de 3 a 30 caracteres'
                    },
        		}
        	},
        	frecuencia : {
        		selector: "#id_frecuencia",
        		validators : {
        			notEmpty: {
                        message: 'La frecuencia es requerida'
                    },
        		}
        	},
        	fecha : {
        		selector: "#id_fecha",
        		validators : {
        			notEmpty: {
                        message: 'La fecha de creación es requerida'
                    },
        		}
        	},
        	modalidad : {
        		selector: "#id_modalidad",
        		validators : {
        			notEmpty: {
                        message: 'La modalidad es requerida'
                    },
        		}
        	},        	
        }
  
    });

});
</script>


<script type="text/javascript">
	$("#id_btn_registra").click(function(){
		var validator = $('#id_form').data('bootstrapValidator');
	    validator.validate();
		
	    if (validator.isValid()) {
	        $.ajax({
	          type: "POST",
	          url: "registraRevista", 
	          data: $('#id_form').serialize(),
	          success: function(data){
	        	  mostrarMensaje(data.mensaje);
	        	  limpiarFormulario();
	        	  validator.resetForm();
	          },
	          error: function(){
	        	  mostrarMensaje(MSG_ERROR);
	          }
	        });
	        
	    }
	});

	$.getJSON("cargaModalidad", {}, function (data){
		$.each(data, function(index, item){
			$("#id_modalidad").append("<option value=" +  item.idModalidad +" >" +  item.descripcion + "</option>");
		});	
	});		

	
	function limpiarFormulario(){	
		$('#id_nombre').val('');
		$('#id_frecuencia').val('');
		$('#id_fecha').val('');
		$('#id_modalidad').val(' ');
	}
</script>
	
</body>
</html>