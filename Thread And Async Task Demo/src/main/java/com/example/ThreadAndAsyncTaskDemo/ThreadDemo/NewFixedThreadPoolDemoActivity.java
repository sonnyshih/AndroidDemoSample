package com.example.ThreadAndAsyncTaskDemo.ThreadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.ThreadAndAsyncTaskDemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/** Thread Pool 種類  
 * 1. newFixedThreadPool：
 *    該方法返回一個最大容量固定的線程池，它會按需創建新線程，
 *    線程數量不大於配置的數量大小。當線程數達到最大以後，線程池會一直維持這麼多不變，剩下的會放在 Queue
 * 
 * 2. newCachedThreadPool:
 *    該方法返回一個無界的線程池，也就是「沒有最大數量限制」。但當工作量減小時，這類線程池會銷毀沒用的線程。
 *    
 * 3. newSingleThreadedExecutor:
 *    該方法返回一個executor，它可以保證所有的任務都在一個單線程中執行。
 * 
 * 4. newScheduledThreadPool：
 *    該方法返回一個固定大小的線程池，它支持延時和定時任務的執行。
 * */

public class NewFixedThreadPoolDemoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_fixed_thread_pool_demo_activity);

		int poolSize = 3; // thread pool size.
		ExecutorService threadPool = Executors.newFixedThreadPool(poolSize);
		// ExecutorService threadPool = Executors.newSingleThreadExecutor(); //
		// create one single thread.

		for (int i = 0; i < 10; i++) {
			Runnable workerRunnable = new WorkerRunnable("" + i);
			threadPool.execute(workerRunnable);
		}

		threadPool.shutdown();

		while (!threadPool.isTerminated()) {
		}

		Log.d("Mylog", "Finished all threads");

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	public class WorkerRunnable implements Runnable {

		private String workerName;

		public WorkerRunnable(String workerName) {
			this.workerName = workerName;
		}

		@Override
		public void run() {
			try {

				Log.d("Mylog", Thread.currentThread().getName()
						+ " Start. Command = " + workerName);

				Thread.sleep(2000);

				Log.d("Mylog", Thread.currentThread().getName()
						+ " End. Command = " + workerName);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
