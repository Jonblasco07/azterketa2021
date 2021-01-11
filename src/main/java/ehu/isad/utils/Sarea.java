package ehu.isad.utils;

import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Sarea {
    public Sarea() {
    }

    public String URLReader(String isbn) {
        String line = " ";
        URL openLibrary;


        try {
            openLibrary = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&jscmd=details&format=json");
            URLConnection con = openLibrary.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            line = in.readLine();
            in.close();
        } catch (java.net.MalformedURLException MalformedURLException) {
            MalformedURLException.printStackTrace();
        } catch (java.io.IOException IOException) {
            IOException.printStackTrace();
        }

        String[] zatiak = line.split("ISBN:" + isbn + "\": ");
        line = zatiak[1].substring(0, zatiak[1].length() - 1);
        System.out.println(line);

        return line;

    }

    public Image createImage(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
    }
}


