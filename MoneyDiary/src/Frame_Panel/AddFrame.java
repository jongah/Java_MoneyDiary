package Frame_Panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Connect.Refresh;
import DB.DB_Manager;
import DB.Dto_all;
import Other.B_button;
import Other.C_inputBar;
import Other.Date_ymd;
import Other.F_input;
import Other.L_Label;
import Other.TF_inputBar;

public class AddFrame extends JFrame{
	//���� �Է��ϴ� ������
	private B_button b_save  = new B_button("Save", 860, 200);
	private Dto_all dto_all = new Dto_all();
	private DB_Manager dao = new DB_Manager();
	private Refresh refresh = new Refresh();
	private L_Label l_date, l_form, l_category, l_content, l_amount;
	private C_inputBar c_inputForm, c_inputCategory;
	private TF_inputBar tf_inputDate, tf_inputContent, tf_inputAmount;
	private Date_ymd date = new Date_ymd();
	private F_input inputFont = new F_input(15);
	private String [] a_form = {"����", "����"};
	private String [] a_category = {"����", "����", "�ʼ�", "��Ÿ"};
	public AddFrame() {
		//addFrame ������
		setLayout(null);
		setTitle("& �� ���� 300 ��� &");
		
		add(l_date = new L_Label("��¥", 50));
		add(l_form = new L_Label("����/����", 180));
		add(l_category = new L_Label("ī�װ�", 330));
		add(l_content = new L_Label("����", 480));
		add(l_amount = new L_Label("�ݾ�", 750));

		add(tf_inputDate = new TF_inputBar(inputFont, date.getDate() + "", 50, 100));
		add(c_inputForm = new C_inputBar(inputFont, a_form, 180, 110));
		add(c_inputCategory = new C_inputBar(inputFont, a_category, 330, 110));
		add(tf_inputContent = new TF_inputBar(inputFont, "������ �Է��� �ּ���", 480, 250));
		add(tf_inputAmount = new TF_inputBar(inputFont, "", 750, 200));
		
		add(b_save);
		
		this.setResizable(false);
		this.setSize(1000, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.getContentPane().setBackground(new Color(255, 255,255));
		
		tf_inputDate.addKeyListener(new KL_textfiled());
		tf_inputAmount.addKeyListener(new KL_textfiled());
		b_save.addActionListener(new BA_save());
	}
	class BA_save implements ActionListener{
		//save��ư�� �������� �׼� ������
		@Override
		public void actionPerformed(ActionEvent e) {
			int day = Integer.parseInt(tf_inputDate.getText());
			if(tf_inputAmount.getText().equals("") || day > date.getlast()) {
				//�ݾ׶��� ����ְ�, ��¥�� �̹����� �������� ���� Ŭ �� ���â ����
				JOptionPane.showMessageDialog(null, "��¥ �Ǵ� �ݾ��� �ȹٷ� �Է��� �ּ���", "�Է��� ������", JOptionPane.ERROR_MESSAGE);
			}else {
				//dto�� ���� set�ϰ� dto��ü�� dao�� insert�޼��忡 �ѱ��
				contentSet();
				try {
					dao.insertData(dto_all);
					refresh.updata();
					//���α׷��� ��� ������ ������Ʈ�� ���� ���� �Է�
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				dispose();
				//â�� �ݴ´�.
			}
		}
	}
	
	class KL_textfiled implements KeyListener{
		//�ؽ�Ʈ �ʵ带 ���� key������
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			//���ڸ��� �Է� �޴´�
			char c = e.getKeyChar();
			if (!Character.isDigit(c)) {
				e.consume();
				return;
			}
			if(e.getSource().equals(tf_inputDate)) {
				//��¥�� 2�ڸ������� �Է�
				if(tf_inputDate.getText().length() > 1)
					e.consume();
			}else {
				//�ݾ��� �ִ� 100���� �������� �Է�
				if(tf_inputAmount.getText().length() > 6)
					e.consume();
			}
		}
	}
	public void contentSet(){
		//dto�� �Է¹��� ������ set�ϴ� �޼���
		String tf_d = tf_inputDate.getText();
		int int_d = (Integer.parseInt(tf_d)) + 1;
		//Date�� DateŸ������ �Է�
		Date tf_date = Date.valueOf(date.getYear() + "-" + date.getMonth() + "-" + int_d);
		String tf_form = c_inputForm.getSelectedItem().toString();
		String tf_cate = c_inputCategory.getSelectedItem().toString();
		String tf_cont = tf_inputContent.getText();
		int tf_amount = Integer.parseInt(tf_inputAmount.getText());
		
		dto_all.setDate(tf_date);
		if(tf_form.equals("����")) {
			//������ ��� �ݾ׿� -�� ���ϰ� ī�װ� �߰�
			dto_all.setAmount(-(tf_amount));
			dto_all.setCategory(tf_cate);
		}else {
			//�ƴҰ�� �ݾ��� �״�� �Է��ϰ� ī�װ��� -���� �Է�
			dto_all.setAmount(tf_amount);
			dto_all.setCategory("-");
		}
		dto_all.setForm(tf_form);
		dto_all.setContent(tf_cont);
	}
}
