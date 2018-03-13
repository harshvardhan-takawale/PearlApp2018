package com.dota.pearl18.pearlapp2018.sync;

import android.content.Context;
import android.util.Log;

import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.ArticleDetails;
import com.dota.pearl18.pearlapp2018.api.ArticlesInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vineeth on 3/12/2018.
 */

public class NewsSyncTask {

    private static final String TAG = "NewsSyncTask";
    private static List<ArticleDetails> list;

    public static void syncNews(Context context){
        ArticlesInterface apiService = ApiClient.getClient().create(ArticlesInterface.class);
        Call<ArrayList<ArticleDetails>> call = apiService.getArticlesList();
        call.enqueue(new Callback<ArrayList<ArticleDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<ArticleDetails>> call, Response<ArrayList<ArticleDetails>> response) {
                list = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<ArticleDetails>> call, Throwable t) {
                Log.e(TAG,"Error in Connectivity");
            }
        });

        if(list!=null && list.size()>0){
            String lastSavedId = NewsPrefs.getLastNewsId(context);
            ArticleDetails details = list.get(0);
            if(!details.getId().equals(lastSavedId)){
                NewsNotification.showNotification(context, list.get(0));
                NewsPrefs.setLastNewsId(context, list.get(0).getId());
            }
        }

    }

    /*public static void testNotification(Context context){
        ArticleDetails testDetail = new ArticleDetails("123", "Test title", null, null, null, null);
        NewsNotification.showNotification(context, testDetail);
    }*/

}
