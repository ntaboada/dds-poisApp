function POI() {
	var self = this;

	self.latitude = 0;
	self.longitude = 0;
	self.identificador = 0;
	self.nombre = "";
	self.direccionPrincipal = "";
	self.direccionSecundaria = "";
	self.imagen = "";
	self.calificacionGeneral = 5;
	self.esFavorito = true;
	self.reviews = [];

	self.categoriaPOI = new CategoriaPOIDefault();

	self.properties = [];

	self.fillProperties = function propiedades() {
		var props = [
			{name: "Nombre", value: self.nombre},
			{name: "Dirección Principal", value: self.direccionPrincipal},
			{name: "Dirección Secundaria", value: self.direccionSecundaria}
		];
		Array.prototype.push.apply(props, self.categoriaPOI.propiedades());
		self.properties = props;
	};

	self.categoriaTitulo = function nombreCategoria() {
		return self.categoriaPOI.prototype.categoria;
	};
}

//Categorias POI
function CategoriaPOIDefault() {
	var self = this;

	self.categoria = "";

	self.propiedades = function getPropiedades() {
		return [];
	};
}

function CategoriaConServicios() {
	this.prototype = new CategoriaPOIDefault();
	
	var self = this;

	self.servicios = [];

	self.addServicio = function addServicio(servicio) {
		self.servicios.push(servicio);
	};

	self.propiedades = function getPropiedades() {
		return [];
	};
}

function Banco() {
	this.prototype = new CategoriaConServicios();

	var self = this;
	self.prototype.categoria = "Banco";
	
	self.sucursal = "";
	self.gerente = "";

	self.propiedades = function getPropiedades() {
		var properties = self.prototype.propiedades();
		Array.prototype.push.apply(properties, [
				{name: "Sucursal", value:self.sucursal},
				{name: "Gerente", value:self.gerente}
			]);
		return properties;
	};

	self.addServicio = function addServicio(servicio) {
		self.prototype.addServicio(servicio);
	}
}

function CGP() {
	this.prototype = new CategoriaConServicios();

	var self = this;
	self.prototype.categoria = "CGP";

	self.polygon = new Polygon();

	self.propiedades = function getPropiedades() {
		return self.prototype.propiedades();
	};

	self.addServicio = function addServicio(servicio) {
		self.prototype.addServicio(servicio);
	}
}

function LocalComercial() {
	this.prototype = new CategoriaPOIDefault();
	
	var self = this;
	self.prototype.categoria = "Local Comercial";

	self.rubro = null;
	self.rangosDisponibilidad = [];

	self.agregarRango = function addRangoDisponibilidad(rango) {
		self.rangosDisponibilidad.push(rango);
	};

	self.propiedades = function getPropiedades() {
		return [
			{name: "Rubro", value:self.rubro.nombreRubro}
		];
	};
}

function ParadaColectivo() {
	this.prototype = new CategoriaPOIDefault();

	var self = this;
	self.prototype.categoria = "Parada de Colectivo";

	self.lineaColectivo = "";	

	self.propiedades = function getPropiedades() {
		return [
			{name: "Número de Línea", value:self.lineaColectivo}
		];
	};
}

//Objetos útiles
function Servicio() {
	var self = this;
	this.nombreServicio = "";
}

function ServicioBanco() {
	this.prototype = new Servicio();

	const diasDisponibles = ["Lunes", "Martes", "Miercoles", "Jueves", "Viernes"];
	const horaDesdeDisponible = 10;
	const minutoDesdeDisponible = 0;
	const horaHastaDisponible = 15;
	const minutoHastaDisponible = 15;

	var self = this;
}

function ServicioCGP() {
	this.prototype = new Servicio();

	var self = this;

	this.rangosDisponibilidad = [];

	self.addRangoDisponibilidad = function addRangoDisponibilidad(rango) {
		self.rangosDisponibilidad.push(rango);
	};
}

ServicioCGP.prototype = Servicio.prototype;
ServicioBanco.prototype = Servicio.prototype;

function Rubro() {
	var self = this;

	self.nombreRubro = "";
}

function Rango() {
	var self = this;

	self.rangoDeAtencion = "";
}

function Polygon() {
	var self = this;

	self.puntosLatitudLongitud = [];
}

function Review() {
	var self = this;

	self.userName = "";
	self.comment = "";
	self.calificacion = "";
}