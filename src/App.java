import java.io.File;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();

        Circle[] stars = new Circle[50];
        for(int i = 0; i < stars.length; ++i){
            double x = (Math.random() * 1000);
            double y = (Math.random() * 700);

            stars[i] = new Circle(x, y, 1, Color.WHITE);
        }
        root.getChildren().addAll(stars);
        addFadeMotionToStars(stars);

        Circle sun = new Circle(500, 350, 50, new ImagePattern( new Image(
            new File("resources/Sun.jpg").toURI().toString()),
            0, 0,
            1, 1,
            true
        ));

        Circle[] solarPath = new Circle[8];
        createPathForSolarSystem(solarPath);

        Circle[] palents = {new Circle(7), new Circle(15), new Circle(15), new Circle(12)
            , new Circle(17), new Circle(25), new Circle(20), new Circle(25)}; 
        
        String[] location = new String[]{
            "resources/Mercury.jpg",
            "resources/Venus.jpg",
            "resources/Earth.jpg",
            "resources/Mars.jpg",
            "resources/Jupiter.jpg",
            "resources/Saturn.jpg",
            "resources/Uranus.jpg",
            "resources/Neptune.jpg"
        };
        for (int i = 0; i < palents.length; ++i){
            palents[i].setFill(new ImagePattern( new Image(
                new File(location[i]).toURI().toString()),
                0, 0,
                1, 1,
                true
            ));
        }
        int duration = 2500;
        for (int i = 0; i < solarPath.length; ++i){
            createMotion(palents[i], solarPath[i], duration);
            duration += 500;
        }

        root.getChildren().add(sun);
        root.getChildren().addAll(solarPath);
        root.getChildren().addAll(palents);
        
        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add("Style.css");
        stage.setTitle("Solar System");
        stage.setScene(scene);
        stage.show();
    }
    private void addFadeMotionToStars(Circle[] stars){
        for (int i = 0; i < stars.length; ++i){
            FadeTransition fade = new FadeTransition(Duration.millis(1000), stars[i]);
            fade.setFromValue(Math.random());
            fade.setToValue(1.0);
            fade.setCycleCount(FadeTransition.INDEFINITE);
            fade.setAutoReverse(true);
            fade.play();
        }
    }
    private void createPathForSolarSystem(Circle[] solarPath){
        int distance = 100;
        for (int i = 0; i < solarPath.length; ++i){
            solarPath[i] = new Circle(500, 350, distance);
            solarPath[i].setFill(null);
            solarPath[i].setStroke(Color.WHITE);
            solarPath[i].setStrokeWidth(.2);
            distance += 35;
        }
    }
    private void createMotion(Circle planet, Circle path, int duration){
        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(planet);
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.millis(duration));
        pathTransition.setCycleCount(PathTransition.INDEFINITE);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.play();
    }
}
