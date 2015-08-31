package de.yadrone.base.manager;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Class to manage thread resources throughout the application. Singleton
 * @author jg
 *
 */
public class ThreadPoolManager {
	private static final boolean DEBUG = false;
    DaemonThreadFactory dtf = new DaemonThreadFactory();
    ExecutorService executor = Executors.newCachedThreadPool(dtf);

	public static ThreadPoolManager threadPoolManager = null;
	private ThreadPoolManager() { }
	
	public static ThreadPoolManager getInstance() {
		if( threadPoolManager == null ) {
			threadPoolManager = new ThreadPoolManager();
		}
		return threadPoolManager;
	}
	
	public void spin(Runnable r) {
	    executor.execute(r);
	}
	
	public void shutdown() {
		List<Runnable> spun = executor.shutdownNow();
		if( DEBUG )
			for(Runnable rs : spun) {
				System.out.println("Marked for Termination:"+rs.toString());
			}
	}
	
	class DaemonThreadFactory implements ThreadFactory {
	    public Thread newThread(Runnable r) {
	        Thread thread = new Thread(r);
	        thread.setDaemon(true);
	        return thread;
	    }
	}
}
