package first.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.org.apache.xerces.internal.parsers.CachingParserPool.SynchronizedGrammarPool;

public class TestThread01 {

	public static void main(String[] args) {

		final TestTicket tk = new TestTicket();

		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i < 15; i++) {
					tk.sale();
				}
			}
		}, "A").start();

		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i < 15; i++) {
					tk.sale();
				}
			}
		}, "B").start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i < 15; i++) {
					tk.sale();
				}
			}
		}, "C").start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i < 150; i++) {
					tk.sale();
				}
			}
		}, "D").start();
		

	}

}

class TestTicket {
	private int num = 100;
	private Lock lock = new ReentrantLock();

	public void sale() {
		lock.lock();
		try {
			if (num > 0) {
				System.out.println(Thread.currentThread().getName() + "卖出第 \t" + (num--) + "\t 还剩 " + num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}