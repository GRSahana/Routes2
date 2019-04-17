package com.homeassignment.routes.Volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


public class MySingleton {

    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private Context mcontext;

    public MySingleton(Context context) {
        mcontext = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getmInstance(Context context){
        if(mInstance==null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }


    public<T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }

}


