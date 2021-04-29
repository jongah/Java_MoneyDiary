package Connect;

import java.awt.Color;

import Frame_Panel.Panel_Diy;
import Frame_Panel.Panel_Setting;
import Frame_Panel.Panel_Stat;
import Frame_Panel.Panel_Total;
import Frame_Panel.AddFrame;
import Frame_Panel.MainFrame;

public class Refresh {
	//���� �ٸ� �������� ��Ƽ� �ѹ��� �׸����� �� ���̺�, ��谪�� ������ �ִ� Ŭ���� 
	private static Panel_Diy p_d = new Panel_Diy(1);
	private static Panel_Total p_t = new Panel_Total(1);
	private static Panel_Stat p_s = new Panel_Stat(1);
	private static Panel_Setting p_se = new Panel_Setting(1);
	private static MainFrame m_f = new MainFrame(1);
	//�޼��� �̿��� ���� Ŭ���� ���� ���� Ŭ���� ȣ��
	public void updata() {
		//���̺�, ��谪 ���ΰ�ħ �޼���
		p_s.setAll_data();
		p_d.setAll_data();
		p_t.setAll_data();
	}
	public void setBlue() {
		//�Ķ������� �׸��� �ٲٴ� �޼���
		p_d.setColor(new Color(104, 192, 212), Color.white);
		p_s.setColor(new Color(242, 170, 170), Color.white);
		p_se.setColor(new Color(155, 199, 192), Color.white);
		m_f.setColor(new Color(89, 166, 189), Color.white);
	}
	public void setBlack() {
		//���������� �׸��� �ٲٴ� �޼���
		p_d.setColor(Color.black, new Color(255, 225, 0));
		p_s.setColor(Color.black, new Color(84, 189, 255));
		p_se.setColor(Color.black, new Color(255, 56, 59));
		m_f.setColor(Color.black, new Color(56, 255, 59));
	}
	public void setBasic() {
		//�⺻���� �ٽ� �׸��� �ٲٴ� �޼���
		p_d.setColor(new Color(217, 217, 217), Color.black);
		p_s.setColor(new Color(217, 217, 217), Color.black);
		p_se.setColor(new Color(217, 217, 217), Color.black);
		m_f.setColor(new Color(217, 217, 217), Color.black);
	}
}
