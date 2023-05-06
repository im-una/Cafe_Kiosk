import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Kiosk implements Runnable{

	public void run() {
		Kiosk window = new Kiosk();
		window.frame.setVisible(true);	
	}

	public JFrame frame;
	JLabel advertising = new JLabel(); // 광고이미지
	JPanel mainPurchase = new JPanel();

	JPanel paymentCheck = new JPanel();
	JLabel paymentCheckImage = new JLabel();
	JLabel cardpayImage = new JLabel();
	JLabel insertCardImage = new JLabel();
	JButton insertCardButton = new JButton();
	JButton OKButton = new JButton();
	JButton CancelButton = new JButton();

	static JLabel finalTotalAmount = new JLabel();	
	static JLabel finalTotalPrice = new JLabel();

	JLabel pressKeyText = new JLabel();
	JButton[] menuImages = new JButton[6];

	JButton goAdvertisingButton = new JButton();
	JButton paymentButton = new JButton();
	JButton drinkButton = new JButton();
	JButton dessertButton = new JButton();
	JLabel welcome = new JLabel(); // 상단 광고 이미지
	JLabel howManyAddText = new JLabel();
	static JLabel howManyText = new JLabel();
	static JLabel amountText = new JLabel();
	static JLabel priceText = new JLabel();
	JLabel orderDetailsText = new JLabel(); // 총 주문 내역
	JLabel orderDetailsBackGround = new JLabel();

	JButton checkButton = new JButton();// 확인버튼
	JButton cancelAddButton = new JButton(); // 수량추가를 취소
	
	static ArrayList<JLabel> orderStatusText = new ArrayList<>();

	ArrayList<JButton> removeButton = new ArrayList<>(); //cancel -> remove
	ArrayList<JButton> quantityChangeButton = new ArrayList<>(); // 수량변경
	JLabel orderStatusBackGround = new JLabel();

	static ArrayList<String> orderProductName = new ArrayList<String>(); // 주문한 제품 이름을 받는 배열
	static ArrayList<String> orderProduct = new ArrayList<String>(); // 주문한 제품 이름,개수,가격을 받는 배열
	
	int menupage = 0;
	// 메뉴 bounds 설정
	int menuHorizontalLength = 50;
	int menuVerticalLength = 0; 
	int menuImageWidth = 150;   
	int menuImageHeight = 150;  
	int menuHorizontalInterval = 170;

	static int orderStatusCount = 0; // 주문상황 수
	static int totalAmount = 0; // 주문 총 양
	static int totalPrice = 0; // 주문 총 금액

	int productNum = 0; // 어떤 제품의 수를 변경했는지
	int addProductNum = 0; // 몇개를 추가 했는지 

	int OrderStatusVerticalLength = -40;   // orderStatus창에 나타나는 text의 위치 조정하기 위한 변수

	ChangeAdvertising changeAdvertising = new ChangeAdvertising(advertising);

	// 음료
	Menu americano = new Menu("아메리카노", 2000, 1);
	Menu latte = new Menu("카페라떼", 2500, 1);
	Menu moca = new Menu("카페모카", 2500, 1);
	Menu banila = new Menu("바닐라라떼", 3000, 1);
	Menu greentea = new Menu("그린티라떼", 3000, 1);
	Menu strawberry = new Menu("딸기라떼", 3000, 1);

	// 디저트
	Menu tiramisu = new Menu("티라미수 케이크", 4000, 1);
	Menu strawcake = new Menu("딸기 케이크", 4500, 1);
	Menu cheese = new Menu("치즈 케이크", 4000, 1);
	Menu waffle = new Menu("와플", 2500, 1);
	Menu cookie = new Menu("쿠키", 2000, 1);
	Menu macaron = new Menu("마카롱", 2000, 1);	

	public Kiosk() {

		frame = new JFrame();
		frame.setBounds(0, 0, 600, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Cafe 키오스크");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		advertising.setBounds(0, 0, 600, 850);
		frame.getContentPane().add(advertising);
		advertising.setLayout(null);

		advertising.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				advertising.setVisible(false);
				mainPurchase.setVisible(true);
				insertCardButton.setEnabled(true);
				insertCardImage.setIcon(new ImageIcon("./images/cardin.jpg"));
			}
		});

		JScrollPane scrollBar = new JScrollPane();
		scrollBar.setBounds(68, 201, 450, 200);
		scrollBar.getVerticalScrollBar().setValue(scrollBar.getVerticalScrollBar().getMaximum());
		paymentCheckImage.add(scrollBar);

		JTextArea textArea = new JTextArea();
		scrollBar.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setVisible(false);
		textArea.setFont((new Font("굴림체", Font.BOLD, 15)));

		// 메인 구매 화면
		mainPurchase.setBounds(0, 0, 600, 850);
		frame.getContentPane().add(mainPurchase);
		mainPurchase.setLayout(null);
		mainPurchase.setVisible(false);

		// 초기 광고 화면
		pressKeyText.setBounds(0, 200, 600, 100);
		pressKeyText.setLayout(null);
		pressKeyText.setText("화면을 터치해 주세요");
		pressKeyText.setHorizontalAlignment(SwingConstants.CENTER);
		pressKeyText.setFont(new Font("굴림", Font.BOLD, 30));
		advertising.add(pressKeyText);

		// 구매화면
		welcome.setBounds(0, 0, 600, 140);
		welcome.setIcon(new ImageIcon("./images/welcome.png"));
		mainPurchase.add(welcome);

		// 음료 버튼 -> 음료 고르는 페이지
		drinkButton.setBounds(50, 150, 100, 40);
		drinkButton.setText("음료");

		drinkButton.setBackground(Color.white);
		drinkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drinkButton.setBackground(Color.gray);
				dessertButton.setBackground(Color.white);

				menupage = 0;
				showMenu();
			}
		});
		mainPurchase.add(drinkButton);
		
		// 디저트 버튼 -> 디저트 고르는 페이지
		dessertButton.setBounds(150, 150, 100, 40);
		dessertButton.setText("디저트");

		dessertButton.setBackground(Color.white);
		dessertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drinkButton.setBackground(Color.white);
				dessertButton.setBackground(Color.gray);

				menupage = 1;
				showMenu();
			}
		});
		mainPurchase.add(dessertButton);	

		goAdvertisingButton.setBounds(110, 750, 130, 40);
		goAdvertisingButton.setText("초기화면으로");

		goAdvertisingButton.setBackground(Color.LIGHT_GRAY);
		goAdvertisingButton.setBorderPainted(false);
		goAdvertisingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				advertising.setVisible(true);
				mainPurchase.setVisible(false);

				for (int i = 0; i < orderStatusText.size(); i++) {
					orderStatusText.get(i).setLocation(1000, 1000);
					removeButton.get(i).setLocation(1000, 1000);
					quantityChangeButton.get(i).setLocation(1000, 1000);
					orderStatusText.remove(i);
				}

				orderStatusText.clear();
				orderStatusCount = 0;
				OrderStatusVerticalLength = -40;

				orderProductName.clear();
				totalAmount = 0;
				totalPrice = 0;
				amountText.setText(totalAmount + "개");
				priceText.setText(totalPrice + "원");

				finalTotalAmount.setText("총 개수 : +totalAmount+ 개");
				finalTotalPrice.setText("총  +totalPrice 원");

				System.out.println(orderStatusText.size());
				System.out.println(orderProductName.toString());

				textArea.selectAll();
				textArea.replaceSelection("");
			}
		});
		mainPurchase.add(goAdvertisingButton);

		paymentButton.setBounds(385, 750, 100, 40);
		paymentButton.setText("결제하기");
		paymentButton.setBackground(Color.LIGHT_GRAY);
		paymentButton.setBorderPainted(false);
		paymentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					mainPurchase.setVisible(false);
					paymentCheck.setVisible(true);
					insertCardButton.setVisible(true);
					
					// 결제확인 창에 출력됨
					textArea.setVisible(true);

					for (int i = 0; i < orderProduct.size(); i++) {
						textArea.append(orderProduct.get(i) + "\n");
						System.out.println(orderProduct.get(i) + "\n");
					}
				}
			}
		);
		mainPurchase.add(paymentButton);

		//메뉴 이미지 버튼 초기화
		for (int i = 0; i < menuImages.length; i++) {
			mainPurchase.add(menuImages[i] = new JButton());

			menuImages[i].setHorizontalAlignment(SwingConstants.CENTER);

			if (i < 3) {
				menuVerticalLength = 200;
				menuImages[i].setBounds(menuHorizontalLength, menuVerticalLength, menuImageWidth, menuImageHeight);

			} else if ((i >= 3) && (i < 6)) {
				menuVerticalLength = 380;
				menuImages[i].setBounds(menuHorizontalLength, menuVerticalLength, menuImageWidth, menuImageHeight);
			}

			menuHorizontalLength = menuHorizontalInterval + menuHorizontalLength;

			if ((i + 1) % 3 == 0) {
				menuHorizontalLength = 50;
			}

			menuImages[i].setBorderPainted(false);
			menuImages[i].setContentAreaFilled(false);
			menuImages[i].setFocusPainted(false);
		}

		orderDetailsText.setBounds(90, 540, 200, 100);
		orderDetailsText.setText("총주문내역");
		orderDetailsText.setHorizontalAlignment(SwingConstants.CENTER);
		mainPurchase.add(orderDetailsText);

		amountText.setBounds(210, 540, 200, 100);
		amountText.setText("0개");
		amountText.setHorizontalAlignment(SwingConstants.CENTER);
		mainPurchase.add(amountText);

		priceText.setBounds(320, 540, 200, 100);
		priceText.setText("0원");
		priceText.setHorizontalAlignment(SwingConstants.CENTER);
		mainPurchase.add(priceText);

		orderDetailsBackGround.setBounds(50, 580, 490, 20);
		orderDetailsBackGround.setIcon(new ImageIcon("otherimages/empty.png"));
		mainPurchase.add(orderDetailsBackGround);

		// 메뉴 버튼을 누르면 선택한 메뉴들이 주문내역에 출력된다
		for (int i = 0; i < menuImages.length; i++) {
			menuImages[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					orderStatusText.add(new JLabel());
					removeButton.add(new JButton());
					quantityChangeButton.add(new JButton());

					for (int i = 0; i < menuImages.length; i++) {
						if (e.getSource() == menuImages[i]) {
							if (menupage == 0) {
								if (i == 0) {
									americano.buy();
								} else if (i == 1) {
									latte.buy();
								} else if (i == 2) {
									moca.buy();
								} else if (i == 3) {
									banila.buy();
								} else if (i == 4) {
									greentea.buy();
								} else if (i == 5) {
									strawberry.buy();
								}
							} else if (menupage == 1) {
								if (i == 0) {
									tiramisu.buy();
								} else if (i == 1) {
									strawcake.buy();
								} else if (i == 2) {
									cheese.buy();
								} else if (i == 3) {
									waffle.buy();
								} else if (i == 4) {
									cookie.buy();
								} else if (i == 5) {
									macaron.buy();
								}
							}
						}
					}

					removeButton.get(orderStatusCount).setIcon(new ImageIcon("./images/cancel.jpg"));
					quantityChangeButton.get(orderStatusCount).setIcon(new ImageIcon("./images/plus.jpg"));

					orderStatusBackGround.add(orderStatusText.get(orderStatusCount));
					orderStatusBackGround.add(removeButton.get(orderStatusCount));
					orderStatusBackGround.add(quantityChangeButton.get(orderStatusCount));

					OrderStatusVerticalLength = -40;
					if (orderStatusCount != 0) {
						for (int i = 0; i < orderStatusCount; i++) {
							OrderStatusVerticalLength = OrderStatusVerticalLength + 20;
						}
					}
					System.out.println("orderStatusCount" + orderStatusCount);
					System.out.println(OrderStatusVerticalLength);

					orderStatusText.get(orderStatusCount).setBounds(5, OrderStatusVerticalLength, 490, 110);
					removeButton.get(orderStatusCount).setBounds(460, OrderStatusVerticalLength + 43, 20, 20);
					quantityChangeButton.get(orderStatusCount).setBounds(430, OrderStatusVerticalLength + 43, 20, 20);

					orderStatusCount++;

					for (int i = 0; i < orderStatusText.size(); i++) {
						removeButton.get(i).addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								for (int i = 0; i < orderStatusText.size(); i++) {
									if (e.getSource() == removeButton.get(i)) {
										orderStatusText.get(i).setLocation(1000, 1000);
										removeButton.get(i).setLocation(1000, 1000);
										quantityChangeButton.get(i).setLocation(1000, 1000);

										orderStatusText.remove(i);
										removeButton.remove(i);
										quantityChangeButton.remove(i);

										orderStatusCount--;

										if (orderStatusText.size() == 0) {
										} else {
											// x 버튼 눌렀을 때 위치 배치 다시
											for (int j = 0; j < orderStatusText.size(); j++) {
												if (i + j < orderStatusText.size()) {
													orderStatusText.get(i + j).setLocation(
															orderStatusText.get(i + j).getX(),
															orderStatusText.get(i + j).getY() - 20);
													removeButton.get(i + j).setLocation(removeButton.get(i + j).getX(),
															removeButton.get(i + j).getY() - 20);
													quantityChangeButton.get(i + j).setLocation(
															quantityChangeButton.get(i + j).getX(),
															quantityChangeButton.get(i + j).getY() - 20);
												}
											}
										}

										System.out.println("삭제됨");
									
										// 디저트 삭제 기능
										if (orderProductName.get(i).equals(tiramisu.productName)) {
											tiramisu.cancel();
											break;}
										else if (orderProductName.get(i).equals(strawcake.productName)) {
											strawcake.cancel();
											break;}
										else if (orderProductName.get(i).equals(cheese.productName)) {
											cheese.cancel();
											break;}
										else if (orderProductName.get(i).equals(waffle.productName)) {
											waffle.cancel();
											break;}
										else if (orderProductName.get(i).equals(cookie.productName)) {
											cookie.cancel();
											break;}
										else if (orderProductName.get(i).equals(macaron.productName)) {
											macaron.cancel();
											break;
										}
										// 드링크 삭제 기능
										else if (orderProductName.get(i).equals(americano.productName)) {
											americano.cancel();
											break;
										}
										else if (orderProductName.get(i).equals(latte.productName)) {
											latte.cancel();
											break;}
										else if (orderProductName.get(i).equals(moca.productName)) {
											moca.cancel();
											break;}

										else if (orderProductName.get(i).equals(banila.productName)) {
											banila.cancel();
											break;}

										else if (orderProductName.get(i).equals(greentea.productName)) {
											greentea.cancel();
											break;}

										else if (orderProductName.get(i).equals(strawberry.productName)) {
											strawberry.cancel();
											break;}
									}
								}
							}
						});

						quantityChangeButton.get(i).addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								for (int i = 0; i < orderStatusText.size(); i++) {
									if (e.getSource() == quantityChangeButton.get(i)) {
										//orderSituationBackGround.setVisible(true);
										//howManyText.setText("현재  : 1 개");
										productNum = i;
										//addProducts(1);
									}									
								}
								addProducts(1); /////////////////
							}
						});						
					}
				}
			});
		}

		orderStatusBackGround.setBounds(50, 610, 490, 130);
		orderStatusBackGround.setIcon(new ImageIcon("./images/empty.png"));
		mainPurchase.add(orderStatusBackGround);

		// 주문내역 확인 창
		paymentCheck.setBounds(0, 0, 600, 850);
		paymentCheck.setLayout(null);
		paymentCheck.setVisible(false);
		frame.getContentPane().add(paymentCheck);

		paymentCheckImage.setBounds(0, 0, 600, 850);
		paymentCheckImage.setLayout(null);
		paymentCheckImage.setVisible(true);
		paymentCheckImage.setIcon(new ImageIcon("./images/check.png"));
		paymentCheck.add(paymentCheckImage);

		// 결제하기 눌렀을때 카드 결제창
		cardpayImage.setBounds(0, 0, 600, 850);
		cardpayImage.setLayout(null);
		cardpayImage.setVisible(false);
		paymentCheck.add(cardpayImage);

		finalTotalAmount.setBounds(350, 400, 100, 20);
		finalTotalAmount.setText("총 개수 : " + totalAmount + " 개");
		finalTotalAmount.setLayout(null);
		finalTotalAmount.setVisible(true);
		paymentCheckImage.add(finalTotalAmount);

		finalTotalPrice.setBounds(450, 400, 100, 20);
		finalTotalPrice.setText("총 " + totalPrice + " 원");
		finalTotalPrice.setLayout(null);
		finalTotalPrice.setVisible(true);
		paymentCheckImage.add(finalTotalPrice);

		OKButton.setBounds(150, 500, 100, 30);
		OKButton.setText("결제하기");
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				paymentCheckImage.setVisible(false);
				cardpayImage.setVisible(true);
				insertCardButton.setVisible(true);
				insertCardButton.setEnabled(true);
			}
		});
		paymentCheckImage.add(OKButton);

		CancelButton.setBounds(350, 500, 100, 30);
		CancelButton.setText("이전으로");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentCheck.setVisible(false);
				mainPurchase.setVisible(true);

				textArea.selectAll();
				textArea.replaceSelection("");
			}
		});
		paymentCheckImage.add(CancelButton);

		insertCardImage.setBounds(120, 160, 400, 350);
		insertCardImage.setIcon(new ImageIcon("./images/cardin.jpg"));
		insertCardImage.setLayout(null);
		insertCardImage.setVisible(true);
		cardpayImage.add(insertCardImage);

		insertCardButton.setBounds(100, 520, 400, 100);
		insertCardButton.setText("카드 투입");
		insertCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardPay CardPay = new CardPay(insertCardImage, insertCardButton, advertising, mainPurchase, cardpayImage);
				CardPay.start();

				insertCardButton.setVisible(false);

				for (int i = 0; i < orderStatusText.size(); i++) {
					orderStatusText.get(i).setLocation(1000, 1000);
					removeButton.get(i).setLocation(1000, 1000);
					quantityChangeButton.get(i).setLocation(1000, 1000);
					orderStatusText.remove(i);
				}

				orderStatusText.clear();
				orderStatusCount = 0;
				OrderStatusVerticalLength = -40;

				orderProductName.clear();
				totalAmount = 0;
				totalPrice = 0;
				amountText.setText(totalAmount + "개");
				priceText.setText(totalPrice + "원");

				finalTotalAmount.setText("총 개수 : " + totalAmount + " 개");
				finalTotalPrice.setText("총  " + totalPrice + " 원");
			}
		});
		cardpayImage.add(insertCardButton);
	
		showMenu();
		changeAdvertising.start(); 
	}

	public void showMenu() {
		// 음료
		if (menupage == 0) {
			menuImages[0].setIcon(new ImageIcon("./images/americano.jpg"));
			menuImages[1].setIcon(new ImageIcon("./images/latte.jpg"));
			menuImages[2].setIcon(new ImageIcon("./images/moca.png"));
			menuImages[3].setIcon(new ImageIcon("./images/banila.jpg"));
			menuImages[4].setIcon(new ImageIcon("./images/greentea.png"));
			menuImages[5].setIcon(new ImageIcon("./images/strawberry.jpg"));
		}
		// 디저트
		else if (menupage == 1) {
			menuImages[0].setIcon(new ImageIcon("./images/cake.jpg"));
			menuImages[1].setIcon(new ImageIcon("./images/cake2.jpg"));
			menuImages[2].setIcon(new ImageIcon("./images/cake3.jpg"));
			menuImages[3].setIcon(new ImageIcon("./images/waffle.jpg"));
			menuImages[4].setIcon(new ImageIcon("./images/cookie.jpg"));
			menuImages[5].setIcon(new ImageIcon("./images/macaron.jpg"));
		}
	}
	
	// 개수 추가
	public void addProducts(int addNum) {		
		addProductNum = addProductNum + addNum;
		// 디저트 삭제 
		if (orderProductName.get(productNum).equals(tiramisu.productName)) {
			tiramisu.addNumOfProduct(addNum, productNum);
		}
		else if (orderProductName.get(productNum).equals(strawcake.productName)) {
			strawcake.addNumOfProduct(addNum, productNum);
		}
		else if (orderProductName.get(productNum).equals(cheese.productName)) {
			cheese.addNumOfProduct(addNum, productNum);
		}
		else if (orderProductName.get(productNum).equals(waffle.productName)) {
			waffle.addNumOfProduct(addNum, productNum);
		}
		else if (orderProductName.get(productNum).equals(cookie.productName)) {
			cookie.addNumOfProduct(addNum, productNum);
		}
		else if (orderProductName.get(productNum).equals(macaron.productName)) {
			macaron.addNumOfProduct(addNum, productNum);
		}

		// 음료 삭제 
		else if (orderProductName.get(productNum).equals(americano.productName)) {
			americano.addNumOfProduct(addNum, productNum);
		}
		else if (orderProductName.get(productNum).equals(latte.productName)) {
			latte.addNumOfProduct(addNum, productNum);
		}
		else if (orderProductName.get(productNum).equals(moca.productName)) {
			moca.addNumOfProduct(addNum, productNum);
		}
		else if (orderProductName.get(productNum).equals(banila.productName)) {
			banila.addNumOfProduct(addNum, productNum);
		}
		else if (orderProductName.get(productNum).equals(greentea.productName)) {
			greentea.addNumOfProduct(addNum, productNum);
		}
		else if (orderProductName.get(productNum).equals(strawberry.productName)) {
			strawberry.addNumOfProduct(addNum, productNum);
		}
	}
}