package work.service;

public class Page {
	
	int year;
	int mon;
	
	public Page(int year, int mon) {
		this.year = year;
		this.mon = mon;
	}

	public int getYear() {
		return year;
	}

	public int getMon() {
		return mon;
	}

	@Override
	public String toString() {
		return "Page [year=" + year + ", mon=" + mon + "]";
	}
	
	

}
