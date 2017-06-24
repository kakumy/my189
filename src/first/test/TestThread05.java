package first.test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TestThread05 {

	public static void main(String[] args) {

		FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				int k = 0;
				for (int i = 0; i < 10000; i++) {
					for (int j = 0; j < 9999; j++) {
						k = i + j;
					}
				}

				System.out.println("k = " + k + "由线程" + Thread.currentThread().getName() + "完成");
				return "new接口的方法";
			}
		});

		Thread thread = new Thread(futureTask, "A");
		thread.start();

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("是不是这个答案");
	}

}
