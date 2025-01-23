package innovatixhub.`in`.vardhan_yadav_seekho

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import innovatixhub.`in`.vardhan_yadav_seekho.presentation.AnimeDetailsScreen
import innovatixhub.`in`.vardhan_yadav_seekho.presentation.AnimeHomeScreen

@Composable
fun Navigation(){
    val navController= rememberNavController()
    NavHost(navController=navController, startDestination =AnimeScreens.HOME_SCREEN.name ){
       composable(AnimeScreens.HOME_SCREEN.name){
           AnimeHomeScreen(navController=navController)
       }
        composable(
            route = "${AnimeScreens.DETAILS_SCREEN.name}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            AnimeDetailsScreen(id,navController=navController)
        }
    }
}