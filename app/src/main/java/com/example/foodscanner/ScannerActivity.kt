package com.example.foodscanner

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode

import com.example.foodscanner.databinding.ActivityScannerBinding

const val TAG = "ScannerActivity"
const val CAMERA_REQUEST_CODE = 101

class ScannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScannerBinding
    private val model: ScannerViewModel by viewModels()
    private lateinit var codeScanner : CodeScanner
    private lateinit var barCode: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.getState().observe(this, { updateUi(it!!) })

        setUpPermissions()
        codeScanner()

        binding.addButton.visibility = View.INVISIBLE
        binding.errorMessageTextView.visibility = View.INVISIBLE

        // Action on our simulateButton
        binding.simulateButton.setOnClickListener{
            simulateBarCode()
        }
        // Action on our addButton
        binding.addButton.setOnClickListener {
            model.addToDB(this.barCode)
        }
    }

    private fun simulateBarCode() {

    }

    private fun updateUi(state: ScannerViewModelState) {
        return when (state) {
            ScannerViewModelState.Success -> {
                binding.errorMessageTextView.visibility = View.INVISIBLE
                binding.addButton.visibility = state.addButtonVisibility
                Toast.makeText(this, "Scan réussie ! ${state.addButtonVisibility}", Toast.LENGTH_SHORT).show()
                navigateToInventory()
            }
            is ScannerViewModelState.Failure -> {
                Toast.makeText(this, "Scan raté ! ${state.addButtonVisibility}", Toast.LENGTH_SHORT).show()
                binding.addButton.visibility = state.addButtonVisibility
                binding.errorMessageTextView.visibility = View.VISIBLE
                binding.errorMessageTextView.text = state.errorMessage
            }
            is ScannerViewModelState.UpdateScan -> {
                Toast.makeText(this, "Update Scan ! ${state.addButtonVisibility}", Toast.LENGTH_SHORT).show()
                binding.addButton.visibility = state.addButtonVisibility
            }
        }
    }

    private fun navigateToInventory() {
        Log.i(TAG, ">>>>>>> navigate to list")
    }

    private fun codeScanner() {
        codeScanner = CodeScanner(this, binding.scannerView)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    binding.textView.text = it.text
                    barCode = it.text
                    model.scan(barCode)
                    codeScanner.releaseResources()

                    Log.i(TAG, "-----------------------------------------------------------------")
                    Log.i(TAG, "--------> ${it.text}")
                    Log.i(TAG, "--------> ${it.timestamp}")
                    Log.i(TAG, "--------> ${it.barcodeFormat}")
                    Log.i(TAG, "--------> ${it.numBits}")
                    Log.i(TAG, "--------> ${it.rawBytes}")
                    Log.i(TAG, "--------> ${it.resultMetadata}")
                    Log.i(TAG, "--------> ${it.resultPoints}")
                    Log.i(TAG, "-----------------------------------------------------------------")

                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    barCode = ""
                    model.scan(barCode)
                    Log.e(TAG, "Erreur de l'initialisation de la caméra : ${it.message}")
                }
            }
        }

        binding.scannerView.setOnClickListener{
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setUpPermissions() {
         val permission: Int = ContextCompat.checkSelfPermission(this,
         android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
             makeRequest()
        }
    }

    private fun makeRequest() {
         ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Vous avez besoin de la caméra pour utiliser cette application", Toast.LENGTH_SHORT).show()
                } else {
                    Log.i(TAG, "L'utilisateur a autorisé la caméra")
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}