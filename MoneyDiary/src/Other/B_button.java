package Other;

import java.awt.Color;

import javax.swing.JButton;

public class B_button extends JButton{
	public B_button(String text, int x, int y) {
		setFont(new F_input(20));
		setText(text);
		setBounds(x, y, 120, 50);
		setBorderPainted(false);
		setBackground(new Color(217, 217, 217));
	}
	public B_button(String text, int x, int y, int w, int h, int f_S) {
		setFont(new F_input(f_S));
		setText(text);
		setBounds(x, y, w, h);
		setBorderPainted(false);
		setBackground(new Color(217, 217, 217));
	}
	public B_button(String text, int y) {
		setFont(new F_input(20));
		setText(text);
		setBounds(30, y, 120, 80);
		setBorderPainted(false);
		setBackground(new Color(217, 217, 217));
	}
}