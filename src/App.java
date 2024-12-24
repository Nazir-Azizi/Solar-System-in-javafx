import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

        Circle sun = new Circle(500, 350, 50);
        sun.setFill(Color.YELLOW);

        Circle[] solarPath = new Circle[8];
        createPathForSolarSystem(solarPath);
        
        Circle mercury = new Circle(7);
        mercury.setFill(Color.GRAY);

        Circle venus = new Circle(15);
        venus.setFill(Color.YELLOW);

        Circle earth = new Circle(15);
        earth.setFill(Color.BLUE);

        Circle mars = new Circle(12);
        mars.setFill(Color.RED);

        Circle jupiter = new Circle(27);
        jupiter.setFill(Color.ORANGE);

        Circle saturn = new Circle(25);
        saturn.setFill(Color.PALEGOLDENROD);

        Circle uranus = new Circle(20);
        uranus.setFill(Color.WHEAT);

        Circle neptune = new Circle(25);
        neptune.setFill(Color.BLUE);

        Circle[] palents = {mercury, venus, earth, mars, jupiter, saturn, uranus, neptune}; 
        PathTransition[] pathTransitions = new PathTransition[8];
        int duration = 2500;
        for (int i = 0; i < solarPath.length; ++i){
            createMotion(pathTransitions[i], palents[i], solarPath[i], duration);
            duration += 300;
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
    private void createPathForSolarSystem(Circle[] solarPath){
        int distance = 100;
        for (int i = 0; i < solarPath.length; ++i){
            solarPath[i] = new Circle(500, 350, distance);
            solarPath[i].setFill(null);
            solarPath[i].setStroke(Color.WHITE);
            solarPath[i].setStrokeWidth(1);
            distance += 35;
        }
    }
    private void createMotion(PathTransition pathTransition, Circle planet, Circle path, int duration){
        pathTransition = new PathTransition();
        pathTransition.setNode(planet);
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.millis(duration));
        pathTransition.setCycleCount(PathTransition.INDEFINITE);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.play();
    }
}
