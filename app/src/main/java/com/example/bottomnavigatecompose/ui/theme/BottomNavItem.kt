package com.example.bottomnavigatecompose.ui.theme

import androidx.compose.ui.graphics.vector.ImageVector

// Define uma classe de dados chamada BottomNavItem, que representa um item de navegação inferior.
data class BottomNavItem(

    // Declaração da propriedade 'name' que armazena o nome do item de navegação.
    val name: String,

    // Declaração da propriedade 'route' que armazena a rota associada ao item de navegação.
    val route: String,

    // Declaração da propriedade 'icon' que armazena um vetor de imagem representando o ícone do item de navegação.
    val icon: ImageVector,

    // Declaração da propriedade 'badgeCount' que armazena um número inteiro representando a contagem do distintivo (badge) do item de navegação.
    // O valor padrão é 0, o que significa que se nenhum valor for fornecido, a contagem do distintivo será zero.
    val badgeCount: Int = 0
)

