package fr.pantheonsorbonne.ufr27.miage.model.tables.object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PDF {

    private byte[] content;

    private static String urlDoc;

    public PDF(File file) throws IOException {
        this.content = Files.readAllBytes(file.toPath());
    }

    public byte[] getContent() {
        return content;
    }

    public static void main(String[] args) {
        try {
            File pdfFile = new File(urlDoc);
            PDF pdf = new PDF(pdfFile);

            byte[] pdfContent = pdf.getContent();

            System.out.println("Taille  : " + pdfContent.length + " octets");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}