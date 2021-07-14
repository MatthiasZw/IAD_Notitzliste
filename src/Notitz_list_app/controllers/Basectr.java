package Notitz_list_app.controllers;

import Notitz_list_app.models.Note;
import Notitz_list_app.uis.FXML_Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public abstract class Basectr {
    protected static ObservableList<Note> data = FXCollections.observableArrayList(
            new Note("Abstand","Vorsicht"),new Note("Hallo","Einkaufen"),
            new Note("Gitarre","Gibson"),new Note("Fahrrad","Reifen"));



protected static Note item=null;

    protected void navigateto (Event event, URL location) throws IOException {

        Parent root = FXMLLoader.load(location);
        Stage appStage=(Stage)
                ((Node)(event.getSource())).getScene().getWindow();
        appStage.hide();
        appStage.setScene(new Scene(root));
        appStage.show();
    }
}
