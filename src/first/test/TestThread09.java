package first.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class ShareData1 {

	private int number = 0;

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void increment() throws InterruptedException {

		lock.lock();
		try {
			while (number != 0) {
				condition.await();
			}
			++number;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void decrement() throws InterruptedException {
		lock.lock();
		try {
			while (number == 0) {
				condition.await();
			}
			--number;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

public class TestThread09 {

	public static void main(String[] args) {
		final ShareData1 sd = new ShareData1();

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
