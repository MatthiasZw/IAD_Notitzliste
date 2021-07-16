package Notitz_list_app.controllers.ui;

import Notitz_list_app.controllers.Basectr;
import Notitz_list_app.models.Note;
import Notitz_list_app.uis.FXML_Location;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.*;

import static java.util.Objects.nonNull;

public class ListNotesCtr extends Basectr  implements Initializable {


    private SelectionModel<Note> selectionModel;

    @FXML
    private TextField txtSearchNote;


    @FXML
    void onOpen(ActionEvent event) throws FileNotFoundException {
        FileChooser fodlg = new FileChooser();
        fodlg.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Iad Notitzen", "*.*")
        );
        File open = fodlg.showOpenDialog(mnbListview.getScene().getWindow());
        if (nonNull(open)) {
            try (BufferedReader br = new BufferedReader(new FileReader(open.toString()))) {

                List<Note> templist = new ArrayList<>();
                br.readLine();
                Scanner sc = new Scanner(br);
                while (sc.hasNextLine()) {
                    var tupel = sc.nextLine().split(";");
                    templist.add(new Note(tupel[0], tupel[1]));
                }

                data = FXCollections.observableList(templist);
                updateListview();

            } catch (IOException e) {
                e.printStackTrace();
            }
            ;
        }


    }

    @FXML
    void onClose(ActionEvent event) {
        System.exit(72);

    }

    @FXML
    void onSave2(ActionEvent event) throws IOException {

        FileChooser fodlg = new FileChooser();
        fodlg.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Iad Notitzen", "*.csv"));
        String close = fodlg.showOpenDialog(mnbListview.getScene().getWindow()).toString();

        FileWriter writer = new FileWriter(close);
        writer.append("titel");
        writer.append(';');
        writer.append("description");
        writer.append('\n');
        for (Note n :data) {
            writer.append(n.getTitle());
            writer.append(';');
            writer.append(n.getDescription());
            writer.append('\n');
        }
        writer.flush();
        writer.close();
}



        @FXML
        private Label lblCountNotes;

        @FXML
        private TableView<Note> tbvNotes;

        @FXML
        private TableColumn cltitel;

        @FXML
        private TableColumn clldes;

    @FXML
    private MenuBar mnbListview;


    @FXML
    void oneditnew(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(FXML_Location.EDIT.getPage());
        Stage stage = (Stage)((Node)mnbListview).getScene().getWindow();
        stage.hide();
        stage.setScene(new Scene(root));
        stage.show();

       // navigateto(mnb,FXML_Location.EDIT.getPage());

    }

        @FXML
        void onDeleteNote(ActionEvent event) {
            if (getNoteItems()!=null){

                final Optional<ButtonType> result = showConfirmdialog();
                if (result.isPresent() && result.get() == ButtonType.OK) {

                    data.remove(getNoteItems());

                   //selectionModel.clearSelection();


                }

                lblCountNotes.textProperty().bind(Bindings.createStringBinding(()->data.size()+(data.size()==1? "Notitz": "Notitzen")));

            }
            //geht auch:
            /*if (tbvNotes.getSelectionModel().getSelectedItems().size()>0){
                data.remove(tbvNotes.getSelectionModel().getSelectedItem());
            }*/
        }

        @FXML
        void onEditNote(ActionEvent event) throws IOException {
            if (getNoteItems()!=null)
                editNote = getNoteItems();


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


        FilteredList<Note> filteredData = updateListview();

        lblCountNotes.textProperty().bind(Bindings.createStringBinding(()->filteredData.size()+(filteredData.size()==1? "Notitz": "Notitzen")));
    }

    private FilteredList<Note> updateListview() {
        FilteredList<Note> filteredData = new FilteredList(data);
        txtSearchNote.setOnKeyReleased(k -> {
            filteredData.setPredicate(n -> {
                return n.getTitle().contains(txtSearchNote.getText()) ||
                        n.getDescription().contains(txtSearchNote.getText());

            });
        });

        tbvNotes.setItems(filteredData);
        return filteredData;
    }

    public Note getNoteItems(){
            return tbvNotes.getSelectionModel().getSelectedItem();

    }

            private Optional<ButtonType> showConfirmdialog() {
                return new Alert(Alert.AlertType.CONFIRMATION, "Wollen Sie wirklich löschen?").showAndWait();


            }
}
