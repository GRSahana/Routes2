package com.homeassignment.routes;

import android.app.ProgressDialog;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.homeassignment.routes.Adapter.ProfileEventDetails;
import com.homeassignment.routes.Adapter.profileEventAdapter;
import com.homeassignment.routes.Volley.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView profile_list;
    private List<ProfileEventDetails> profileDetails;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private profileEventAdapter mAdapter;
    ProgressDialog pDialog;
    String url = "http://www.mocky.io/v2/5808f00d10000005074c6340";
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profileDetails = new ArrayList<>();
        profile_list = (RecyclerView)findViewById(R.id.recycler_view);

        profile_list.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        profile_list.setLayoutManager(linearLayoutManager);

        loadAllProfile();
    }

    @Override
    public void onBackPressed() {

            super.onBackPressed();
    }

    private void loadAllProfile() {

        pDialog = new ProgressDialog(this);

        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        //Log.d("json array", response);
                        hidePDialog();
                        jsonObject = new JSONObject(response);

                        jsonArray = jsonObject.getJSONArray("routes");
                            //converting the string to json array object


                            //traversing through all the object
                            for (int i = 0; i < jsonArray.length(); i++) {

                                //getting product object from json array
                                JSONObject product = jsonArray.getJSONObject(i);

                                //adding the product to product list


                                profileDetails.add(new ProfileEventDetails(
                                        product.getString("id"),
                                        product.getString("name"),
                                        product.getString("image"),
                                        product.getString("description"),
                                        product.getString("accessible"),
                                        product.getString("stops")
                                ));
                            }
                            //creating recyclerview adapter
                            mAdapter = new profileEventAdapter(MainActivity.this, profileDetails);

                            //setting adapter to recyclerview
                            profile_list.setAdapter(mAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    //This indicates that the reuest has either time out or there is no connection
                    Toast.makeText(MainActivity.this, "Check Your internet connection!!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error in loading data", Toast.LENGTH_LONG).show();
                }
                Log.e("error", error.toString());

            }
        });
        VolleyLog.DEBUG = true;

        MySingleton.getmInstance(MainActivity.this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
