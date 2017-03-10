package dds.poi.model.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * Created by Nicolas on 15/06/2016.
 */
public class PlainFile {
    private String filePath;
    private File file;
    private List<String> wordsFiles;


    public PlainFile(String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);
        this.wordsFiles = new ArrayList<>();
    }

    public List<String> getWordsFiles() {
        return wordsFiles;
    }

    public void setWordsFiles(List<String> wordsFiles) {
        this.wordsFiles = wordsFiles;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String readFile() throws IOException {
        return FileUtils.readFileToString(file, "UTF-8");
    }

    public List<String> keyWords(String file) throws IOException {
        String token = "";
        String sFile = this.readFile();
        StringTokenizer t = new StringTokenizer(sFile);
            while (t.hasMoreTokens()) {
                token = t.nextToken();
                wordsFiles.add(t.nextToken());
            }
        return wordsFiles;
    }


}
