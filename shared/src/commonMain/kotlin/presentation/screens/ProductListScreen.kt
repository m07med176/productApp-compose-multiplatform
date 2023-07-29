package presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.entity.ProductEntity
import getPlatformName
import presentation.components.ProductItemCell

@Composable
fun ProductListScreen(products:List<ProductEntity>){
    var isLoading by remember { mutableStateOf(true) }
    var singleProduct by remember { mutableStateOf(ProductEntity()) }

    AnimatedVisibility(isLoading) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Hello, ${getPlatformName()}",
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black
                ),
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
                content = {
                    items(products.size) {
                        ProductItemCell(products[it]){productItem->
                            isLoading = false
                            singleProduct = productItem
                        }
                    }
                }
            )
        }
    }

    AnimatedVisibility(!isLoading) {
        ProductItemScreen(singleProduct){
            isLoading = true
        }
    }
}
