package com.example.freecart

import ProductAdapter
import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.freecart.ProductViewModel.ApiViewModel
import com.example.freecart.databinding.ActivityProductListBinding
import com.example.freecart.handler.ApiViewModelFactory
import com.example.freecart.utility.PermissionUtil
import com.logidtic.blueaid.utility.PreferenceHandler
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class ProductListActivity : AppCompatActivity()  , KodeinAware,PermissionUtil.PermissionCallback {
    private val preferenceHandler: PreferenceHandler by instance()

    override val kodein: Kodein by closestKodein()

    var boolean = true
    lateinit var layoutManger : Any
    private lateinit var apiViewModel: ApiViewModel
    private val factory: ApiViewModelFactory by instance()
    private lateinit var viewDataBinding: ActivityProductListBinding

    lateinit var productAdapter :ProductAdapter
    companion object{
        var permissionHelper: PermissionUtil?=null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_list)
        apiViewModel = ViewModelProvider(this,factory).get(ApiViewModel::class.java)
        apiViewModel.fetchproductlist()
        viewDataBinding.handler= Handler()
        requestPermissions()
        manageData()
        layoutManger = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }
    fun requestPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionHelper = PermissionUtil(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
                101
            )
            permissionHelper?.request(this)

        }
    }

    private fun manageData() {
        Log.e("TAG", "manageData()")

        apiViewModel.isTaskStarted().observe(this, Observer<Boolean> {
            if (it) viewDataBinding.progressBar.visibility = View.VISIBLE
        })

        apiViewModel.isTaskFinished().observe(this, Observer<Boolean> {
            if (it) viewDataBinding.progressBar.visibility = View.GONE
        })




        apiViewModel.getproductList().observe(this) {
            if (it != null) {
                if (!it.products.isNullOrEmpty())

                    productAdapter  = ProductAdapter(this,it.products)
                viewDataBinding.recyclar.hasFixedSize()
                viewDataBinding.recyclar.layoutManager = layoutManger as LinearLayoutManager
                viewDataBinding.recyclar.adapter = productAdapter


                //  viewDataBinding.inputComment.setText(it.data!![0].feedback.toString())
            }
        }
    }


    override fun onPermissionGranted(requestCode: Int) {
       // TODO("Not yet implemented")
    }

    override fun onIndividualPermissionGranted(
        grantedPermission: Array<String>?,
        requestCode: Int
    ) {
       // TODO("Not yet implemented")
    }

    override fun onPermissionDenied(requestCode: Int) {
       // TODO("Not yet implemented")
    }

    override fun onPermissionDeniedBySystem(requestCode: Int) {
        TODO("Not yet implemented")
    }
    inner class Handler {
        fun moveClick() {
            startActivity(Intent(this@ProductListActivity, MainActivity::class.java))

        }

        fun changeLayout()
        {
            changeView()
           }
    }

    fun changeView(){
        if (!boolean){
            layoutManger = LinearLayoutManager(this@ProductListActivity, LinearLayoutManager.VERTICAL,false)
            boolean = true
            viewDataBinding.imgtype.setImageResource(R.drawable.ic_baseline_grid_on_24)
            viewDataBinding.recyclar.layoutManager = layoutManger as LinearLayoutManager
            productAdapter.setdata(1)

        }
        else{
            boolean = false
            viewDataBinding.imgtype.setImageResource(R.drawable.ic_baseline_view_headline_24)

            layoutManger = GridLayoutManager(this@ProductListActivity, 2)
            viewDataBinding.recyclar.layoutManager = layoutManger as GridLayoutManager
            productAdapter.setdata(2)

        }


    }



}