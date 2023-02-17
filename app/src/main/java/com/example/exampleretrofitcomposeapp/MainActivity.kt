package com.example.exampleretrofitcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exampleretrofitcomposeapp.ui.theme.WeatherAppv1Theme
import com.example.exampleretrofitcomposeapp.viewmodels.MainViewModel
import com.example.exampleretrofitcomposeapp.views.HomeScreen

class MainActivity : ComponentActivity() {
    private val mainVm:MainViewModel by viewModels{
        ViewModelProvider.AndroidViewModelFactory.getInstance(application = application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp(mainVm)
        }
    }
}

@Composable
fun WeatherApp(
    mainVm:MainViewModel= viewModel()
){
    // miejsce na viewmodel

    val context = LocalContext.current

    val navController = rememberNavController()

    WeatherAppv1Theme {

        Scaffold {innerPadding->
            WeatherNavHost(
                mainVm,
                navController = navController,
                Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun WeatherNavHost(
    mainVm:MainViewModel= viewModel(),
    navController: NavHostController,
    modifier: Modifier=Modifier
){
    NavHost(
        navController=navController,
        startDestination = "Home",
        modifier = modifier
    ){
        composable("Home"){
            HomeScreen(mainVm)
        }
    }
    
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherAppv1Theme {
        WeatherApp()
    }
}