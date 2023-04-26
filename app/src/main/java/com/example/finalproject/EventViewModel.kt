package com.example.finalproject

import androidx.lifecycle.ViewModel
import java.text.DateFormat
import java.util.*

class EventViewModel : ViewModel() {

    val eventList: MutableList<Event> = mutableListOf()

    val personList: MutableList<Person> = mutableListOf()


    fun createNewPerson(name: String, giftIdeas: String){
        var gifts = giftIdeas
        val giftList: MutableList<String> = mutableListOf()
        var i = 0
        while(giftIdeas.length > 0){
            if(giftIdeas.get(i) == ',' ){
                giftList.add(gifts.substring(0, i))
                gifts = gifts.substring(i + 1)
            }
            i++
        }
        val temp = Person(name, giftList)
        personList.add(temp)
    }

    fun createNewEvent(title : String, date: Long, people: List<Person>){
        val event = Event(title, date.toDateString(), people)
        eventList.add(event)
    }

    fun Long.toDateString(dateFormat: Int =  DateFormat.MEDIUM): String {
        val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
        return df.format(this)
    }

}