package com.fiixed.videodiary;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends Activity {

    private static final int VIDEO_CAPTURE = 101;
    private Uri fileUri;
    private ImageView mVidScreenshot;
    private ListView mListView;
    private VideoAdapter videoAdapter;

    Video[] myVideosArray = new Video[]
            {
                    new Video("Wednesday 15th Nov, 2013", "home, birthday", "image01"),
                    new Video("Tuesday 14th Nov, 2013", "home, happy", "image02"),
                    new Video("Monday 13th Nov 2013", "work", "image03"),
                    new Video("Tuesday 14th Nov, 2013", "home, happy", "image02"),
                    new Video("Tuesday 14th Nov, 2013", "home, happy", "image02"),
                    new Video("Tuesday 14th Nov, 2013", "home, happy", "image02"),
                    new Video("Tuesday 14th Nov, 2013", "home, happy", "image02"),
                    new Video("Tuesday 14th Nov, 2013", "home, happy", "image02"),
                    new Video("Tuesday 14th Nov, 2013", "home, happy", "image02"),
                    new Video("Tuesday 14th Nov, 2013", "home, happy", "image02"),
                    new Video("Tuesday 14th Nov, 2013", "home, happy", "image02"),
                    new Video("Tuesday 14th Nov, 2013", "home, happy", "image02"),
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        VideoAdapter videoAdapter = new VideoAdapter(getApplicationContext(), R.layout.row, myVideosArray);

        mListView = (ListView) findViewById(R.id.videoListView);

        if (mListView != null) {
            mListView.setAdapter(videoAdapter);
        }

//        mVidScreenshot = (ImageView) findViewById(R.id.vidImageView);
//        mVidScreenshot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
//
//                startActivity(intent);
//            }
//        });


    }

    public void startRecording() {
        File mediaFile = new
                File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/myvideo.mp4");

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        fileUri = Uri.fromFile(mediaFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, VIDEO_CAPTURE);
    }



    private boolean hasCamera() {
        if (getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FRONT)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_video:
                startRecording();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSearch() {
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Video has been saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
        }
    }


}
