package orders.appup_kw.newsfeed20.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import orders.appup_kw.gamenews.network.POJONews
import retrofit2.Response
import retrofit2.http.*

interface MainNetwork {
    @GET("read/{id}")
    suspend fun getNewsID(@Path("id") id: Int?): POJONews

    @GET("read/{id1}/{id2}")
    suspend fun getNewsID(@Path("id1") id1: Int?, @Path("id2") id2: Int?): List<POJONews>

    @GET("login")
    suspend fun login(): Response<ResponseBody>

    @Multipart
    @POST("create")
    suspend fun create(@PartMap partMap: HashMap<String, RequestBody>, @Part file: MultipartBody.Part?)
            : Response<ResponseBody>

}