/**
 * Created by Nicolas on 27/09/2016.
 */
function Filter() {
    var self = this;
    this.filtersList = [];

    this.addFilter = function (inputFilter) {
        self.filtersList.push(inputFilter);
    }

    this.removeFilter = function (indexFilter) {
        self.filtersList.splice( indexFilter, 1 );
    }

}