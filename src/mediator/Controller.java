package mediator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import screen.ErrorScreen;
import screen.StartScreen;
import screen.UserScreen;
import turtle.Turtle;

public class Controller {
    private final String FILE_ERROR_PROMPT = "Failed to obtain resource files!";
    private String DEFAULT_CSS = Controller.class.getClassLoader().
	    getResource("default.css").toExternalForm(); 
    private ResourceBundle CURRENT_TEXT_DISPLAY;
    private ResourceBundle CURRENT_ERROR_DISPLAY;
    private ResourceBundle CURRENT_LANGUAGE;
    private ResourceBundle CURRENT_SETTINGS;
    // TODO: Read this in from files rather than storing as instance variables
    private final double DEFAULT_HEIGHT = 650;
    private final double DEFAULT_WIDTH = 900;
    private Stage PROGRAM_STAGE;

    public Controller(Stage primaryStage) {
	PROGRAM_STAGE = primaryStage;
    }

    /**
     * Makes a new Turtle given a name, an ImageView (previously attached to the Stage), a penColor, and an empty Group
     * that has already been attached to the Stage to hold lines for the pen
     */
    public double makeNewTurtleCommand(String name, ImageView turtleImage, 
	    Color penColor, Group penLines) {
	return 0.0;
    }

    /**
     * Returns an UnmodifiableMap of variables to their values
     */
    public Map<String, Double> getVariables() {
	return null;
    }

    /**
     * Returns an ImmutableList of available User Commands
     */
    public List<String> getUserCommands() {
	return null;
    }
    
    /**
     * 
     * @param key
     * @return
     */
    public String resourceDisplayText(String key) {
	return CURRENT_TEXT_DISPLAY.getString(key);
    }

    /**
     * Parses input from a text field or button press by the user
     */
    public double parseInput(String userTextInput) {
	return 0.0;
    }
    
    public void loadStartScreen() {
	try {
	    StartScreen startScreen = new StartScreen(this);
	    // test the ErrorScreen
	    //ErrorScreen startScreen = new ErrorScreen(this, "TESTING");
	    Parent programRoot = startScreen.getRoot();
	    Scene programScene = new Scene(programRoot, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	    programScene.getStylesheets().add(DEFAULT_CSS);
	    PROGRAM_STAGE.setScene(programScene);
	    PROGRAM_STAGE.show();	
	}
	catch (Exception e) {
	    String errorMessage = "Error loading Start Screen!";
	    loadErrorScreen(errorMessage);
	}
    }

    // TODO: get language and call findResources(String language)
    public void loadUserScreen() {
	try {
	    UserScreen programScreen = new UserScreen(this);
	    Parent programRoot = programScreen.getRoot();
	    Scene programScene = new Scene(programRoot, DEFAULT_WIDTH, DEFAULT_HEIGHT);	
	    programScene.getStylesheets().add(DEFAULT_CSS);
	    PROGRAM_STAGE.setScene(programScene);
	    // TODO: fix below
	    findResources("English");
	}
	catch (Exception e) {
	    String errorMessage = "Error loading User Screen!";
	    loadErrorScreen(errorMessage);
	}
    }

    public List<Turtle> onScreenTurtles() {
	return null;
    }

    /**
     * Change the Language. Changes the prompts displayed in the user interface as well as
     * acceptable commands by changing the ResourceBundles used by the program.
     * 
     * @param language: the new language to be used in the program
     */
    public void changeLanguage(String language) {
	findResources(language);
    }

    /**
     * Searches through the class path to find the appropriate resource files to use for 
     * the program. If it can't locate the files, it displays an error screen to the user
     * with the default @param FILE_ERROR_PROMPT defined at the top of the Controller class
     * 
     * @param language: The language to define which .properties files to use in the Program
     */
    private void findResources(String language) {
	String currentDir = System.getProperty("user.dir");
	String settingsFile = "settings";
	try {
	    File file = new File(currentDir);
	    URL[] urls = {file.toURI().toURL()};
	    ClassLoader loader = new URLClassLoader(urls);
	    try {
		CURRENT_TEXT_DISPLAY = ResourceBundle.getBundle(language + "Prompts", 
			Locale.getDefault(), loader);
		CURRENT_ERROR_DISPLAY = ResourceBundle.getBundle(language + "Errors", 
			Locale.getDefault(), loader);
	    }
	    // if .properties file doesn't exist for specified language, default to English
	    catch (Exception e) {
		String defaultLanguage = "English";
		CURRENT_TEXT_DISPLAY = ResourceBundle.getBundle(defaultLanguage + "Prompts", 
			Locale.getDefault(), loader);
		CURRENT_ERROR_DISPLAY = ResourceBundle.getBundle(defaultLanguage + "Errors", 
			Locale.getDefault(), loader);
	    }
	    CURRENT_LANGUAGE = ResourceBundle.getBundle(language, Locale.getDefault(), loader);
	    CURRENT_SETTINGS = ResourceBundle.getBundle(settingsFile, Locale.getDefault(), loader);
	}
	catch (MalformedURLException e) {
	    loadErrorScreen(FILE_ERROR_PROMPT);
	}
    }

    /**
     * Creates an Error Screen to display to the user indicating an error type by the String
     * @param errorMessage. 
     * 
     * @param errorMessage: The message to be displayed to the user on the Error Screen
     */
    private void loadErrorScreen(String errorMessage) {
	ErrorScreen errorScreen = new ErrorScreen(this, errorMessage);
	Parent errorScreenRoot = errorScreen.getRoot();
	Scene errorScene = new Scene(errorScreenRoot);
	errorScene.getStylesheets().add(DEFAULT_CSS);
	PROGRAM_STAGE.setScene(errorScene);
    }
}
