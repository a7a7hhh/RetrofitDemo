package data.api.base;

import data.api.ApiService;

/**
 * Created by Winkey on 2017/7/7.
 */

public class ApiManager {

    private static ApiManager sInstance = null;
    public ApiService mApiService;

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        if (sInstance == null) {
            synchronized (ApiManager.class) {
                if (sInstance == null) {
                    sInstance = new ApiManager();
                }
            }
        }
        return sInstance;
    }


    public ApiService getApiService() {
        mApiService = ApiServiceFactory.getInstance().provideApiService(ApiConfig.IS_TEST ? ApiConfig.TEST_HOST : ApiConfig.HOST);
        return mApiService;
    }
}
