package com.eray.dictionarykotlin



class Worddao {



    fun allWord(db:DataBaseHelper):ArrayList<Word>{
        val allwords=ArrayList<Word>()
        val dbx=db.writableDatabase
        val cursor=dbx.rawQuery("SELECT * FROM kelimeler",null)

        while (cursor.moveToNext()){
            val wrd=Word(cursor.getInt(cursor.getColumnIndex("kelime_id"))
                ,cursor.getString(cursor.getColumnIndex("ingilizce"))
                ,cursor.getString(cursor.getColumnIndex("turkce")))
            allwords.add(wrd)
        }
        return allwords
    }

    fun searchWords(db:DataBaseHelper,searchWord:String):ArrayList<Word>{
        val allwords=ArrayList<Word>()
        val dbx=db.writableDatabase
        val cursor=dbx.rawQuery("SELECT * FROM kelimeler WHERE ingilizce like '%$searchWord%'",null)

        while (cursor.moveToNext()){
            val wrd=Word(cursor.getInt(cursor.getColumnIndex("kelime_id"))
                ,cursor.getString(cursor.getColumnIndex("ingilizce"))
                ,cursor.getString(cursor.getColumnIndex("turkce")))
            allwords.add(wrd)
        }
        return allwords
    }




}