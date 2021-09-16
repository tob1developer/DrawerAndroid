package com.tob1.example.test.drawerandroid.ui.layout.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.tob1.example.test.drawerandroid.data.PeopleRepository
import com.tob1.example.test.drawerandroid.data.local.AppDatabase
import com.tob1.example.test.drawerandroid.data.models.People
import com.tob1.example.test.drawerandroid.ui.models.ButtonUI
import com.tob1.example.test.drawerandroid.ui.models.PeopleUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PeopleViewModel(private val peopleRepository: PeopleRepository) : ViewModel() {

    /**
     * Co chuc nang call list tu database ->>> tranh bi xu ly nhieu tren 1 luong
     * */
    val listPeopleUI : LiveData<List<PeopleUI>> = liveData {
        emitSource(peopleRepository.getAllPeople())
    }


    /**
     * add them callback btn de khi co su kien ben UI -> btn trong viewmodel se hoat dong
     * */
    private val _btnAdd = MutableLiveData<ButtonUI>().apply {
        value = ButtonUI(
            onClick = {
                val peopleUI =  PeopleUI("Hettt","Dich.")
                addPeople(peopleUI)
            }
        )
    }
    val btnAdd : LiveData<ButtonUI> = _btnAdd


    private fun addPeople(peopleUI: PeopleUI){
        val people = People(null, peopleUI.firstName, peopleUI.lastName)
        viewModelScope.launch(Dispatchers.IO){ //De tranh k bi de len Luong` UI
            peopleRepository.insertPeople(people)
        }
    }
}


/**
 * @param context khoi tao context de goi dc database
 * co chuc nang khoi tao PeopleViewModel -> 1 co the dung nhieu man hinh( Fragment)
 * */
class PeopleLiveDataFactory(private val context: Context)  : ViewModelProvider.Factory{
    private val peopleDao = AppDatabase.getDataBase(context).peopleDao()
    private val peopleRepository = PeopleRepository(peopleDao)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return PeopleViewModel(peopleRepository) as T
    }
}