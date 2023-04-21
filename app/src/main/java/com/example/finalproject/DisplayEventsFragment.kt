package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DisplayEventsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Just to test if Firebase works will probably have to be used in ViewModel.kt
        lateinit var dbRef : DatabaseReference
        dbRef = Firebase.database.reference


        return inflater.inflate(R.layout.fragment_display_events, container, false)
    }
}