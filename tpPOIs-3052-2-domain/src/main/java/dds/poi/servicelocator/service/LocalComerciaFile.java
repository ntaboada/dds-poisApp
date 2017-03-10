package dds.poi.servicelocator.service;

import dds.poi.model.UpdateLocalComercial;
import dds.poi.servicelocator.service.FileUpdateService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by nicolas.taboada on 15/06/2016.
 */
public class LocalComerciaFile implements FileUpdateService {
    private String filePath;
    private File file;
    private String contentFile;


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void readFile() throws IOException {
        this.contentFile = FileUtils.readFileToString(file, "UTF-8");
    }

    public List<String> desConcatenateFantasyNameAndUpdate(List<String> stringWordsFile, UpdateLocalComercial updateLocalComercial){
        String wordTodesConcatenate = stringWordsFile.get(0);
        stringWordsFile.remove(0);
        String[] wordParts = wordTodesConcatenate.split(";");
        updateLocalComercial.setNombreFantasia(wordParts[0]);
        stringWordsFile.add(wordParts[1]);
        return stringWordsFile;
    }

    public List<String> getWordsFromFile() throws IOException {
        List<String> wordsFile = new ArrayList<>();
        String token = " ";
        String sFile = this.contentFile;
        StringTokenizer t = new StringTokenizer(sFile);
        while (t.hasMoreTokens()) {
            token = t.nextToken();
            wordsFile.add(token);
        }
        return wordsFile;
    }

    @Override
    public UpdateLocalComercial localComercialesToUpdate() throws IOException {
        UpdateLocalComercial updateLocalComercial = new UpdateLocalComercial();
        this.readFile();
        List<String> stringWordsFile = this.getWordsFromFile();
        desConcatenateFantasyNameAndUpdate(stringWordsFile, updateLocalComercial);
        updateLocalComercial.setEtiquetasAUpdatear(stringWordsFile);
        return updateLocalComercial;
    }




}
