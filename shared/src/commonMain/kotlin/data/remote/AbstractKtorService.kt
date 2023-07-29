package data.remote

import data.dto.Product
import data.dto.ProductResponse

interface AbstractKtorService {
    suspend fun getAllProducts():ProductResponse

    suspend fun getSingleProduct(productId: Int):Product
}