package com.tob1.example.test.drawerandroid.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.tob1.example.test.drawerandroid.data.local.PeopleDao
import com.tob1.example.test.drawerandroid.data.models.People
import com.tob1.example.test.drawerandroid.ui.models.PeopleUI
import timber.log.Timber

class PeopleRepository(
    private val peopleDao: PeopleDao
) {

    /**
     * Co chuc nang lay toan bo people trong database va chuyen doi People -> PeopleUI
     * */
    fun getAllPeople(): LiveData<List<PeopleUI>> = Transformations.map(peopleDao.getAllPeople()){ list ->
        list.map { people ->
            PeopleUI(firstName = people.firstName, lastName = people.lastName + people.id)
        }
    }
    /**
     * @param people truyen gia tri people vao de insert
     *
     *
     * */

    fun insertPeople(people: People){
        peopleDao.insertAll(people)
        Timber.d("insert People")
    }



}