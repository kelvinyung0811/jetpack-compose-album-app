package com.example.keysoc_album_app.di

import android.content.Context
import androidx.room.Room
import com.example.keysoc_album_app.data.api.local.AlbumDatabase
import com.example.keysoc_album_app.util.Constants.ALBUM_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AlbumDatabase {
        return Room.databaseBuilder(
            context,
            AlbumDatabase::class.java,
            ALBUM_DATABASE
        ).build()
    }
}