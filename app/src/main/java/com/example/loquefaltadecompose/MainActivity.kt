package com.example.loquefaltadecompose



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            Surface(

                modifier = Modifier.fillMaxSize()) {
                Conversacion(mensajes = InformacionMensajes.listaMensajes)


            }


                }


        }
    }




data class Mensaje(val nombre: String, val desc: String)



@Composable
fun Conversacion(mensajes: List<Mensaje>) {

    LazyColumn {

        items(mensajes) { mensaje ->

            MensajeTarjeta(msg = mensaje)



        }

    }


}


@Composable
fun MensajeTarjeta(msg: Mensaje) {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(all = 10.dp)


    ) {
        Image(
            painterResource(id = R.drawable.jijo),
            contentDescription = "Perfil",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(
                    1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape
                )

        )
        Spacer(modifier = Modifier.width(5.dp))




        //Hace de una linea los comentarios
        var esExapandible by remember { mutableStateOf(false) }


        //Colorea el comentario, refencia del surface de abajo
        val surfaceColor by animateColorAsState(targetValue =
                if(esExapandible) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        )





        //Si se clickean se expanden
        Column( modifier = Modifier.clickable{esExapandible = !esExapandible}) {
            Text(
                text = msg.nombre,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(5.dp))
            Surface(shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)

                ) {
                Text(
                    text = msg.desc,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if(esExapandible) Int.MAX_VALUE else 1, //Funcion de una linea que se expande
                    style = MaterialTheme.typography.bodySmall

                )
            }
        }


    }


}


@Composable
fun PreviewMensajeTarjeta() {


    Surface {
        MensajeTarjeta(Mensaje("Pepe", "Pepe dominara el mundo"))


    }

}






@Preview
@Composable
fun previewConvesacion() {


        Surface {

    Conversacion(mensajes = InformacionMensajes.listaMensajes)

    }

}