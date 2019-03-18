package GUI.Buttons;

import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * Class the implements the functionality of the help button by displaying a help page
 */
public class HelpButton extends Button implements LanguageChangeable {

    private static final String HELP_BUTTON_NAME = "Help";
    private Language myLanguage;

    /**
     * HelpButton constructor, sets language to default language of English and sets text of the button
     */
    public HelpButton(){
        myLanguage = Language.ENGLISH;
        this.setText(myLanguage.getTranslatedWord(HELP_BUTTON_NAME));
        this.setOnAction(event ->{
            Alert help = showHelpMenu();
            help.show();
        });
    }

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        this.setText(myLanguage.getTranslatedWord(HELP_BUTTON_NAME));
    }

    private Alert showHelpMenu(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(myLanguage.getTranslatedWord("Help"));
        alert.setHeaderText(myLanguage.getTranslatedWord("HelpHeader"));
        ScrollPane pane = new ScrollPane();
        pane.setContent(new Label(myLanguage.getTranslatedWord("HelpInfo")));
        alert.getDialogPane().setExpandableContent(pane);
        return alert;
    }

}
