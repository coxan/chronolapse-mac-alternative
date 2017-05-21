package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.nio.file.DirectoryIteratorException;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML private CheckBox startup_launch;
    @FXML private CheckBox startup_rec;
    @FXML private Button button_dir;
    @FXML private TextField text_ssDir;
    @FXML private TextField text_vidDir;
    @FXML private Button button_save;
    @FXML private Button button_reset;

    private File ssDir;
    private File vidDir;

    public void initialize(URL url, ResourceBundle rb){
        button_dir.setOnMouseClicked(directory = new DirectoryChooser().showDialog());
        button_save.setOnMouseClicked(x -> saveSettings());
        button_reset.setOnMouseClicked(x -> {
            ssDir = null;
            vidDir = null;
            startup_launch.setSelected(false);
            startup_rec.setSelected(false);
            text_ssDir.setText("");
            saveSettings();
        });
    }

    void saveSettings(){
        Settings.ssPath = ssDir;
        Settings.videoPath = vidDir;
        Settings.startupCapture = startup_rec.isSelected();
        Settings.startupLaunch = startup_launch.isSelected();
    }
}
