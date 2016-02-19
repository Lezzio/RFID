package fr.pag.rfid.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

	private static final ExecutorService threadPool = Executors.newCachedThreadPool();
	
	public static void executeTask(Runnable task) {
		threadPool.execute(task);
	}

}
