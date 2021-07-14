package Notitz_list_app.controllers.ui;

import Notitz_list_app.controllers.Basectr;
import Notitz_list_app.models.Note;
import Notitz_list_app.uis.FXML_Location;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ListNotesCtr extends Basectr implements Initializable {



        @FXML
        private TextField txtSearchNote;

        @FXML
        private Label lblCountNotes;

        @FXML
        private TableView<Note> tbvNotes;

        @FXML
        private TableColumn cltitel;

        @FXML
        private TableColumn clldes;

        @FXML
        void onDeleteNote(ActionEvent event) {
            if (getNoteItems()!=null){
                data.remove(getNoteItems());
            }
            //geht auch:
            /*if (tbvNotes.getSelectionModel().getSelectedItems().size()>0){
                data.remove(tbvNotes.getSelectionModel().getSelectedItem());
            }*/
        }

        @FXML
        void onEditNote(ActionEvent event) throws IOException {
            if (getNoteItems()!=null)
                item= getNoteItems();


                super.navigateto(event,FXML_Location.EDIT.getPage());
        }

        @FXML
        void onNewNote(ActionEvent event) throws IOException {
                super.navigateto(event,FXML_Location.EDIT.getPage());
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


            cltitel.setCellValueFactory(new PropertyValueFactory<>("title"));
            clldes.setCellValueFactory(new PropertyValueFactory<>("description"));

            FilteredList<Note> filteredData = new FilteredList(data);
            txtSearchNote.setOnKeyReleased(k -> {filteredData.setPredicate(n ->{
                return n.getTitle().contains(txtSearchNote.getText()) ||
                        n.getDescription().contains(txtSearchNote.getText());

                });
        });

            tbvNotes.setItems(filteredData);

            lblCountNotes.textProperty().bind(Bindings.createStringBinding(()->filteredData.size()+(filteredData.size()==1? "Notitz": "Notitzen")));
    }
    public Note getNoteItems(){
            return tbvNotes.getSelectionModel().getSelectedItem();

    }
}
