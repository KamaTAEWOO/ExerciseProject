// �ι�° ���â
package application;

import java.net.URL;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MiniProjectSubControl extends MiniProjectMainControl implements Initializable{
	// �ݱ� ��ư 
	@FXML private Button p2endButton;
	// ��̸�, ��Ʈ, ����,Ÿ�� �� �ֱ�.
	@FXML private Label p2exercise1, p2exercise2, p2exercise3, p2exercise4;
	
	@FXML private Label p2set1, p2set2, p2set3, p2set4;
	
	@FXML private Label p2count1, p2count2, p2count3, p2count4;
	
	@FXML private Label fc1, fc2, fc3, fc4;
	
	@FXML private Label p2timeValue1, p2timeValue2, p2timeValue3, p2timeValue4;
	
	//����
	@FXML private Label p2exerciseName;
	//�ð�
	@FXML private Label p2date;
	String savesetStr, savecountStr, savenameStr, savetimeStr;
	
	public void setSavetimeStr(String savetimeStr) {
		this.savetimeStr = savetimeStr;
	}

	String filePath ="C:\\gitProject\\abc.txt";
	
	public void setSavenameStr(String savenameStr) {
		this.savenameStr = savenameStr;
	}

	public void setSavecountStr(String savecountStr) {
		this.savecountStr = savecountStr;
	}


	public void setSavesetStr(String saveStr) {
		this.savesetStr = saveStr;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LocalDateTime ldt = LocalDateTime.now();
		p2date.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt).toString());
		p2exerciseName.setText(part);
		p2endButton.setOnAction(e->closeBtnOnAction(e));
		initOfList();
	}
	
	public void textsave() {
		try {
			FileWriter filewriter = new FileWriter(filePath, true);
			filewriter.write(savenameStr+"\n");
			filewriter.write(" ��Ʈ : "+savesetStr + "\n");
			filewriter.write(" ���� : "+savecountStr + "\n");
			filewriter.write(" �ð� : "+savetimeStr + "\n");
			filewriter.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// �ݱ� ��ư Ŭ���� ������
	public void closeBtnOnAction(ActionEvent e) {
		for(int i =0; i < checkList.size(); i ++) {
			setSavesetStr(setList.get(i));
			setSavecountStr(countList.get(i));
			setSavenameStr(checkList.get(i));
			setSavetimeStr(timeList.get(i));
			textsave();
		}
		
		checkList.clear();
		setList.clear();
		countList.clear();
		timeList.clear();
//		fullcount.clear();
		dialog.close();
	}
	
	public void initOfList() {
		Label[] name = {p2exercise1, p2exercise2, p2exercise3, p2exercise4};
		Label[] set = {p2set1, p2set2, p2set3, p2set4};
		Label[] count = {p2count1, p2count2, p2count3, p2count4};
		Label[] fullcount = {fc1, fc2, fc3, fc4};
		Label[] time = {p2timeValue1, p2timeValue2, p2timeValue3, p2timeValue4};
		
		for(int i = 0; i < checkList.size(); i++) {
			name[i].setText(checkList.get(i));
			set[i].setText(setList.get(i) + "��Ʈ");
			count[i].setText(countList.get(i) + "��");
			fullcount[i].setText(Integer.parseInt(setList.get(i)) * Integer.parseInt(countList.get(i))+"��");
			time[i].setText(timeList.get(i) + "��");
		}
	}
}
