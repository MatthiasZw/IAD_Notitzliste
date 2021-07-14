package Notitz_list_app.controllers.ui;

import Notitz_list_app.controllers.Basectr;
import Notitz_list_app.uis.FXML_Location;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.util.Objects.nonNull;

public class EditNotesCtr extends Basectr implements Initializable {
    @FXML
    private TextField txtTitel;

    @FXML
    private TextArea txaDescription;

    @FXML
    void onCancel(ActionEvent event) throws IOException {
        super.navigateto(event, FXML_Location.LIST.getPage());

    }

    @FXML
    void onDelete(ActionEvent event) {
        txaDescription.clear();
        txtTitel.clear();

    }

    @FXML
    void onList(ActionEvent event) throws IOException {
        super.navigateto(event, FXML_Location.LIST.getPage());
    }

    @FXML
    void onSave(ActionEvent event) throws IOException {
       // if (nonNull (editNote)) data.remove(editNote);
        //if (txtTitel.getText().trim().equals("") || txaDescription.getText().trim().equals("")){

            //
        //}

        super.navigateto(event, FXML_Location.LIST.getPage());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (item != null) {
            txtTitel.setText(item.getTitle());
            txaDescription.setText(item.getDescription());


        }
    }
}
