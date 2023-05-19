package com.example.finalproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EventViewModel : ViewModel() {

    var eventList: MutableList<Event> = mutableListOf()

    val personList: MutableList<Person> = mutableListOf()

    val selectedPersonCreate: MutableLiveData<Int?> = MutableLiveData()

    var selectedPersonDisplay: Person = Person("", listOf())

    var selectedDate: MutableLiveData<String> = MutableLiveData()

    var selectedEvent: Event? = null

    val dbRef = Firebase.database.reference


    fun createNewPerson(name: String, giftIdeas: String) {
        var gifts = giftIdeas
        val giftList: MutableList<String> = mutableListOf()
        var i = 0
        while (i < gifts.length) {
            if (gifts.get(i) == ',') {
                giftList.add(gifts.substring(0, i))
                gifts = gifts.substring(i + 1)
                i = 0
            }
            i++
        }
        giftList.add(gifts)
        val temp = Person(name, giftList)
        personList.add(temp)
        dbRef.child("addedPeople").push().setValue(temp)
    }

    fun createNewEvent(title: String, date: String, people: List<Int>) {
        val event = Event(title, date, people)
        eventList.add(event)
        sortEvents()
        dbRef.child("addedEvents").push().setValue(event)
    }

    fun clearEvents() {
        eventList.clear()
        dbRef.child("addedEvents").removeValue()
    }

    fun clearPeople() {
        personList.clear()
        selectedPersonCreate.value = null
        dbRef.child("addedPeople").removeValue()
    }

    fun pickedPerson(person: Int) {
        selectedPersonCreate.value = person
    }

    fun sortEvents() {
        val newEventList: MutableList<Event> = mutableListOf()

        while (eventList.size > 0) {
            var smallestEvent = eventList[0]
            var i = 1
            while (i < eventList.size) {

                val possibleYear = smallestEvent.date.substring(smallestEvent.date.length - 4).toInt()
                val possibleDay = smallestEvent.date.substring(smallestEvent.date.indexOf("/") + 1, smallestEvent.date.lastIndexOf("/")).toInt()
                val possibleMonth = smallestEvent.date.substring(0, smallestEvent.date.indexOf("/")).toInt()

                val compareYear = eventList[i].date.substring(eventList[i].date.length - 4).toInt()
                val compareDay = eventList[i].date.substring(eventList[i].date.indexOf("/") + 1, eventList[i].date.lastIndexOf("/")).toInt()
                val compareMonth = eventList[i].date.substring(0, eventList[i].date.indexOf("/")).toInt()

                if (compareYear < possibleYear) {
                    smallestEvent = eventList[i]
                }
                else if (compareYear == possibleYear && compareMonth < possibleMonth) {
                    smallestEvent = eventList[i]
                }
                else if (compareYear == possibleYear && compareMonth == possibleMonth && compareDay < possibleDay) {
                    smallestEvent = eventList[i]
                }
                i++
            }
            newEventList.add(smallestEvent)
            eventList.remove(smallestEvent)
        }

        eventList = newEventList
    }

}