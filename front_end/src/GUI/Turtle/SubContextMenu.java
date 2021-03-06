package GUI.Turtle;

import GUI.Commands.IntegerCommandInputDialog;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Abstract super class that creates a context menu that allows the user to click on menu items and run commands that
 * will affect the turtle
 * Author: Ryan Culhane
 */
public abstract class SubContextMenu extends Menu implements LanguageChangeable {

    private static final String TURTLE_CONTEXT_NAME = "Interactive Turtle Commands";
    private Language myLanguage;
    private Consumer<String> myCommandAccess;
    protected List<String> contextSpecificCommandsWithInput;
    protected List<String> noInputContextSpecificCommands;
    protected List<String> allContextSpecificCommands;

    /**
     * SubContextMenu constructor
     * @param language language of the program
     * @param commandAccess ability to run commands
     */
    public SubContextMenu(Language language, Consumer<String> commandAccess) {
        setText(TURTLE_CONTEXT_NAME);
        myLanguage = language;
        myCommandAccess = commandAccess;
        contextSpecificCommandsWithInput = new ArrayList<>();
        noInputContextSpecificCommands = new ArrayList<>();
        allContextSpecificCommands = new ArrayList<>();
        defineContextSpecificCommands();
        initializeMenuItems();
    }

    /**
     * Forces the subclass to define a list of commands that can be run from the menu.  Subclasses should put these
     * commands in the order they should appear in the menu into the allContextSpecificCommands List.  Subclasses
     * should also populate the contextSpecificCommandsWithInput list with the commands that can be run but need inut
     * and the noInputContextSpecificCommands list with commands that do not need any inputs to be run
     */
    protected abstract void defineContextSpecificCommands();

    private void initializeMenuItems() {
        for (String command : contextSpecificCommandsWithInput) {
            MenuItem item = new MenuItem(myLanguage.getTranslatedWord(command));
            item.setOnAction(e -> promptForFurtherInput(command));
            this.getItems().add(item);
        }
        for (String command : noInputContextSpecificCommands){
            MenuItem item = new MenuItem(myLanguage.getTranslatedWord(command));
            item.setOnAction(e -> myCommandAccess.accept(myLanguage.getTranslatedWord(command)));
            this.getItems().add(item);
        }
    }

    private void promptForFurtherInput(String text) {
        IntegerCommandInputDialog dialog = new IntegerCommandInputDialog();
        Optional<String> input = dialog.showAndWait();
        input.ifPresent(argument -> myCommandAccess.accept(myLanguage.getTranslatedWord(text) + " " + argument));
    }

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        for (int i = 0; i<getItems().size(); i++){
            getItems().get(i).setText(myLanguage.getTranslatedWord(allContextSpecificCommands.get(i)));
        }
    }
}

