package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            if(etTodoTitle.text.toString().isNotEmpty()){
                val todo = Todo(etTodoTitle.text.toString())
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        btnDeleteDoneTodo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}