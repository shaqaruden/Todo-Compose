package ca.on.listech.todo_compose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.on.listech.todo_compose.data.models.TodoTask
import ca.on.listech.todo_compose.data.repositories.TodoRepository
import ca.on.listech.todo_compose.util.RequestState
import ca.on.listech.todo_compose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<TodoTask>>> = _allTasks

    fun getAllTasks() {
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllTasks.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTasks.value = RequestState.Error(e)
        }
    }

    private val _task: MutableStateFlow<TodoTask?> = MutableStateFlow((null))
    val task: StateFlow<TodoTask?> = _task

    fun getTask(taskID: Int) {
        viewModelScope.launch {
            repository.getTask(taskID).collect {
                _task.value = it
            }
        }
    }
}