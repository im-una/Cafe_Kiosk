import javax.swing.*;

// thread �̿��Ͽ� ī����� ������ -> �ʱ�ȭ��
public class CardPay extends Thread {

	JLabel insertCardImage;
	JButton insertCardButton;
	boolean running = true;
	
	JLabel advertising;
	JPanel mainPurchase;
	JLabel cardPayImage;
	
	public CardPay(JLabel insertCardImage, JButton insertCardButton, JLabel advertising, JPanel mainPurchase, JLabel cardPayImage) {
		this.insertCardImage = insertCardImage;
		this.insertCardButton = insertCardButton;
		this.advertising = advertising;
		this.mainPurchase = mainPurchase;
		this.cardPayImage = cardPayImage;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1; i++) {
			insertCardImage.setIcon(new ImageIcon("./images/loading.jpg"));
			insertCardButton.setEnabled(false);

			try {
				sleep(3000); // 3�� �� 
				System.out.println("ī�������");
				
				advertising.setVisible(true);
				mainPurchase.setVisible(false);
				
			} catch (InterruptedException e) {
				running = false;
			}
		}

	}
}

