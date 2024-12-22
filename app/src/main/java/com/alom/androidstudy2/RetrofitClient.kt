package com.alom.androidstudy2
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory


object RetrofitClient {
    val apiService: RetrofitInterface
        get() = instance.create(RetrofitInterface::class.java)

    private val instance: Retrofit
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()


            val apiKeyInterceptor = Interceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .addHeader("getApiKey", BuildConfig.API_KEY)
                    .build()
                chain.proceed(newRequest)
            }


            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://goaplrynweyxovekoezl.supabase.co/rest/v1/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }
}
