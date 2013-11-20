package com.fiixed.videodiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by abell on 11/19/13.
 */
public class VideoAdapter extends ArrayAdapter<Video> {

    Context mContext;
    int mLayoutResourceId;
    Video[] mData = null;

    public VideoAdapter(Context context, int layoutResourceId, Video[] data){
        super(context,layoutResourceId,data);
        this.mContext = context;
        this.mLayoutResourceId = layoutResourceId;
        this.mData = data;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        //inflate the layout for a single row
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        row = layoutInflater.inflate(mLayoutResourceId, parent, false);

        //get a reference to all the different view elements we wish to update
        TextView dateView = (TextView)row.findViewById(R.id.listViewDateTimeTextView);
        TextView tagsView = (TextView)row.findViewById(R.id.listViewTagTextView);
        ImageView vidImageView = (ImageView)row.findViewById(R.id.vidImageView);

        //get the data from the data array
        Video video = mData[position];

        //setting the view to the data we need to display
        dateView.setText(video.mDate);
        tagsView.setText(video.mTags);

        int resId = mContext.getResources().getIdentifier(video.mNameOfImage, "drawable", mContext.getPackageName());
        vidImageView.setImageResource(resId);

        //returning the row view(because this is called getView after all)
        return row;
    }
}
