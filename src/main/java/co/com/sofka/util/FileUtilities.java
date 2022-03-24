package co.com.sofka.util;

import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;

import static co.com.sofka.util.UpdateTitleKey.TITLE;

public class FileUtilities {

    public static Logger LOGGER = Logger.getLogger(FileUtilities.class);
    private static final String JSON_PATH = "src/test/resources/files/jsonplaceholder/updateTitle.json";

    //función para convertir en string el json de actualizar usuario
    public static String updateTitleBodyConverter(String title) {
        String result;
        try {
            result = new String(Files.readAllBytes(Paths.get(JSON_PATH)));
            result = result.replace(TITLE.getValue(), title);
            return result;
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            return "";
        }
    }
}
