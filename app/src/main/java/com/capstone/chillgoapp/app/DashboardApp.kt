package com.capstone.chillgoapp.app

import SplashScreen
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.capstone.chillgoapp.R
import com.capstone.chillgoapp.navigation.NavigationItem
import com.capstone.chillgoapp.navigation.Screen
import com.capstone.chillgoapp.screens.CartScreen
import com.capstone.chillgoapp.screens.DetailScreen
import com.capstone.chillgoapp.screens.HomeScreen
import com.capstone.chillgoapp.screens.LoginScreen
import com.capstone.chillgoapp.screens.MoreScreen
import com.capstone.chillgoapp.screens.OnBoardingScreen
import com.capstone.chillgoapp.screens.ProfileScreen
import com.capstone.chillgoapp.screens.ReviewsScreen
import com.capstone.chillgoapp.screens.SignUpScreen
import com.capstone.chillgoapp.screens.TermAndConditionsScreen
import com.capstone.chillgoapp.screens.UmkmMapScreen
import com.capstone.chillgoapp.ui.theme.ChillGoAppTheme
import com.capstone.chillgoapp.ui.theme.PrimaryBorder
import com.capstone.chillgoapp.ui.theme.PrimaryMain

@Composable
fun DashboardApp(
    modifier: Modifier = Modifier,
) {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Splash.route &&
                currentRoute != Screen.OnBoarding.route &&
                currentRoute != Screen.LoginScreen.route &&
                currentRoute != Screen.SignUpScreen.route &&
                currentRoute != Screen.UmkmDetail.route &&
                currentRoute != Screen.TermsAndConditionsScreen.route &&
                currentRoute != Screen.DetailTicket.route &&
                currentRoute != Screen.Reviews.route
            ) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    onMoveScreen = {
                        navController.navigate(Screen.OnBoarding.route) {
                            popUpTo(Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(Screen.OnBoarding.route) {
                OnBoardingScreen(
                    onNavigateToLogin = {
                        navController.navigate(Screen.LoginScreen.route)
                    },
                    onNavigateToSignUp = {
                        navController.navigate(Screen.SignUpScreen.route)
                    }
                )
            }
            composable(Screen.LoginScreen.route) {
                LoginScreen(
                    onNavigateToSignUp = {
                        navController.navigate(Screen.SignUpScreen.route)
                    },
                    onNavigateToHome = {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.OnBoarding.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(Screen.SignUpScreen.route) {
                SignUpScreen(
                    onNavigateToLogin = {
                        navController.popBackStack()
                    },
                    onNavigateToHome = {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.OnBoarding.route) {
                                inclusive = true
                            }
                        }
                    },
                    onNavigateToTerm = {
                        navController.navigate(Screen.TermsAndConditionsScreen.route)
                    }
                )
            }
            composable(Screen.TermsAndConditionsScreen.route) {
                TermAndConditionsScreen()
            }
            composable(Screen.HomeScreen.route) {
                Column(
                    modifier = modifier
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                ) {
                    HomeScreen(
                        navigateToDetail = { ticketId ->
                            navController.navigate(Screen.DetailTicket.createRoute(ticketId))
                        },
                        navigateToMore = {
                            navController.navigate(Screen.More.route)
                        }
                    )
                }
            }
            composable(Screen.Cart.route) {
                CartScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navigateToDetail = { ticketId ->
                        navController.navigate(Screen.DetailTicket.createRoute(ticketId))
                    }
                )
            }
            composable(
                route = Screen.DetailTicket.route,
                arguments = listOf(navArgument("ticketId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("ticketId") ?: -1L
                DetailScreen(
                    ticketId = id,
                    navigateToReviews = {
                        navController.navigate(Screen.Reviews.route)
                    },
                    navigateToUmkmDetail = {
                        navController.navigate(Screen.UmkmDetail.route)
                    }
//                    navigateBack = {
//                        navController.navigateUp()
//                    },
//                    navigateToCart = {
//                        navController.popBackStack()
//                        navController.navigate(Screen.Cart.route) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
                )
            }
            composable(Screen.UmkmDetail.route) {
                UmkmMapScreen()
            }
            composable(Screen.More.route) {
                MoreScreen(
                    modifier = modifier.padding(innerPadding),
                    navigateToDetail = {
                        navController.navigate(Screen.DetailTicket.createRoute(it))
                    })
            }
            composable(Screen.Reviews.route) {
                ReviewsScreen(
                    onBackPressed = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = PrimaryMain
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.HomeScreen
            ),
            NavigationItem(
                title = stringResource(R.string.favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                    selectedIconColor = PrimaryBorder,
                    selectedTextColor = PrimaryBorder
                )
            )
        }
    }
}

private fun shareOrder(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.subject_ticket))
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.subject_ticket)
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    ChillGoAppTheme {
        DashboardApp()
    }
}
