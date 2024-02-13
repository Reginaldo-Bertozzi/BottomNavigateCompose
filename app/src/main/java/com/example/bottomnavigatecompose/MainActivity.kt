package com.example.bottomnavigatecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigatecompose.ui.theme.BottomNavItem
import com.example.bottomnavigatecompose.ui.theme.BottomNavigateComposeTheme

// Classe principal MainActivity, que estende ComponentActivity.
class MainActivity : ComponentActivity() {

    // Função chamada quando a atividade é criada.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Define o conteúdo da atividade usando Compose.
        setContent {
            // Aplica o tema personalizado BottomNavigateComposeTheme.
            BottomNavigateComposeTheme {

                // Lembra o NavController para gerenciar a navegação.
                val navController = rememberNavController()

                // Define a estrutura do layout com um Scaffold.
                Scaffold(
                    bottomBar = {
                        // Chama a função BottomNavigateBar para criar a barra de navegação inferior.
                        BottomNavigateBar(
                            items = listOf(
                                // Lista de BottomNavItem para os itens da barra de navegação.
                                BottomNavItem(
                                    name = "Home",
                                    route = "home",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Chat",
                                    route = "chat",
                                    icon = Icons.Default.Notifications
                                ),
                                BottomNavItem(
                                    name = "Settings",
                                    route = "settings",
                                    icon = Icons.Default.Settings
                                )
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    // Chama a função Navigation para definir as telas e a navegação.
                    Navigation(navController = navController)
                }
            }
        }
    }
}

// Anotação indicando que a função usa recursos experimentais do Material 3.
@OptIn(ExperimentalMaterial3Api::class)
// Função que cria a barra de navegação inferior.
@Composable
fun BottomNavigateBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    // Obtém a entrada atual na pilha de retrocesso.
    val backStackEntry = navController.currentBackStackEntryAsState()

    // Cria um componente BottomNavigation com base na lista de itens fornecida.
    BottomNavigation(
        modifier = Modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        // Itera sobre os itens e cria um BottomNavigationItem para cada um.
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    // Cria o conteúdo do ícone com suporte a distintivo (badge) se aplicável.
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        if (item.badgeCount > 0) {
                            // Adiciona um distintivo (badge) se a contagem for maior que zero.
                            BadgedBox(
                                badge = {
                                    Text(text = item.badgeCount.toString())
                                }
                            ) {
                                // Exibe o ícone dentro do distintivo.
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                        } else {
                            // Se não houver distintivo, exibe apenas o ícone.
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }
                        // Exibe o nome do item se estiver selecionado.
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

// Função que define as telas e a navegação usando o Compose Navigation.
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // Define o conteúdo da tela Home.
            HomeScreen()
        }
        composable("chat") {
            // Define o conteúdo da tela Chat.
            ChatScreen()
        }
        composable("settings") {
            // Define o conteúdo da tela Settings.
            SettingsScreen()
        }
    }
}

// Função que define o conteúdo da tela Home.
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen")
    }
}

// Função que define o conteúdo da tela Chat.
@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Chat Screen")
    }
}

// Função que define o conteúdo da tela Settings.
@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings Screen")
    }
}
