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
			final int COLUMN_NUM = 12;
			
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
			         
			         String input = textArea.getText();
			         String[] splitInput = input.split("\n");
			         int size = (splitInput.length/COLUMN_NUM)+1;
			         System.out.print(size);
			         
			         for(int i = 0; i<splitInput.length;i++) {
			        	 System.out.println(splitInput[i]);
			         }
			         
			         
			         
//			         String[][] classMatrix = new String[size][COLUMN_NUM];
//			         int count = 0;
//			         for(int i = 0; i<size;i++) {
//			        	 for(int n = 0; i<COLUMN_NUM;n++) {
//			        		 String current = splitInput[count];
//			        		 classMatrix[i][n]="hi";
//			        		 count++;
//			        	 }
//			         }
//			         		         
			         
			         Text monday = new Text("		Monday		");		
			         Text tuesday = new Text("Tuesday		");			         
			         Text wednesday = new Text("Wednesday		");			         
			         Text thursday = new Text("Thursday		");			         
			         Text friday = new Text("Friday		");			         
			         Text saturday = new Text("Saturday		");			         
			         Text sunday = new Text("Sunday");			         

			         newRoot.add(monday, 0, 0);
			         newRoot.add(tuesday, 1, 0);
			         newRoot.add(wednesday, 2, 0);
			         newRoot.add(thursday, 3, 0);
			         newRoot.add(friday, 4, 0);
			         newRoot.add(saturday, 5, 0);
			         newRoot.add(sunday, 6, 0);
			         
			         
			         newRoot.setPadding(new Insets(10));
			         newRoot.setVgap(15);
			         newRoot.setHgap(15);
			         Stage stage = new Stage();
			         stage.setTitle("New Visualized Schedule!!!!!");
			         stage.setScene(new Scene(newRoot,750,200));
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
