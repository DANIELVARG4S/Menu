package com.orion.menu.superheroapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.menu.R

class   SuperHeroAdapter(
    var superheroList:List<SuperheroItemResponse> = emptyList() ,
    private val onItemSelected:(String) -> Unit
):
    RecyclerView.Adapter<SuperHeroViewHolder>()
 {
        @SuppressLint("NotifyDataSetChanged")
        fun updateList(list: List<SuperheroItemResponse>){
            superheroList = list
            notifyDataSetChanged()
        }
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
         return SuperHeroViewHolder(
             LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent,false)
         )
     }


     override fun onBindViewHolder(viewholder: SuperHeroViewHolder, position: Int) {
         viewholder.bind(superheroList[position],onItemSelected)
//        val iten = superheroList[position]
//         viewholder.bind(iten)
     }

     override fun getItemCount() = superheroList.size
 }