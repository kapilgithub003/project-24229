package com.assignment.androidapp.di

import android.content.Context
import androidx.room.Room
import com.assignment.androidapp.data.local.dao.MangaDao
import com.assignment.androidapp.data.local.db.AppDatabase
import com.assignment.androidapp.data.local.dao.UserDao
import com.assignment.androidapp.data.local.dao.UserSessionDao
import com.assignment.androidapp.data.repository.UserRepositoryImpl
import com.assignment.androidapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "db1"
        ).build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    fun provideUserSessionDao(db: AppDatabase): UserSessionDao = db.userSessionDao()

    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository =
        UserRepositoryImpl(userDao)

    @Provides
    fun provideMangaDao(database: AppDatabase): MangaDao = database.mangaDao()
}
