package com.example.finalproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EventViewModel : ViewModel() {

    var eventList: MutableLiveData<MutableList<Event>> = MutableLiveData(mutableListOf())

    val personList: MutableList<Person> = mutableListOf()

    val selectedPersonCreate: MutableLiveData<Int?> = MutableLiveData()

    var selectedPersonDisplay: Person = Person("", mutableListOf())

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

    fun createNewEvent(title: String, date: String, people: MutableList<Int>) {
        val event = Event(title, date, people)
        eventList.value?.add(event)
        sortEvents()
        dbRef.child("addedEvents").push().setValue(event)
    }

    fun clearEvents() {
        eventList.value?.clear()
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

        while (eventList.value?.size!! > 0) {
            var smallestEvent = eventList.value!![0]
            var i = 1
            while (i < eventList.value!!.size) {

                val possibleYear = smallestEvent.date.substring(smallestEvent.date.length - 4).toInt()
                val possibleDay = smallestEvent.date.substring(smallestEvent.date.indexOf("/") + 1, smallestEvent.date.lastIndexOf("/")).toInt()
                val possibleMonth = smallestEvent.date.substring(0, smallestEvent.date.indexOf("/")).toInt()

                val compareYear = eventList.value!![i].date.substring(eventList.value!![i].date.length - 4).toInt()
                val compareDay = eventList.value!![i].date.substring(eventList.value!![i].date.indexOf("/") + 1, eventList.value!![i].date.lastIndexOf("/")).toInt()
                val compareMonth = eventList.value!![i].date.substring(0, eventList.value!![i].date.indexOf("/")).toInt()

                if (compareYear < possibleYear) {
                    smallestEvent = eventList.value!![i]
                }
                else if (compareYear == possibleYear && compareMonth < possibleMonth) {
                    smallestEvent = eventList.value!![i]
                }
                else if (compareYear == possibleYear && compareMonth == possibleMonth && compareDay < possibleDay) {
                    smallestEvent = eventList.value!![i]
                }
                i++
            }
            newEventList.add(smallestEvent)
            eventList.value!!.remove(smallestEvent)
        }

        eventList.value = newEventList
    }

    fun deleteEvent(event : Event){
        eventList.value!!.remove(event)
        dbRef.child("addedEvents").removeValue()
        for(event in eventList.value!!){
            dbRef.child("addedEvents").push().setValue(event)
        }
    }

    fun deletePersonFromEvent(person : Person, event: Event){
        val personNum = personList.indexOf(person)
        event.people.remove(personNum)
        dbRef.child("addedEvents").removeValue()
        for(event in eventList.value!!){
            dbRef.child("addedEvents").push().setValue(event)
        }
    }

    fun deleteGiftFromPerson(gift: String){
        val indexOfPerson = personList.indexOf(selectedPersonDisplay)
        selectedPersonDisplay.giftIdeas.remove(gift)
        personList[indexOfPerson] = selectedPersonDisplay
        dbRef.child("addedPeople").removeValue()
        for(person in personList){
            dbRef.child("addedPeople").push().setValue(person)
        }
    }
}