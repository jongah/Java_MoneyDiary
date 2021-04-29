package Connect;

import java.awt.Color;

import Frame_Panel.Panel_Diy;
import Frame_Panel.Panel_Setting;
import Frame_Panel.Panel_Stat;
import Frame_Panel.Panel_Total;
import Frame_Panel.AddFrame;
import Frame_Panel.MainFrame;

public class Refresh {
	//서로 다른 프레임을 모아서 한번에 테마설정 및 테이블, 통계값을 지정해 주는 클래스 
	private static Panel_Diy p_d = new Panel_Diy(1);
	private static Panel_Total p_t = new Panel_Total(1);
	private static Panel_Stat p_s = new Panel_Stat(1);
	private static Panel_Setting p_se = new Panel_Setting(1);
	private static MainFrame m_f = new MainFrame(1);
	//메서드 이용을 위한 클래스 내용 없는 클래스 호출
	public void updata() {
		//테이블, 통계값 새로고침 메서드
		p_s.setAll_data();
		p_d.setAll_data();
		p_t.setAll_data();
	}
	public void setBlue() {
		//파란색으로 테마를 바꾸는 메서드
		p_d.setColor(new Color(104, 192, 212), Color.white);
		p_s.setColor(new Color(242, 170, 170), Color.white);
		p_se.setColor(new Color(155, 199, 192), Color.white);
		m_f.setColor(new Color(89, 166, 189), Color.white);
	}
	public void setBlack() {
		//검은색으로 테마를 바꾸는 메서드
		p_d.setColor(Color.black, new Color(255, 225, 0));
		p_s.setColor(Color.black, new Color(84, 189, 255));
		p_se.setColor(Color.black, new Color(255, 56, 59));
		m_f.setColor(Color.black, new Color(56, 255, 59));
	}
	public void setBasic() {
		//기본으로 다시 테마를 바꾸는 메서드
		p_d.setColor(new Color(217, 217, 217), Color.black);
		p_s.setColor(new Color(217, 217, 217), Color.black);
		p_se.setColor(new Color(217, 217, 217), Color.black);
		m_f.setColor(new Color(217, 217, 217), Color.black);
	}
}
