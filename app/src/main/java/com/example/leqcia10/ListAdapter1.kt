package com.example.leqcia10

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import com.example.leqcia10.databinding.RecyclerviewItemBinding

class ListAdapter1(private val data: MutableList<User>,
                   private val onItemRemoved: (Int) -> Unit,
                   private val onItemUpdated: (Int,User) -> Unit
): RecyclerView.Adapter<ListAdapter1.ListViewHolder>() {

    inner class ListViewHolder(private val binding: RecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(user: User) {
            binding.fname.text = user.fName
            binding.lname.text = user.lName
            binding.mail.text = user.email

            binding.btnRmv.setOnClickListener{
                val currentPosition = bindingAdapterPosition
                if (currentPosition != RecyclerView.NO_POSITION) {
                    onItemRemoved(currentPosition)
                }
            }

            binding.btnUpdt.setOnClickListener{
                val currentPosition = bindingAdapterPosition
                if (currentPosition != RecyclerView.NO_POSITION) {
                    onItemUpdated(currentPosition,user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        Log.d("ItemRecycler","Item was added!!!")
        return ListViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position])
    }

}