package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private boolean timeLapse;
    private static boolean isRecording;
    Text status;
    private int count;
    static Stage window;

    @Override
    public void start(final Stage primaryStage) throws Exception{
        window = primaryStage;
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
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setScene(new Scene(root, 300, 400));
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
            Rectangle allScreenBounds = new Rectangle();
            Rectangle2D result = new Rectangle2D.Double();
            GraphicsEnvironment localGE = GraphicsEnvironment.getLocalGraphicsEnvironment();
            for (GraphicsDevice gd : localGE.getScreenDevices()) {
                for (GraphicsConfiguration graphicsConfiguration : gd.getConfigurations()) {
                    result.union(result, graphicsConfiguration.getBounds(), result);
                }
            }
            allScreenBounds.setSize((int) result.getWidth(), (int) result.getHeight());

            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(allScreenBounds);
            ImageIO.write(screenShot, "png" , new File(System.getProperty("user.home"),
                    String.valueOf("yo".concat(".png"))));
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    static void toggleRecording(){

    }
}
