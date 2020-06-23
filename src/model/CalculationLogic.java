package model;

import java.util.ArrayList;

public class CalculationLogic {

	//合計金額算出メソッド
	public int totalCalc(ArrayList<CartBean> cartlist) {
		int total = 0;

		for (int i = 0; i < cartlist.size(); i++) {
			CartBean cBean = cartlist.get(i);
			total += cBean.getSubTotal();
		}

		return total;

	}

	//税算出メソッド
	public int taxCalc(int total) {
		return (int) (total * 0.1);
	}
}
