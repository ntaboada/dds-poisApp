function ResultPOI(){
    var self = this;
    self.identificador;
    self.nombre;
    self.direccion;
    self.favoritePOI;

}

ResultPOI.asResultPOI = function(jsonSearchResults){
    return angular.extend(new ResultPOI(), jsonSearchResults)
}