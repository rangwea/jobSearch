package cn.hrg.cd.domain;

/**
 * 职位对象
 * @author Administrator
 *
 */
public class JobInfo {
	@Override
	public String toString() {
		return "JobInfo [id=" + id + ", title=" + title + ", content="
				+ content + ", company=" + company + ", city=" + city
				+ ", salaryLevel=" + salaryLevel + ", type=" + type + ", date="
				+ date + "]";
	}
	private Long id;
	private String title;
	private String content;
	
	private String company;
	private String city;
	private String salaryLevel;
	private String type;
	private String date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSalaryLevel() {
		return salaryLevel;
	}
	public void setSalaryLevel(String salaryLevel) {
		this.salaryLevel = salaryLevel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
