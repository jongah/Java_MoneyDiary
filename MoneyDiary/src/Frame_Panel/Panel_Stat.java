package Frame_Panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Connect.GetSumValue;
import Connect.SetTableModel;
import Other.B_button;
import Other.C_inputBar;
import Other.T_data;
import Other.Date_ymd;
import Other.F_input;
import Other.L_Label;
import Other.TF_inputBar;

public class Panel_Stat extends JPanel{
	//검색, 월별, 통계가 보여지는 패널
	private static int month, year;
	private static T_data datatable;
	private static L_Label lv_spen, lv_income, lv_differ, lv_balance;
	private static L_Label lv_food, lv_leisure, lv_essen, lv_other;
	private static B_button b_last, b_next, b_search;
	private L_Label l_year, l_month;
	private int x, y;
	private SetTableModel setdata = new SetTableModel();
	private GetSumValue sum = new GetSumValue();
	private JScrollPane scroll;
	private Date_ymd date;
	private L_Label l_spen, l_income, l_differ, l_balance;
	private L_Label l_food, l_leisure, l_essen, l_other;
	private C_inputBar c_search;
	private TF_inputBar tf_search;
	private F_input c_font = new F_input(15);
	private String[] getSum = new String[4];
	private String [] a_search = {"전체", "수입", "지출", "최저금액", "최고금액", "음식", "여가", "필수", "기타"};
	
	public Panel_Stat(int set) {
		//테마변경과 데이터 새로고침 이용을 위한 빈 생성자,다른 생성자와 구분을 위해 의미없는 값인 set을 받는다.
	}
	public Panel_Stat() {
		//Panel_Stat 생성자 메서드
		setLayout(null);
		b_last = new B_button("←", 400, 30, 70, 40, 25);
		b_next = new B_button("→", 580, 30, 70, 40, 25);
		add(b_last);
		add(b_next);
		
		date = new Date_ymd();
		Panel_Stat.year = date.getYear();
		Panel_Stat.month = date.getMonth();
		
		getSum = sum.sumBasics(Panel_Stat.year, Panel_Stat.month);
		
		x = 410; y = 115;
		add(Panel_Stat.lv_spen = new L_Label(getSum[0], x, y));
		add(Panel_Stat.lv_income = new L_Label(getSum[1], x, y += 40));
		add(Panel_Stat.lv_differ = new L_Label(getSum[2], x, y += 40));
		add(Panel_Stat.lv_balance = new L_Label(getSum[3], x, y += 40));
		
		x = 160; y = 115;
		add(l_spen = new L_Label("지출", x, y));
		add(l_income = new L_Label("수입", x, y += 40));
		add(l_differ = new L_Label("차액", x, y += 40));
		add(l_balance = new L_Label("잔액", x, y += 40));
		
		getSum = sum.sumCategory(Panel_Stat.year, Panel_Stat.month);
		
		x = 860; y = 115;
		add(Panel_Stat.lv_food = new L_Label(getSum[0], x, y));
		add(Panel_Stat.lv_leisure = new L_Label(getSum[1], x, y += 40));
		add(Panel_Stat.lv_essen = new L_Label(getSum[2], x, y += 40));
		add(Panel_Stat.lv_other = new L_Label(getSum[3], x, y += 40));
		
		x = 610; y = 115;
		add(l_food = new L_Label("음식", x, y));
		add(l_leisure = new L_Label("여가", x, y += 40));
		add(l_essen = new L_Label("필수", x, y += 40));
		add(l_other = new L_Label("기타", x, y += 40));
		
		int y = 310;
		add(c_search = new C_inputBar(c_font, a_search, 50, y));
		add(tf_search = new TF_inputBar(c_font, 180, y, 710));
		add(b_search = new B_button("search", 920, y, 80, 30, 15));
		
		Panel_Stat.datatable = new T_data(setdata.setTableModel_Base(Panel_Stat.year, Panel_Stat.month));
		scroll = new JScrollPane(Panel_Stat.datatable);
		scroll.setBounds(50, 370, 950, 265);
		add(scroll);
		
		l_year = new L_Label(Panel_Stat.year, 480, 0);
		l_year.setFont(new F_input(20));
		add(l_year);
		add(l_month = new L_Label(Panel_Stat.month, 480, 30));
		
		this.setBounds(140, 10, 1050, 650);
		this.setBackground(new Color(255, 255,255));
		
		b_last.addActionListener(new BA_moveMonth());
		b_next.addActionListener(new BA_moveMonth());
		b_search.addActionListener(new BA_search());
	}
	public void setAll_data() {
		//db값을 띄우는 컴포넌트 새로고침 메서드 
		Panel_Stat.datatable.setModel(setdata.setTableModel_Base(Panel_Stat.year, Panel_Stat.month));
		getSum = sum.sumBasics(Panel_Stat.year, Panel_Stat.month);
		Panel_Stat.lv_spen.setText(getSum[0]);
		Panel_Stat.lv_income.setText(getSum[1]);
		Panel_Stat.lv_differ.setText(getSum[2]);
		Panel_Stat.lv_balance.setText(getSum[3]);
		getSum = sum.sumCategory(Panel_Stat.year, Panel_Stat.month);
		Panel_Stat.lv_food.setText(getSum[0]);
		Panel_Stat.lv_leisure.setText(getSum[1]);
		Panel_Stat.lv_essen.setText(getSum[2]);
		Panel_Stat.lv_other.setText(getSum[3]);
	}
	public void setColor(Color back, Color fore) {
		//테마 설정 메서드
		b_last.setBackground(back);
		b_next.setBackground(back);
		b_search.setBackground(back);
		b_last.setForeground(fore);
		b_next.setForeground(fore);
		b_search.setForeground(fore);
	}
	class BA_search implements ActionListener{
		//검색 버튼을 눌렀을 때 
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String content = c_search.getSelectedItem().toString();
			String word = tf_search.getText();
			if(word.equals(""))
				word = "no";
			if(content.equals("전체") && word.equals("no"))
				//전체 + 검색 키워드가 없으면 그냥 년도와 월만 넘겨줘서 테이블을 가져온다.
				Panel_Stat.datatable.setModel(setdata.setTableModel_Base(Panel_Stat.year, Panel_Stat.month));
			else
				//아닐 경우 콤보박스 내용, 검색어, 년도, 월을 넘겨줘서 테이블 가져옴
				Panel_Stat.datatable.setModel(setdata.SetTableModel_Search(content, word, Panel_Stat.year, Panel_Stat.month));
		}	
	}
	class BA_moveMonth implements ActionListener{
		//월을 넘기는 버튼 년도도 바뀐다.
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(b_last)){
				if(Panel_Stat.month == 1) {
					Panel_Stat.month = 12;
					Panel_Stat.year --;
				}else {
					Panel_Stat.month --;
				}
			}else{
				if(Panel_Stat.month == 12) {
					Panel_Stat.month = 1;
					Panel_Stat.year ++;
				}else {
					Panel_Stat.month ++;
				}
			}
			l_month.setText(Panel_Stat.month +"");
			l_year.setText(Panel_Stat.year + "");
			//월이 바뀌면 이 패널의 컴포넌트를 다 새로고침 한다.
			setAll_data();
		}
	}	
}
