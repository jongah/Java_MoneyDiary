package Other;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class T_data extends JTable{
	public T_data(DefaultTableModel model) {
		setModel(model);
		getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
        getTableHeader().setResizingAllowed(false);
        setRowHeight(30);
	}
}