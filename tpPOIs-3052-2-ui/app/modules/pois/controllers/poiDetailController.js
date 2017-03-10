var mapDrawed = false;
var poiByName = false;

function POIDetailController(userId, poi, poiName, $state, POIService) {

    if(!localStorage.getItem("UserId") || localStorage.getItem("UserId") == 0) {
        $state.go("main.login");
    }

  var self = this;
  self.userReview = "";
  self.userQualification = "4";
  self.poi = poi;
  self.poiName = poiName;
  if(self.poi == "0" || self.poi == "") {
    poiByName = true;

    POIService.getPoiDetailFromPOIName(
      function successCallback(response) {
        parsePOIDetail(response, self);
      },
      function errorCallback(data) {
        alert("Ocurrió un error al obtener detalles del POI");
        $state.go("main.busqueda");
      }, self.poiName);

  }else{
    poiByName = false;

    POIService.getPoiDetail(
      function successCallback(response) {
        parsePOIDetail(response, self);
      },
      function errorCallback(data) {
        alert("Ocurrió un error al obtener detalles del POI");
        $state.go("main.busqueda");
      }, self.poi);

  }


  self.mostrarServicios = function () {
    if(self.poi.categoriaPOI.prototype.servicios) {
      $state.go("main.poi_detail." + self.poi.categoriaTitulo());
    }
  };

  self.sendReview = function () {

    if(poiByName) {

      POIService.sendReviewForPOIByName(
        function callback(response) {
          addReviewToView(self);
          disableReviewControls(self);
        },
        function errorCallback() {
          alert("Ocurrió un error al enviar sus comentarios");
        }, self.poiName, self.userQualification, self.userReview);

    }else{
      POIService.sendReview(
        function callback(response) {
          addReviewToView(self);
          disableReviewControls(self);
        },
        function errorCallback() {
          alert("Ocurrió un error al enviar sus comentarios");
        }, self.poi.identificador, self.userQualification, self.userReview);

    }
  };

    self.saveFavoritePOI = function() {
      if(!poiByName) {

        POIService.saveFavorite(
          function callback(response) {
            self.poi.esFavorito = true;
          },
          function errorCallback() {
            alert("Ocurrió un error al marcar el Punto de Interés como favorito");
          }, self.poi.identificador);

      }else{
        alert("No es posible marcar este Punto de Interés como favorito");
      }
    };

    self.removeFavoritePOI = function() {
      if(!poiByName) {

        POIService.removeFavorite(
          function callback(response) {
            self.poi.esFavorito = false;
          },
          function errorCallback() {
            alert("Ocurrió un error al desmarcar el Punto de Interés como favorito");
          }, self.poi.identificador);

      }else{
        alert("No es posible desmarcar este Punto de Interés como favorito");
      }
    };

};

angular.module("pois-app")
.controller("POIDetailController", POIDetailController);

POIDetailController.$inject = ["userId", "poi", "poiName", "$state", "POIService"];

function initMap(poi){

        var latLngPoi = {lat: poi.latitude, lng: poi.longitude};

        // Create a map object and specify the DOM element for display.
        var map = new google.maps.Map(document.getElementById('map'), {
          center: latLngPoi,
          scrollwheel: false,
          zoom: 17
        });

        // Create a marker and set its position.
        var marker = new google.maps.Marker({
          map: map,
          position: latLngPoi,
          title: poi.nombre
        }); 
}

function parsePOIDetail(response, self) {
    self.poi = new POI();
    self.poi.direccionPrincipal = response.poi.direccionPrincipal;
    self.poi.direccionSecundaria = response.poi.direccionSecundaria;
    self.poi.identificador = response.poi.identificador;
    self.poi.nombre = response.poi.nombrePOI;
    self.poi.latitude = parseFloat(response.poi.coordenadas[0]);
    self.poi.longitude = parseFloat(response.poi.coordenadas[1]);
    self.poi.imagen = response.poi.image;

    self.poi.esFavorito = response.esFavorito;
    self.poi.calificacionGeneral = response.calificacionGeneral;

    for (i = 0; i < response.poi.reviewsList.length; i++) {
      var review = new Review();

      review.userName = response.poi.reviewsList[i]["user"].userName;
      review.comment = response.poi.reviewsList[i]["comment"];
      review.calificacion = response.poi.reviewsList[i]["puntaje"];
      self.poi.reviews.push(review);
    }

    getCategoriaForPOI(response.poi.categoria, self);

    self.poi.fillProperties();

    initMap(self.poi);
    self.mostrarServicios();
}

function getCategoriaForPOI(categoria, self) {
  
  if(categoria.rubro) {
    //ES UN LOCAL COMERCIAL
    var localComercial = new LocalComercial();

    var rubro = new Rubro();
    rubro.nombreRubro = categoria.rubro.nombreRubro;
    localComercial.rubro = rubro;

    for (i = 0; i < categoria.rangosDeDisponibilidad.length; i++) {
      var rango = new Rango();
      var rangoAtencion = getDayOfWeekFromNumber(categoria.rangosDeDisponibilidad[i].dayOfWeek) + " desde las " + categoria.rangosDeDisponibilidad[i].openHour + ":" + categoria.rangosDeDisponibilidad[i].openHourMinutes + " hasta las " + categoria.rangosDeDisponibilidad[i].closeHour + ":" + categoria.rangosDeDisponibilidad[i].closeHourMinutes;
      rango.rangoDeAtencion = rangoAtencion;

      localComercial.agregarRango(rango);
    }

    self.poi.categoriaPOI = localComercial;
  }


  if(categoria.lineaColectivo) {
    //ES UNA PARADA DE COLECTIVO
    var paradaColectivo = new ParadaColectivo();
    paradaColectivo.lineaColectivo = categoria.lineaColectivo;
    self.poi.categoriaPOI = paradaColectivo;
  }


  if(categoria.sucursal) {
    //ES UN BANCO
    var banco = new Banco();
    banco.sucursal = categoria.sucursal;
    banco.gerente = categoria.gerente;

    for (i = 0; i < categoria.servicios.length; i++) {
      var servicio = new ServicioBanco();
      servicio.nombreServicio = categoria.servicios[i].nombreServicio;

      banco.addServicio(servicio);
    }
    self.poi.categoriaPOI = banco;
  }

  else if(categoria.servicios) {
    //ES UN CGP
    var cgp = new CGP();

    for (i = 0; i < categoria.servicios.length; i++) {
      var servicio = new ServicioCGP();
      servicio.nombreServicio = categoria.servicios[i].nombreServicio;

      for (j = 0; j < categoria.servicios[i].horarioDeAtencion.length; j++) {
        var rango = new Rango();
        var rangoAtencion = getDayOfWeekFromNumber(categoria.servicios[i].horarioDeAtencion[j].dayOfWeek) + " desde las " + categoria.servicios[i].horarioDeAtencion[j].openHour + ":" + categoria.servicios[i].horarioDeAtencion[j].openHourMinutes + " hasta las " + categoria.servicios[i].horarioDeAtencion[j].closeHour + ":" + categoria.servicios[i].horarioDeAtencion[j].closeHourMinutes;
        rango.rangoDeAtencion = rangoAtencion;

        servicio.addRangoDisponibilidad(rango);
      }

      cgp.addServicio(servicio);
    }
    self.poi.categoriaPOI = cgp;
  }


}


function getDayOfWeekFromNumber(numberOfDay) {
  if(numberOfDay == 1) {
    return "Lunes";
  }else if(numberOfDay == 2) {
    return "Martes";
  }else if(numberOfDay == 3) {
    return "Miercoles";
  }else if(numberOfDay == 4) {
    return "Jueves";
  }else if(numberOfDay == 5) {
    return "Viernes";
  }else if(numberOfDay == 6) {
    return "Sabado";
  }else{
    return "Domingo";
  }
}

function addReviewToView(self) {
  var review = new Review();

  review.userName = "MI REVIEW";
  review.comment = self.userReview;
  review.calificacion = self.userQualification;

  self.poi.reviews.unshift(review);
}

function disableReviewControls(self) {
  self.userReview = "";
  self.userQualification = "3";
}