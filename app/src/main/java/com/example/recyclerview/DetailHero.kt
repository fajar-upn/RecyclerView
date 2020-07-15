package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.recyclerview.Model.HeroModel
import com.example.recyclerview.R

class DetailHero : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var hero:HeroModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hero)

        val ivPhoto: ImageView = findViewById(R.id.iv_detail_photo)
        val tvName: TextView = findViewById(R.id.tv_detail_name)
        val tvDescription: TextView = findViewById(R.id.tv_detail_description)

        val hero = intent.getParcelableExtra<HeroModel>(EXTRA_DATA)

        if(hero != null){
            Glide.with(this).load(hero.photo).into(ivPhoto)
            tvName.text = hero.name
            tvDescription.text = hero.detail
        }
    }
}