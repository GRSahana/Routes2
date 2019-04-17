package com.homeassignment.routes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.homeassignment.routes.R.drawable.access;
import static com.homeassignment.routes.R.drawable.dot;
import static com.homeassignment.routes.R.drawable.line;

public class RouteDetailsActivity extends Activity {
    TextView tvname, tvdesc;
    ImageView routeImage, ivaccessability;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);
        Intent intentFromActivity = getIntent();
        if (intentFromActivity.hasExtra("RouteName")) {

            String routeName = getIntent().getStringExtra("RouteName");
            String routeDesc = getIntent().getStringExtra("Desc");
            String Accessability = getIntent().getStringExtra("Access");
            String stops = getIntent().getStringExtra("Stops");
            String image = getIntent().getStringExtra("Image");
            tvname = (TextView) findViewById(R.id.routeName);
            tvdesc = (TextView) findViewById(R.id.description);
            routeImage = (ImageView) findViewById(R.id.routeImage);
            ivaccessability = (ImageView) findViewById(R.id.access);
            Log.d("route name",stops);

            try {
                jsonArray = new JSONArray(stops);

//                jsonArray = jsonObject.getJSONArray(stops);
                //converting the string to json array object


                //traversing through all the object
                LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout);
                for (int i = 0; i < jsonArray.length(); i++) {

                    //getting product object from json array
                    JSONObject product = jsonArray.getJSONObject(i);
                    TextView tv = new TextView(this);
                    Log.d("route name",product.getString("name"));
                    tv.setText("   "+product.getString("name"));
                    tv.setId(i + 5);
                    Drawable img = this.getResources().getDrawable( R.drawable.dot);
                    img.setBounds( 0, 0, 60, 60 );
                    tv.setCompoundDrawables(img,null,null,null);
                    ll.addView(tv);

                    if(i!=jsonArray.length()-1){

                        ImageView im = new ImageView(this);
                        im.setMaxHeight(10);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(22,0, 0,0);
                        im.setLayoutParams(lp);
                        im.setImageResource(line);
                        ll.addView(im);
                    }
                }
            }
            catch (JSONException e){

            }

            tvname.setText(routeName);
            tvdesc.setText(routeDesc);
            if (Accessability.equalsIgnoreCase("false")) {
                ivaccessability.setVisibility(View.INVISIBLE);
            }
            else {
                ivaccessability.setImageResource(access);
            }

            Picasso.with(this).load(image).fit().into(routeImage);


        }
    }
}
