package Notitz_list_app.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Note {
    public StringProperty title = new SimpleStringProperty();
    public StringProperty description = new SimpleStringProperty();

    public Note(String title, String description)
    { this.title.setValue(title);
        this.description = new SimpleStringProperty(description);

    } public String getTitle(){
        return this.title.get();
    } public String getDescription(){
        return this.description.get();
    }

}
