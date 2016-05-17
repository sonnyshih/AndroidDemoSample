
package com.example.demo.activity.WebServiceTaskManagerDemo.task;


import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.VStoreNavigationItemInfoEntity;
import com.example.demo.activity.WebServiceTaskManagerDemo.type.ErrorType;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GetUrlShopAllNavigationTask extends WebServiceTask<List<VStoreNavigationItemInfoEntity>>{

    private ShopAllNavigationWebServiceTaskResultListener listener;

    public GetUrlShopAllNavigationTask(ShopAllNavigationWebServiceTaskResultListener listener) {
        this.listener = listener;
    }

    @Override
    public HttpMethodType getMethod() {
        return HttpMethodType.GET;
    }

    @Override
    public String generateServiceUrl() {
        return "http://www.ows.newegg.com/Stores.egg/ShopAllNavigation";
    }

    @Override
    public Type generateResultType() {
        return new TypeToken<List<VStoreNavigationItemInfoEntity>>() {}.getType();
    }

    @Override
    public void onTaskSucceed(List<VStoreNavigationItemInfoEntity> entity) {
        if (entity == null || entity.size() == 0) {
            listener.onShopAllNavigationWebServiceTaskEmpty();
        } else {
            listener.onShopAllNavigationWebServiceTaskSucceed(entity);
        }
    }

    @Override
    public void onTaskFailed(ErrorType errorType) {
        listener.onShopAllNavigationWebServiceTaskFailed(errorType);
    }

    public interface ShopAllNavigationWebServiceTaskResultListener {
        void onShopAllNavigationWebServiceTaskSucceed(
                List<VStoreNavigationItemInfoEntity> entity);

        void onShopAllNavigationWebServiceTaskFailed(ErrorType errorType);

        void onShopAllNavigationWebServiceTaskEmpty();
    }
}
