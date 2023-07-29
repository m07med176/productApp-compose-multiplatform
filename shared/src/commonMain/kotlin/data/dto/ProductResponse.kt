package data.dto

import kotlinx.serialization.Serializable
import data.dto.Product
import domain.entity.ProductEntity

@Serializable
data class ProductResponse (
    var products: List<Product> = emptyList(),
    var total:Int = 0,
    var skip:Int = 0,
    var limit:Int = 0
)

@Serializable
data class Product(
    var id:Int = 0,
    var title: String? = null,
    var description: String? = null,
    var price:Int = 0,
    var discountPercentage:Double = 0.0,
    var rating:Float = 0f,
    var stock:Int = 0,
    var brand: String? = null,
    var category: String,
    var thumbnail: String? = null,
    var images: List<String> = emptyList(),
)

