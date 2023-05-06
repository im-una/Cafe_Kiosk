import javax.swing.*;

// thread 이용하여 카드결제 페이지 -> 초기화면
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
				sleep(3000); // 3초 후 
				System.out.println("카드결제중");
				
				advertising.setVisible(true);
				mainPurchase.setVisible(false);
				
			} catch (InterruptedException e) {
				running = false;
			}
		}

	}
}

