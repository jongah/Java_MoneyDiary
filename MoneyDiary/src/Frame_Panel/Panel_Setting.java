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
	//���� �г�
	private static B_button b_delete, b_setBlue, b_setBlack, b_setBasic;
	private Refresh refresh = new Refresh();
	private Panel_Diy p_d = new Panel_Diy(1);
	private Panel_Total p_t = new Panel_Total(1);
	private Panel_Stat p_s = new Panel_Stat(1);
	private DB_Manager dao = new DB_Manager();
	public Panel_Setting(int set) {
		//�׸������ ������ ���ΰ�ħ �̿��� ���� �� ������,�ٸ� �����ڿ� ������ ���� �ǹ̾��� ���� set�� �޴´�.
	}
	public Panel_Setting() {
		//Panel_Setting ������ �޼���
		setLayout(null);
		add(b_delete = new B_button("�������봩�������ÿ�", 425, 100 ,200, 200, 15));
		int x = 250, y = 400;
		add(b_setBlue = new B_button("�Ķ��Ķ���", x, 400, 150, 60, 20));
		add(b_setBlack = new B_button("�����", x += 200, 400, 150, 60, 20));
		add(b_setBasic = new B_button("ȸ��ȸ��", x += 200, 400, 150, 60, 20));
		this.setBounds(140, 10, 1050, 650);
		this.setBackground(new Color(255, 255,255));
		
		b_delete.addActionListener(new BA_delete());
		b_setBlue.addActionListener(new BA_tema());
		b_setBlack.addActionListener(new BA_tema());
		b_setBasic.addActionListener(new BA_tema());
	}
	class BA_delete implements ActionListener{
		//������ ���ÿ� ��ư�� ������ �� �ߴ� ����(?)â
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String [] yes_no = {"���﷡��", "�����Ⱦ��"};
			int result = JOptionPane.showOptionDialog(null, "��� ����� �������� �� �ֽ��ϴ�!!", "������ ��ȸ",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, yes_no, "���﷡��");
			if(result == 0) {
				//���﷡�並 ������ DB�� ���̺��� ���������� �׸��� ��� ������Ʈ ���ΰ�ħ
				dao.deleteTable();
				refresh.updata();
			}
		}
	}
	class BA_tema implements ActionListener{
		//�׸� ���� ��ư, refesh�� ���� �޼��� ȣ��
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
		//���� �г��� �׸� ���� �޼���
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
