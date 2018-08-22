package com.apps.a7pl4y3r.yournotes.independent

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.apps.a7pl4y3r.yournotes.R
import com.apps.a7pl4y3r.yournotes.databases.NoteDatabase
import com.apps.a7pl4y3r.yournotes.helpers.NoteCardAdapter
import kotlinx.android.synthetic.main.activity_display_notes.*

class DisplayNotes : AppCompatActivity() {

    private lateinit var textView: TextView
    private val arrayListNames = ArrayList<String>()
    private val arrayListNotes = ArrayList<String>()
    private val arrayListDates = ArrayList<String>()

    private fun getNoteNames(res: Cursor){

        res.moveToFirst()
        do {

            arrayListNames.add(res.getString(1))
            arrayListNotes.add(res.getString(2))
            arrayListDates.add(res.getString(3))

        }while (res.moveToNext())

    }

    private fun setAdapter(){

        val noteDatabase = NoteDatabase(this, intent.getStringExtra("PARENT"), intent.getStringExtra("PARENT"))
        val res = noteDatabase.readNotes()

        if(res.count == 0){

            textView.text = "You have no notes here!"
            textView.setTextColor(ContextCompat.getColor(this, R.color.button_material_dark))
            linearNotes.addView(textView)

        } else {

            getNoteNames(res)
            val noteCardAdapter = NoteCardAdapter(this, arrayListNames, arrayListNotes, arrayListDates)
            listViewNotes.adapter = noteCardAdapter

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_notes)
        textView = TextView(this)

        fabCreateNote.setOnClickListener({
            startActivity(Intent(this,
                    AddNote::class.java).putExtra("PARENT",intent.getStringExtra("PARENT")))})
    }

    override fun onResume() {
        super.onResume()
        linearNotes.removeView(textView)
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        setAdapter()
    }

}
