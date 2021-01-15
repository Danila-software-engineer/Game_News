package orders.appup_kw.gamenews.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import orders.appup_kw.gamenews.network.MainNetwork
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class NetworkWithoutAuthModule {

    @WithoutAuthInterceptor
    @Singleton
    @Provides
    fun provideBlogService(): MainNetwork =
        Retrofit.Builder()
                .baseUrl("https://news-new-server.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MainNetwork::class.java)

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WithoutAuthInterceptor







