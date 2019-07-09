package com.app.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import static com.app.util.Constants.*;

/**
 * This represents a news item
 */
public class NewsEntity implements Parcelable
{
    private String title;
    private String summary;
    private String articleUrl;
    private String byline;
    private String publishedDate;
    private List<MediaEntity> mediaEntityList;

    private static final String TAG = NewsEntity.class.getSimpleName();

    public NewsEntity(JSONObject jsonObject)
    {
        mediaEntityList = new ArrayList<>();

        if(jsonObject.has(titleTag))
            if(!jsonObject.isNull(titleTag))
                title = jsonObject.optString(titleTag);

        if(jsonObject.has(abstractTag))
            if(!jsonObject.isNull(abstractTag))
                summary = jsonObject.optString(abstractTag);

        if(jsonObject.has(urlTag))
            if(!jsonObject.isNull(urlTag))
                articleUrl = jsonObject.optString(urlTag);

        if(jsonObject.has(bylineTag))
            if(!jsonObject.isNull(bylineTag))
                byline = jsonObject.optString(bylineTag);

        if(jsonObject.has(publishedDateTag))
            if(!jsonObject.isNull(publishedDateTag))
                publishedDate = jsonObject.optString(publishedDateTag);

        JSONArray mediaArray = jsonObject.optJSONArray(multimediaTag);
        if(mediaArray != null && mediaArray.length() >= 1)
        {
            for (int i = 0; i < mediaArray.length(); i++)
            {
                JSONObject mediaObject = mediaArray.optJSONObject(i);
                MediaEntity mediaEntity = new MediaEntity(mediaObject);
                mediaEntityList.add(mediaEntity);
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public String getByline() {
        return byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<MediaEntity> getMediaEntity() {
        return mediaEntityList;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "NewsEntity{" +
                "title='" + title + '\'' +
                "summary='" + summary + '\'' +
                "articleUrl='" + articleUrl + '\'' +
                "byline='" + byline + '\'' +
                "publishedDate='" + publishedDate + '\'' +
                ", mediaEntityList='" + mediaEntityList + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(title);
        dest.writeString(summary);
        dest.writeString(articleUrl);
        dest.writeString(byline);
        dest.writeString(publishedDate);
        dest.writeTypedList(mediaEntityList);
    }

    private NewsEntity(Parcel in)
    {
        title = in.readString();
        summary = in.readString();
        articleUrl = in.readString();
        byline = in.readString();
        publishedDate = in.readString();
        in.readTypedList(mediaEntityList,MediaEntity.CREATOR);
    }

    public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
        @Override
        public NewsEntity createFromParcel(Parcel in) {
            return new NewsEntity(in);
        }

        @Override
        public NewsEntity[] newArray(int size) {
            return new NewsEntity[size];
        }
    };
}
