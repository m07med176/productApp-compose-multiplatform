package data.remote

import data.dto.Product
import data.dto.ProductResponse
import data.getUrl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorService(private val httpClient: HttpClient):AbstractKtorService {
    override suspend fun getAllProducts(): ProductResponse
    = httpClient.get(Endpoints.GET_ALL_PRODUCT).body()

    override suspend fun getSingleProduct(productId: Int):Product
     = httpClient.get(Endpoints.GET_SINGLE_PRODUCT.getUrl(productId)).body()
}