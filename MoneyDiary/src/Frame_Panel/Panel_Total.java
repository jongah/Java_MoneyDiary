package Frame_Panel;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Connect.SetTableModel;
import Other.T_data;

public class Panel_Total extends JPanel{
	//���ݱ����� ��� �Է� �����͸� �� �� �ִ� �г�
	private static T_data datatable;
	private SetTableModel setdata = new SetTableModel();
	private JScrollPane scroll;
	
	public Panel_Total(int set) {
		//�׸������ ������ ���ΰ�ħ �̿��� ���� �� ������,�ٸ� �����ڿ� ������ ���� �ǹ̾��� ���� set�� �޴´�.
	}
	public Panel_Total() {
		//Panel_Total ������ �޼���
		setLayout(null);
		
		datatable = new T_data(setdata.setTableModel_Base(0, 0));
		scroll = new JScrollPane(datatable);
		scroll.setBounds(50, 30, 950, 550);
		add(scroll);
		
		this.setBounds(140, 10, 1050, 650);
		this.setBackground(new Color(255, 255,255));
	}
	public void setAll_data() {
		//���̺� ���ΰ�ħ �޼���
		Panel_Total.datatable.setModel(setdata.setTableModel_Base(0, 0));
	}
}
