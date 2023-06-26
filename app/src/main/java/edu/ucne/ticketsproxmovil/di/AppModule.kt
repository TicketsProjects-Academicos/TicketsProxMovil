package edu.ucne.ticketsproxmovil.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.ticketsproxmovil.data.remote.ApiTickets
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMoshi(): Moshi{
        return  Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesTicketsProxApi(moshi: Moshi): ApiTickets {
        return Retrofit.Builder()
            .baseUrl("http://www.ticketsproxapia.somee.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiTickets::class.java)
    }
}