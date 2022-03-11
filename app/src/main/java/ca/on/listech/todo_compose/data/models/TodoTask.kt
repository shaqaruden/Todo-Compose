package ca.on.listech.todo_compose.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.on.listech.todo_compose.util.Constants.DB_Table

@Entity(tableName = DB_Table)
data class TodoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)
