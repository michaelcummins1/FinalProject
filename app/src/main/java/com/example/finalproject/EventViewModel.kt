package com.example.finalproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DateFormat
import java.util.*

class EventViewModel : ViewModel() {

    val eventList: MutableList<Event> = mutableListOf()

    val personList: MutableList<Person> = mutableListOf()

    val selectedPerson: MutableLiveData<Person?> = MutableLiveData()

    var selectedDate: MutableLiveData<Date?> = MutableLiveData()


    fun createNewPerson(name: String, giftIdeas: String){
        var gifts = giftIdeas
        val giftList: MutableList<String> = mutableListOf()
        var i = 0
        while(i < giftIdeas.length){
            if(giftIdeas.get(i) == ',' ){
                giftList.add(gifts.substring(0, i))
                gifts = gifts.substring(i + 1)
            }
            i++
        }
        giftList.add(giftIdeas)
        val temp = Person(name, giftList)
        personList.add(temp)
    }

    fun createNewEvent(title : String, date: String, people: List<Person>){
        val event = Event(title, date, people)
        eventList.add(event)
    }

    fun clearEvents(){
        eventList.clear()
    }

    fun clearPeople(){
        personList.clear()
        selectedPerson.value = null
    }

    fun pickedPerson(person: Person){
        selectedPerson.value = person
    }

    fun selectedDate(date: Date){
        selectedDate.value = date
    }

}