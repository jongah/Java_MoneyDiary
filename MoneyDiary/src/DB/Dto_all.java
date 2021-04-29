package DB;

import java.sql.Date;

public class Dto_all {
	private Date date;
	private String form;
	private String category;
	private String content;
	private int amount;
	
	public Dto_all() {
		super();
	}
	
	public Dto_all(Date date, String form, String category, String content, int amount) {
		super();
		this.date = date;
		this.form = form;
		this.category = category;
		this.content = content;
		this.amount = amount;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
