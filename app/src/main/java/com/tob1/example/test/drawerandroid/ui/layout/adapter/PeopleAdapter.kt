package com.tob1.example.test.drawerandroid.ui.layout.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tob1.example.test.drawerandroid.databinding.ItemPeopleBinding
import com.tob1.example.test.drawerandroid.ui.models.PeopleUI
import timber.log.Timber

class PeopleAdapter : ListAdapter<PeopleUI, PeopleAdapter.PeopleViewHolder>(PeopleDiffUtil) {

    private lateinit var binding : ItemPeopleBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        binding = ItemPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }



    inner class PeopleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(peopleUI: PeopleUI){
            // navigate ...
            binding.tvFistName.text = peopleUI.firstName
            binding.tvLastName.text = peopleUI.lastName
            Timber.d("navigate to item People")
        }
    }

        /**
         * Cai object nay de phan biet giua 2 item ( Co gang lam sao de co su khac biet...
         * .. commit sau se fix loi 2 item giong nhau)
         * */
    object PeopleDiffUtil : DiffUtil.ItemCallback<PeopleUI>(){
        override fun areItemsTheSame(oldItem: PeopleUI, newItem: PeopleUI): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PeopleUI, newItem: PeopleUI): Boolean {
            return oldItem.lastName == newItem.lastName
        }
    }
}