package com.apps.a7pl4y3r.yournotes.helpers

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.apps.a7pl4y3r.yournotes.R
import com.apps.a7pl4y3r.yournotes.ViewNote
import kotlin.collections.ArrayList

class NoteCardAdapter(
        val context: Context,
        val names: ArrayList<String>,
        val notes: ArrayList<String>,
        val dates: ArrayList<String>
        ) : BaseAdapter() {

    override fun getView(i: Int, p1: View?, p2: ViewGroup?): View {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.card_note, p2, false)
        val title = view.findViewById<Button>(R.id.btViewNote)
        val date = view.findViewById<TextView>(R.id.tvDate)

        title.text = names[i]
        date.text = dates[i]

        title.setOnClickListener({

            val intent = Intent(context, ViewNote::class.java)
              intent.putExtra("NAME", names[i])
                    .putExtra("NOTE", notes[i])
                    .putExtra("DATE", dates[i])

            startActivity(context, intent, null)

        })

        return view
    }

    override fun getItem(i: Int): Any {
        return names[i]
    }

    override fun getItemId(i: Int): Long {
    return i.toLong()
    }

    override fun getCount(): Int {
        return names.size
    }
}