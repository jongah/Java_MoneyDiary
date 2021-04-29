package Frame_Panel;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connect.GetSumValue;
import Connect.SetTableModel;
import DB.DB_Manager;
import Other.B_button;
import Other.T_data;
import Other.Date_ymd;
import Other.F_input;
import Other.L_Label;

public class Panel_Diy extends JPanel{
	//���� �г�(�� �Է�, �̹��� ���� Ȯ��, ������ ��� Ȯ��)
	private static T_data datatable;
	private static int month, year;
	private static L_Label lv_spen, lv_income, lv_balance, lv_differ;
	private static B_button b_add;
	private SetTableModel setdata = new SetTableModel();
	private GetSumValue sum = new GetSumValue();
	private Date_ymd date;
	private JScrollPane scroll;
	private L_Label l_spen, l_income, l_balance, l_differ;
	private String[] getSum = new String[4];
	
	public Panel_Diy(int set) {
		//�׸������ ������ ���ΰ�ħ �̿��� ���� �� ������,�ٸ� �����ڿ� ������ ���� �ǹ̾��� ���� set�� �޴´�.
	}
	public Panel_Diy() {
		//panel_Diy ������
		setLayout(null);
		
		date = new Date_ymd();
		Panel_Diy.month = date.getMonth();
		Panel_Diy.year = date.getYear();
		
		getSum = sum.sumBasics(Panel_Diy.year, Panel_Diy.month);
		
		datatable = new T_data(setdata.setTableModel_Base(Panel_Diy.year, Panel_Diy.month));
		scroll = new JScrollPane(datatable);
		scroll.setBounds(50, 90, 950, 325);
		add(scroll);
		
		int x = 300; int y = 440;
		add(Panel_Diy.lv_spen = new L_Label(getSum[0], x, y));
		add(Panel_Diy.lv_income = new L_Label(getSum[1], x, y += 40));
		add(Panel_Diy.lv_differ = new L_Label(getSum[2], x, y += 40));
		add(Panel_Diy.lv_balance = new L_Label(getSum[3], x, y += 40));
		
		x = 50; y = 440;
		add(l_spen = new L_Label("����", x, y));
		add(l_income = new L_Label("����", x, y += 40));
		add(l_differ = new L_Label("����", x, y += 40));
		add(l_balance = new L_Label("�ܾ�", x, y += 40));
		
		add(new L_Label(Panel_Diy.month, 480, 30));
		add(b_add = new B_button("Add", 880, 20));
		
		this.setBounds(140, 10, 1050, 650);
		this.setBackground(new Color(255, 255,255));
		
		b_add.addActionListener(new BA_add());
	}
	class BA_add implements ActionListener{
		//add��ư�� ������ �� �׼� ������
		@Override
		public void actionPerformed(ActionEvent e) {
			//addframe�� ����.
			new AddFrame();
		}
	}
	public void setAll_data() {
		//db�� ���� �����ִ� ������Ʈ�� ���� ���� �������� �޼���
		datatable.setModel(setdata.setTableModel_Base(Panel_Diy.year, Panel_Diy.month));
		getSum = sum.sumBasics(Panel_Diy.year, Panel_Diy.month);
		Panel_Diy.lv_spen.setText(getSum[0]);
		Panel_Diy.lv_income.setText(getSum[1]);
		Panel_Diy.lv_balance.setText(getSum[2]);
		Panel_Diy.lv_differ.setText(getSum[3]);
	}
	public void setColor(Color back, Color fore) {
		//�׸� ���� �޼���
		b_add.setBackground(back);
		b_add.setForeground(fore);
	}
}






