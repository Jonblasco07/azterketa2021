package ehu.isad.controllers.db;

import ehu.isad.model.Captcha;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaptchaKud {
    private static final CaptchaKud instance = new CaptchaKud();

    public static CaptchaKud getInstance() {
        return instance;
    }

    private CaptchaKud() { }

    public List<Captcha> lortuCaptcha(){
        String query = "select id, filename, value, date from captchas";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia().getInstantzia().getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Captcha> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String filename = rs.getString("filename");
                String value = rs.getString("value");
                int date = rs.getInt("date");
                emaitza.add(new Captcha(id, filename, value, date));
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return emaitza;

    }

    public void datuakEguneratu(Integer id, String content){
        String query = "UPDATE captchas SET value='"+content+"' WHERE id="+id;
        if(content == null){
            query = "UPDATE captchas SET value=null WHERE id="+id;
        }
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);

    }

    public void captchaGehitu(String path){
        String query = "INSERT INTO captchas (filename,date) VALUES ('"+path+"' , 88888 )";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);

    }
}

