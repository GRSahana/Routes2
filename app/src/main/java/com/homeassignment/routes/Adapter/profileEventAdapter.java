package com.homeassignment.routes.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.homeassignment.routes.R;
import com.homeassignment.routes.RouteDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class profileEventAdapter extends RecyclerView.Adapter<profileEventAdapter.EventViewHolder> implements Filterable {

    //this context we will use to inflate the layout
    private Context mCtx;


    //we are storing all the products in a list
    private List<ProfileEventDetails> profileList;
    private List<ProfileEventDetails> profileListFiltered;

    public profileEventAdapter(Context mCtx, List<ProfileEventDetails> profileList) {
        this.mCtx = mCtx;
        this.profileList = profileList;
        this.profileListFiltered = profileList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.route_list, null);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        //getting the product of the specified position
        final ProfileEventDetails product = profileList.get(position);

        //binding the data with the viewholder views
        holder.textViewProfileName.setText(product.getProfile_name());
        Picasso.with(mCtx).load(product.getProfile_image()).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    profileList = profileListFiltered;
                } else {
                    List<ProfileEventDetails> filteredList = new ArrayList<>();
                    for (ProfileEventDetails row : profileListFiltered) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getProfile_name().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    profileList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = profileList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                profileList = (ArrayList<ProfileEventDetails>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }






    class EventViewHolder extends RecyclerView.ViewHolder {

        TextView textViewProfileName;
        ImageView imageView;

        public EventViewHolder(View itemView) {
            super(itemView);

            textViewProfileName =  (TextView)itemView.findViewById(R.id.profile_name);
            imageView =  (ImageView)itemView.findViewById(R.id.profile_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        ProfileEventDetails checkEventSelected = profileList.get(pos);
                        Intent intent = new Intent(mCtx,RouteDetailsActivity.class);
                        intent.putExtra("RouteName",checkEventSelected.getProfile_name());
                        intent.putExtra("Desc",checkEventSelected.getDescription());
                        intent.putExtra("Access",checkEventSelected.getAccesebility());
                        intent.putExtra("Image",checkEventSelected.getProfile_image());
                        intent.putExtra("Stops",checkEventSelected.getStops());
                        mCtx.startActivity(intent);
                    }
                }
            });
        }
    }



}
