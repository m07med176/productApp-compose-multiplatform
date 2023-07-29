package domain.entity

data class ProductEntity(
    var id:Int = 0,
    var title: String? = null,
    var description: String? = null,
    var price:Int = 0,
    var discountPercentage:Double = 0.0,
    var rating:Float = 0f,
    var stock:Int = 0,
    var brand: String? = null,
    var category: String = "",
    var thumbnail: String? = null,
    var isFavorite:Boolean = false,
    var images: List<String> = emptyList(),
)
