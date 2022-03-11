package ca.on.listech.todo_compose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ca.on.listech.todo_compose.data.models.TodoTask

@Database(entities = [TodoTask::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDao
}