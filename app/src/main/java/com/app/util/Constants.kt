package com.app.util

class Constants
{
    companion object
    {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        const val TIMEOUT_REQUEST: Long = 30

        const val sessionManagerPref : String = "daggerKotlin_AndroidPref"

        const val logInSess : String = "logInSess"

        const val userIdSess : String = "userIdSess"

        const val imageUrl = "https://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbStandard.jpg"

        const val httpTag = "http"

        const val httpsTag = "https"

        const val numResultTag = "num_results"

        const val resultsTag = "results"

        /* NewsEntity Response Tags */

        const val titleTag = "title"

        const val abstractTag = "abstract"

        const val urlTag = "url"

        const val bylineTag = "byline"

        const val publishedDateTag = "published_date"

        const val multimediaTag = "multimedia"

        /* MediaEntity Response Tags */

        const val formatTag = "format"

        const val heightTag = "height"

        const val widthTag = "width"

        const val typeTag = "type"

        const val subTypeTag = "subtype"

        const val captionTag = "caption"

        const val copyRightTag = "copyright"
    }
}