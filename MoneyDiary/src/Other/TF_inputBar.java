package Other;

import java.awt.Font;

import javax.swing.JTextField;

public class TF_inputBar extends JTextField{
	public TF_inputBar(Font f, String hint, int x, int width){
		super(hint);
		setFont(f);
		setBounds(x, 110, width, 30);
	}
	public TF_inputBar(Font f, int x, int y, int width) {
		setFont(f);
		setBounds(x, y, width, 30);
	}
}