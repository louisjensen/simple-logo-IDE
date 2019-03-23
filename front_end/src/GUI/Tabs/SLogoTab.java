package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

/**
 * Tabs to be used in the SLogo project.  Makes the tabs scrollable and so that contents get added to the top
 * Author: Louis Jensen
 */
public class SLogoTab extends BasicTab {

    protected GUIdata guiData;

    /**
     * Default SLogoTab Constructor
     */
    public SLogoTab(){
        super();
    }

    /**
     * Constructor that sets Title of tab and allows tab to pass info to command line
     * @param tabTitle Tab Title
     * @param data Object that can pass info to be displayed or executed by GUIDisplay
     */
    public SLogoTab(String tabTitle, GUIdata data){
        super(tabTitle);
        guiData = data;
    }

    /**
     * Adds a new item to tab
     * @param newContents String to add to tab display
     */
    @Override
    public void addContents(String newContents){
        Label content = newLabel(newContents);
        content.setOnMouseClicked(event -> {
            guiData.setMyTextToUpdate(content.getText());
        });
        myVBoxOfStrings.getChildren().add(0, content);
    }

    protected Label newLabel(String newContents){
        Label contents = new Label(newContents);
        contents.setWrapText(true);
        return contents;
    }

}
