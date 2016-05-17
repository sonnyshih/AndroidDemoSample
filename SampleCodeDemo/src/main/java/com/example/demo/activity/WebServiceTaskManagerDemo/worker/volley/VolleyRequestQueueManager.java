package com.example.demo.activity.WebServiceTaskManagerDemo.worker.volley;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.demo.manage.ApplicationManager;

public class VolleyRequestQueueManager {

    private static VolleyRequestQueueManager instance;

    private RequestQueue requestQueue;

    private VolleyRequestQueueManager() {
        requestQueue = Volley.newRequestQueue(ApplicationManager.getInstance()
                .getContext());
    }

    public static VolleyRequestQueueManager getInstance() {
        if (instance == null) {
            instance = new VolleyRequestQueueManager();
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
