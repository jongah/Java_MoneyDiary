package Connect;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import DB.DB_Manager;

public class SetTableModel{
	//���̺� �ִ� ���� �ٽ� DB���� ������ �߰� Ŭ����
	DB_Manager dao = new DB_Manager();
	private Vector data;
	public DefaultTableModel setTableModel_Base(int year, int month) {
		//�⵵�� ���� �´� �����͸� data�� set�ϰ� ���̺� �� ����� �޼��� ȣ��
		data = (Vector) dao.select_PanelDiy(year, month);
		return setTableModel();
		//���̺� �� ���� �޼��� ȣ��
	}
	public DefaultTableModel SetTableModel_Search(String content, String word, int year, int month) {
		//�˻��� �����͸� data�� set �ϰ�(�޺��ڽ� ���� �˻�Ű����, �⵵�� ���� �Է¹޴´�) ���̺� �� ����� �޼��� ȣ��
		String col = "";
		if(content.equals("����") || content.equals("����")) {
			col = "form";
		}else if(content.equals("�����ݾ�")) {
			content = "min"; col = "��";
		}else if(content.equals("�ְ�ݾ�")) {
			content = "max"; col = "��";
		}else if(content.equals("��ü")) {
			col = "all";
		}else {
			col = "category";
		}
		data = (Vector) dao.select_Search(col, content, word, year, month);
		return setTableModel();
		//���̺� �� ���� �޼��� ȣ��
	}
	public DefaultTableModel setTableModel() {
		Vector col = new Vector();
		col.add("��¥");
		col.add("����/����");
		col.add("ī�װ�");
		col.add("����");
		col.add("�ݾ�");
		DefaultTableModel tableModel = null;
		tableModel = new DefaultTableModel(data, col){
			public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		    }
		};
		return tableModel;
		//���̺� ���� ����
	}
}