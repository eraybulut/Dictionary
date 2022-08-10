package com.eray.dictionarykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eray.dictionarykotlin.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        var gelenKey=intent.getSerializableExtra("key") as Word

        var gelenen=gelenKey.words_en
        var gelentr=gelenKey.words_tr

        binding.textViewDetailEn.text=gelenen
        binding.textViewDetailTr.text=gelentr


    }
}