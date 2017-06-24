package first.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Mythread implements Callable<String> {

	@Override
	public String call() throws Exception {

		System.out.println(Thread.currentThread().getName()+"线程");
		
		return "线程实现的第三种方式";
	}

}
public class TestThread03 {

	public static void main(String[] args) {

		FutureTask<String> futureTaskResult = new FutureTask<String>(new Mythread());

		new Thread(futureTaskResult,"A").start();
		try {
			String str = futureTaskResult.get();
			System.out.println(str);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
