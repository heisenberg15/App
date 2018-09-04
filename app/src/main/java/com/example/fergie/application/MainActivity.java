package com.example.fergie.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fergie.application.models.PostsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    Toolbar toolbar;
    PostsAdapter postsAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar_id);
        recyclerView = findViewById(R.id.recycler_view_id);


        initToolbar();
        initRecyclerView();
        getJsonInfo();
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
    }

    private void initRecyclerView()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void getJsonInfo()
    {
        String url = "http://jsonstub.com/feed";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, "", new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                JSONArray jsonArray;

                try
                {
                    jsonArray = response.getJSONArray("posts");
                    ArrayList<PostsModel> postsModels = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject curObj = jsonArray.getJSONObject(i);
                        String name = curObj.getString("name");
                        String message = curObj.getString("message");
                        String img = curObj.getString("photoUrl");

                        PostsModel postsModel = new PostsModel(name, message, img);
                        postsModels.add(postsModel);

                        postsAdapter = new PostsAdapter(postsModels, getApplicationContext());
                        recyclerView.setAdapter(postsAdapter);

                    }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("JsonStub-User-Key", "a6504fb9-780f-483e-9021-19135c3cfc97");
                headers.put("JsonStub-Project-Key", "58df32c7-1d41-4932-8f00-d2d4a61ae791");
                return headers;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }
}
