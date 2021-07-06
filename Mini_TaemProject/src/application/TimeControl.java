package application;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.text.Font;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.FontWeight;

public class TimeControl implements Initializable {
	@FXML private Label lblTime;
	@FXML private TextField timetext;
	@FXML private Button strbnt;
	private boolean stop;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		stop = false;
		Thread thread = new Thread() {
			@Override
			public void run() {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.US);
				while(!stop) {
					String strTime = sdf.format(new Date());
					Platform.runLater(()->{
						lblTime.setMaxSize(1000, 500);
						lblTime.setTextFill(javafx.scene.paint.Color.BLACK);
						lblTime.setFont(Font.font("LABDigital", FontWeight.BOLD, 30));
						lblTime.setText(strTime);
					});
					try { Thread.sleep(100); } catch (InterruptedException e) {}
				}
			};
		};
		thread.setDaemon(true);
		thread.start();
	}

}
