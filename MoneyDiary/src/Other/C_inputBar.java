package Other;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;

public class C_inputBar extends JComboBox{
	public C_inputBar(Font f, String [] s_inputBar, int x, int y) {
		super(s_inputBar);
		setBackground(new Color(255, 255, 255));
		setFont(f);
		setBounds(x, y, 100, 30);
	}
}
