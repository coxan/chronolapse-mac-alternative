package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable{

    @FXML
    private FlowPane root;

    @FXML
    private ToggleButton bt_start;

    @FXML
    private CheckBox cb_ss;

    @FXML
    private CheckBox cb_wc;

    @FXML
    private Button bt_force;

    @FXML
    private Button bt_ssConfig;

    @FXML
    private Button bt_wcConfig;

    @FXML
    private Button bt_regHotkey;

    @FXML
    private TextField tf_hotkey;

    @FXML
    private TextField tf_interval;

    @FXML
    private DatePicker dp_start;

    @FXML
    private DatePicker dp_end;

    @FXML
    private CheckBox cb_schedule;

    @FXML
    private TextField tf_src;

    @FXML
    private TextField tf_out;

    @FXML
    private Button bt_src;

    @FXML
    private Button bt_out;

    @FXML
    private TextField tf_width;

    @FXML
    private TextField tf_height;

    @FXML
    private CheckBox cb_resize;

    @FXML
    private CheckBox cb_rename;

    @FXML
    private Button bt_convert;

    @FXML
    private CheckBox cb_audio;

    @FXML
    private ComboBox<?> combo_codec;

    @FXML
    private Button bt_calculate;

    @FXML
    private Button bt_render;

    @FXML
    private TextField tf_renderOut;

    @FXML
    private TextField tf_renderSrc;

    @FXML
    private TextField tf_audioFile;

    @FXML
    private TextField tf_renderFrames;


    public void initialize(URL url, ResourceBundle rb){

        // Capture Tab
        bt_start.setOnMouseClicked(x -> Capture.toggle());
        cb_ss.selectedProperty().addListener((observable, oldValue, newValue) -> {
            cb_ss.isSelected() ? Settings.enableSS(true) : Settings.enableSS(false);
        });
        cb_wc.selectedProperty().addListener((observable, oldValue, newValue) -> {
            cb_wc.isSelected() ? Settings.enableWC(true) : Settings.enableWC(false);
        });
        bt_force.setOnMouseClicked(x -> Capture.force());
        bt_ssConfig.setOnMouseClicked(SSConfig.showDialog());
        bt_wcConfig.setOnMouseClicked(WCConfig.showDialog());
        bt_regHotkey.setOnMouseClicked(x -> {
            String hotkey = KeyEvent.KEY_PRESSED.getName();
            Settings.setCaptureHotkey(hotkey);
            tf_hotkey.setText(hotkey);
        });

        tf_interval.textProperty().addListener((observable, oldValue, newValue) -> {
            Scanner s = new Scanner(newValue);
            if (s.hasNextInt()){
                int i = s.nextInt();
                if (i > 0 && i < Integer.MAX_VALUE)
                    Settings.setCaptureInterval(i);
            }
        });

        // Schedule Tab
    }

    void saveSettings(){
        Settings.ssPath = ssDir;
        Settings.videoPath = vidDir;
        Settings.startupCapture = startup_rec.isSelected();
        Settings.startupLaunch = startup_launch.isSelected();
    }
}
