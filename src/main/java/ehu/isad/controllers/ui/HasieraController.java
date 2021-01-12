package ehu.isad.controllers.ui;

import ehu.isad.Main;
import ehu.isad.controllers.db.DatuakKud;
import ehu.isad.model.Datuak;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.security.DigestInputStream;

public class HasieraController implements Initializable {
    Main main;
    private List<Datuak> datuLista1 = new ArrayList<Datuak>();


    @FXML
    private TextField textfield;

    @FXML
    private Button checkBtn;

    @FXML
    private TableView<Datuak> taula;

    @FXML
    private TableColumn<Datuak, String> url;

    @FXML
    private TableColumn<Datuak, String> md5;

    @FXML
    private TableColumn<Datuak, String> version;

    @FXML
    private Text text;


    @FXML
    void begiratu(ActionEvent event) throws IOException {
        String aux =textfield.getText();
        String md5a= "aaa";
        try {
            md5a = Sarea.lortuMd5(aux);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        List<Datuak> laglist= DatuakKud.getInstance().lortuDatuak(aux, md5a);
        if (laglist.size()==0){
            Datuak datuak = new Datuak(aux, md5a, "0");
            datuLista1.add(datuak);
            datuaKargatu1();
            text.setText("Datubasean ez dago");


        }else{
            Datuak datuak = laglist.get(0);
            datuLista1.add(datuak);
            datuaKargatu1();
            text.setText("Datubasean dago");
        }
    }




    public void setMainApp(Main main) { this.main= main;}
    private void datuaKargatu1(){
        ObservableList<Datuak> datuak1 = FXCollections.observableArrayList(datuLista1);
        taula.setItems(datuak1);
    }

    public void pantailaKargatu() {
        taula.setEditable(true);

        url.setCellValueFactory(new PropertyValueFactory<>("url"));
        md5.setCellValueFactory(new PropertyValueFactory<>("md5"));
        version.setCellValueFactory(new PropertyValueFactory<>("version"));

        version.setOnEditCommit(
                t->{
                    t.getTableView().getItems().get(t.getTablePosition().getRow())
                            .setVersion(t.getNewValue());
                });
        Callback<TableColumn<Datuak, String>, TableCell<Datuak, String >> defaultTextFieldCellFactory
                = TextFieldTableCell.forTableColumn();
        version.setCellFactory(col -> {
            TableCell<Datuak, String> cell = defaultTextFieldCellFactory.call(col);
            return cell ;
        });
        datuaKargatu1();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.pantailaKargatu();
        text.setText("");

    }

}
