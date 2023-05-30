import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class PdfOpener {
    public static void main(String[] args) {
        String pdfUrl = "https://web.stanford.edu/class/cs103x/cs103x-notes.pdf";
        String destinationFolder = "src/PDFs";
        openPdf(pdfUrl, destinationFolder);
    }

    public static void openPdf(String url, String destinationFolder) {
        try {
            URL pdfUrl = new URL(url);
            String fileName = getFileNameFromUrl(url);
            Path destinationPath = Path.of(destinationFolder, fileName);

            // Download the PDF
            try (BufferedInputStream in = new BufferedInputStream(pdfUrl.openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(destinationPath.toString())) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            }

            // Open the PDF
            Desktop.getDesktop().open(destinationPath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileNameFromUrl(String url) {
        int lastIndexOfSlash = url.lastIndexOf("/");
        if (lastIndexOfSlash != -1) {
            return url.substring(lastIndexOfSlash + 1);
        } else {
            return "";
        }
    }
}
