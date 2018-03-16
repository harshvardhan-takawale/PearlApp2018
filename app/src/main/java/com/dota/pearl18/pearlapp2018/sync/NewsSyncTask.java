package com.dota.pearl18.pearlapp2018.sync;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.FeedDetails;
import com.dota.pearl18.pearlapp2018.api.FeedInterface;
import com.dota.pearl18.pearlapp2018.api.FeedResponseDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vineeth on 3/12/2018.
 */

public class NewsSyncTask {

    private static final String TAG = "NewsSyncTask";
    private static List<FeedDetails> list;
    private static final String SORT_KEY = "createdAt";
    private static final String ORDER = "desc";

    public static void syncNews(Context context){

        FeedInterface apiservice = ApiClient.getClient().create(FeedInterface.class);
        Call<FeedResponseDetails> call = apiservice.getScoresfeed(1, SORT_KEY, ORDER);

        call.enqueue(new Callback<FeedResponseDetails>() {
            @Override
            public void onResponse(@NonNull Call<FeedResponseDetails> call, @NonNull Response<FeedResponseDetails> response) {
                FeedResponseDetails feedResponse = response.body();
                int resultCount = feedResponse.getTotalresults();
                if (resultCount != 0) {
                    list = feedResponse.getDocs();
                }
            }

            @Override
            public void onFailure(@NonNull Call<FeedResponseDetails> call, @NonNull Throwable t) {
                Log.d(TAG, "Error in connectivity");
            }
        });


        if(list!=null && list.size()>0){
            String lastSavedId = NewsPrefs.getLastNewsId(context);
            FeedDetails details = list.get(0);
            if(!details.getId().equals(lastSavedId)){
                NewsNotification.showNotification(context, list.get(0));
                NewsPrefs.setLastNewsId(context, list.get(0).getId());
            }
        }
    //    testNotification(context);
    }

    /*public static void testNotification(Context context){
        ArticleDetails testDetail = new ArticleDetails("123", "Test title", null, null, null, null);
        NewsNotification.showNotification(context, testDetail);
    }*/

}
