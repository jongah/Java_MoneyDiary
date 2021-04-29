package Connect;

import DB.DB_Manager;

public class GetSumValue{
	//�Է��� �������� �հ� ���� ����� �ϴ� �߰� Ŭ����
	//daoŬ������ ȣ���ؼ� ���� �迭�� ������ �����Ѵ�.
	private String sum_spen, sum_income, balance, differ, ca_food, ca_leisure, ca_essen, ca_other;
	private DB_Manager dao = new DB_Manager();
	private String [] s_basics = new String[4];
	private String [] category = {"����", "����", "�ʼ�", "��Ÿ"};
	
	public String [] sumBasics(int year, int month) {
		//����, ����, ����, �ܾ�
		s_basics[0] = dao.selectSum_Where(year, month, "form", "����");
		s_basics[1] = dao.selectSum_Where(year, month, "form", "����");
		if(s_basics[0] == null)
			s_basics[0] = "0";
		if(s_basics[1] == null)
			s_basics[1] = "0";
		int spen = Integer.parseInt(s_basics[0]);
		int income = Integer.parseInt(s_basics[1]);
		s_basics[2] = Integer.toString(income + spen);
		s_basics[3] = dao.selectSum_Balance();
		return s_basics;
		//�迭�� ���� �����ؼ� �迭�� ����
	}
	public String [] sumCategory(int year, int month) {
		//����, ����, �ʼ�, ��Ÿ
		for(int i = 0; i < s_basics.length; i ++) {
			s_basics[i] = dao.selectSum_Where(year, month, "category", category[i]);
		}
		return s_basics;
		//�迭����
	}
}