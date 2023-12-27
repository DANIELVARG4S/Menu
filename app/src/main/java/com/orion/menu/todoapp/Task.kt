package com.orion.menu.todoapp

data class Task(val namae:String, val category: TaskCategory, var isSelected:Boolean = false )