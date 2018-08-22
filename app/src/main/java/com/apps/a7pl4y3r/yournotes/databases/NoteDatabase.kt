package com.apps.a7pl4y3r.yournotes.databases

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val Col0 = "ID"
private const val Col1 = "NoteName"
private const val Col2 = "NoteContent"
private const val Col3 = "NoteDate"

class NoteDatabase(
        val context: Context,
        val parentName: String,
        val TableName: String
        ) : SQLiteOpenHelper(context, parentName, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TableName ($Col0 INTEGER PRIMARY KEY AUTOINCREMENT,$Col1 TEXT,$Col2 TEXT,$Col3 TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TableName")
        onCreate(db)
    }

    fun createNote(name: String, content: String, date: String): Boolean{

        val contentValues = ContentValues()
        contentValues.put(Col1, name)
        contentValues.put(Col2, content)
        contentValues.put(Col3, date)

        return this.writableDatabase.insert(TableName, null, contentValues) != -1L

    }

    fun readNotes(): Cursor{

        return this.writableDatabase.rawQuery("select * from $TableName", null)

    }

}