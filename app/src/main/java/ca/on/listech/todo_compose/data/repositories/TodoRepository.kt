package ca.on.listech.todo_compose.data.repositories

import ca.on.listech.todo_compose.data.ToDoDao
import ca.on.listech.todo_compose.data.models.TodoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TodoRepository @Inject constructor(private val todoDao: ToDoDao) {
    val getAllTasks: Flow <List<TodoTask>> = todoDao.getAllTasks()
    val sortByLowPriority: Flow <List<TodoTask>> = todoDao.sortByLowPriority()
    val sortByHighPriority: Flow <List<TodoTask>> = todoDao.sortByHighPriority()

    fun getSelectedTask(taskID: Int): Flow<TodoTask> {
        return todoDao.getSelectedTask(taskID)
    }

    suspend fun addTask(todoTask: TodoTask) {
        todoDao.addTask(todoTask)
    }

    suspend fun updateTask(todoTask: TodoTask) {
        todoDao.updateTask(todoTask)
    }

    suspend fun deleteTask(todoTask: TodoTask) {
        todoDao.deleteTask(todoTask)
    }

    suspend fun deleteAllTasks() {
        todoDao.deleteAllTasks()
    }

    fun searchDatabase(searchQuery: String): Flow<List<TodoTask>> {
        return todoDao.searchDatabase(searchQuery)
    }
}