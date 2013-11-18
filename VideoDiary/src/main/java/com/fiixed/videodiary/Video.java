package com.fiixed.videodiary;

import java.util.Date;

/**
 * Created by abell on 11/18/13.
 */
public class Video {
    public Date mDate;
    public String[] mTags;
    public String mNameOfImage;

    public Video (Date mDate, String[] mTags,String mNameOfImage ){

        this.mDate = mDate;
        this.mTags = mTags;
        this.mNameOfImage = mNameOfImage;

    }
}
