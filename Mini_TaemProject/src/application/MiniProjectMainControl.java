package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class MiniProjectMainControl implements Initializable{
	// 메인버튼: 어깨, 가슴, 등, 하체 버튼
	@FXML private Button shoulder, chest, back, lowerBody;

	// 운동 이미지
	@FXML private ImageView image1, image2, image3, image4;
	
	// 메이버튼에 따른 테이블 변경
	@FXML private Pane changeTable;
	// 타이머 넣기 테이블 변
	@FXML private Pane timerPane;
	//결과보기 버튼
	@FXML private Button resultButton;
	
	//다이얼로그
	private Stage primaryStage;
	static Stage dialog; // 다른 클래스에서 다이얼로그를 닫기 위해 사용함.
	
	// 공유변수. -> 부위가 다르면 clear() 필수임. 아니면 겹침.
	// 체크박스에 사용된 변수 => 다른 컨트롤에 전달해야함.
	public static List<String> checkList = new ArrayList<String>();
	// set, count 담는 리스트
	public static List<String> setList = new ArrayList<String>();
	public static List<String> countList = new ArrayList<String>();
	// 타이머 담는 리스트
	public static List<String> timeList = new ArrayList<String>();
	// 부위 종류
	public static String part;
	// 텍스트필드 클리어를 위한 변수
	public static boolean resultCheck;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Button[] mainBtn = {shoulder, chest, back, lowerBody};
		//처음 화면 이미지.
		ImageView[] images = {image1, image2, image3, image4};
		images[0].setImage(new Image(getClass().getResourceAsStream("/images/noSelect1.png")));
		images[1].setImage(new Image(getClass().getResourceAsStream("/images/noSelect2.jfif")));
		images[2].setImage(new Image(getClass().getResourceAsStream("/images/noSelect1.png")));
		images[3].setImage(new Image(getClass().getResourceAsStream("/images/noSelect2.jfif")));
		
		// 메인버튼 클릭 리스너
		mainBtn[0].setOnAction(e->shoulderHandleBtnOnAction(e));// 어깨
		mainBtn[1].setOnAction(e->chestHandleBtnOnAction(e));// 가슴
		mainBtn[2].setOnAction(e->backHandleBtnOnAction(e));// 등
		mainBtn[3].setOnAction(e->lowerBodyHandleBtnOnAction(e));// 하체
		
		//결과보기 버튼 클릭 리스너
		resultButton.setOnAction(e->resultHendleBtnOnAction(e));
		
		//화면에 타이머 배치
		try {
			timerPane.getChildren().add(FXMLLoader.load(getClass().getResource("TimeFx.fxml")));
		} catch (IOException e1) {
			System.out.println("타이머가 안 돌아갑니다.");
		}
	
	}
	
	//다이얼로그 함수
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	//메인버튼 리스너 함수
	public void mainHandleBtnOnAction(int num) {
		initImage(num);// 이미지 변경
		changeTable.getChildren().clear();
		try {
			switch(num) {
			case 1:
				changeTable.getChildren().add(FXMLLoader.load(getClass().getResource("shoulderData.fxml")));
				part = "어깨";
				break;
			case 2:
				changeTable.getChildren().add(FXMLLoader.load(getClass().getResource("chestData.fxml")));
				part = "가슴";
				break;
			case 3:
				changeTable.getChildren().add(FXMLLoader.load(getClass().getResource("backData.fxml")));
				part = "등";
				break;
			case 4:
				changeTable.getChildren().add(FXMLLoader.load(getClass().getResource("lowerBodyData.fxml")));
				part = "하체";
				break;
			}
		}catch (IOException e) {
			System.out.println("메인 버튼에서 에러 발생.");
		}
		
	}
	
	// 어깨 클릭 리스너 함수
	public void shoulderHandleBtnOnAction(ActionEvent event){ mainHandleBtnOnAction(1); }
	// 가슴 클릭 리스너 함수
	public void chestHandleBtnOnAction(ActionEvent event) { mainHandleBtnOnAction(2); }
	// 등 클릭 리스너 함수
	public void backHandleBtnOnAction(ActionEvent event) { mainHandleBtnOnAction(3); }
	// 하체 클릭 리스너 함수
	public void lowerBodyHandleBtnOnAction(ActionEvent event) { mainHandleBtnOnAction(4); }
	
	
	//메인 버튼에 해당하는 이미지 넣기
	public void initImage(int num) {
		ImageView[] images = {image1, image2, image3, image4};
			switch(num) {
			case 1:
				//어깨
				images[0].setImage(new Image(getClass().getResourceAsStream("/images/dumbbell.jpg")));
				images[1].setImage(new Image(getClass().getResourceAsStream("/images/bentover.jfif")));
				images[2].setImage(new Image(getClass().getResourceAsStream("/images/side.png")));
				images[3].setImage(new Image(getClass().getResourceAsStream("/images/overhead.jfif")));
				break;
			case 2:
				//가슴
				images[0].setImage(new Image(getClass().getResourceAsStream("/images/Press.jpg")));
				images[1].setImage(new Image(getClass().getResourceAsStream("/images/fly.png")));
				images[2].setImage(new Image(getClass().getResourceAsStream("/images/bench_press.jpg")));
				images[3].setImage(new Image(getClass().getResourceAsStream("/images/Incline.jpg")));
				break;
			case 3:
				//등
				images[0].setImage(new Image(getClass().getResourceAsStream("/images/let_pool.PNG")));
				images[1].setImage(new Image(getClass().getResourceAsStream("/images/city.PNG")));
				images[2].setImage(new Image(getClass().getResourceAsStream("/images/Wonam.PNG")));
				images[3].setImage(new Image(getClass().getResourceAsStream("/images/pull_up.PNG")));
				break;
			case 4:
				//하체
				images[0].setImage(new Image(getClass().getResourceAsStream("/images/extension.png")));
				images[1].setImage(new Image(getClass().getResourceAsStream("/images/squat.PNG")));
				images[2].setImage(new Image(getClass().getResourceAsStream("/images/Wonam.PNG")));
				images[3].setImage(new Image(getClass().getResourceAsStream("/images/standing.PNG")));
		}
	}
	
	//결과보기 버튼 
	public void resultHendleBtnOnAction(ActionEvent event) { 
		try {
			//텍스트 필드 클리어
			resultCheck = true;
			
			//다이얼로그 및 리스트 값들 뿌리기.
			dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			
			AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("miniproject2.fxml"));
			Scene scene = new Scene(anchorPane);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
		} catch (IOException e) {
			System.out.println("다이얼로그 에러.");
			e.printStackTrace();
		}
	}
	
}















