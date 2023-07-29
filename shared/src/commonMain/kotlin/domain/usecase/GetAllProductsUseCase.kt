package domain.usecase

import data.dto.toProductEntity
import domain.entity.ProductEntity
import domain.repository.IRepository
import kotlinx.coroutines.flow.flow

class GetAllProductsUseCase(private val repository: IRepository) {
    private val movieList = mutableListOf<ProductEntity>()
    operator fun invoke() = flow {
            val response = repository.getAllProducts().toProductEntity()
            movieList.addAll(response)
            emit(movieList)

    }
}