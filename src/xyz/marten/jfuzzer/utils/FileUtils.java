package xyz.marten.jfuzzer.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by marten on 2/26/16.
 */
public class FileUtils {
    public static List<String> fileToList(String filename) throws IOException {
        return Files.readAllLines(new File(filename).toPath(), Charset.defaultCharset());
    }
}
