
package com.example.demo.activity.WebServiceTaskManagerDemo.worker;

import android.os.AsyncTask;

import com.example.demo.activity.WebServiceTaskManagerDemo.manager.WebServiceTaskManager;
import com.example.demo.activity.WebServiceTaskManagerDemo.task.WebServiceTask;
import com.example.demo.activity.WebServiceTaskManagerDemo.type.ErrorType;

import java.lang.reflect.Type;
import java.util.Map;

public abstract class WebServiceWorker <R> {

    private WebServiceTask<R> task;
    private Object tag;
    private AsyncTask<Void, Void, Map<String, String>> headerAsyncTask;

    public WebServiceWorker(WebServiceTask<R> task) {

        this.task = task;
    }

    public void startTask() {
        headerAsyncTask = new AsyncTask<Void, Void, Map<String, String>>() {

            @Override
            protected Map<String, String> doInBackground(Void... params) {
                return task.generateHttpHeaders();
            }

            @Override
            protected void onPostExecute(Map<String, String> result) {
                super.onPostExecute(result);
                switch (task.getMethod()) {
                    case GET:
                        startHttpGetRequest(task.generateServiceUrl(), result,
                                task.generateResultType());
                        break;
                    case POST:
                        startHttpPostRequest(task.generateServiceUrl(),
                                task.generatePostBody(), result,
                                task.generateResultType());
                        break;
                }
            }
        }.execute();
    }

    public void cancelTask() {
        headerAsyncTask.cancel(true);
        cancelRequest();
    }

    protected abstract void startHttpGetRequest(String serviceUrl,
                                                Map<String, String> headerValues, Type type);

    protected abstract void startHttpPostRequest(String serviceUrl,
                                                 String postBody, Map<String, String> headerValue, Type type);

    protected abstract void cancelRequest();

    protected void notifyWorkSucceed(R result) {
        synchronized (WebServiceTaskManager.workers) {
            WebServiceTaskManager.workers.remove(this);
            task.onTaskSucceed(result);
        }
    }

    protected void notifyWorkFailed(ErrorType errorType) {
        synchronized (WebServiceTaskManager.workers) {
            WebServiceTaskManager.workers.remove(this);
            task.onTaskFailed(errorType);
        }
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
