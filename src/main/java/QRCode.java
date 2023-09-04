import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {

    static String path = "C:\\Users\\Utilizador\\Notas\\QRCodes\\qrcode.jpg";

    public void generate(String _args) {
        try {
            generateQRcode(_args, path, "UTF-8", 200, 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("QR Code successfully created!");
    }

    static void generateQRcode(String data, String path, String charset, int h, int w) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
            MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}