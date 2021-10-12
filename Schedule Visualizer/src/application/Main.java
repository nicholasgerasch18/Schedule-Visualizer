package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//hi
			//sdadsada
			//sadsadsa
			System.out.print("HI");
			primaryStage.setTitle("Schedule Visualizer");
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			root.setPadding(new Insets(10));

			//creating window elements
			Text scheduleVisualizer = new Text("Augustana Schedule Visualizer");
			root.setAlignment(scheduleVisualizer, Pos.TOP_CENTER);
		    root.setTop(scheduleVisualizer);
			
			TextArea textArea = new TextArea();
			root.setMargin(textArea, new Insets(20));
		    root.setCenter(textArea);
			
		    Button submit = new Button("Submit");
		    root.setAlignment(submit, Pos.BOTTOM_CENTER);
		    root.setMargin(submit, new Insets(10));
		    root.setBottom(submit);
		    
		    
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			submit.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			     public void handle(ActionEvent e) {
			         GridPane newRoot = new GridPane();
			         
			         Text formattedSchedule = new Text("Your schedule looks like this");
			         
			         newRoot.add(formattedSchedule, 0, 0);
			         newRoot.setPadding(new Insets(10));
			         newRoot.setVgap(15);
			         newRoot.setHgap(15);
			         Stage stage = new Stage();
			         stage.setTitle("TheCount");
			         stage.setScene(new Scene(newRoot,400,200));
			         stage.show();
			     }
			 });
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
