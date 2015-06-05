package com.example.ThreadAndAsyncTaskDemo.ThreadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.ThreadAndAsyncTaskDemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/** Thread Pool ����  
 * 1. newFixedThreadPool�G
 *    �Ӥ�k��^�@�ӳ̤j�e�q�T�w���u�{���A���|���ݳЫطs�u�{�A
 *    �u�{�ƶq���j��t�m���ƶq�j�p�C��u�{�ƹF��̤j�H��A�u�{���|�@�������o��h���ܡA�ѤU���|��b Queue
 * 
 * 2. newCachedThreadPool:
 *    �Ӥ�k��^�@�ӵL�ɪ��u�{���A�]�N�O�u�S���̤j�ƶq����v�C����u�@�q��p�ɡA�o���u�{���|�P���S�Ϊ��u�{�C
 *    
 * 3. newSingleThreadedExecutor:
 *    �Ӥ�k��^�@��executor�A���i�H�O�ҩҦ������ȳ��b�@�ӳ�u�{������C
 * 
 * 4. newScheduledThreadPool�G
 *    �Ӥ�k��^�@�өT�w�j�p���u�{���A��������ɩM�w�ɥ��Ȫ�����C
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
