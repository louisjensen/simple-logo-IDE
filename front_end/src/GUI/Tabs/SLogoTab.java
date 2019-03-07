package GUI.Tabs;

import GUI.GUI.GUIComponent;
import GUI.GUI.GUIdata;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class SLogoTab extends Tab implements GUIComponent {

    protected GUIdata guiData;
    VBox myVBoxOfStrings;
    ScrollPane myScrollPane;

    public SLogoTab(){
        super();
        initializeScrollPane();
        setContent(myScrollPane);
    }

    public SLogoTab(String tabTitle){
        this();
        setText(tabTitle);
    }

    public SLogoTab(String tabTitle, GUIdata data){
        this(tabTitle);
        guiData = data;
    }

    private void initializeScrollPane() {
        myVBoxOfStrings = new VBox();
        myScrollPane = new ScrollPane();
        myScrollPane.setContent(myVBoxOfStrings);
        myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void addContents(String newContents){
        Label contents = new Label(newContents);
        contents.setWrapText(true);
        contents.setOnMouseClicked(event -> {
            System.out.println(contents.getText());
            guiData.setMyTextToUpdate(contents.getText());
        });
        myVBoxOfStrings.getChildren().add(0, contents);
    }

    public void clearContents(){
        myVBoxOfStrings.getChildren().clear();
    }

}
