
package com.example.demo.activity.WebServiceTaskManagerDemo.manager;


import com.example.demo.activity.WebServiceTaskManagerDemo.task.WebServiceTask;
import com.example.demo.activity.WebServiceTaskManagerDemo.worker.volley.VolleyWorker;
import com.example.demo.activity.WebServiceTaskManagerDemo.worker.WebServiceWorker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WebServiceTaskManager {
    public static Set<WebServiceWorker<?>> workers = new HashSet<WebServiceWorker<?>>();

    private static WebServiceWorker createWebServiceWorker(WebServiceTask task) {
        return new VolleyWorker(task);
    }

    public static void startTask(WebServiceTask<?> task, Object user) {
        synchronized (workers) {
            WebServiceWorker<?> worker = createWebServiceWorker(task);
            worker.setTag(user);
            worker.startTask();

            workers.add(worker);
        }
    }

    public static void cancelTasks(Object user) {
        synchronized (workers) {
            List<WebServiceWorker<?>> cancelWorkers = new ArrayList<WebServiceWorker<?>>();

            for (WebServiceWorker<?> worker : workers) {
                if (user == worker.getTag()) {
                    worker.cancelTask();
                    cancelWorkers.add(worker);
                }
            }

            workers.removeAll(cancelWorkers);
        }
    }

    public static int getTaskCount(Object user) {
        synchronized (workers) {
            int count = 0;
            for (WebServiceWorker<?> worker : workers) {
                if (user == worker.getTag()) {
                    count++;
                }
            }
            return count;
        }
    }

    public static void cancelAllApplicationTasks() {
        synchronized (workers) {
            for (WebServiceWorker<?> worker : workers) {
                worker.cancelTask();
            }
            workers.clear();
        }
    }

    public static int getAllApplicationTaskCount() {
        synchronized (workers) {
            return workers.size();
        }
    }

}
