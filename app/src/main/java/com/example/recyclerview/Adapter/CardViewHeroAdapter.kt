package com.example.recyclerview.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerview.DetailHero
import com.example.recyclerview.Model.HeroModel
import com.example.recyclerview.R

class CardViewHeroAdapter(var listHero: ArrayList<HeroModel>): RecyclerView.Adapter<CardViewHeroAdapter.CardViewHolder>(){

    private lateinit var onItemClickCallback:DetailOnItemClickCallback
    private lateinit var onItemClickCallback1: ShareOnItemClickCallback

    fun setDetailOnItemClickCallback(onItemClickCallback: DetailOnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setShareOnItemClickCallback(onItemClickCallback1: ShareOnItemClickCallback){
        this.onItemClickCallback1 = onItemClickCallback1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewHeroAdapter.CardViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card_view_hero,parent,false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listHero.size
    }

    override fun onBindViewHolder(holder: CardViewHeroAdapter.CardViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(listHero[position].photo)
            .apply(RequestOptions().override(350,550))
            .into(holder.ivPhoto)

        holder.tvName.text = listHero[position].name
        holder.tvDesc.text = listHero[position].detail

        holder.itemView.setOnClickListener{Toast.makeText(holder.itemView.context,
            "Choosen"+listHero[holder.adapterPosition].name,Toast.LENGTH_SHORT).show()
            onItemClickCallback.onItemClicked(listHero[holder.adapterPosition])}
        holder.btnFavorite.setOnClickListener{
            Toast.makeText(holder.itemView.context,
            "favorite"+listHero[holder.adapterPosition].name,Toast.LENGTH_SHORT).show()}
        holder.btnShare.setOnClickListener{Toast.makeText(holder.itemView.context,
            "Share"+listHero[holder.adapterPosition].name,Toast.LENGTH_SHORT).show()
            onItemClickCallback1.onItemClicked1(listHero[holder.adapterPosition])}
    }

    class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var ivPhoto: ImageView = itemView.findViewById(R.id.iv_card_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_card_name)
        var tvDesc: TextView = itemView.findViewById(R.id.tv_card_description)
        var btnFavorite: Button = itemView.findViewById(R.id.btn_favorite)
        var btnShare: Button = itemView.findViewById(R.id.btn_share)

    }

    interface DetailOnItemClickCallback{
        fun onItemClicked(hero:HeroModel)
    }

    interface ShareOnItemClickCallback{
        fun onItemClicked1(hero:HeroModel)
    }

}