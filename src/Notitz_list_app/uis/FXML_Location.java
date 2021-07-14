package Notitz_list_app.uis;

import java.net.URL;

public enum FXML_Location {
    LIST("ListNotesUi.fxml"),

    EDIT("EditNotesUi.fxml");

    private String location;

    FXML_Location(String location){
        this.location=location;

    }
    public URL getPage(){
        return getClass().getResource(this.location);

    }
}
