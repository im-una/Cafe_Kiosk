import java.awt.event.*;
import java.util.*;

public class Menu {
	String productName; // ��ǰ �̸�
	int price;			// ����
	int originalPrice = 0; 
	int amount = 0;
	
	Menu(String productName, int price, int amount) {
		this.productName = productName;
		this.originalPrice = price;
		this.amount = amount;
	}
	
	void buy() {
		//��ǰ �ʱ�ȭ
		price = originalPrice;
		amount = 1;
	
		price = price * amount;
		Kiosk.totalPrice = Kiosk.totalPrice+price;
		Kiosk.orderProductName.add(productName);
		Kiosk.orderProduct.add(productName+" "+amount+"�� "+price);
		Kiosk.orderStatusText.get(Kiosk.orderStatusCount).setText(productName+" "+amount+"�� "+price);
		Kiosk.totalAmount++;
		Kiosk.amountText.setText(Kiosk.totalAmount+"��");
		Kiosk.priceText.setText(Kiosk.totalPrice+"��");

		Kiosk.finalTotalAmount.setText("�� ���� : "+Kiosk.totalAmount+" ��");
		Kiosk.finalTotalPrice.setText("�� "+Kiosk.totalPrice+" ��");
		
		System.out.println("�߰� �� �迭 ��");
		System.out.println(Kiosk.orderProductName.toString());
	}
	
	void cancel() {

			Kiosk.totalAmount -= amount;
			Kiosk.totalPrice -= price;
			Kiosk.amountText.setText(Kiosk.totalAmount + "��");
			Kiosk.priceText.setText(Kiosk.totalPrice + "��");
			Kiosk.orderProductName.remove(productName);
			Kiosk.orderProduct.remove(productName+" "+amount+"�� "+price);
			
			Kiosk.finalTotalAmount.setText("�� ���� : "+Kiosk.totalAmount+" ��");
			Kiosk.finalTotalPrice.setText("�� "+Kiosk.totalPrice+" ��");
			
			System.out.println(Kiosk.orderProductName.toString());
	}
	
	void addNumOfProduct(int num, int orderStatusCount) {
		Kiosk.orderProduct.remove(productName+" "+amount+"�� "+price);
		
		amount = amount + num;
		Kiosk.howManyText.setText("����  : " + amount + " ��");
		
		price = price + originalPrice * num;
		Kiosk.totalPrice = Kiosk.totalPrice + originalPrice * num;

		System.out.println("productName" + productName);
		Kiosk.orderStatusText.get(orderStatusCount).setText(productName+" "+amount+"�� "+ price);
		
		Kiosk.totalAmount = Kiosk.totalAmount + num;
		System.out.println("amount" + amount + "totalAmount" + Kiosk.totalAmount);
		Kiosk.amountText.setText(Kiosk.totalAmount+"��");
		Kiosk.priceText.setText(Kiosk.totalPrice+"��");
		
		Kiosk.orderProduct.add(productName+" "+amount+"�� "+price);
		Kiosk.finalTotalAmount.setText("�� ���� : "+Kiosk.totalAmount+" ��");
		Kiosk.finalTotalPrice.setText("�� "+Kiosk.totalPrice+" ��");
	}
}