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
	//값을 입력하는 프레임
	private B_button b_save  = new B_button("Save", 860, 200);
	private Dto_all dto_all = new Dto_all();
	private DB_Manager dao = new DB_Manager();
	private Refresh refresh = new Refresh();
	private L_Label l_date, l_form, l_category, l_content, l_amount;
	private C_inputBar c_inputForm, c_inputCategory;
	private TF_inputBar tf_inputDate, tf_inputContent, tf_inputAmount;
	private Date_ymd date = new Date_ymd();
	private F_input inputFont = new F_input(15);
	private String [] a_form = {"수입", "지출"};
	private String [] a_category = {"음식", "여가", "필수", "기타"};
	public AddFrame() {
		//addFrame 생성자
		setLayout(null);
		setTitle("& 월 수입 300 기원 &");
		
		add(l_date = new L_Label("날짜", 50));
		add(l_form = new L_Label("수입/지출", 180));
		add(l_category = new L_Label("카테고리", 330));
		add(l_content = new L_Label("내용", 480));
		add(l_amount = new L_Label("금액", 750));

		add(tf_inputDate = new TF_inputBar(inputFont, date.getDate() + "", 50, 100));
		add(c_inputForm = new C_inputBar(inputFont, a_form, 180, 110));
		add(c_inputCategory = new C_inputBar(inputFont, a_category, 330, 110));
		add(tf_inputContent = new TF_inputBar(inputFont, "내용을 입력해 주세요", 480, 250));
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
		//save버튼을 눌렀을때 액션 리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			int day = Integer.parseInt(tf_inputDate.getText());
			if(tf_inputAmount.getText().equals("") || day > date.getlast()) {
				//금액란이 비어있고, 날짜가 이번달의 마지막날 보다 클 때 경고창 띄우기
				JOptionPane.showMessageDialog(null, "날짜 또는 금액을 똑바로 입력해 주세요", "입력을 잘하자", JOptionPane.ERROR_MESSAGE);
			}else {
				//dto에 값을 set하고 dto객체를 dao의 insert메서드에 넘긴다
				contentSet();
				try {
					dao.insertData(dto_all);
					refresh.updata();
					//프로그램의 모든 데이터 컴포넌트에 값을 새로 입력
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				dispose();
				//창을 닫는다.
			}
		}
	}
	
	class KL_textfiled implements KeyListener{
		//텍스트 필드를 위한 key리스너
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
			//숫자만만 입력 받는다
			char c = e.getKeyChar();
			if (!Character.isDigit(c)) {
				e.consume();
				return;
			}
			if(e.getSource().equals(tf_inputDate)) {
				//날짜는 2자리까지만 입력
				if(tf_inputDate.getText().length() > 1)
					e.consume();
			}else {
				//금액은 최대 100만원 단위까지 입력
				if(tf_inputAmount.getText().length() > 6)
					e.consume();
			}
		}
	}
	public void contentSet(){
		//dto에 입력받은 정보를 set하는 메서드
		String tf_d = tf_inputDate.getText();
		int int_d = (Integer.parseInt(tf_d)) + 1;
		//Date는 Date타입으로 입력
		Date tf_date = Date.valueOf(date.getYear() + "-" + date.getMonth() + "-" + int_d);
		String tf_form = c_inputForm.getSelectedItem().toString();
		String tf_cate = c_inputCategory.getSelectedItem().toString();
		String tf_cont = tf_inputContent.getText();
		int tf_amount = Integer.parseInt(tf_inputAmount.getText());
		
		dto_all.setDate(tf_date);
		if(tf_form.equals("지출")) {
			//지출일 경우 금액에 -를 곱하고 카테고리 추가
			dto_all.setAmount(-(tf_amount));
			dto_all.setCategory(tf_cate);
		}else {
			//아닐경우 금액을 그대로 입력하고 카테고리는 -으로 입력
			dto_all.setAmount(tf_amount);
			dto_all.setCategory("-");
		}
		dto_all.setForm(tf_form);
		dto_all.setContent(tf_cont);
	}
}
