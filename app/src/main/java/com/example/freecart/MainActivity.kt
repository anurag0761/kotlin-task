package com.example.freecart

import ImageAdapters
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.freecart.ProductViewModel.ApiViewModel
import com.example.freecart.bean.ImageItem
import com.example.freecart.bean.ImageListResponse
import com.example.freecart.databinding.ActivityMainBinding
import com.example.freecart.databinding.ActivityProductListBinding
import com.example.freecart.handler.ApiViewModelFactory
import com.github.dhaval2404.imagepicker.ImagePicker
import com.logidtic.blueaid.utility.PreferenceHandler
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import java.io.File

class MainActivity : AppCompatActivity(), KodeinAware{
    //private val preferenceHandler: PreferenceHandler by instance()

    override val kodein: Kodein by closestKodein()
    val imageListResponse = ImageListResponse()

    var boolean = true
    private lateinit var apiViewModel: ApiViewModel
    private val factory: ApiViewModelFactory by instance()
    private lateinit var viewDataBinding: ActivityMainBinding

    lateinit var imageAdapter :ImageAdapters

    lateinit var  picFile: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        apiViewModel = ViewModelProvider(this,factory).get(ApiViewModel::class.java)
        viewDataBinding.handler = Handler()
        manage()
    }

    fun manage(){
        apiViewModel.getimgList().observe(this) {
            if(it.imageList?.size!! <= 5) {
                imageAdapter = ImageAdapters(this, it.imageList)
                viewDataBinding.recyclerImg.hasFixedSize()
                val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                viewDataBinding.recyclerImg.layoutManager = layoutManager
                viewDataBinding.recyclerImg.adapter = imageAdapter
            }
            else Toast.makeText(this,"you reached max limit",Toast.LENGTH_LONG).show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val uri: Uri = data?.data!!
                println("got image $uri")


               // viewDataBinding.proimg.setImageURI(uri)
                val imageItem =  ImageItem()
                imageItem.image = uri

                imageListResponse.imageList?.add(imageItem)
             apiViewModel.setImage(imageListResponse)


               // picFile = File(uri.path)




            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
    inner class Handler {
        /* fun inputPasswordEyeClick() {
             Log.e("TAG", "onActivityResult())")
             UtilAction.activatePasswordEye(dataBindingView.inputPassword, dataBindingView.inputPasswordEyeImg)
         }*/
        fun showPickerDialog() {
            val pickerDialog = Dialog(this@MainActivity)
            pickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            pickerDialog.setCancelable(true)
            pickerDialog.setContentView(R.layout.dialogue_image_picker)
            pickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val cameraPick = pickerDialog.findViewById<TextView>(R.id.cameraPick)
            val galleryPick = pickerDialog.findViewById<TextView>(R.id.galleryPick)

            cameraPick.setOnClickListener {
                ImagePicker.with(this@MainActivity)
                    .cameraOnly()
                    .crop()
                    .compress(512)
                    .maxResultSize(720, 720)
                    .start()
                pickerDialog.dismiss()
            }

            galleryPick.setOnClickListener {
                ImagePicker.with(this@MainActivity)
                    .galleryOnly()
                    .crop()
                    .compress(512)
                    .maxResultSize(720, 720)
                    .start()

                pickerDialog.dismiss()
            }


            pickerDialog.show()

            val displayMetrics = DisplayMetrics()
            this@MainActivity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val displayWidth = displayMetrics.widthPixels
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(pickerDialog.window!!.attributes)
            val dialogWindowWidth = (displayWidth * 0.8f).toInt()
            layoutParams.width = dialogWindowWidth
            pickerDialog.window!!.attributes = layoutParams
        }

        fun onBackpresss() {
            onBackPressed()

        }


    }




}