package com.apps.a7pl4y3r.yournotes.independent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.apps.a7pl4y3r.yournotes.R
import com.apps.a7pl4y3r.yournotes.databases.FolderDatabase
import kotlinx.android.synthetic.main.activity_add_folder.*

class AddFolder : AppCompatActivity() {

    private fun folderNameIsValid(): Boolean{

        if(etFolderName.text.isEmpty())
            return false

        return true

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_folder)

        fabCreateFolder.setOnClickListener({

            if (folderNameIsValid()) {

                val folderDatabase = FolderDatabase(this)
                if(folderDatabase.createFolder(etFolderName.text.toString().trim(),0))
                    Toast.makeText(this, "Folder created successfully!", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Failed to create folder!", Toast.LENGTH_SHORT).show()

            } else Toast.makeText(this, "Folder name is invalid", Toast.LENGTH_SHORT).show()

        })

    }
}
