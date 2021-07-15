package Notitz_list_app.controllers.ui;

import Notitz_list_app.controllers.Basectr;
import Notitz_list_app.models.Note;
import Notitz_list_app.uis.FXML_Location;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.util.Objects.nonNull;

public class EditNotesCtr extends Basectr implements Initializable {
    @FXML
    private TextField txtTitel;

    @FXML
    private TextArea txaDescription;







    @FXML
    void onCancel(ActionEvent event) throws IOException {
        editNote=null;
        super.navigateto(event, FXML_Location.LIST.getPage());

    }

    @FXML
    void onDelete(ActionEvent event) {

        final Optional<ButtonType> result = showConfirmdialog();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            txaDescription.clear();
            txtTitel.clear();

        }}

    @FXML
    void onList(ActionEvent event) throws IOException {
        super.navigateto(event, FXML_Location.LIST.getPage());
    }

    @FXML
    void onSave(ActionEvent event) throws IOException {
        if( nonNull( editNote)) data.remove(editNote);
        if(txtTitel.getText().trim().equals("") || txaDescription.getText().trim().equals(""))
        {
            // Alert// return
            data.remove(new Note(txtTitel.getText(), txaDescription.getText()));
        } else
        data.add(new Note(txtTitel.getText(), txaDescription.getText()));

        super.navigateto(event, FXML_Location.LIST.getPage());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (editNote != null) {
            txtTitel.setText(editNote.getTitle());
            txaDescription.setText(editNote.getDescription());
        }
    }private Optional<ButtonType> showConfirmdialog() {
        return new Alert(Alert.AlertType.CONFIRMATION, "Wirklich löschen?").showAndWait();
    }
}
