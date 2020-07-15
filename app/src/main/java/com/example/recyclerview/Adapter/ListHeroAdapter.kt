package com.example.recyclerview.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerview.Model.HeroModel
import com.example.recyclerview.R

class ListHeroAdapter(val listHero: ArrayList<HeroModel>): RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHeroAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false);
        return ListViewHolder(view);
    }

    override fun onBindViewHolder(holder: ListHeroAdapter.ListViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(listHero[position].photo)
            .apply(RequestOptions().override(55,55))
            .into(holder.ivPhoto)

        holder.tvName.text = listHero[position].name
        holder.tvDescription.text = listHero[position].detail

        holder.itemView.setOnClickListener{onItemClickCallback
            .onItemClicked(listHero[holder.adapterPosition])}
    }


    override fun getItemCount(): Int {
        return listHero.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name);
        var tvDescription: TextView = itemView.findViewById(R.id.tv_item_detail);
        var ivPhoto : ImageView = itemView.findViewById(R.id.iv_item_photo);
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: HeroModel)
    }
}