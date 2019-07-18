package com.app.ui.main.news

import android.net.Uri
import android.text.TextUtils
import androidx.databinding.BindingAdapter
import com.app.daggerkotlin.R
import com.app.models.NewsEntity
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import com.app.util.Constants.Companion.httpTag
import com.app.util.Constants.Companion.httpsTag
import com.facebook.drawee.backends.pipeline.Fresco

object NewsListBindingAdapter
{
    /*
    * Links - https://blog.stylingandroid.com/data-binding-part-2/
    * https://philio.me/using-android-data-binding-adapters-with-dagger-2/
    * https://medium.com/@thinkpanda_75045/defining-android-binding-adapter-in-kotlin-b08e82116704
    * */

    @JvmStatic
    @BindingAdapter("newsItemImage")
    fun newsItemImage(imageView: SimpleDraweeView, newsEntity: NewsEntity)
    {
        if (newsEntity.mediaEntity.size >= 1)
        {
            val thumbnailURL = newsEntity.mediaEntity[0].url.replace(httpTag, httpsTag)

            if (!TextUtils.isEmpty(thumbnailURL))
            {
                val draweeController = Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri(Uri.parse(thumbnailURL)))
                    .setOldController(imageView.controller).build()
                imageView.controller = draweeController
            }
        }
        else
            imageView.hierarchy.setPlaceholderImage(imageView.context.getDrawable(R.drawable.place_holder))
    }
}
