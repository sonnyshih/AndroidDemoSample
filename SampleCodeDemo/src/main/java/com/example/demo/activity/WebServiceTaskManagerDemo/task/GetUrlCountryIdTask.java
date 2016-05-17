package com.example.demo.activity.WebServiceTaskManagerDemo.task;


import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.country.TrackingCountryInfoEntity;
import com.example.demo.activity.WebServiceTaskManagerDemo.type.ErrorType;

import java.lang.reflect.Type;

public class GetUrlCountryIdTask extends WebServiceTask<TrackingCountryInfoEntity>{

    private TrackingCountryIdWebServiceTaskListener listener;

    public GetUrlCountryIdTask(TrackingCountryIdWebServiceTaskListener listener) {
        this.listener = listener;
    }

    @Override
    public HttpMethodType getMethod() {
        return HttpMethodType.GET;
    }

    @Override
    public String generateServiceUrl() {
        return "http://www.ows.newegg.com/Tracking.egg/Country";
    }

    @Override
    public Type generateResultType() {
        return TrackingCountryInfoEntity.class;
    }

    @Override
    public void onTaskSucceed(TrackingCountryInfoEntity entity) {
        if (entity == null) {
            listener.onTrackingCountryIdWebServiceTaskEmpty();
        } else {
            listener.onTrackingCountryIdWebServiceTaskSucceed(entity);
        }
    }

    @Override
    public void onTaskFailed(ErrorType errorType) {
        listener.onTrackingCountryIdWebServiceTaskFailed(errorType);
    }

    public interface TrackingCountryIdWebServiceTaskListener {
        public void onTrackingCountryIdWebServiceTaskSucceed(
                TrackingCountryInfoEntity trackingCountryInfo);

        public void onTrackingCountryIdWebServiceTaskFailed(ErrorType errorType);

        public void onTrackingCountryIdWebServiceTaskEmpty();
    }
}
