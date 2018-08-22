package com.apps.a7pl4y3r.yournotes.databases

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val TableName = "Folders"
private const val Col0 = "ID"
private const val Col1 = "Name"
private const val Col2 = "Color"

class FolderDatabase(context: Context) : SQLiteOpenHelper(context, "FolderDataBase",null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TableName ($Col0 INTEGER PRIMARY KEY AUTOINCREMENT,$Col1 TEXT,$Col2 TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TableName")
        onCreate(db)
    }

    fun createFolder(name: String, color: Int): Boolean{

        val contentValues = ContentValues()
        contentValues.put(Col1, name)
        contentValues.put(Col2, color)
        return this.writableDatabase.insert(TableName, null, contentValues) != -1L

    }

    fun readFolders(): Cursor{
        return this.writableDatabase.rawQuery("select * from $TableName", null)
    }

}