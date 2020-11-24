package com.kingbird.myapplicationtest.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 说明：
 *
 * @author :Pan Yingdao
 * @date : on 2020/11/24 0024 17:12:17
 */
public class LabelTypeBean implements Parcelable {

    int imageId;
    String tag;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LabelTypeBean(int imageId, String tag) {
        this.imageId = imageId;
        this.tag = tag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected LabelTypeBean(Parcel in) {
        this.imageId = in.readInt();
        this.tag = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(tag);
    }

    public static final Parcelable.Creator<LabelTypeBean> CREATOR = new Parcelable.Creator<LabelTypeBean>() {
        @Override
        public LabelTypeBean createFromParcel(Parcel source) {
            return new LabelTypeBean(source);
        }

        @Override
        public LabelTypeBean[] newArray(int size) {
            return new LabelTypeBean[size];
        }
    };
}
