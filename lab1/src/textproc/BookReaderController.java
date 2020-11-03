package textproc;

import java.io.File;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class BookReaderController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("BookReader");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Set<String> stopwords = new HashSet<String>();
		Scanner scan = new Scanner(new File("/Users/TomasVu/Downloads/edaa01-workspace/lab1/undantagsord.txt"));
		scan.findWithinHorizon("\uFEFF", 1);
		
		while(scan.hasNext())
			stopwords.add(scan.next().toLowerCase());
		
		scan.close();
		
		GeneralWordCounter r = new GeneralWordCounter(stopwords);
		
		Scanner s = new Scanner(new File("/Users/TomasVu/Downloads/edaa01-workspace/lab1/nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			r.process(word);
		}
		s.close();
		
		ObservableList<Entry<String, Integer>> words
        	= FXCollections.observableArrayList(r.getWords());
		ListView<Entry<String, Integer>> listView
        	= new ListView<Entry<String, Integer>>(words);
        root.setCenter(listView);
        
        HBox hbox = new HBox();
        Button b1 = new Button("Alphabetic");
        Button b2 = new Button("Frequency");
        Button b3 = new Button("Find");
        TextField text = new TextField();
        
        b1.setOnAction(event -> {words.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey()));});
        b2.setOnAction(event -> {words.sort((e1, e2) -> -(e1.getValue().compareTo(e2.getValue())));});
        b3.setOnAction(event -> {
        	String target = text.getText().toLowerCase().trim(); //v3
        	for(Entry<String, Integer> e : words) {
        		if(e.getKey().equals(target)) {
        			listView.scrollTo(e);
        			listView.getSelectionModel().select(e);	//v4
        			break;	//avoid unnecessary iteration
        		}
        	}
        });



		HBox.setHgrow(text, Priority.ALWAYS);	//v2
		hbox.getChildren().addAll(b1, b2, text, b3);
        root.setBottom(hbox);
	
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
