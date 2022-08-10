package com.eray.dictionarykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.eray.dictionarykotlin.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {
    private lateinit var binding:ActivityMainBinding
    private lateinit var wordArrayList:ArrayList<Word>
    private lateinit var dictonaryAdapter:DictionaryAdapter
    private val db=DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        copyDataBase()


        binding.toolbar.title="Dictionary"
        binding.toolbar.setLogo(R.drawable.ic_baseline_translate_24)
        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager=LinearLayoutManager(this)


        wordArrayList=Worddao().allWord(db)

        dictonaryAdapter= DictionaryAdapter(this,wordArrayList)

        binding.rv.adapter=dictonaryAdapter






    }

    fun copyDataBase(){

        val copyDb=DatabaseCopyHelper(this)
        try {
            copyDb.createDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }

        try {
            copyDb.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tollbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.action_search ->{
                val searchView=item.actionView as SearchView
                searchView.setOnQueryTextListener(this)

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    override fun onQueryTextSubmit(query: String): Boolean {
        search(query)
        Log.e("Enter ile ",query.toString())
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        search(newText)
        return true
    }

    fun search(searchWord:String){
        wordArrayList=Worddao().searchWords(db,searchWord)

        dictonaryAdapter= DictionaryAdapter(this,wordArrayList)

        binding.rv.adapter=dictonaryAdapter

    }
}