package pdfviewer;

import java.io.*;

public class PDFFilter implements FilenameFilter {

    public boolean accept(File directory, String fileName) {
	
	return fileName.endsWith(".pdf");
    }
}
