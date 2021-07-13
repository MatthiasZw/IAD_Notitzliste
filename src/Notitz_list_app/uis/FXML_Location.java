package Notitz_list_app.uis;

public enum FXML_Location {
    LIST("../Notitz_list_app/uis/ListNotesUi.fxml"),

    EDIT("../Notitz_list_app/uis/EditNotesUi.fxml");

    private String location;
    FXML_Location(String location){
        this.location=location;

    }
    public String getLocation(){
        return this.location;

    }
}
