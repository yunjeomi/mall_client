package mall.client.vo;

public class Paging {
	private int currentPage;
	private int rowPerPage;
	private int beginRow;
	private int totalRow;
	private int lastPage;
	private String categoryName;
	private String searchWord;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRowPerPage() {
		return rowPerPage;
	}
	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}
	public int getBeginRow() {
		return beginRow;
	}
	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	@Override
	public String toString() {
		return "Paging [currentPage=" + currentPage + ", rowPerPage=" + rowPerPage + ", beginRow=" + beginRow
				+ ", totalRow=" + totalRow + ", lastPage=" + lastPage + ", categoryName=" + categoryName
				+ ", searchWord=" + searchWord + "]";
	}
	
}
