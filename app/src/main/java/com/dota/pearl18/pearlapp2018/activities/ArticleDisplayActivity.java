package com.dota.pearl18.pearlapp2018.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.ArticleDetails;
import com.dota.pearl18.pearlapp2018.api.ArticlesInterface;
import com.dota.pearl18.pearlapp2018.api.TestApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDisplayActivity extends AppCompatActivity {
    private String id;

    private TextView mTitle;
    private TextView mAuthors;
    private TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_display);
        id = getIntent().getStringExtra("_id");

        mTitle = findViewById(R.id.tv_title);
        mAuthors = findViewById(R.id.tv_authors);
        mContent = findViewById(R.id.tv_content);

        //TODO: CHANGE THIS ALSO TO APICLIENT
        ArticlesInterface articlesInterface = TestApiClient.getClient().create(ArticlesInterface.class);

        Call<ArticleDetails> call  = articlesInterface.getArticle(id);
        call.enqueue(new Callback<ArticleDetails>() {
            @Override
            public void onResponse(Call<ArticleDetails> call, Response<ArticleDetails> response) {
                ArticleDetails current = response.body();

                mTitle.setText(current.getTitle());

                if (current.getAuthors().size() == 0) {
                    mAuthors.setVisibility(View.GONE);
                } else {
                    mAuthors.setVisibility(View.VISIBLE);
                    mAuthors.setText(formatAuthors(current.getAuthors()));
                }

                String content = current.getContent();
                if ( content != null && content.contains("</p>")){
                    mContent.setText(formatContent(current.getContent()));
                } else {
                    mContent.setText(content);
                }
            }

            @Override
            public void onFailure(Call<ArticleDetails> call, Throwable t) {
                //Log.i(TAG, "onFailure: " + call.request().url());
            }
        });

    }

    private String formatAuthors(ArrayList<String> authList) {
        StringBuilder sb = new StringBuilder("By: ");
        int lim = authList.size();
        for (int i = 0; i < lim; i++) {
            sb.append(authList.get(i));
            if (i != lim-1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private Spanned formatContent(String content) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT);
        } else {
            //noinspection deprecation
            return Html.fromHtml(content);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
