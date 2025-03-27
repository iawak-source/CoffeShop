package com.uilover.project2092.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.uilover.project2092.Model.ItemsModel

class MainRepository {
    private val firebaseDatabase=FirebaseDatabase.getInstance()

    fun loadFiltered(id:Int):LiveData<MutableList<ItemsModel>> {
        val ListData=MutableLiveData<MutableList<ItemsModel>>()
        val ref=firebaseDatabase.getReference("Items")
        val query:Query=ref.orderByChild("categoryId").equalTo(id.toString())

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ItemsModel>()
                for(childSnashot in snapshot.children){
                    val item=childSnashot.getValue(ItemsModel::class.java)
                    item?.let { lists.add(it) }
                }
                ListData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return ListData
    }
}