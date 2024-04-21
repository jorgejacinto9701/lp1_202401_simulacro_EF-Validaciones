
<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>

<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>

<title>Sistemas - Jorge Jacinto Gutarra</title>
</head>
<body>
<jsp:include page="intranetCabecera.jsp" />
	<div class="container" style="margin-top: 4%">
	<h4>Consulta Revista</h4>
	
		<div class="row" style="margin-top: 5%">
			<div class="col-md-4">
				<label class="control-label" for="id_nombre">Nombre</label> 
			</div>	
			<div class="col-md-5">
				<input	class="form-control" type="text" id="id_nombre" placeholder="Ingrese el Nombre">
			</div>
		</div>
		
		<div class="row" style="margin-top: 1%">
			<div class="col-md-4">
				<label class="control-label" for="id_frecuencia">Frecuencia</label> 
			</div>	
			<div class="col-md-5">
				<input	class="form-control" type="text" id="id_frecuencia" placeholder="Ingrese la Frecuencia">
			</div>
		</div>
		
		
		
		<div class="row" style="margin-top: 1%">
				<div class="col-md-4">
					<label class="control-label" for="id_modalidad">Modalidad</label>
				</div>
				<div class="col-md-5">
					<select	class="form-control" id="id_modalidad">
						<option value="-1">[Seleccione]</option>
					</select>
				</div>
		</div>
		<div class="row" style="margin-top: 1%">
				<div class="col-md-4">
					<label class="control-label" for="id_estado">Estado</label>
				</div>
				<div class="col-md-5">
					<input type="checkbox" class="custom-control-input" id="id_estado" checked="checked" />
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-primary" id="id_btn_filtro">Filtro</button>
				</div>
		
		</div>
		
		 <div class="row" style="margin-top: 4%">
            <table id="id_table" class="table table-bordered table-hover table-condensed">
                <thead style='background-color:#233d58; color:white'>
                    <tr>
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Frecuencia</th>
                        <th>Fecha Creación</th>
                        <th>Estado</th>
                        <th>Modalidad</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                
                
                </tbody>
            </table>
        </div>
</div>
</body>
</html>



