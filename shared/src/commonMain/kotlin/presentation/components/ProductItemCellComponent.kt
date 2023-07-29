package presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.entity.ProductEntity
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import presentation.theme.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductItemCell(product: ProductEntity,onClick: (product: ProductEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium.copy(
            topStart = CornerSize(16.dp),
            topEnd = CornerSize(16.dp),
            bottomStart = CornerSize(16.dp),
            bottomEnd = CornerSize(16.dp)
        ),
        onClick = {onClick(product)},
        backgroundColor = Color.Background
    ) {
        Column {
            KamelImage(
                asyncPainterResource(product.thumbnail?:""),
                "${product.category} by ${product.brand}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.0f)
            )

            Column( modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)){
                ProductRating(product.rating)
                ProductBrandName(product.brand?:"Nothing")
                ProductName(product.title?:"Nothing")
                ProductPrice(product.price.toString())
            }

        }
    }
}

@Composable
fun WithTitle(title: String,textCompose: @Composable () -> Unit){
    buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold,color = Color.red
        )) {
            append(title)
        }
        textCompose()

    }
}

@Composable
fun ProductBrandName(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        text = title,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        textAlign = TextAlign.Start,
        maxLines = 1,
        color = Color.lightGray
    )
}

@Composable
fun ProductName(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        text = title,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        textAlign = TextAlign.Start,
        maxLines = 1,
        color = Color.mainText
    )
}

@Composable
fun ProductPrice(title: String) {
        Text(
            modifier = Modifier
                .padding(2.dp),
            text = "$title $",
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            maxLines = 1,
            color = Color.red
        )
}

@Composable
fun ProductRating(rate: Float) {
    Row(
        modifier = Modifier
            .height(30.dp)
            .padding(2.dp)
    ) {
        for (i in 1..5) {
            if (i <= rate) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Filled star",
                    tint = Color.primary
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Empty star",
                    tint = Color.primary
                )
            }
        }
    }
}