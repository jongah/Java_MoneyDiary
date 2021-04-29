package Frame_Panel;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Connect.SetTableModel;
import Other.T_data;

public class Panel_Total extends JPanel{
	//지금까지의 모든 입력 데이터를 볼 수 있는 패널
	private static T_data datatable;
	private SetTableModel setdata = new SetTableModel();
	private JScrollPane scroll;
	
	public Panel_Total(int set) {
		//테마변경과 데이터 새로고침 이용을 위한 빈 생성자,다른 생성자와 구분을 위해 의미없는 값인 set을 받는다.
	}
	public Panel_Total() {
		//Panel_Total 생성자 메서드
		setLayout(null);
		
		datatable = new T_data(setdata.setTableModel_Base(0, 0));
		scroll = new JScrollPane(datatable);
		scroll.setBounds(50, 30, 950, 550);
		add(scroll);
		
		this.setBounds(140, 10, 1050, 650);
		this.setBackground(new Color(255, 255,255));
	}
	public void setAll_data() {
		//테이블 새로고침 메서드
		Panel_Total.datatable.setModel(setdata.setTableModel_Base(0, 0));
	}
}
