package domain.repository

import data.dto.Product
import data.dto.ProductResponse

interface IRepository {
    suspend fun getAllProducts(): ProductResponse

    suspend fun getSingleProduct(productId: Int):Product
}