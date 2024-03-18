package com.capstone.chillgoapp.navigation

sealed class Screen(
    val route: String = ""
) {

    object SignUpScreen : Screen("signup")
    object TermsAndConditionsScreen : Screen("terms")
    object LoginScreen : Screen("login")
    object HomeScreen : Screen("home")

    object Splash : Screen("splash")
    object OnBoarding : Screen("onBoarding")
    object Dashboard : Screen("dashboard")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object More : Screen("more")
    object Reviews : Screen("reviews")
    object UmkmDetail : Screen("umkmDetail")
    object DetailTicket : Screen("home/{ticketId}") {
        fun createRoute(ticketId: Long) = "home/$ticketId"
    }
}


//object PostOfficeAppRouter {
//
//    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)
//
//    fun navigateTo(destination : Screen){
//        currentScreen.value = destination
//    }
//
//
//}