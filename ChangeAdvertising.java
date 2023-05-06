import java.awt.*;
import javax.swing.*;

// thread 이용하여 3초마다 광고 이미지가 바뀌는 것 구현
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
				// 3초 간격
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
