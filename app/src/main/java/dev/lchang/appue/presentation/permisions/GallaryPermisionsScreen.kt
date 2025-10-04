package dev.lchang.appue.presentation.permisions

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter

@Composable
fun GallaryPermisionsScreen() {
    val context = LocalContext.current
    var selectedImagenUri by remember {mutableStateOf<Uri?>(null)}

    var pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){
        uri->
        if (uri != null) selectedImagenUri = uri

    }
    //Permisos de galeria
val permissionlauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestPermission()
){
    isGrated ->
    if(isGrated) pickImageLauncher.launch("image/*")
}
    val permission = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
        android.Manifest.permission.READ_MEDIA_IMAGES
    }else{
        Manifest.permission.READ_EXTERNAL_STORAGE
    }
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Selecciona una imagen de tu galeria",
            style = MaterialTheme.typography.titleLarge
        )
        Button(onClick = {
            val isGranted = ContextCompat.checkSelfPermission(context, permission)
            if (isGranted == PackageManager.PERMISSION_GRANTED) {
                pickImageLauncher.launch("image/*")
            } else {
                permissionlauncher.launch(permission)

            }
        }) {
            Text(text = "Seleccionar imagen")
        }
        //
        selectedImagenUri?.let {
            Spacer(modifier = Modifier.padding(16.dp))
            Image(
                painter = rememberAsyncImagePainter(model = it),
                contentDescription = null,
                modifier = Modifier.fillMaxSize().size(200.dp).padding(16.dp)
            )
        }
    }
    }
