/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package orders.appup_kw.gamenews.repository


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import orders.appup_kw.gamenews.di.network.AuthInterceptor
import orders.appup_kw.gamenews.di.network.WithoutAuthInterceptor
import orders.appup_kw.gamenews.network.MainNetwork
import orders.appup_kw.gamenews.network.POJONews
import java.io.InputStream
import javax.inject.Inject

// GitHub page API is 1 based: https://developer.github.com/v3/#pagination
private const val GITHUB_STARTING_PAGE_INDEX = 1

/**
 * Repository class that works with local and remote data sources.
 */
@ExperimentalCoroutinesApi
class NewsFeedRepository @Inject constructor(
    @WithoutAuthInterceptor private var mainWithoutAuthNetwork: MainNetwork,
    @AuthInterceptor private var mainAuthNetwork: MainNetwork
) {

    //fun getSearchResultStream(): Flow<PagingData<POJONews>> {
    //    return Pager(
    //            config = PagingConfig(
    //                    pageSize = NETWORK_PAGE_SIZE,
    //                    enablePlaceholders = false
    //            ),
    //            pagingSourceFactory = {NewsFeedPagingSource(mainWithoutAuthNetwork)}
    //    ).flow
    //}

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    suspend fun createNews(inputStream: InputStream, post: String) :Int{

        val part = MultipartBody.Part.createFormData(
                "file", "myPic", RequestBody.create(
                MediaType.parse("multipart/form-data"),
                inputStream.readBytes()
        )
        )

        val map: HashMap<String, RequestBody> = HashMap()
        map["post"] = RequestBody.create(MultipartBody.FORM, post)

        return mainAuthNetwork.create(
                map,
                part
        ).code()
    }

    suspend fun readNews(id: Int): POJONews {
        return mainWithoutAuthNetwork.getNewsID(id)
    }

}
