package dds.poi.servicelocator.service;

import dds.poi.model.UpdateLocalComercial;

import java.io.IOException;

/**
 * Created by nicolas.taboada on 15/06/2016.
 */
public interface FileUpdateService {
    UpdateLocalComercial localComercialesToUpdate() throws IOException;

    void setFilePath(String filePath);
}

