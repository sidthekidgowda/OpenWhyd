package com.openwhyd.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
object NetworkModule {
    @JvmStatic
    @Provides
    @Reusable
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @JvmStatic
    @Provides
    @Reusable
    fun providesOKHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addNetworkInterceptor(loggingInterceptor).build()
    }


//    @JvmStatic
//    @Provides
//    @Reusable
//    fun providesRetrofitService(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder().baseUrl("https://www.anapioficeandfire.com/api/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
//            .client(okHttpClient)
//            .build()
//    }

//    @JvmStatic
//    @Provides
//    @Reusable
//    fun providesGameOfThronesService(retrofitService: Retrofit): GameOfThronesService {
//        return retrofitService.create(GameOfThronesService::class.java)
//    }
}