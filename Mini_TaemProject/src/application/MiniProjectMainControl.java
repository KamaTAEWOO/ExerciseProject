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
	// ���ι�ư: ���, ����, ��, ��ü ��ư
	@FXML private Button shoulder, chest, back, lowerBody;

	// � �̹���
	@FXML private ImageView image1, image2, image3, image4;
	
	// ���̹�ư�� ���� ���̺� ����
	@FXML private Pane changeTable;
	// Ÿ�̸� �ֱ� ���̺� ��
	@FXML private Pane timerPane;
	//������� ��ư
	@FXML private Button resultButton;
	
	//���̾�α�
	private Stage primaryStage;
	static Stage dialog; // �ٸ� Ŭ�������� ���̾�α׸� �ݱ� ���� �����.
	
	// ��������. -> ������ �ٸ��� clear() �ʼ���. �ƴϸ� ��ħ.
	// üũ�ڽ��� ���� ���� => �ٸ� ��Ʈ�ѿ� �����ؾ���.
	public static List<String> checkList = new ArrayList<String>();
	// set, count ��� ����Ʈ
	public static List<String> setList = new ArrayList<String>();
	public static List<String> countList = new ArrayList<String>();
	// Ÿ�̸� ��� ����Ʈ
	public static List<String> timeList = new ArrayList<String>();
	// ���� ����
	public static String part;
	// �ؽ�Ʈ�ʵ� Ŭ��� ���� ����
	public static boolean resultCheck;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Button[] mainBtn = {shoulder, chest, back, lowerBody};
		//ó�� ȭ�� �̹���.
		ImageView[] images = {image1, image2, image3, image4};
		images[0].setImage(new Image(getClass().getResourceAsStream("/images/noSelect1.png")));
		images[1].setImage(new Image(getClass().getResourceAsStream("/images/noSelect2.jfif")));
		images[2].setImage(new Image(getClass().getResourceAsStream("/images/noSelect1.png")));
		images[3].setImage(new Image(getClass().getResourceAsStream("/images/noSelect2.jfif")));
		
		// ���ι�ư Ŭ�� ������
		mainBtn[0].setOnAction(e->shoulderHandleBtnOnAction(e));// ���
		mainBtn[1].setOnAction(e->chestHandleBtnOnAction(e));// ����
		mainBtn[2].setOnAction(e->backHandleBtnOnAction(e));// ��
		mainBtn[3].setOnAction(e->lowerBodyHandleBtnOnAction(e));// ��ü
		
		//������� ��ư Ŭ�� ������
		resultButton.setOnAction(e->resultHendleBtnOnAction(e));
		
		//ȭ�鿡 Ÿ�̸� ��ġ
		try {
			timerPane.getChildren().add(FXMLLoader.load(getClass().getResource("TimeFx.fxml")));
		} catch (IOException e1) {
			System.out.println("Ÿ�̸Ӱ� �� ���ư��ϴ�.");
		}
	
	}
	
	//���̾�α� �Լ�
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	//���ι�ư ������ �Լ�
	public void mainHandleBtnOnAction(int num) {
		initImage(num);// �̹��� ����
		changeTable.getChildren().clear();
		try {
			switch(num) {
			case 1:
				changeTable.getChildren().add(FXMLLoader.load(getClass().getResource("shoulderData.fxml")));
				part = "���";
				break;
			case 2:
				changeTable.getChildren().add(FXMLLoader.load(getClass().getResource("chestData.fxml")));
				part = "����";
				break;
			case 3:
				changeTable.getChildren().add(FXMLLoader.load(getClass().getResource("backData.fxml")));
				part = "��";
				break;
			case 4:
				changeTable.getChildren().add(FXMLLoader.load(getClass().getResource("lowerBodyData.fxml")));
				part = "��ü";
				break;
			}
		}catch (IOException e) {
			System.out.println("���� ��ư���� ���� �߻�.");
		}
		
	}
	
	// ��� Ŭ�� ������ �Լ�
	public void shoulderHandleBtnOnAction(ActionEvent event){ mainHandleBtnOnAction(1); }
	// ���� Ŭ�� ������ �Լ�
	public void chestHandleBtnOnAction(ActionEvent event) { mainHandleBtnOnAction(2); }
	// �� Ŭ�� ������ �Լ�
	public void backHandleBtnOnAction(ActionEvent event) { mainHandleBtnOnAction(3); }
	// ��ü Ŭ�� ������ �Լ�
	public void lowerBodyHandleBtnOnAction(ActionEvent event) { mainHandleBtnOnAction(4); }
	
	
	//���� ��ư�� �ش��ϴ� �̹��� �ֱ�
	public void initImage(int num) {
		ImageView[] images = {image1, image2, image3, image4};
			switch(num) {
			case 1:
				//���
				images[0].setImage(new Image(getClass().getResourceAsStream("/images/dumbbell.jpg")));
				images[1].setImage(new Image(getClass().getResourceAsStream("/images/bentover.jfif")));
				images[2].setImage(new Image(getClass().getResourceAsStream("/images/side.png")));
				images[3].setImage(new Image(getClass().getResourceAsStream("/images/overhead.jfif")));
				break;
			case 2:
				//����
				images[0].setImage(new Image(getClass().getResourceAsStream("/images/Press.jpg")));
				images[1].setImage(new Image(getClass().getResourceAsStream("/images/fly.png")));
				images[2].setImage(new Image(getClass().getResourceAsStream("/images/bench_press.jpg")));
				images[3].setImage(new Image(getClass().getResourceAsStream("/images/Incline.jpg")));
				break;
			case 3:
				//��
				images[0].setImage(new Image(getClass().getResourceAsStream("/images/let_pool.PNG")));
				images[1].setImage(new Image(getClass().getResourceAsStream("/images/city.PNG")));
				images[2].setImage(new Image(getClass().getResourceAsStream("/images/Wonam.PNG")));
				images[3].setImage(new Image(getClass().getResourceAsStream("/images/pull_up.PNG")));
				break;
			case 4:
				//��ü
				images[0].setImage(new Image(getClass().getResourceAsStream("/images/extension.png")));
				images[1].setImage(new Image(getClass().getResourceAsStream("/images/squat.PNG")));
				images[2].setImage(new Image(getClass().getResourceAsStream("/images/Wonam.PNG")));
				images[3].setImage(new Image(getClass().getResourceAsStream("/images/standing.PNG")));
		}
	}
	
	//������� ��ư 
	public void resultHendleBtnOnAction(ActionEvent event) { 
		try {
			//�ؽ�Ʈ �ʵ� Ŭ����
			resultCheck = true;
			
			//���̾�α� �� ����Ʈ ���� �Ѹ���.
			dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			
			AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("miniproject2.fxml"));
			Scene scene = new Scene(anchorPane);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
		} catch (IOException e) {
			System.out.println("���̾�α� ����.");
			e.printStackTrace();
		}
	}
	
}















