package model;

public class MemberVo {
	private String memberName, phoneNumber;
	private int point;

	public MemberVo() {
	}

	public MemberVo(String memberName, String phoneNumber, int point) {
		super();
		this.memberName = memberName;
		this.phoneNumber = phoneNumber;
		this.point = point;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "MemberVo [memberName=" + memberName + ", phoneNumber=" + phoneNumber + ", point=" + point + "]";
	}
}
