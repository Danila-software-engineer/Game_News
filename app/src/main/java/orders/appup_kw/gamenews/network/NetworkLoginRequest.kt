package orders.appup_kw.gamenews.network

import com.google.gson.GsonBuilder
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BasicAuthInterceptor(username: String, password: String): Interceptor {
    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {



        var request = chain.request()

        request = request.newBuilder().header("Authorization", credentials).build()

        return chain.proceed(request)
    }
}

class BasicAuthClient<T>(login: String, password: String) {


    var inter = HttpLoggingInterceptor()

    private val client =  OkHttpClient.Builder()
        .addInterceptor(inter.setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(BasicAuthInterceptor(login, password))
        .build()

    val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://news-new-server.herokuapp.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun create(service: Class<T>): T {
        return retrofit.create(service)
    }
}