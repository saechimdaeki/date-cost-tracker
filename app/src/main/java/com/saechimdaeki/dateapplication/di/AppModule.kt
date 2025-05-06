package com.saechimdaeki.dateapplication.di

import android.content.Context
import androidx.room.Room
import com.saechimdaeki.dateapplication.data.db.CostDao
import com.saechimdaeki.dateapplication.data.db.CostDatabase
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
    fun provideCostDatabase(@ApplicationContext context: Context): CostDatabase {
        return Room.databaseBuilder(
            context,
            CostDatabase::class.java,
            "cost_database"
        ).build()
    }

    @Provides
    fun provideCostDao(database: CostDatabase): CostDao {
        return database.costDao()
    }
}