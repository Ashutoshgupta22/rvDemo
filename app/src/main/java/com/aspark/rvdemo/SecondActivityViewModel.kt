package com.aspark.rvdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondActivityViewModel: ViewModel() {

    private var _imageList = MutableLiveData<ArrayList<String>>()
    val imageList : LiveData<ArrayList<String>> = _imageList
    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val dataRepo: DataRepository by lazy { DataRepository() }

    fun getImages() {

        viewModelScope.launch(Dispatchers.IO) {

            dataRepo.getRemoteImages {

                if (it != null) {
                    _imageList.postValue(it)

                }
                else
                    _errorMessage.postValue("Something went wrong!")
            }
        }

    }

}