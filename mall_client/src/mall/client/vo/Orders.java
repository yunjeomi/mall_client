package mall.client.vo;

public class Orders {
	private int ordersNo;
	private String ebookISBN;
	private int clientNo;
	private String ordersDate;
	private String ordersState;
	
	public int getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(int ordersNo) {
		this.ordersNo = ordersNo;
	}
	public int getClientNo() {
		return clientNo;
	}
	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}
	public String getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(String ordersDate) {
		this.ordersDate = ordersDate;
	}
	public String getOrdersState() {
		return ordersState;
	}
	public void setOrdersState(String ordersState) {
		this.ordersState = ordersState;
	}
	public String getEbookISBN() {
		return ebookISBN;
	}
	public void setEbookISBN(String ebookISBN) {
		this.ebookISBN = ebookISBN;
	}
	@Override
	public String toString() {
		return "Orders [ordersNo=" + ordersNo + ", ebookISBN=" + ebookISBN + ", clientNo=" + clientNo + ", ordersDate="
				+ ordersDate + ", ordersState=" + ordersState + "]";
	}
	
}
