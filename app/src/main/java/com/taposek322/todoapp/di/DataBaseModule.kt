package com.taposek322.todoapp.di

import android.app.Application
import androidx.room.Room
import com.taposek322.todoapp.data.database.ToDoDatabase
import com.taposek322.todoapp.data.database.dataBaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(application: Application):ToDoDatabase {
       return Room.databaseBuilder(application, ToDoDatabase::class.java, dataBaseName).build()
    }

}