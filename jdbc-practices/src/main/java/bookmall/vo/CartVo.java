package bookmall.vo;

public class CartVo {
	private int bookNo;
	private int memberNo;
	private int amount;
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "CartVo [bookNo=" + bookNo + ", memberNo=" + memberNo + ", amount=" + amount + "]";
	}
	
	
}
