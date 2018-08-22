package com.apps.a7pl4y3r.yournotes.independent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.apps.a7pl4y3r.yournotes.R
import com.apps.a7pl4y3r.yournotes.databases.NoteDatabase
import kotlinx.android.synthetic.main.activity_add_note.*
import java.util.*

class AddNote : AppCompatActivity() {

    private fun dataIsValid(): Boolean{

        if(etNoteName.text.isEmpty() || etNote.text.isEmpty())
            return false

        return true

    }

    private fun month(monthId: Int): String{

        when(monthId){

            0 -> return "Jan"
            1 -> return "Feb"
            2 -> return "Mar"
            3 -> return "Apr"
            4 -> return "May"
            5 -> return "June"
            6 -> return "July"
            7 -> return "Aug"
            8 -> return "Sep"
            9 -> return "Oct"
            10 -> return "Nov"
            11 -> return "Dec"

        }

        return "LOLLOLLOL"
    }

    private fun currentDate(): String{

        val c = Calendar.getInstance()
        return "${month(c.get(Calendar.MONTH))} ${c.get(Calendar.DAY_OF_MONTH)} ${c.get(Calendar.YEAR)}"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        fabCreateNote.setOnClickListener({

            if(dataIsValid()){

                val noteDatabase = NoteDatabase(this, intent.getStringExtra("PARENT"),
                        intent.getStringExtra("PARENT"))

                if(noteDatabase.createNote(etNoteName.text.toString().trim(), etNote.text.toString().trim(), currentDate()))
                    Toast.makeText(this, "Note created!", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Failed to create note!", Toast.LENGTH_SHORT).show()


            } else Toast.makeText(this, "Data is not valid!", Toast.LENGTH_SHORT).show()


        })

    }
}
