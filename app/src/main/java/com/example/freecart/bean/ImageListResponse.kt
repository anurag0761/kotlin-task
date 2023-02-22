package com.example.freecart.bean

import android.net.Uri

class ImageListResponse : BaseBean(){


		var imageList: ArrayList<ImageItem?>? = ArrayList()
	}

data class ImageItem(

	var image: Uri? = null
)

