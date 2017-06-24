package first.test;


public class TestThread02 {

	private static final long COUNT = 50000 * 10000;

	public static void main(String[] args) throws InterruptedException {
		serialTest();
		concurrent();
	}

	public static void concurrent() throws InterruptedException{
		long startTime = System.currentTimeMillis();

		Thread t1 = new Thread(new Runnable() {
			int a = 0;

			@Override
			public void run() {
				for (long i = 1; i <= COUNT; i++) {
					a = a + 5;
				}
			}
		}, "A");
		t1.start();

		Thread t2 = new Thread(new Runnable() {
			int b = 0;

			@Override
			public void run() {
				for (long i = 1; i <= COUNT; i++) {
					b = b + 5;
				}
			}
		}, "B");

		t2.start();

		t1.join();
		t2.join();
		long endTime = System.currentTimeMillis();

		long needTime = endTime - startTime;
		System.out.println("current needTime: " + needTime + "\t ms");
	}

	public static void serialTest() {

		long startTime = System.currentTimeMillis();
		int a = 0;
		int b = 0;
		for (long i = 1; i <= COUNT; i++) {
			a = a + 5;
		}
		for (long i = 1; i <= COUNT; i++) {
			b = b + 5;
		}

		long endTime = System.currentTimeMillis();

		long needTime = endTime - startTime;
		System.out.println("serial needTime: " + needTime + "\t ms");
	}
}
