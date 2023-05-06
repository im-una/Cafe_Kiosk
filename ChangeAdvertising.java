import java.awt.*;
import javax.swing.*;

// thread �̿��Ͽ� 3�ʸ��� ���� �̹����� �ٲ�� �� ����
public class ChangeAdvertising extends Thread{
	
	JLabel advertising; 
	boolean running = true;
	
	public ChangeAdvertising(JLabel advertising){
		this.advertising = advertising;
	}
	
	@Override
	public synchronized void run() {
		while (running) {
			try {
				// 3�� ����
				advertising.setIcon(new ImageIcon("./images/thread1.jpeg"));
				sleep(3000);
				
				advertising.setIcon(new ImageIcon("./images/thread2.jpg"));
				sleep(3000);
				
				advertising.setIcon(new ImageIcon("./images/thread3.jpg"));
				sleep(3000);
				
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
}
