package com.apps.a7pl4y3r.yournotes.independent

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.apps.a7pl4y3r.yournotes.R
import com.apps.a7pl4y3r.yournotes.helpers.CardAdapter
import com.apps.a7pl4y3r.yournotes.databases.FolderDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    private fun getTitles(res: Cursor): ArrayList<String>{

        val arrayList = ArrayList<String>()
        res.moveToFirst()
        do {

            arrayList.add(res.getString(1))

        }while (res.moveToNext())

        return arrayList
    }

    private fun setAdapter(){

        val folderDatabase = FolderDatabase(this)
        val res = folderDatabase.readFolders()

        if(res.count == 0){

            textView.setTextColor(ContextCompat.getColor(this, R.color.button_material_dark))
            textView.text = "No folders created!"
            linear.addView(textView)

        } else {

            val cardAdapter = CardAdapter(this, getTitles(res))
            listView.adapter = cardAdapter

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = TextView(this)

        fab.setOnClickListener({startActivity(Intent(this, AddFolder::class.java))})
    }

    override fun onResume() {
        super.onResume()
        linear.removeView(textView)
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        setAdapter()
    }
}
