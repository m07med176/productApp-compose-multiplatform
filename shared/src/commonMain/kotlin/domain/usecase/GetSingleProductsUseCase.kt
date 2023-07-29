package domain.usecase

import data.dto.toSingleProductEntity
import domain.repository.IRepository
import kotlinx.coroutines.flow.flow

class GetSingleProductsUseCase(private val repository: IRepository) {
    operator fun invoke(productId:Int) = flow {
            val response = repository.getSingleProduct(productId).toSingleProductEntity()
            emit(response)
    }
}