package Frame_Panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import javax.swing.JPanel;

import Connect.Refresh;
import DB.DB_Manager;
import Other.B_button;

public class Panel_Setting extends JPanel{
	//설정 패널
	private static B_button b_delete, b_setBlue, b_setBlack, b_setBasic;
	private Refresh refresh = new Refresh();
	private Panel_Diy p_d = new Panel_Diy(1);
	private Panel_Total p_t = new Panel_Total(1);
	private Panel_Stat p_s = new Panel_Stat(1);
	private DB_Manager dao = new DB_Manager();
	public Panel_Setting(int set) {
		//테마변경과 데이터 새로고침 이용을 위한 빈 생성자,다른 생성자와 구분을 위해 의미없는 값인 set을 받는다.
	}
	public Panel_Setting() {
		//Panel_Setting 생성자 메서드
		setLayout(null);
		add(b_delete = new B_button("절대절대누르지마시오", 425, 100 ,200, 200, 15));
		int x = 250, y = 400;
		add(b_setBlue = new B_button("파랑파랑이", x, 400, 150, 60, 20));
		add(b_setBlack = new B_button("블랙모드", x += 200, 400, 150, 60, 20));
		add(b_setBasic = new B_button("회색회색", x += 200, 400, 150, 60, 20));
		this.setBounds(140, 10, 1050, 650);
		this.setBackground(new Color(255, 255,255));
		
		b_delete.addActionListener(new BA_delete());
		b_setBlue.addActionListener(new BA_tema());
		b_setBlack.addActionListener(new BA_tema());
		b_setBasic.addActionListener(new BA_tema());
	}
	class BA_delete implements ActionListener{
		//누르지 마시오 버튼을 눌렀을 때 뜨는 권유(?)창
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String [] yes_no = {"지울래요", "지우기싫어요"};
			int result = JOptionPane.showOptionDialog(null, "모든 기록을 날려버릴 수 있습니다!!", "마지막 기회",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, yes_no, "지울래요");
			if(result == 0) {
				//지울래요를 누르면 DB의 테이블을 날려버린다 그리고 모든 컴포넌트 새로고침
				dao.deleteTable();
				refresh.updata();
			}
		}
	}
	class BA_tema implements ActionListener{
		//테마 변경 버튼, refesh의 각각 메서드 호출
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(b_setBlue)) {
				refresh.setBlue();
			}else if(e.getSource().equals(b_setBasic)) {
				refresh.setBasic();
			}else {
				refresh.setBlack();
			}
		}
	}
	public void setColor(Color back, Color fore) {
		//설정 패널의 테마 변경 메서드
		b_delete.setBackground(back);
		b_delete.setForeground(fore);
		b_setBlue.setBackground(back);
		b_setBlue.setForeground(fore);
		b_setBlack.setBackground(back);
		b_setBlack.setForeground(fore);
		b_setBasic.setBackground(back);
		b_setBasic.setForeground(fore);
	}
} 
