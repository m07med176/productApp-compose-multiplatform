import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import data.dto.Product
import domain.entity.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import presentation.components.ProductItemCell
import presentation.screens.DefaultScreen
import presentation.screens.ErrorScreen
import presentation.screens.LoadingScreen
import presentation.screens.ProductItemScreen
import presentation.screens.ProductListScreen
import presentation.viewmodel.ProductsViewModel

@Composable
fun ProductAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(primary = Color.Black),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp)
        )
    ) {
        content()
    }
}

@Composable
fun App() {
    ProductAppTheme {
        val productsViewModel = getViewModel(Unit, viewModelFactory { ProductsViewModel() })
        ProductListPage(productsViewModel)
    }
}

@Composable
fun ProductListPage(viewModel: ProductsViewModel) {

    val uiState by viewModel.products.collectAsState()
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when(uiState){
            is ProductsViewModel.ProductsState.Error -> ErrorScreen((uiState as ProductsViewModel.ProductsState.Error<List<ProductEntity>>).error.message)
            is ProductsViewModel.ProductsState.Idle -> DefaultScreen()
            is ProductsViewModel.ProductsState.Loading -> LoadingScreen()
            is ProductsViewModel.ProductsState.Success -> (
                    uiState as ProductsViewModel.ProductsState.Success<List<ProductEntity>>).data.apply {
                    ProductListScreen(this)
            }
        }
    }
}



expect fun getPlatformName(): String