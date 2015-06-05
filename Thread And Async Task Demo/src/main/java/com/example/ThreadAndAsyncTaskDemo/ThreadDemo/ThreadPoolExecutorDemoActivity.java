package com.example.ThreadAndAsyncTaskDemo.ThreadDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.example.ThreadAndAsyncTaskDemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ThreadPoolExecutorDemoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_pool_executor_demo_activity);

		// RejectedExecutionHandler implementation
		RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();

		// Get the ThreadFactory implementation to use
		ThreadFactory threadFactory = Executors.defaultThreadFactory();

		int corePoolSize = 2; // い┮玂絬祘计珹盯絬祘
		int maximumPoolSize = 4; // いす砛程絬祘计
		int keepAliveTime = 10; // 讽絬祘计み沧ゎ玡緇盯絬祘单穝ヴ叭程丁

		// ⊿Τ Queue
		// BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		
		//  Queue
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(corePoolSize);
		
		// creating the ThreadPoolExecutor
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(corePoolSize,
				maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, queue,
				threadFactory, rejectionHandler);

		// start the monitoring thread
		MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
		Thread monitorThread = new Thread(monitor);
		monitorThread.start();

		// submit work to the thread pool
		for (int i = 0; i < 10; i++) {
			executorPool.execute(new WorkerThread("cmd" + i));
		}

		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// shut down the pool
		executorPool.shutdown();

		// shut down the monitor thread
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		monitor.shutdown();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	public class WorkerThread implements Runnable {

		private String command;

		public WorkerThread(String s) {
			this.command = s;
		}

		@Override
		public void run() {
			Log.d("Mylog", Thread.currentThread().getName()
					+ " Start. Command = " + command);
			processCommand();
			Log.d("Mylog", Thread.currentThread().getName() + " End.");
		}

		private void processCommand() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return this.command;
		}
	}

	public class RejectedExecutionHandlerImpl implements
			RejectedExecutionHandler {

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			Log.d("Mylog", r.toString() + " is rejected");
		}

	}

	public class MyMonitorThread implements Runnable {
		private ThreadPoolExecutor executor;

		private int seconds;

		private boolean run = true;

		public MyMonitorThread(ThreadPoolExecutor executor, int delay) {
			this.executor = executor;
			this.seconds = delay;
		}

		public void shutdown() {
			this.run = false;
		}

		@Override
		public void run() {
			while (run) {
				Log.d("Mylog",
						String.format(
								"[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
								this.executor.getPoolSize(),
								this.executor.getCorePoolSize(),
								this.executor.getActiveCount(),
								this.executor.getCompletedTaskCount(),
								this.executor.getTaskCount(),
								this.executor.isShutdown(),
								this.executor.isTerminated()));
				try {
					Thread.sleep(seconds * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
