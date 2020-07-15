package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.Adapter.CardViewHeroAdapter
import com.example.recyclerview.Adapter.GridHeroAdapter
import com.example.recyclerview.Adapter.ListHeroAdapter
import com.example.recyclerview.Model.HeroModel

class MainActivity : AppCompatActivity() {

    private lateinit var rvHeroes: RecyclerView
    private var list: ArrayList<HeroModel> = arrayListOf()
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(HeroesData.listData)
        showRecyclerView()
    }

    private fun setActionBarTitle(title: String){
        if(supportActionBar!=null){
            (supportActionBar as ActionBar).title = title
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun showSelectedData(hero: HeroModel){
        Toast.makeText(this,"Choosen"+hero.name,Toast.LENGTH_SHORT).show()
    }

    private fun showRecyclerView() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: HeroModel) {
                showSelectedData(data)
                val intent = Intent(this@MainActivity,DetailHero::class.java)
                intent.putExtra(DetailHero.EXTRA_DATA,data)
                startActivity(intent)
            }
        })
    }

    private fun gridRecyclerView(){
        rvHeroes.layoutManager = GridLayoutManager(this,2)
        val gridHeroAdapter = GridHeroAdapter(list)
        rvHeroes.adapter = gridHeroAdapter

        gridHeroAdapter.setOnItemClickCallback(object : GridHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(hero: HeroModel) {
                showSelectedData(hero)
                val intent = Intent(this@MainActivity,DetailHero::class.java)
                intent.putExtra(DetailHero.EXTRA_DATA,hero)
                startActivity(intent)
            }
        })
    }

    private fun cardRecyclerView() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val cardHeroAdapter = CardViewHeroAdapter(list)
        rvHeroes.adapter = cardHeroAdapter
        cardHeroAdapter.setDetailOnItemClickCallback(object : CardViewHeroAdapter.DetailOnItemClickCallback{
            override fun onItemClicked(hero: HeroModel) {
                showSelectedData(hero)
                val intent = Intent(this@MainActivity, DetailHero:: class.java)
                intent.putExtra(DetailHero.EXTRA_DATA,hero)
                startActivity(intent)
            }
        })

        cardHeroAdapter.setShareOnItemClickCallback(object : CardViewHeroAdapter.ShareOnItemClickCallback{
            override fun onItemClicked1(hero: HeroModel) {
                showSelectedData(hero)
                val sendIntent: Intent = Intent().apply{
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,hero.name)
                    putExtra(Intent.EXTRA_TEXT,hero.detail)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent,null)
                startActivity(shareIntent)

                val shareIntentPhoto : Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM,hero.photo)
                    type = "image/jpeg"
                }
                startActivity(shareIntentPhoto)


            }
        })



    }

    private fun setMode(selectMode: Int){
        when(selectMode){
            R.id.action_list->{
                title = "Mode List"
                showRecyclerView()
            }

            R.id.action_grid->{
                title = "Mode Grid"
                gridRecyclerView()
            }

            R.id.action_cardView->{
                title = "Mode Card"
                cardRecyclerView()
            }
        }
        setActionBarTitle(title)
    }
}
