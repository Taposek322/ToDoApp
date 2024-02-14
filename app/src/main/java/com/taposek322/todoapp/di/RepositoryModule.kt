package com.taposek322.todoapp.di

import com.taposek322.todoapp.data.repository.RepositoryImpl
import com.taposek322.todoapp.domain.repository.ToDoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(repositoryImpl: RepositoryImpl):ToDoRepository
}