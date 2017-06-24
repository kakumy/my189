package first.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestThread04 {

	public static void main(String[] args) {

		FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				System.out.println(Thread.currentThread().getName() + "...");
				return "匿名类部类的方式实现Callable接口";
			}
		});

		new Thread(futureTask, "线程A").start();
		
		
		try {
			String string = futureTask.get();
			System.out.println(string);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
