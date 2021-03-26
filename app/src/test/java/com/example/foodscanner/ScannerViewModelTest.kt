package com.example.foodscanner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foodscanner.repository.Repository
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ScannerViewModelTest {


    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `invalid credentials yields state Failure`() {

        val repository = Repository()
        val model = ScannerViewModel(repository)

        val observer = model.getState().testObserver()

        assertEquals(
            ScannerViewModelState.Failure("Aucun produit trouvé pour ce code barre !"),
            observer.observedValues.first()
        )
    }


}