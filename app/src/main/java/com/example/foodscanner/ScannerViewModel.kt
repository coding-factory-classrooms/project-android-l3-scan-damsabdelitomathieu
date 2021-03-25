package com.example.foodscanner

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.foodscanner.api.model.FoodRequest
import com.example.foodscanner.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class ScannerViewModelState(
    open val errorMessage: String = "",
    open val addButtonVisibility: Int = View.VISIBLE,
    open val imageViewVisibility: Int = View.INVISIBLE,
    open val imageViewUrl: String = "",
    open val foodNameVisibility: Int = View.INVISIBLE,
    open val foodName: String = "",
) {

    object Success : ScannerViewModelState()

    data class SuccessWithInfo(
        override val imageViewVisibility: Int,
        override val imageViewUrl: String,
        override val foodName: String,
    ) :
        ScannerViewModelState("", View.VISIBLE, imageViewVisibility, imageViewUrl, View.VISIBLE, foodName)

    data class UpdateScan(override val addButtonVisibility: Int) :
        ScannerViewModelState("", addButtonVisibility, View.INVISIBLE, "")

    data class Failure(override val errorMessage: String) :
        ScannerViewModelState(errorMessage, View.INVISIBLE, View.INVISIBLE, "")
}

class ScannerViewModel(private val repository: Repository) : ViewModel() {

    private val state = MutableLiveData<ScannerViewModelState>()
    fun getState(): LiveData<ScannerViewModelState> = state

    val myResponse: MutableLiveData<Response<FoodRequest>> = MutableLiveData()

    fun scan(code: String) {
        viewModelScope.launch {
            if (getFood(code)) {
                Log.i("Response", "code trouvé")

                var viewVisibility = View.VISIBLE

                if (myResponse.value!!.body()!!.getImageUrl().isBlank())
                    viewVisibility = View.INVISIBLE

                state.value = ScannerViewModelState.SuccessWithInfo(
                    viewVisibility,
                    myResponse.value!!.body()!!.getImageUrl(),
                    myResponse.value!!.body()!!.getFoodName(),
                )
            } else {
                Log.i("Response", "code non trouvé")
                state.value =
                    ScannerViewModelState.Failure("Aucun produit trouvé pour ce code barre !")
            }
        }
    }

    fun addToDB(code: String) {

    }

    private suspend fun getFood(barCode: String): Boolean {

        val response: Response<FoodRequest> = repository.getFood(barCode)
        myResponse.value = response

        if (myResponse.value != null && myResponse.value!!.body() != null) {
            return myResponse.value!!.body()!!.isFound()
        }

        return false
    }
}