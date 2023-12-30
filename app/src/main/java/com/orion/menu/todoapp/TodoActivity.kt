package com.orion.menu.todoapp

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.orion.menu.R
import com.orion.menu.todoapp.TaskCategory.*

class TodoActivity : AppCompatActivity() {

    private val categories = listOf(
        Business,
        Other,
        Personal
    )

    private val tasks = mutableListOf(
        Task("PruebaBussines", Business),
        Task("PruebaPersonal", Personal),
        Task("PruebaOtra", Other)
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TaskAdapter

    private lateinit var fabAddTask:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        initComponent()
        initUi()
        initListener()
    }
    private fun initListener() {
        fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val  rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {

            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()) {
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.todo_dialog_category_bussiness) -> Business
                    getString(R.string.todo_dialog_category_personal) -> Personal
                    else -> Other
                }

                tasks.add(Task(currentTask, currentCategory))
                updateTasks()
                dialog.hide()
            }
        }
        dialog.show()
    }

    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)

        fabAddTask = findViewById(R.id.fabAddTask)
    }
    private fun initUi() {
        categoriesAdapter = CategoriesAdapter(categories){ position-> updateCategories(position)}
        rvCategories.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TaskAdapter(tasks) {position ->onItemselected(position)}
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }

    private fun onItemselected(position:Int) {
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }

    private fun updateCategories( position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

    private fun updateTasks() {
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTask = tasks.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tasks = newTask
        tasksAdapter.notifyDataSetChanged()
    }
}