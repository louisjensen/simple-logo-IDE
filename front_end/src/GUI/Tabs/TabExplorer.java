package GUI.Tabs;

import GUI.Commands.CommandExecutable;
import GUI.Commands.CommandLine;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.GUI.GUIDisplay;
import GUI.GUI.GUIdata;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.spec.ECField;
import java.util.List;
import java.util.function.Consumer;

public class TabExplorer extends TabPane implements CommandExecutable, LanguageChangeable {

    private static final String VARIABLES = "Variables";
    private static final String METHODS = "Methods";
    private static final String COMMANDS = "CommandHistory";
    private static final Double QUARTER = 1.0 / 4;
    private static final Double HALF = 1.0 / 2;
    private Consumer<String> myCommandAccess;
    private SLogoTab myVariables;
    private SLogoTab myCommands;
    private SLogoTab myMethods;
    private GUIdata myDataTracker;
    private CommandLine myTextBox;
    private Language myLanguage;


    public TabExplorer(){
        super();
        setPrefSize(GUIDisplay.SCENE_WIDTH * QUARTER, GUIDisplay.SCENE_HEIGHT * HALF);
    }

    public TabExplorer(GUIdata dataTracker, Language language, CommandLine textBox){
        super();
        myLanguage = language;
        myDataTracker = dataTracker;
        myTextBox = textBox;
        initializeTabs();
        setPrefSize(GUIDisplay.SCENE_WIDTH * QUARTER, GUIDisplay.SCENE_HEIGHT * HALF);
    }

    private void initializeTabs() {
        myVariables = makeInteractiveTab(VARIABLES, myDataTracker);
        myMethods = makeMethodsTab(METHODS, myDataTracker);
        myCommands = makeTab(COMMANDS, myDataTracker, myTextBox);
        getTabs().addAll(myVariables, myMethods, myCommands);
    }

    @Override
    public boolean isResizable(){
        return true;
    }

    @Override
    public void resize(double width, double height){
        super.setWidth(width);
        super.setHeight(height);
    }

    @Override
    public void giveAbilityToRunCommands(Consumer<String> commandAccess) {
        myCommandAccess = commandAccess;
    }

    @Override
    public void runCommand(String command) {
        myCommandAccess.accept(command);
    }

    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        myVariables.setText(myLanguage.getTranslatedWord(VARIABLES));
        myCommands.setText(myLanguage.getTranslatedWord(COMMANDS));
        myMethods.setText(myLanguage.getTranslatedWord(METHODS));
    }

    private SLogoTab makeVariablesTab(String tabType, GUIdata dataTracker) {
        SLogoTabInteractive tab = new TabVariables(myLanguage.getTranslatedWord(tabType), dataTracker);
        tab.getContent().setOnMouseClicked(event -> {
            runCommand(dataTracker.getMyCommandToRun());
        });
        return tab;
    }

    private SLogoTab makeMethodsTab(String tabType, GUIdata dataTracker) {
        SLogoTabInteractive tab = new TabMethods(myLanguage.getTranslatedWord(tabType), dataTracker);
        tab.getContent().setOnMouseClicked(event -> {
            runCommand(dataTracker.getMyCommandToRun());
        });
        return tab;
    }

    private SLogoTab makeInteractiveTab(String tabType, GUIdata dataTracker) {
        SLogoTab tab;
        try {
            Class tabClass = Class.forName("GUI.Tabs.TabVariables");
            Constructor constructor = tabClass.getConstructor(String.class, GUIdata.class);
            tab = (SLogoTabInteractive) constructor.newInstance(myLanguage.getTranslatedWord(tabType), dataTracker);
        } catch (Exception e) {
           tab = new SLogoTab();
        }
        tab.getContent().setOnMouseClicked(event -> {
            runCommand(dataTracker.getMyCommandToRun());
        });
        return tab;
    }

    private SLogoTab makeTab(String tabType, GUIdata data, TextArea commandLine){
        SLogoTab tab = new SLogoTab(myLanguage.getTranslatedWord(tabType), data);
        tab.getContent().setOnMouseClicked(event -> {
            commandLine.setText(data.getMyTextToUpdate());
        });
        return tab;
    }

    public void addToCommandHistory(String command){
        myCommands.addContents(command);
    }

    public void clearCommandHistory() {
        myCommands.clearContents();
    }

    public void addVariable(String name, Double val) {
        myVariables.addContents(name + " " + val);
    }

    public void addUserDefinedCommand(String name, List<String> myVars) {
        myMethods.addContents(name + ": " + String.join(" ", myVars));
    }
}
