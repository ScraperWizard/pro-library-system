package PDFs;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class PdfOpener {

    //    String pdfName = "cs103x-notes.pdf";
    //    String pdfFolderPath = "src/PDFs";
    //    openPdf(pdfName, pdfFolderPath);

    public static boolean openPdf(String pdfName) {
        try {
            String pdfFolderPath = "src/PDFs";
            pdfName = pdfName.replaceAll("\\s", "-");
            System.out.println(pdfFolderPath + "/" + pdfName + ".pdf");
            File pdfFile = new File(pdfFolderPath + "/" + pdfName + ".pdf");

            // Check if the file exists
            if (!pdfFile.exists()) {
                System.out.println("The specified PDF file does not exist.");
                return false;
            }

            // Open the PDF
            Desktop.getDesktop().open(pdfFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
