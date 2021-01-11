package ehu.isad.controllers.ui;

import ehu.isad.Main;
import ehu.isad.controllers.db.CaptchaKud;
import ehu.isad.model.Captcha;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HasieraController implements Initializable {
    Main main;

    @FXML
    private TableView<Captcha> taula;
    @FXML
    private TableColumn<Captcha, Integer> id;

    @FXML
    private TableColumn<Captcha, String> path;

    @FXML
    private TableColumn<Captcha, String> content;

    @FXML
    private TableColumn<Captcha, Integer> date;

    @FXML
    private TableColumn<Captcha, Image> irudia;

    @FXML
    private Button txertatu;

    @FXML
    private Button gorde;

    @FXML
    void g(ActionEvent event) {
        List<Captcha> captchaLista = new ArrayList<Captcha>();
        captchaLista = CaptchaKud.getInstance().lortuCaptcha();
        for(int i=0; i<captchaLista.size(); i++){
            CaptchaKud.getInstance().datuakEguneratu(captchaLista.get(i).getId(),captchaLista.get(i).getValue());
        }
    }

    @FXML
    void t(ActionEvent event) {
        List<Captcha> captchaLista = new ArrayList<Captcha>();
        captchaLista = CaptchaKud.getInstance().lortuCaptcha();
        int random = (int)(Math.random() * 90000000 + 1);
        String izena = "capcha"+random;
        Sarea.getInstance().irudiaGorde("http://45.32.169.98/captcha.php",izena);
        CaptchaKud.getInstance().captchaGehitu(izena+".png");
        datuaKargatu();

    }


public void setMainApp(Main main) { this.main= main;}


    public void pantailaKargatu() {
        taula.setEditable(true);
        List<Captcha> lagList = CaptchaKud.getInstance().lortuCaptcha();
        ObservableList<Captcha> lagak = FXCollections.observableArrayList(lagList);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        path.setCellValueFactory(new PropertyValueFactory<>("filename"));
        content.setCellValueFactory(new PropertyValueFactory<>("value"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        taula.setItems(lagak);


        //irudia jarri
        irudia.setCellValueFactory(new PropertyValueFactory<>("Irudia"));
        irudia.setCellFactory(p -> new TableCell<>() {
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty){
                    final ImageView imageview = new ImageView();
                    imageview.setFitHeight(20);
                    imageview.setFitWidth(50);
                    imageview.setImage(image);
                    setGraphic(imageview);
                    setAlignment(Pos.CENTER);
                    // tbData.refresh();
                }else{
                    setGraphic(null);
                    setText(null);
                }
            };
        });

        content.setOnEditCommit(
                t->{
                    t.getTableView().getItems().get(t.getTablePosition().getRow())
                            .setValue(t.getNewValue());
                });

        //Balioa editatu
        Callback<TableColumn<Captcha, String>, TableCell<Captcha, String >> defaultTextFieldCellFactoryIzena
                = TextFieldTableCell.forTableColumn();

        content.setCellFactory(col -> {
            TableCell<Captcha, String> cell = defaultTextFieldCellFactoryIzena.call(col);
            return cell ;
        });

        datuaKargatu();

    }
    private void datuaKargatu(){
        List<Captcha> captchaLista = new ArrayList<Captcha>();
        captchaLista = CaptchaKud.getInstance().lortuCaptcha();
        ObservableList<Captcha> captchas = FXCollections.observableArrayList(captchaLista);
        taula.setItems(captchas);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.pantailaKargatu();


    }

}
