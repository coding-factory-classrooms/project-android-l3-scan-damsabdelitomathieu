package com.example.foodscanner

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.foodscanner.api.model.FoodRequest
import com.example.foodscanner.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class ScannerViewModelState(open val errorMessage: String = "", open val addButtonVisibility: Int = View.VISIBLE) {

    object Success : ScannerViewModelState()
    data class UpdateScan(override val addButtonVisibility: Int) :
        ScannerViewModelState("", addButtonVisibility)

    data class Failure(override val errorMessage: String) : ScannerViewModelState(errorMessage, View.INVISIBLE)
}

class ScannerViewModel(private val repository: Repository) : ViewModel() {

    private val state = MutableLiveData<ScannerViewModelState>()
    fun getState(): LiveData<ScannerViewModelState> = state

    val myResponse: MutableLiveData<Response<FoodRequest>> = MutableLiveData()

    fun scan(code: String) {
        if (getFood(code)) {
            state.value = ScannerViewModelState.Success
        } else {
            state.value = ScannerViewModelState.Failure("Aucun produit trouvé pour ce code barre !")
        }
    }

    fun addToDB(code: String) {

    }

    private fun getFood(barCode: String): Boolean {
        viewModelScope.launch {
            val response: Response<FoodRequest> = repository.getFood(barCode)
            myResponse.value = response
        }

        if (myResponse.value != null)
            return myResponse.value!!.isSuccessful

        return false
    }
}

fun searchFood(code: String): Boolean {
    return code == "3123349014822"
}