package com.uilover.project2092.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.uilover.project2092.Model.ItemsModel
import com.uilover.project2092.Repository.MainRepository

class MainViewModel:ViewModel() {
    private val repository=MainRepository()

    fun loadFiltered(id:Int):LiveData<MutableList<ItemsModel>>{
        return repository.loadFiltered(id)
    }
}