package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable{

    @FXML private FlowPane root;
    @FXML private ToggleButton bt_start;
    @FXML private CheckBox cb_ss;
    @FXML private CheckBox cb_wc;
    @FXML private Button bt_force;
    @FXML private Button bt_ssConfig;
    @FXML private Button bt_wcConfig;
    @FXML private Button bt_regHotkey;
    @FXML private TextField tf_hotkey;
    @FXML private TextField tf_interval;
    @FXML private DatePicker dp_start;
    @FXML private DatePicker dp_end;
    @FXML private CheckBox cb_schedule;
    @FXML private TextField tf_src;
    @FXML private TextField tf_out;
    @FXML private Button bt_src;
    @FXML private Button bt_out;
    @FXML private TextField tf_width;
    @FXML private TextField tf_height;
    @FXML private CheckBox cb_resize;
    @FXML private CheckBox cb_rename;
    @FXML private Button bt_convert;
    @FXML private Button bt_renderOut;
    @FXML private Button bt_renderSrc;
    @FXML private CheckBox cb_audio;
    @FXML private ComboBox<?> combo_codec;
    @FXML private Button bt_calculate;
    @FXML private Button bt_render;
    @FXML private TextField tf_renderOut;
    @FXML private TextField tf_renderSrc;
    @FXML private Button bt_audioFile;
    @FXML private TextField tf_audioFile;
    @FXML private TextField tf_renderFrames;

    public void initialize(URL url, ResourceBundle rb){

        // Capture Tab
        bt_start.setOnMouseClicked(x -> Capture.toggle());
        cb_ss.selectedProperty().addListener((observable, oldValue, newValue) -> Capture.enableSS = newValue);
        cb_wc.selectedProperty().addListener((observable, oldValue, newValue) -> Capture.enableWC = newValue);
        bt_force.setOnMouseClicked(x -> Capture.force());
        bt_ssConfig.setOnMouseClicked(SSConfig.showDialog());
        bt_wcConfig.setOnMouseClicked(WCConfig.showDialog());
        bt_regHotkey.setOnMouseClicked(x -> {
            String hotkey = KeyEvent.KEY_PRESSED.getName();
            Capture.setCaptureHotkey(hotkey);
            tf_hotkey.setText(hotkey);
        });

        tf_interval.textProperty().addListener((observable, oldValue, newValue) -> {
            Scanner s = new Scanner(newValue);
            if (s.hasNextInt()){
                int i = s.nextInt();
                if (i > 0 && i < Integer.MAX_VALUE)
                    Capture.interval = i;
            }
        });

        // Schedule Tab
        dp_start.chronologyProperty().addListener((observable, oldValue, newValue) -> Schedule.setStartTime(newValue));
        dp_end.chronologyProperty().addListener(((observable, oldValue, newValue) -> Schedule.setStopTime(newValue)));
        cb_schedule.selectedProperty().addListener(((observable, oldValue, newValue) -> Schedule.run = newValue));

        //Modify Tab
        bt_src.setOnMouseClicked(x -> {
            File file = new DirectoryChooser().showDialog(Main.window).getAbsoluteFile();
            Modify.setSrc(file);
            tf_src.setText(file.getAbsolutePath());
        });

        bt_out.setOnMouseClicked(x -> {
            File file = new DirectoryChooser().showDialog(Main.window).getAbsoluteFile();
            Modify.setOut(file);
            tf_out.setText(file.getAbsolutePath());
        });

        cb_rename.selectedProperty().addListener((observable, oldValue, newValue) -> Modify.setRename(newValue));
        cb_resize.selectedProperty().addListener(((observable, oldValue, newValue) -> Modify.setResize(newValue)));
        tf_width.textProperty().addListener(((observable, oldValue, newValue) -> Modify.setResizeWidth(newValue)));
        tf_height.textProperty().addListener(((observable, oldValue, newValue) -> Modify.setResizeHeight(newValue)));
        bt_convert.setOnMouseClicked(x -> Modify.convert());

        //Render Tab
        bt_renderOut.setOnMouseClicked(x -> {
            File file = new DirectoryChooser().showDialog(Main.window).getAbsoluteFile();
            Render.setOut(file);
            tf_renderOut.setText(file.getAbsolutePath());
        });

        bt_renderSrc.setOnMouseClicked(x -> {
            File file = new DirectoryChooser().showDialog(Main.window).getAbsoluteFile();
            Render.setSrc(file);
            tf_renderSrc.setText(file.getAbsolutePath());
        });

        cb_audio.selectedProperty().addListener((observable, oldValue, newValue) -> Render.setAudio(newValue));
        combo_codec.selectionModelProperty().addListener((observable, oldValue, newValue) -> Render.setCodec(newValue.getSelectedIndex()));
        bt_calculate.setOnMouseClicked(x -> Render.calculate());
        bt_render.setOnMouseClicked(x -> Render.render());
        bt_audioFile.setOnMouseClicked(x -> {
            File file = new DirectoryChooser().showDialog(Main.window).getAbsoluteFile();
            Render.setAudioFile(file);
            tf_audioFile.setText(file.getAbsolutePath());
        });
        tf_renderFrames.textProperty().addListener(((observable, oldValue, newValue) -> Render.setFrames(newValue)));

    }

    void saveSettings(){
        Settings.ssPath = ssDir;
        Settings.videoPath = vidDir;
        Settings.startupCapture = startup_rec.isSelected();
        Settings.startupLaunch = startup_launch.isSelected();
    }
}
