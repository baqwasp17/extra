import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static java.lang.Math.*;

public class ParametricEqn extends Application {

	private GraphicsContext g;

	private double oldx = 400;
	private double oldy = 300;

	public double t = 0.0;
	public static void main(String args[]) {
		Application.launch(args);
	}

	private Parent createContent() {
		Pane root = new Pane();
		root.setPrefSize(800, 600);

		Canvas canvas = new Canvas(800, 600);
		g = canvas.getGraphicsContext2D();

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				t += 0.017;
				draw();
			}
		};

		timer.start();

		root.getChildren().add(canvas);

		return root;
	}

	public void draw() {
		Point2D p = curveFunction();

		g.setStroke(Color.BLACK);
		
		double newx = p.getX() + 400;
		double newy = p.getY() + 300;

		if(oldx != 400 && oldy != 300)
			g.strokeLine(oldx, oldy, newx, newy);

		//g.strokeOval(newx, newy, 1, 1);

		oldx = newx;
		oldy = newy;
	}

	public Point2D curveFunction() {
		// Butterfly Curve
		double x = sin(t)*(pow(E, cos(t)) - 2*cos(4*t) - pow(sin(t/12),5));
		double y = cos(t)*(pow(E, cos(t)) - 2*cos(4*t) - pow(sin(t/12),5));

		// cool stuff
		//double x = cos(t) - cos(80*t)*sin(t);
		//double y = 2*sin(t) - sin(80*t);

		// cool stuff
		//double x = t - 1.6*cos(24*t);
		//double y = t - 1.6*sin(25*t);

		// more stuff
		//double x = cos(t) + t*sin(t);
		//double y = sin(t) + t*cos(t);

		// MORE
/*
		 double x = 5.5*cos(t) - cos(5.5*t);
		 double y = 5.5*sin(t) - sin(5.5*t);
*/
		return new Point2D(x,-y).multiply(50);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Whatever");
		Scene scene = new Scene(createContent());
		stage.setScene(scene);
		stage.show();
	}
}
