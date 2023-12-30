package fr.pantheonsorbonne.ufr27.miage.model.tables.object;

import java.io.File;
import java.io.IOException;

public class Excel {

    private File excel;

    private static String urlDoc;

    public void ExcelFile(File file) throws IOException {
            this.excel = file;
    }

    public static void main(String[] args) {
        File excelFile = new File(urlDoc);
    }
}
