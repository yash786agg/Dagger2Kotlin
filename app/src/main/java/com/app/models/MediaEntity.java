package com.app.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import static com.app.util.Constants.*;

/**
 * This class represents a media item
 */
public class MediaEntity implements Parcelable
{
    private String url;
    private String format;
    private int height;
    private int width;
    private String type;
    private String subType;
    private String caption;
    private String copyright;

    MediaEntity(JSONObject jsonObject)
    {
        if(jsonObject.has(urlTag))
            if(!jsonObject.isNull(urlTag))
                url = jsonObject.optString(urlTag);

        if(jsonObject.has(formatTag))
            if(!jsonObject.isNull(formatTag))
                format = jsonObject.optString(formatTag);

        if(jsonObject.has(heightTag))
            if(!jsonObject.isNull(heightTag))
                height = jsonObject.optInt(heightTag);

        if(jsonObject.has(widthTag))
            if(!jsonObject.isNull(widthTag))
                width = jsonObject.optInt(widthTag);

        if(jsonObject.has(typeTag))
            if(!jsonObject.isNull(typeTag))
                type = jsonObject.optString(typeTag);

        if(jsonObject.has(subTypeTag))
            if(!jsonObject.isNull(subTypeTag))
                subType = jsonObject.optString(subTypeTag);

        if(jsonObject.has(captionTag))
            if(!jsonObject.isNull(captionTag))
                caption = jsonObject.optString(captionTag);

        if(jsonObject.has(copyRightTag))
            if(!jsonObject.isNull(copyRightTag))
                copyright = jsonObject.optString(copyRightTag);
    }

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getCaption() {
        return caption;
    }

    public String getCopyright() {
        return copyright;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "MediaEntity{" +
                "url='" + url + '\'' +
                "format='" + format + '\'' +
                "height='" + height + '\'' +
                "width='" + width + '\'' +
                "type='" + type + '\'' +
                "subType='" + subType + '\'' +
                "caption='" + caption + '\'' +
                ", copyright='" + copyright + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(url);
        dest.writeString(format);
        dest.writeInt(height);
        dest.writeInt(width);
        dest.writeString(type);
        dest.writeString(subType);
        dest.writeString(caption);
        dest.writeString(copyright);
    }

    private MediaEntity(Parcel in)
    {
        url = in.readString();
        format = in.readString();
        height = in.readInt();
        width = in.readInt();
        type = in.readString();
        subType = in.readString();
        caption = in.readString();
        copyright = in.readString();
    }

    public static final Creator<MediaEntity> CREATOR = new Creator<MediaEntity>() {
        @Override
        public MediaEntity createFromParcel(Parcel in) {
            return new MediaEntity(in);
        }

        @Override
        public MediaEntity[] newArray(int size) {
            return new MediaEntity[size];
        }
    };
}
