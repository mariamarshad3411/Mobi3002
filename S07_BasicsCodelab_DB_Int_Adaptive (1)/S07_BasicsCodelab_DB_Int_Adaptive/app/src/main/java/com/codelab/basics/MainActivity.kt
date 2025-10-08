package com.codelab.basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ✅ Pass 'this' as the context to DBClass
        val db = DBClass(this)

        setContent {
            var favorite by remember { mutableStateOf(db.getMostAccessed()) }
            val pokemonList = remember { db.findAll() }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFE4EC), Color(0xFFFFF0F5))
                        )
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "Favorite Pokémon: ${favorite.name} (Access: ${favorite.accessCount})",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFAD7FA8),
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(pokemonList) { pokemon ->
                        var showDetails by remember { mutableStateOf(false) }
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFC1E3)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable {
                                    db.incAccessCount(pokemon.number)
                                    favorite = db.getMostAccessed()
                                    showDetails = !showDetails
                                },
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "${pokemon.number}. ${pokemon.name}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF9C27B0)
                                )
                                if (showDetails) {
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "Power Level: ${pokemon.powerLevel}",
                                        fontSize = 16.sp,
                                        color = Color(0xFFCE93D8)
                                    )
                                    Text(
                                        text = "Description: ${pokemon.description}",
                                        fontSize = 16.sp,
                                        color = Color(0xFFCE93D8)
                                    )
                                    Text(
                                        text = "Access Count: ${pokemon.accessCount}",
                                        fontSize = 16.sp,
                                        color = Color(0xFFCE93D8)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
