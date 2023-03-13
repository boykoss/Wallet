package com.example.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet.ViewModel.NoteViewModel
import com.example.wallet.adapters.NoteClickDeleteInterface
import com.example.wallet.adapters.NoteClickInterface
import com.example.wallet.adapters.NoteRVAdapter
import com.example.wallet.databinding.ActivityMyWalletsBinding
import com.example.wallet.room.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyWallets_Activity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {
    lateinit var binding: ActivityMyWalletsBinding
    lateinit var viewModel: NoteViewModel
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWalletsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // on below line we are initializing
        // all our variables.
        notesRV = binding.notesRV
        addFAB = binding.floatButtonAddwallet
        // on below line we are setting layout
        // manager to our recycler view.
        notesRV.layoutManager = LinearLayoutManager(this)
        // on below line we are initializing our adapter class.
        val noteRVAdapter = NoteRVAdapter(this, this, this)
        // on below line we are setting
        // adapter to our recycler view.
        notesRV.adapter = noteRVAdapter


        //VIEWMODEL
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        // on below line we are calling all notes method
        // from our view modal class to observer the changes on list.
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                noteRVAdapter.updateList(it)
            }
        })


        addFAB.setOnClickListener{
            // adding a click listener for fab button
            // and opening a new intent to add a new note.
            val intent = Intent(this, AddOrEditWallet_Activity::class.java)
            startActivity(intent)
            //this.finish()

        }
}

    override fun onNoteClick(note: Note) {
        // opening a new intent and passing a data to it.
        val intent = Intent(this, AddOrEditWallet_Activity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)

    }

    override fun onDeleteIconClick(note: Note) {
        // in on note click method we are calling delete
        // method from our view modal to delete our not.
        viewModel.deleteNote(note)
        // displaying a toast message
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}