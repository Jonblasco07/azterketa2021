package ehu.isad.controllers.db;

import ehu.isad.model.Datuak;
import java.security.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatuakKud {
    private static final DatuakKud instance = new DatuakKud();

    public static DatuakKud getInstance() {
        return instance;
    }

    private DatuakKud() { }

    public List<Datuak> lortuDatuak(String url, String md5){

        String query = "select version from checksums where md5= '" +md5+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia().getInstantzia().getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Datuak> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                String version = rs.getString("version");
                emaitza.add(new Datuak(url, md5, version));
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return emaitza;

    }
}

