package Frame_Panel;

import java.lang.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Other.B_button;

public class MainFrame extends JFrame{
	//프로그램의 메인 프레임
	private static B_button b_diy, b_stat, b_total, b_setting;
	private Panel_Diy p_diy;
	private Panel_Stat p_stat;
	private Panel_Total p_total;
	private Panel_Setting p_setting;
	public MainFrame(int set) {
		//테마변경과 데이터 새로고침 이용을 위한 빈 생성자,다른 생성자와 구분을 위해 의미없는 값인 set을 받는다.
	}
	public MainFrame(){
		//메인 프레임 생성자
		setLayout(null);
		setTitle("만수르 기원 텅장 탈출기");
		
		int y = 130;
		add(b_diy = new B_button("기입장", y));
		add(b_stat = new B_button("통계", y += 90));
		add(b_total = new B_button("전체", y += 90));
		add(b_setting = new B_button("설정", y += 90));
		
		p_diy = new Panel_Diy();
		p_stat = new Panel_Stat();
		p_total = new Panel_Total();
		p_setting = new Panel_Setting();
		
		add(p_diy);
		
		this.setSize(1200, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(255, 255,255));
		
		b_diy.addActionListener(new BA_change());
		b_stat.addActionListener(new BA_change());	
		b_total.addActionListener(new BA_change());
		b_setting.addActionListener(new BA_change());
	}
	public void setColor(Color back, Color fore) {
		//테마 변경 메서드
		b_diy.setBackground(back);
		b_diy.setForeground(fore);
		b_stat.setBackground(back);
		b_stat.setForeground(fore);
		b_total.setBackground(back);
		b_total.setForeground(fore);
		b_setting.setBackground(back);
		b_setting.setForeground(fore);
	}
	class BA_change implements ActionListener{
		//다른 패널로 변경하는 메서드(패널을 모두 지우고 현재 클릭한 버튼과 일치하는 패널을 띄운다)
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(b_diy)) {
				remove(p_stat);
				remove(p_total);
				remove(p_setting);
				add(p_diy);
			}else if(e.getSource().equals(b_stat)){
				remove(p_diy);
				remove(p_total);
				remove(p_setting);
				add(p_stat);
			}else if(e.getSource().equals(b_total)) {
				remove(p_diy);
				remove(p_stat);
				remove(p_setting);
				add(p_total);
			}else {
				remove(p_diy);
				remove(p_stat);
				remove(p_total);
				add(p_setting);
			}
			revalidate();
			repaint();
		}
	}
}

