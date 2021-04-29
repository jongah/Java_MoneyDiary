package Other;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class L_Label extends JLabel {
	public L_Label(int content, int x, int y) {
		setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
		setText(content + "");
		setBounds(x, y, 90, 40);
		setHorizontalAlignment(JLabel.CENTER);
	}
	public L_Label(int x, int y) {
		setFont(new F_input(20));
		setBounds(x, y, 200, 25);
	}
	public L_Label(String text, int x, int y) {
		setFont(new F_input(20));
		setText(text);
		setBounds(x, y, 200, 25);
	}
	public L_Label(String text, int x) {
		setFont(new F_input(20));
		setText(text);
		setBounds(x, 70, 100, 20);
	}
}
