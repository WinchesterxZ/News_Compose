package com.example.newscompose.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [NewsArticle::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao
    companion object{
        @Volatile
        private var daoInstance:NewsDao ?= null
        private fun buildDatabase(context: Context):NewsDatabase{
            return Room.databaseBuilder(
                context = context.applicationContext,
                name = "news_database",
                klass = NewsDatabase::class.java
            ).fallbackToDestructiveMigration().build()
        }

        @OptIn(InternalCoroutinesApi::class)
         fun getDaoInstance(context: Context) : NewsDao{
            synchronized(this){
                if(daoInstance == null) {
                    daoInstance=buildDatabase(context).newsDao
                }
                return daoInstance as NewsDao
            }
        }
    }

}