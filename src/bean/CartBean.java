package bean;

import java.io.Serializable;

public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int proCd;
	private String proName = "";
	private int proPrice;
	private int quantity;
	private int stock;
	private int subTotal;

	public int getProCd() {
		return proCd;
	}

	public void setProCd(int proCd) {
		this.proCd = proCd;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getProPrice() {
		return proPrice;
	}

	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

}
