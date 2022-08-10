package com.eray.dictionarykotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class DictionaryAdapter(var mcontex:Context,var incomingwordArray:List<Word>) :RecyclerView.Adapter<DictionaryAdapter.holderCardView>() {



    inner class holderCardView(view:View):RecyclerView.ViewHolder(view){
        var cardView:CardView
        var textViewEn:TextView
        var textViewTr:TextView

        init {
            cardView=view.findViewById(R.id.cardView)
            textViewEn=view.findViewById(R.id.textViewEn)
            textViewTr=view.findViewById(R.id.textViewTr)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderCardView {
        var desing=LayoutInflater.from(mcontex).inflate(R.layout.rv_card_desing,parent,false)

        return holderCardView(desing)
    }

    override fun onBindViewHolder(holder: holderCardView, position: Int) {
        var words=incomingwordArray[position]

        holder.textViewTr.text=words.words_tr
        holder.textViewEn.text=words.words_en

        holder.cardView.setOnClickListener(){
            var intent =Intent(mcontex,DetailActivity::class.java)

            intent.putExtra("key",words)
            mcontex.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return incomingwordArray.size
    }
}