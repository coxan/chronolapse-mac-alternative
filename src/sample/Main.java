package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private boolean timeLapse;
    Text status;
    private int count;

    @Override
    public void start(final Stage primaryStage) throws Exception{
        count = 0;
        final DirectoryChooser dc = new DirectoryChooser();
        Button button = new Button("ahoy pls click meh");
        button.setDisable(true);
        Button dir = new Button("Select directory test...");
        dir.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dc.showDialog(primaryStage);
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                toggleRun(60);
                status.setText("Running.");
            }
        });
        status = new Text("Not running.");
        primaryStage.setTitle("djajdioajw");
        FlowPane layout = new FlowPane(25, 25);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(status, button, dir);
        primaryStage.setScene(new Scene(layout, 300, 400));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        toggleRun(60);
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void toggleRun(int period){
        final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                dothis();
                status.setText(String.format("Running. %s image(s) captured.", count++));
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    void dothis(){
        try {
            Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage img = new Robot().createScreenCapture(rect);
            ImageIO.write(img, "png" , new File(System.getProperty("user.home"),
                    String.valueOf(System.currentTimeMillis()).concat(".png")));
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
