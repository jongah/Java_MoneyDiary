package Connect;

import DB.DB_Manager;

public class GetSumValue{
	//입력한 데이터의 합계 등의 계산을 하는 중간 클래스
	//dao클래스를 호출해서 값을 배열에 저장해 리턴한다.
	private String sum_spen, sum_income, balance, differ, ca_food, ca_leisure, ca_essen, ca_other;
	private DB_Manager dao = new DB_Manager();
	private String [] s_basics = new String[4];
	private String [] category = {"음식", "여가", "필수", "기타"};
	
	public String [] sumBasics(int year, int month) {
		//수입, 지출, 차액, 잔액
		s_basics[0] = dao.selectSum_Where(year, month, "form", "지출");
		s_basics[1] = dao.selectSum_Where(year, month, "form", "수입");
		if(s_basics[0] == null)
			s_basics[0] = "0";
		if(s_basics[1] == null)
			s_basics[1] = "0";
		int spen = Integer.parseInt(s_basics[0]);
		int income = Integer.parseInt(s_basics[1]);
		s_basics[2] = Integer.toString(income + spen);
		s_basics[3] = dao.selectSum_Balance();
		return s_basics;
		//배열에 값을 저장해서 배열을 리턴
	}
	public String [] sumCategory(int year, int month) {
		//음식, 여가, 필수, 기타
		for(int i = 0; i < s_basics.length; i ++) {
			s_basics[i] = dao.selectSum_Where(year, month, "category", category[i]);
		}
		return s_basics;
		//배열리턴
	}
}