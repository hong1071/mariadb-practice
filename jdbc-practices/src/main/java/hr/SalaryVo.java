package hr;

public class SalaryVo {
	private Long no;
	private int salary;
	private String fromDate;
	private String toDate;

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}

	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
