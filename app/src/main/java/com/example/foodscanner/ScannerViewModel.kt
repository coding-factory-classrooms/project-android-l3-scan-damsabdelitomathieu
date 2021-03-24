package com.example.foodscanner

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class ScannerViewModelState(open val errorMessage: String = "", open val addButtonVisibility: Int = View.VISIBLE) {

    object Success : ScannerViewModelState()
    data class UpdateScan(override val addButtonVisibility: Int) :
        ScannerViewModelState("", addButtonVisibility)

    data class Failure(override val errorMessage: String) : ScannerViewModelState(errorMessage, View.INVISIBLE)
}

class ScannerViewModel : ViewModel() {

    private val state = MutableLiveData<ScannerViewModelState>()
    fun getState(): LiveData<ScannerViewModelState> = state

    fun scan(code: String) {
        Log.e("Scanner", "*************** searchFood() -> ${searchFood(code)}")

        if (searchFood(code)) {
            state.value = ScannerViewModelState.Success
        } else {
            state.value = ScannerViewModelState.Failure("Aucun produit trouvé pour ce code barre !")
        }
    }

    fun addToDB(code: String) {

    }
}

fun searchFood(code: String): Boolean = code == "3123349014822"
