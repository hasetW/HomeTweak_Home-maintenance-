package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Category
import com.example.myapplication.data.remote.CategoryService
import retrofit2.Call

class CategoryRepository(private val categoryService: CategoryService) {

    fun getCategories(): Call<List<Category>> {
        return categoryService.getCategories()
    }

    fun getCategoryById(categoryId: Int): Call<Category> {
        return categoryService.getCategoryById(categoryId)
    }
}
