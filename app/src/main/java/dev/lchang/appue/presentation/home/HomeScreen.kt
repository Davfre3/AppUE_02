package dev.lchang.appue.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import dev.lchang.appue.data.model.CountryModel

val mockCountries = listOf(
    CountryModel("Colombia", 8,"https://flagcdn.com/w320/co.png"),
    CountryModel("Argentina", 1,"https://flagcdn.com/w320/ar.png"),
    CountryModel("Francia", 4,"https://flagcdn.com/w320/fr.png"),
    CountryModel("Brasil", 3,"https://flagcdn.com/w320/br.png"),
    CountryModel("Perú", 62,"https://flagcdn.com/w320/pe.png"),
    CountryModel("Uruguay", 9,"https://flagcdn.com/w320/uy.png")
)

@Composable
fun HomeScreen()
{
    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    )
    {
        //Bienvenido a la pantalla principal
        Text(text = "Ranking FIFA 2025")
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(mockCountries){ country ->

                Card(
                    modifier = Modifier.fillMaxSize().padding(vertical =8.dp)
                ){
                    Row(modifier = Modifier.padding(12.dp)) {
                        Image(
                            contentDescription = country.name,
                            modifier = Modifier.size(64.dp),
                            //contentScale = ContentScale.Crop,

                            painter = rememberAsyncImagePainter(country.imageUrl)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column{
                            Text(text = country.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = "Ranking FIFA: ${country.ranking}")
                        }
                    }
                }
            }
        }

    }
}