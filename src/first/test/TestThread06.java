package first.test;

import sun.awt.windows.ThemeReader;

/**
 * 新建资源类
 * 
 * @author zjx
 *
 */
class ShareData {

	private int number = 0;

	/**
	 * 生产者
	 * @throws InterruptedException
	 */
	public synchronized void increment() throws InterruptedException {
		while (number != 0) {
			this.wait();
		}
		++number;
		System.out.println(Thread.currentThread().getName() + "\t" + number);
		this.notifyAll();
	}

	/**
	 * 消费者
	 * @throws InterruptedException
	 */
	public synchronized void decrement() throws InterruptedException {
		while (number == 0) {
			this.wait();
		}
		--number;
		System.out.println(Thread.currentThread().getName() + "\t" + number);
		this.notifyAll();
	}
}

public class TestThread06 {
	public static void main(String[] args) {

		final ShareData sd = new ShareData();

		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(200);
						sd.increment();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "A").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(300);
						sd.decrement();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "B").start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(400);
						sd.increment();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "C").start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(100);
						sd.decrement();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "D").start();
	}
}
