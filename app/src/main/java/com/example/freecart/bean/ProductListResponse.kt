package com.example.freecart.bean

class ProductListResponse : BaseBean(){

		val total: Int? = null
		val limit: Int? = null
		val skip: Int? = null
		val products: ArrayList<ProductsItem?>? = null
	}

data class ProductsItem(
	var discountPercentage: Any? = null,
	var thumbnail: String? = null,
	var images: List<String?>? = null,
	var price: Int? = null,
	var rating: Double? = null,
	var description: String? = null,
	val id: Int? = null,
	var title: String? = null,
	var stock: Int? = null,
	var category: String? = null,
	var brand: String? = null
)

