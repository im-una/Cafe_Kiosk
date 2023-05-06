import java.awt.event.*;
import java.util.*;

public class Menu {
	String productName; // 제품 이름
	int price;			// 가격
	int originalPrice = 0; 
	int amount = 0;
	
	Menu(String productName, int price, int amount) {
		this.productName = productName;
		this.originalPrice = price;
		this.amount = amount;
	}
	
	void buy() {
		//제품 초기화
		price = originalPrice;
		amount = 1;
	
		price = price * amount;
		Kiosk.totalPrice = Kiosk.totalPrice+price;
		Kiosk.orderProductName.add(productName);
		Kiosk.orderProduct.add(productName+" "+amount+"개 "+price);
		Kiosk.orderStatusText.get(Kiosk.orderStatusCount).setText(productName+" "+amount+"개 "+price);
		Kiosk.totalAmount++;
		Kiosk.amountText.setText(Kiosk.totalAmount+"개");
		Kiosk.priceText.setText(Kiosk.totalPrice+"원");

		Kiosk.finalTotalAmount.setText("총 개수 : "+Kiosk.totalAmount+" 개");
		Kiosk.finalTotalPrice.setText("총 "+Kiosk.totalPrice+" 원");
		
		System.out.println("추가 후 배열 값");
		System.out.println(Kiosk.orderProductName.toString());
	}
	
	void cancel() {

			Kiosk.totalAmount -= amount;
			Kiosk.totalPrice -= price;
			Kiosk.amountText.setText(Kiosk.totalAmount + "개");
			Kiosk.priceText.setText(Kiosk.totalPrice + "원");
			Kiosk.orderProductName.remove(productName);
			Kiosk.orderProduct.remove(productName+" "+amount+"개 "+price);
			
			Kiosk.finalTotalAmount.setText("총 개수 : "+Kiosk.totalAmount+" 개");
			Kiosk.finalTotalPrice.setText("총 "+Kiosk.totalPrice+" 원");
			
			System.out.println(Kiosk.orderProductName.toString());
	}
	
	void addNumOfProduct(int num, int orderStatusCount) {
		Kiosk.orderProduct.remove(productName+" "+amount+"개 "+price);
		
		amount = amount + num;
		Kiosk.howManyText.setText("현재  : " + amount + " 개");
		
		price = price + originalPrice * num;
		Kiosk.totalPrice = Kiosk.totalPrice + originalPrice * num;

		System.out.println("productName" + productName);
		Kiosk.orderStatusText.get(orderStatusCount).setText(productName+" "+amount+"개 "+ price);
		
		Kiosk.totalAmount = Kiosk.totalAmount + num;
		System.out.println("amount" + amount + "totalAmount" + Kiosk.totalAmount);
		Kiosk.amountText.setText(Kiosk.totalAmount+"개");
		Kiosk.priceText.setText(Kiosk.totalPrice+"원");
		
		Kiosk.orderProduct.add(productName+" "+amount+"개 "+price);
		Kiosk.finalTotalAmount.setText("총 개수 : "+Kiosk.totalAmount+" 개");
		Kiosk.finalTotalPrice.setText("총 "+Kiosk.totalPrice+" 원");
	}
}