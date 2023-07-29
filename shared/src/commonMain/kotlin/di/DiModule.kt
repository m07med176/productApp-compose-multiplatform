package di

import data.remote.KtorBuilder.createHttpClient
import data.remote.KtorService
import data.repository.Repository
import domain.usecase.GetAllProductsUseCase
import domain.usecase.GetSingleProductsUseCase

object DiModule {
        private val httpClient = createHttpClient()
        private val service = KtorService(httpClient)
        private val repository = Repository(service)
        val getAllProductUseCase = GetAllProductsUseCase(repository)
        val getSingleProductsUseCase = GetSingleProductsUseCase(repository)
}