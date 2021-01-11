package ehu.isad.model;

import ehu.isad.utils.Utils;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Captcha {
    private int id;
    private String filename;
    private String value;
    private int date;
    private Image irudia;

    public Captcha(int id, String filename, String value, int date) {
        this.id = id;
        this.filename = filename;
        this.value = value;
        this.date = date;

        String imagePath = Utils.lortuEzarpenak().getProperty("pathToImages")+filename;
        try {
            this.irudia = new Image(new FileInputStream(imagePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Image getIrudia() {
        return irudia;
    }

    public void setIrudia(Image irudia){
        this.irudia=irudia;
    }
    @Override
    public String toString() {
        return "Captcha{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", value='" + value + '\'' +
                ", date=" + date +
                ", irudia=" + irudia +
                '}';
    }
}
