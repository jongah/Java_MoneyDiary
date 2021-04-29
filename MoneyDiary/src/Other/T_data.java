package Other;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class T_data extends JTable{
	public T_data(DefaultTableModel model) {
		setModel(model);
		getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
        getTableHeader().setResizingAllowed(false);
        setRowHeight(30);
	}
}