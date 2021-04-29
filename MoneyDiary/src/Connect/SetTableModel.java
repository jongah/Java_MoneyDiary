package Connect;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import DB.DB_Manager;

public class SetTableModel{
	//테이블에 넣는 값을 다시 DB에서 빼오는 중간 클래스
	DB_Manager dao = new DB_Manager();
	private Vector data;
	public DefaultTableModel setTableModel_Base(int year, int month) {
		//년도와 월에 맞는 데이터를 data에 set하고 테이블 모델 만드는 메서드 호출
		data = (Vector) dao.select_PanelDiy(year, month);
		return setTableModel();
		//테이블 모델 생성 메서드 호출
	}
	public DefaultTableModel SetTableModel_Search(String content, String word, int year, int month) {
		//검색한 데이터를 data에 set 하고(콤보박스 값과 검색키워드, 년도와 월을 입력받는다) 테이블 모델 만드는 메서드 호출
		String col = "";
		if(content.equals("수입") || content.equals("지출")) {
			col = "form";
		}else if(content.equals("최저금액")) {
			content = "min"; col = "최";
		}else if(content.equals("최고금액")) {
			content = "max"; col = "최";
		}else if(content.equals("전체")) {
			col = "all";
		}else {
			col = "category";
		}
		data = (Vector) dao.select_Search(col, content, word, year, month);
		return setTableModel();
		//테이블 모델 생성 메서드 호출
	}
	public DefaultTableModel setTableModel() {
		Vector col = new Vector();
		col.add("날짜");
		col.add("수입/지출");
		col.add("카테고리");
		col.add("내용");
		col.add("금액");
		DefaultTableModel tableModel = null;
		tableModel = new DefaultTableModel(data, col){
			public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		    }
		};
		return tableModel;
		//테이블 모델을 리턴
	}
}