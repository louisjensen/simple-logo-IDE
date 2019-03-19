package GUI.Buttons;

import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * Abstract super class for the different choosers in the toolbar. Creates a menu of items from the properties file
 * specified by the createBundle() method that needs to be implemented by the subclass.  Sets actions for when the
 * menu items are clicked based on the consumer and how keys are processed for consumption based on the
 * processKeyForConsumption method that needs to be implemented by the subclass.
 * @param <T> the type that can be consumed by the consumer
 * Author: Ryan Culhane
 */
public abstract class PaletteChooser<T> extends MenuButton implements LanguageChangeable {

    protected ResourceBundle myBundle;
    protected Consumer<T> myConsumer;
    protected Language myLanguage;

    /**
     * PaletteChooser constructor calls createBundle (a method that needs to be implemented by the subclass)
     * @param consumer the consumer that can accept the option that is chosen from the menu
     */
    public PaletteChooser(Consumer<T> consumer){
        myConsumer = consumer;
        myLanguage = Language.ENGLISH;
        createBundle();
        buildChooser();
    }

    /**
     * Each PaletteChooser has a resource bundle corresponding to the properties file from which the options are read
     * in. Subclasses must implement the createBundle() method to establish which properties file that chooser uses
     * for its menu options
     */
    protected abstract void createBundle();

    private void buildChooser(){
        for (String key : Collections.list(myBundle.getKeys())){
            MenuItem menuItem = new MenuItem(myBundle.getString(key));
            menuItem.setOnAction(e -> {
                setText(myBundle.getString(key));
                myConsumer.accept(processKeyForConsumption(key));
            });
            this.getItems().add(menuItem);
        }
        setText(getItems().get(0).getText());
    }

    /**
     * Takes the key from the properties file and converts it to the appropriate type for consumption either using
     * String Concatenation or reflection (valueOf in the case of enums).
     * @param key key from the properties file that needs to be converted into something to be consumed by the consumer
     * @return an object of the appropriate type for consumption by the consumer
     */
    protected abstract T processKeyForConsumption(String key);

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
    @Override
    public void setLanguage(Language newLanguage){
        myLanguage = newLanguage;
    }
}
