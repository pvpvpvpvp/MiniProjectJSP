package com.numberlist.vo;

public class NumberListVo {
	private Long numberId;
	private String numberName;
	private String numberHp;
	private String numberTel;

	
	public Long getNumberId() {
		return numberId;
	}

	public void setNumberId(Long numberId) {
		this.numberId = numberId;
	}


	public String getNumberName() {
		return numberName;
	}

	public void setNumberName(String numberName) {
		this.numberName = numberName;
	}

	public String getNumberHp() {
		return numberHp;
	}

	public void setNumberHp(String numberHp) {
		this.numberHp = numberHp;
	}

	public String getNumberTel() {
		return numberTel;
	}

	public void setNumberTel(String numberTel) {
		this.numberTel = numberTel;
	}

	@Override
	public String toString() {
		return "NumberVO [numberName=" + numberName + ", numberHp=" + numberHp + ", numberTel=" + numberTel + "]";
	}
}