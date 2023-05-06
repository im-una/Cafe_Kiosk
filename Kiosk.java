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
	JLabel advertising = new JLabel(); // �����̹���
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
	JLabel welcome = new JLabel(); // ��� ���� �̹���
	JLabel howManyAddText = new JLabel();
	static JLabel howManyText = new JLabel();
	static JLabel amountText = new JLabel();
	static JLabel priceText = new JLabel();
	JLabel orderDetailsText = new JLabel(); // �� �ֹ� ����
	JLabel orderDetailsBackGround = new JLabel();

	JButton checkButton = new JButton();// Ȯ�ι�ư
	JButton cancelAddButton = new JButton(); // �����߰��� ���
	
	static ArrayList<JLabel> orderStatusText = new ArrayList<>();

	ArrayList<JButton> removeButton = new ArrayList<>(); //cancel -> remove
	ArrayList<JButton> quantityChangeButton = new ArrayList<>(); // ��������
	JLabel orderStatusBackGround = new JLabel();

	static ArrayList<String> orderProductName = new ArrayList<String>(); // �ֹ��� ��ǰ �̸��� �޴� �迭
	static ArrayList<String> orderProduct = new ArrayList<String>(); // �ֹ��� ��ǰ �̸�,����,������ �޴� �迭
	
	int menupage = 0;
	// �޴� bounds ����
	int menuHorizontalLength = 50;
	int menuVerticalLength = 0; 
	int menuImageWidth = 150;   
	int menuImageHeight = 150;  
	int menuHorizontalInterval = 170;

	static int orderStatusCount = 0; // �ֹ���Ȳ ��
	static int totalAmount = 0; // �ֹ� �� ��
	static int totalPrice = 0; // �ֹ� �� �ݾ�

	int productNum = 0; // � ��ǰ�� ���� �����ߴ���
	int addProductNum = 0; // ��� �߰� �ߴ��� 

	int OrderStatusVerticalLength = -40;   // orderStatusâ�� ��Ÿ���� text�� ��ġ �����ϱ� ���� ����

	ChangeAdvertising changeAdvertising = new ChangeAdvertising(advertising);

	// ����
	Menu americano = new Menu("�Ƹ޸�ī��", 2000, 1);
	Menu latte = new Menu("ī���", 2500, 1);
	Menu moca = new Menu("ī���ī", 2500, 1);
	Menu banila = new Menu("�ٴҶ��", 3000, 1);
	Menu greentea = new Menu("�׸�Ƽ��", 3000, 1);
	Menu strawberry = new Menu("�����", 3000, 1);

	// ����Ʈ
	Menu tiramisu = new Menu("Ƽ��̼� ����ũ", 4000, 1);
	Menu strawcake = new Menu("���� ����ũ", 4500, 1);
	Menu cheese = new Menu("ġ�� ����ũ", 4000, 1);
	Menu waffle = new Menu("����", 2500, 1);
	Menu cookie = new Menu("��Ű", 2000, 1);
	Menu macaron = new Menu("��ī��", 2000, 1);	

	public Kiosk() {

		frame = new JFrame();
		frame.setBounds(0, 0, 600, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Cafe Ű����ũ");
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
		textArea.setFont((new Font("����ü", Font.BOLD, 15)));

		// ���� ���� ȭ��
		mainPurchase.setBounds(0, 0, 600, 850);
		frame.getContentPane().add(mainPurchase);
		mainPurchase.setLayout(null);
		mainPurchase.setVisible(false);

		// �ʱ� ���� ȭ��
		pressKeyText.setBounds(0, 200, 600, 100);
		pressKeyText.setLayout(null);
		pressKeyText.setText("ȭ���� ��ġ�� �ּ���");
		pressKeyText.setHorizontalAlignment(SwingConstants.CENTER);
		pressKeyText.setFont(new Font("����", Font.BOLD, 30));
		advertising.add(pressKeyText);

		// ����ȭ��
		welcome.setBounds(0, 0, 600, 140);
		welcome.setIcon(new ImageIcon("./images/welcome.png"));
		mainPurchase.add(welcome);

		// ���� ��ư -> ���� ���� ������
		drinkButton.setBounds(50, 150, 100, 40);
		drinkButton.setText("����");

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
		
		// ����Ʈ ��ư -> ����Ʈ ���� ������
		dessertButton.setBounds(150, 150, 100, 40);
		dessertButton.setText("����Ʈ");

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
		goAdvertisingButton.setText("�ʱ�ȭ������");

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
				amountText.setText(totalAmount + "��");
				priceText.setText(totalPrice + "��");

				finalTotalAmount.setText("�� ���� : +totalAmount+ ��");
				finalTotalPrice.setText("��  +totalPrice ��");

				System.out.println(orderStatusText.size());
				System.out.println(orderProductName.toString());

				textArea.selectAll();
				textArea.replaceSelection("");
			}
		});
		mainPurchase.add(goAdvertisingButton);

		paymentButton.setBounds(385, 750, 100, 40);
		paymentButton.setText("�����ϱ�");
		paymentButton.setBackground(Color.LIGHT_GRAY);
		paymentButton.setBorderPainted(false);
		paymentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					mainPurchase.setVisible(false);
					paymentCheck.setVisible(true);
					insertCardButton.setVisible(true);
					
					// ����Ȯ�� â�� ��µ�
					textArea.setVisible(true);

					for (int i = 0; i < orderProduct.size(); i++) {
						textArea.append(orderProduct.get(i) + "\n");
						System.out.println(orderProduct.get(i) + "\n");
					}
				}
			}
		);
		mainPurchase.add(paymentButton);

		//�޴� �̹��� ��ư �ʱ�ȭ
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
		orderDetailsText.setText("���ֹ�����");
		orderDetailsText.setHorizontalAlignment(SwingConstants.CENTER);
		mainPurchase.add(orderDetailsText);

		amountText.setBounds(210, 540, 200, 100);
		amountText.setText("0��");
		amountText.setHorizontalAlignment(SwingConstants.CENTER);
		mainPurchase.add(amountText);

		priceText.setBounds(320, 540, 200, 100);
		priceText.setText("0��");
		priceText.setHorizontalAlignment(SwingConstants.CENTER);
		mainPurchase.add(priceText);

		orderDetailsBackGround.setBounds(50, 580, 490, 20);
		orderDetailsBackGround.setIcon(new ImageIcon("otherimages/empty.png"));
		mainPurchase.add(orderDetailsBackGround);

		// �޴� ��ư�� ������ ������ �޴����� �ֹ������� ��µȴ�
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
											// x ��ư ������ �� ��ġ ��ġ �ٽ�
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

										System.out.println("������");
									
										// ����Ʈ ���� ���
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
										// �帵ũ ���� ���
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
										//howManyText.setText("����  : 1 ��");
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

		// �ֹ����� Ȯ�� â
		paymentCheck.setBounds(0, 0, 600, 850);
		paymentCheck.setLayout(null);
		paymentCheck.setVisible(false);
		frame.getContentPane().add(paymentCheck);

		paymentCheckImage.setBounds(0, 0, 600, 850);
		paymentCheckImage.setLayout(null);
		paymentCheckImage.setVisible(true);
		paymentCheckImage.setIcon(new ImageIcon("./images/check.png"));
		paymentCheck.add(paymentCheckImage);

		// �����ϱ� �������� ī�� ����â
		cardpayImage.setBounds(0, 0, 600, 850);
		cardpayImage.setLayout(null);
		cardpayImage.setVisible(false);
		paymentCheck.add(cardpayImage);

		finalTotalAmount.setBounds(350, 400, 100, 20);
		finalTotalAmount.setText("�� ���� : " + totalAmount + " ��");
		finalTotalAmount.setLayout(null);
		finalTotalAmount.setVisible(true);
		paymentCheckImage.add(finalTotalAmount);

		finalTotalPrice.setBounds(450, 400, 100, 20);
		finalTotalPrice.setText("�� " + totalPrice + " ��");
		finalTotalPrice.setLayout(null);
		finalTotalPrice.setVisible(true);
		paymentCheckImage.add(finalTotalPrice);

		OKButton.setBounds(150, 500, 100, 30);
		OKButton.setText("�����ϱ�");
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
		CancelButton.setText("��������");
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
		insertCardButton.setText("ī�� ����");
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
				amountText.setText(totalAmount + "��");
				priceText.setText(totalPrice + "��");

				finalTotalAmount.setText("�� ���� : " + totalAmount + " ��");
				finalTotalPrice.setText("��  " + totalPrice + " ��");
			}
		});
		cardpayImage.add(insertCardButton);
	
		showMenu();
		changeAdvertising.start(); 
	}

	public void showMenu() {
		// ����
		if (menupage == 0) {
			menuImages[0].setIcon(new ImageIcon("./images/americano.jpg"));
			menuImages[1].setIcon(new ImageIcon("./images/latte.jpg"));
			menuImages[2].setIcon(new ImageIcon("./images/moca.png"));
			menuImages[3].setIcon(new ImageIcon("./images/banila.jpg"));
			menuImages[4].setIcon(new ImageIcon("./images/greentea.png"));
			menuImages[5].setIcon(new ImageIcon("./images/strawberry.jpg"));
		}
		// ����Ʈ
		else if (menupage == 1) {
			menuImages[0].setIcon(new ImageIcon("./images/cake.jpg"));
			menuImages[1].setIcon(new ImageIcon("./images/cake2.jpg"));
			menuImages[2].setIcon(new ImageIcon("./images/cake3.jpg"));
			menuImages[3].setIcon(new ImageIcon("./images/waffle.jpg"));
			menuImages[4].setIcon(new ImageIcon("./images/cookie.jpg"));
			menuImages[5].setIcon(new ImageIcon("./images/macaron.jpg"));
		}
	}
	
	// ���� �߰�
	public void addProducts(int addNum) {		
		addProductNum = addProductNum + addNum;
		// ����Ʈ ���� 
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

		// ���� ���� 
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