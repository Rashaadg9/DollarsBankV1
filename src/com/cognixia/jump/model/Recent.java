package com.cognixia.jump.model;

public class Recent
{
	private int id;
	private int count;
	private String r1;
	private String r2;
	private String r3;
	private String r4;
	private String r5;
	
	
	public Recent()
	{
		this(-1, -1, "N/A", "N/A", "N/A", "N/A", "N/A");
	}
	
	public Recent(int id, int count, String r1, String r2, String r3, String r4, String r5)
	{
		this.id = id;
		this.count = count;
		this.r1 = r1;
		this.r2 = r2;
		this.r3 = r3;
		this.r4 = r4;
		this.r5 = r5;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getR1() {
		return r1;
	}


	public void setR1(String r1) {
		this.r1 = r1;
	}


	public String getR2() {
		return r2;
	}


	public void setR2(String r2) {
		this.r2 = r2;
	}


	public String getR3() {
		return r3;
	}


	public void setR3(String r3) {
		this.r3 = r3;
	}


	public String getR4() {
		return r4;
	}


	public void setR4(String r4) {
		this.r4 = r4;
	}


	public String getR5() {
		return r5;
	}


	public void setR5(String r5) {
		this.r5 = r5;
	}


	@Override
	public String toString() {
		return "Recent [id=" + id + ", count=" + count + ", r1=" + r1 + ", r2=" + r2 + ", r3=" + r3 + ", r4=" + r4
				+ ", r5=" + r5 + "]";
	}
	
}
