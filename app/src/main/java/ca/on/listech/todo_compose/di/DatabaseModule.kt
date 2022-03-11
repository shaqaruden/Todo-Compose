package ca.on.listech.todo_compose.di

import android.content.Context
import androidx.room.Room
import ca.on.listech.todo_compose.data.TodoDatabase
import ca.on.listech.todo_compose.util.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    )  = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        DB_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(db: TodoDatabase) = db.todoDao()
}