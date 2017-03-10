package dds.poi.model;

import java.util.List;

/**
 * Created by nicolas.taboada on 15/06/2016.
 */
public class UpdateLocalComercial {
        private String nombreFantasia;
        private List<String> etiquetasAUpdatear;

        public UpdateLocalComercial() {
        }


        public UpdateLocalComercial(String nombreFantasia, List<String> etiquetasAUpdatear) {
            this.nombreFantasia = nombreFantasia;
            this.etiquetasAUpdatear = etiquetasAUpdatear;
        }

         public String getNombreFantasia() {
            return nombreFantasia;
        }


        public List<String> getEtiquetasAUpdatear() {
            return etiquetasAUpdatear;
        }

        public void setEtiquetasAUpdatear(List<String> etiquetasAUpdatear) {
            this.etiquetasAUpdatear = etiquetasAUpdatear;
        }

        public void setNombreFantasia(String nombreFantasia) {
            this.nombreFantasia = nombreFantasia;
        }
}
