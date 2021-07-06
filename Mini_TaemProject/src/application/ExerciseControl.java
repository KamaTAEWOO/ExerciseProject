package application;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ExerciseControl extends MiniProjectMainControl implements Initializable{
		final int NUMBER = 4;
		// ���۽ð�, ����ð�, ��Ż�ð�
		@FXML private Button start1, start2, start3, start4;

		@FXML private Button end1, end2, end3, end4;

		@FXML public Label timeValue1,timeValue2, timeValue3, timeValue4;

		// üũ�ڽ�
		@FXML public CheckBox exercise1, exercise2, exercise3, exercise4;
		
		// ��Ʈ
		@FXML private TextField set1, set2, set3, set4;
		
		// count
		@FXML private TextField count1, count2, count3, count4;

		// �����ġ�� ���� ����.
		private Boolean isStart = false; // �������� �Ǵ��� �ʵ�.
		private Timeline timeLine1 = new Timeline(); 
		private DoubleProperty timeSeconds1 = new SimpleDoubleProperty();
		private Duration time1 = Duration.ZERO;
		private Timeline timeLine2 = new Timeline();
		private DoubleProperty timeSeconds2 = new SimpleDoubleProperty();
		private Duration time2 = Duration.ZERO;
		private Timeline timeLine3= new Timeline();  
		private DoubleProperty timeSeconds3 = new SimpleDoubleProperty();
		private Duration time3 = Duration.ZERO;
		private Timeline timeLine4 = new Timeline(); 
		private DoubleProperty timeSeconds4 = new SimpleDoubleProperty();
		private Duration time4 = Duration.ZERO;
		private Boolean stop1 = false;
		private Boolean stop2 = false;
		private Boolean stop3 = false;
		private Boolean stop4 = false;
		
		

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//�ؽ�Ʈ �ʵ� �Է� ����.
		TextField[] setField = {set1, set2, set3, set4};
		TextField[] countField = {count1, count2, count3, count4};
		for(int i = 0; i < setField.length; i++) {
			setField[i].setDisable(true);
			countField[i].setDisable(true);
		}
		
		//�ؽ�Ʈ �ʵ� Ŭ����
		if(resultCheck == true) {
			for(int i = 0; i < NUMBER; i++) {
				setField[i].clear();
				countField[i].clear();
			}
		}
		
		// ����, ���� ��ư ������
		start1.setOnAction(e->start1HandleBtnOnAction(e));
		end1.setOnAction(e->end1HandleBtnOnAction(e));
		start2.setOnAction(e->start2HandleBtnOnAction(e));
		end2.setOnAction(e->end2HandleBtnOnAction(e));
		start3.setOnAction(e->start3HandleBtnOnAction(e));
		end3.setOnAction(e->end3HandleBtnOnAction(e));
		start4.setOnAction(e->start4HandleBtnOnAction(e));
		end4.setOnAction(e->end4HandleBtnOnAction(e));
		// üũ�ڽ� ������.
		exercise1.setOnMouseClicked(e->initCheckBox1(e));
		exercise2.setOnMouseClicked(e->initCheckBox2(e));
		exercise3.setOnMouseClicked(e->initCheckBox3(e));
		exercise4.setOnMouseClicked(e->initCheckBox4(e));
	}
	
	// �ð� ��� �� ��ư 
	public void start1HandleBtnOnAction(ActionEvent event){ btnStartController(1); }
	public void end1HandleBtnOnAction(ActionEvent event){ btnEndController(1); }
	public void start2HandleBtnOnAction(ActionEvent event){ btnStartController(2); }
	public void end2HandleBtnOnAction(ActionEvent event){ btnEndController(2); }
	public void start3HandleBtnOnAction(ActionEvent event){ btnStartController(3); }
	public void end3HandleBtnOnAction(ActionEvent event){ btnEndController(3); }
	public void start4HandleBtnOnAction(ActionEvent event){ btnStartController(4); }
	public void end4HandleBtnOnAction(ActionEvent event){ btnEndController(4); }
	
	public void btnStartController(int num) {
		if(num == 1) {
			initTimerStart(0);
			setList.add(set1.getText());
			countList.add(count1.getText());
		}else if(num == 2) {
			initTimerStart(1);
			setList.add(set2.getText());
			countList.add(count2.getText());
		}else if(num == 3) {
			initTimerStart(2);
			setList.add(set3.getText());
			countList.add(count3.getText());
		}else {
			initTimerStart(3);
			setList.add(set4.getText());
			countList.add(count4.getText());
		}
	}
	
	public void btnEndController(int num) {
		if(num == 1) {
			timeLine1.stop(); //timeLine����
			timeList.add(timeValue1.getText());
		}else if(num == 2) {
			timeLine2.stop(); //timeLine����
			timeList.add(timeValue2.getText());
		}else if(num == 3) {
			timeLine3.stop(); //timeLine����
			timeList.add(timeValue3.getText());
		}else {
			timeLine4.stop(); //timeLine����
			timeList.add(timeValue4.getText());
		}
	}
	
	public void initTimerStart(int num) {
		for(int i = 0; i < 4; i++) {
			if(i == num) {
		    	if(num == 0) {
		    		timeLine1.stop(); // ���� �ð��� �����Ϸ��� timeLine�� �ʱ�ȭ�Ǿ� �ϹǷ� stop()
		    		time1 = Duration.ZERO; // time�� ���� ���� ���� �� ������ 0�̵Ǿ�� ��.
		    		timeValue1.textProperty().bind(timeSeconds1.asString()); // timeCheck �� timeSeconds �� ����
                }else if(num == 1){
                	timeLine2.stop(); // ���� �ð��� �����Ϸ��� timeLine�� �ʱ�ȭ�Ǿ� �ϹǷ� stop()
                	time2 = Duration.ZERO; // time�� ���� ���� ���� �� ������ 0�̵Ǿ�� ��.
                	timeValue2.textProperty().bind(timeSeconds2.asString()); // timeCheck �� timeSeconds �� ����
                }else if(num == 2){
                	timeLine3.stop(); // ���� �ð��� �����Ϸ��� timeLine�� �ʱ�ȭ�Ǿ� �ϹǷ� stop()
                	time3 = Duration.ZERO; // time�� ���� ���� ���� �� ������ 0�̵Ǿ�� ��.
                	timeValue3.textProperty().bind(timeSeconds3.asString()); // timeCheck �� timeSeconds �� ����
                }else if(num == 3){
                	timeLine4.stop(); // ���� �ð��� �����Ϸ��� timeLine�� �ʱ�ȭ�Ǿ� �ϹǷ� stop()
                	time4 = Duration.ZERO; // time�� ���� ���� ���� �� ������ 0�̵Ǿ�� ��.
                	timeValue4.textProperty().bind(timeSeconds4.asString()); // timeCheck �� timeSeconds �� ����
                }
		    	isStart=true; //newButton�� Ŭ�������Ƿ� isStart �� true��
		    	
		    	if(isStart == true){ 
		    		if(num == 0) {
		    			initTimerStart1();
		    		}else if(num == 1) {
		    			initTimerStart2();
		    		}else if(num == 2) {
		    			initTimerStart3();
		    		}else if(num == 3) {
			    		initTimerStart4();
		    		}
		    	}
			}
		}
	}
	
	public void initTimerStart1() {
    	if (timeLine1 == null) {
    		  // ���� �� �� ����.
	    } else {
	         timeLine1 = new Timeline(
	             new KeyFrame(Duration.millis(10), // 0.01�� ������ üũ
	             new EventHandler<ActionEvent>() {
	                 @Override
	                 public void handle(ActionEvent t) {
	                     Duration duration = ((KeyFrame)t.getSource()).getTime();
	                     time1 = time1.add(duration);
	                     timeSeconds1.set(time1.toSeconds());
	                
	                  }
	             })
	         );
	         timeLine1.setCycleCount(Timeline.INDEFINITE);
	         timeLine1.play();
	    }
    }
	public void initTimerStart2() {
	   	if (timeLine2 == null) {
	   		  // ���� �� �� ����.
	    } else {
	        timeLine2 = new Timeline(
	            new KeyFrame(Duration.millis(10), // 0.01�� ������ üũ
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent t) {
	                    Duration duration = ((KeyFrame)t.getSource()).getTime();
	                    time2 = time2.add(duration);
	                    timeSeconds2.set(time2.toSeconds());
	               
	                 }
	            })
	        );
	        timeLine2.setCycleCount(Timeline.INDEFINITE);
	        timeLine2.play();
	    }
    }
	public void initTimerStart3() {
	   	if (timeLine3 == null) {
	   		  // ���� �� �� ����.
        } else {
            timeLine3 = new Timeline(
                new KeyFrame(Duration.millis(10), // 0.01�� ������ üũ
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        Duration duration = ((KeyFrame)t.getSource()).getTime();
                        time3 = time3.add(duration);
                        timeSeconds3.set(time3.toSeconds());
                   
                     }
                })
            );
            timeLine3.setCycleCount(Timeline.INDEFINITE);
            timeLine3.play();
        }
	 }
	 public void initTimerStart4() {
	   	if (timeLine4 == null) {
	   		  // ���� �� �� ����.
        } else {
            timeLine4 = new Timeline(
                new KeyFrame(Duration.millis(10), // 0.01�� ������ üũ
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        Duration duration = ((KeyFrame)t.getSource()).getTime();
                        time4 = time4.add(duration);
                        timeSeconds4.set(time4.toSeconds());
                   
                     }
                })
            );
            timeLine4.setCycleCount(Timeline.INDEFINITE);
            timeLine4.play();
        }
	 }
	 
	// üũ�ڽ�
	public void initCheckBox1(MouseEvent e2) { initCheckBox(0); }
	public void initCheckBox2(MouseEvent e2) { initCheckBox(1); }
	public void initCheckBox3(MouseEvent e2) { initCheckBox(2); }
	public void initCheckBox4(MouseEvent e2) { initCheckBox(3); }
	//üũ�ڽ�����Ʈ
	public void initCheckBox(int num) {
		CheckBox[] checkName = {exercise1, exercise2, exercise3, exercise4};
		TextField[] setField = {set1, set2, set3, set4};
		TextField[] countField = {count1, count2, count3, count4};
		
		for(int i = 0; i < checkName.length; i++) {
			if(i == num) {
				if(checkName[i].isSelected()) {
		        	checkList.add(checkName[i].getText());
		        	for(int k = 0; k < NUMBER; k++) {
		        		if(i == k) {
		        			setField[k].setDisable(false);
		        			countField[k].setDisable(false);
		        		}
		        	}
		        }else {
		        	checkList.remove(checkName[i].getText());
		        	for(int k = 0; k < NUMBER; k++) {
		        		if(i == k) {
		        			setField[k].setDisable(true);
		        			countField[k].setDisable(true);
		        		}
		        	}
		        }
			}
		}
	}
}
