package com.assignment.androidapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.androidapp.data.local.dao.MangaDao
import com.assignment.androidapp.domain.model.User
import com.assignment.androidapp.data.local.dao.UserDao
import com.assignment.androidapp.domain.model.UserSession
import com.assignment.androidapp.data.local.dao.UserSessionDao
import com.assignment.androidapp.domain.model.Manga

@Database(entities = [User::class,  UserSession::class, Manga::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userSessionDao(): UserSessionDao
    abstract fun mangaDao(): MangaDao
}
