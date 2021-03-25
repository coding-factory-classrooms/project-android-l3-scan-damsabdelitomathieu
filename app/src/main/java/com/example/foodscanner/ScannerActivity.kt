package com.example.foodscanner

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.budiyev.android.codescanner.*
import com.bumptech.glide.Glide
import com.example.foodscanner.databinding.ActivityScannerBinding
import com.example.foodscanner.repository.Repository

const val TAG = "ScannerActivity"
const val CAMERA_REQUEST_CODE = 101

class ScannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScannerBinding
    private lateinit var model: ScannerViewModel
    private lateinit var codeScanner: CodeScanner
    private lateinit var barCode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = ScannerViewModelFactory(repository)
        model = ViewModelProvider(this, viewModelFactory).get(ScannerViewModel::class.java)
        model.initialiseFoodDao(this)

        model.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                binding.textView.text = barCode

                Log.i("Response", "status --> ${response.body()?.status.toString()}")
                Log.i("Response", "code --> ${response.body()?.code.toString()}")
                Log.i("Response", "product --> ${response.body()?.product.toString()}")
                Log.i(
                    "Response",
                    "status_verbose --> ${response.body()?.status_verbose.toString()}"
                )
                Log.i("Response", "Marque --> ${response.body()?.getBrands()?.toString()}")
                Log.i("Response", "name --> ${response.body()?.getFoodName()?.toString()}")
                Log.i(
                    "Response",
                    "small image --> ${response.body()?.getSmallImageUrl()?.toString()}"
                )
                Log.i("Response", "image --> ${response.body()?.getImageUrl()?.toString()}")
            } else {
                reinitalizeTextView()
                Log.i("Response", "${response.toString()} : ${response.code().toString()}")
            }
        })

        model.getState().observe(this, { updateUi(it!!) })

        setUpPermissions()
        codeScanner()

        binding.addButton.visibility = View.INVISIBLE
        binding.errorMessageTextView.visibility = View.INVISIBLE
        binding.imageView.visibility = View.INVISIBLE
        binding.foodNameTextView.visibility = View.INVISIBLE

        binding.imageView.isVisible = false
        binding.foodNameTextView.isVisible = false

        // Action on our simulateButton
        binding.simulateButton.setOnClickListener {
            simulateBarCode()
        }
        // Action on our addButton
        binding.addButton.setOnClickListener {
            insertIntoDatabase()
        }
    }

    private fun insertIntoDatabase() {
        model.addFood()
        Toast.makeText(this, "Produit ajouté !",Toast.LENGTH_SHORT).show()
        navigateToInventory()
    }

    private fun simulateBarCode() {
        codeScanner.releaseResources()
        barCode = "3021760400012"
        binding.textView.text = barCode
        model.scan(barCode)
    }

    private fun updateUi(state: ScannerViewModelState) {
        return when (state) {
            ScannerViewModelState.Success -> {
                startSuccessAnimation(state)
            }
            is ScannerViewModelState.SuccessWithInfo -> {
                startFoodDetailAnimation(state)
                startSuccessAnimation(state)
            }
            is ScannerViewModelState.Failure -> {
                startFailureAnimation(state)
            }
            is ScannerViewModelState.UpdateScan -> {
                binding.addButton.visibility = state.addButtonVisibility
            }
        }
    }

    private fun startFailureAnimation(state: ScannerViewModelState) {

        Log.i("ANIMATION", "animation failure !")

        binding.errorMessageTextView.text = state.errorMessage
        binding.errorMessageTextView.visibility = View.VISIBLE

        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator() //add this
        fadeIn.duration = 1000

        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator() //and this
        fadeOut.startOffset = 4000
        fadeOut.duration = 1000

        val animation = AnimationSet(false) //change to false
        animation.addAnimation(fadeIn)
        animation.addAnimation(fadeOut)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.errorMessageTextView.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        binding.addButton.animate()
            .alpha(0f)
            .setDuration(500)
            .withEndAction {
                binding.addButton.visibility = state.addButtonVisibility
            }

        binding.foodNameTextView.animate()
            .alpha(0f)
            .setDuration(500)
            .withEndAction {
                binding.foodNameTextView.isVisible = false
                binding.errorMessageTextView.startAnimation(animation)
            }

        binding.imageView.animate()
            .alpha(0f)
            .setDuration(500)

        reinitalizeTextView()
    }

    private fun startSuccessAnimation(state: ScannerViewModelState) {

        Log.i("ANIMATION", "animation success !")

        binding.addButton.visibility = state.addButtonVisibility
        binding.addButton.alpha = 0f

        binding.addButton.animate()
            .alpha(1f)
            .setDuration(2000)
    }

    private fun startFoodDetailAnimation(state: ScannerViewModelState) {

        Log.i("ANIMATION", "food detail animation !")

        binding.imageView.isVisible = true
        binding.foodNameTextView.isVisible = true

        binding.foodNameTextView.text = state.foodName
        binding.foodNameTextView.visibility = state.foodNameVisibility

        binding.imageView.visibility = state.imageViewVisibility

        val url: String = state.imageViewUrl.replace("https", "http")

        if (url.isNotBlank()) {
            //Picasso.get().load(url).into(binding.imageView)
            Glide.with(this)
                .load("http://static.openfoodfacts.org/images/products/302/176/040/0012/front_fr.70.400.jpg")
                .into(binding.imageView)
        }
        Log.i("ANIMATION", "imageURL : $url")

        binding.foodNameTextView.alpha = 0f
        binding.imageView.alpha = 0f

        binding.foodNameTextView.animate()
            .alpha(1f)
            .setDuration(2000)

        binding.imageView.animate()
            .alpha(1f)
            .setDuration(2000)
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
                    codeScanner.releaseResources()
                    barCode = it.text
                    model.scan(barCode)
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    barCode = ""
                    reinitalizeTextView()
                    model.scan(barCode)
                    Log.e(TAG, "Erreur de l'initialisation de la caméra : ${it.message}")
                }
            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
            reinitalizeTextView()
        }
    }

    private fun reinitalizeTextView() {
        binding.textView.text = "Scannez votre produit"
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
        val permission: Int = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this,
                        "Vous avez besoin de la caméra pour utiliser cette application",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.i(TAG, "L'utilisateur a autorisé la caméra")
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}