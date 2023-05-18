package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val viewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val dbRef = Firebase.database.reference

            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val allDBEntries = dataSnapshot.children
                    for (allEventEntries in allDBEntries) {
                        for (singleEventEntry in allEventEntries.children) {


                            val nameTest = singleEventEntry.child("name").value
                            val giftsTest = singleEventEntry.child("giftIdeas").value

                            if(nameTest != null && giftsTest != null){
                                val name = singleEventEntry.child("name").value.toString()
                                var gifts = singleEventEntry.child("giftIdeas").value.toString()
                                gifts = gifts.substring(1, gifts.length - 1)
                                val giftList: MutableList<String> = mutableListOf()
                                var i = 0
                                while(i < gifts.length){
                                    if(gifts.get(i) == ',' ){
                                        giftList.add(gifts.substring(0, i))
                                        gifts = gifts.substring(i + 1)
                                        i = 0
                                    }
                                    i++
                                }
                                giftList.add(gifts)
                                val tempPerson = Person(name, giftList)
                                viewModel.personList.add(tempPerson)
                            }

                            val titleTest = singleEventEntry.child("title").value
                            val dateTest = singleEventEntry.child("date").value
                            val peopleTest = singleEventEntry.child("people").value

                            if(titleTest != null && dateTest != null && peopleTest != null){
                                val title = singleEventEntry.child("title").value.toString()
                                val date = singleEventEntry.child("date").value.toString()
                                var people = singleEventEntry.child("people").value.toString()
                                people = people.substring(1, people.length - 1)
                                val peopleList : MutableList<Int> = mutableListOf()
                                var j = 0
                                while(j < people.length) {
                                    if (people.get(j) == ',') {
                                        peopleList.add(people.substring(0, j).toInt())
                                        people = people.substring(j + 1)
                                        j = 0
                                    }
                                    j++
                                }
                                peopleList.add(people.toInt())
                                val tempEvent = Event(title, date, peopleList)
                                viewModel.eventList.add(tempEvent)
                            }

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })

    }
}