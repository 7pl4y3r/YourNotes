package com.apps.a7pl4y3r.yournotes.helpers

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.apps.a7pl4y3r.yournotes.independent.DisplayNotes
import com.apps.a7pl4y3r.yournotes.R
import com.apps.a7pl4y3r.yournotes.databases.FolderDatabase

class CardAdapter(
        val context: Context,
        val res: ArrayList<String>) : BaseAdapter() {

    override fun getView(i: Int, view1: View?, p2: ViewGroup?): View? {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.card_folder, p2, false)
        val title = view.findViewById<TextView>(R.id.btFolderTitle)

        title.text = res[i]
        title.setOnClickListener({
            startActivity(
                    context,
                    Intent(context, DisplayNotes::class.java).putExtra("PARENT", res[i]),
                    null)
        })

        return view

    }

    override fun getItem(i: Int): Any {
        return res[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
         return res.size
    }
}