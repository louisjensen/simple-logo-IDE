package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class SLogoTabVariables extends SLogoTab {

    public SLogoTabVariables(){
        super();
    }

    public SLogoTabVariables(String tabTitle, GUIdata data){
        super(tabTitle, data);
    }

    @Override
    public void addContents(String newContents) {
        Label contents = new Label(newContents);
        contents.setWrapText(true);
        contents.setOnMouseClicked(event -> {
            //System.out.println("variablestab");
            displayDialogMenu(contents);
        });
        myVBoxOfStrings.getChildren().add(0, contents);
    }

    private void displayDialogMenu(Label contents) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Variable Editor");
        dialog.setHeaderText(contents.getText().substring(1));
        dialog.setContentText("Enter new value:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value -> {
           // String s = contents.getText() + " " + value;
            runCommand(contents, value);
        });
    }

    private void runCommand(Label contents, String value) {
        String[] s = contents.getText().split(" ");
        String str = "make " + s[0] + " " +value;
        System.out.println(str);
        guiData.setMyCommandToRun(str);
    }
}
