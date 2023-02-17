package com.example.exampleretrofitcomposeapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exampleretrofitcomposeapp.viewmodels.MainViewModel

@Composable
fun HomeScreen(
    mainVm: MainViewModel= viewModel()
){
    FormColumn(mainVm)
}

@Composable
fun FormColumn(
    mainVm:MainViewModel= viewModel(),
    modifier: Modifier=Modifier
){

    val character by mainVm.character.collectAsState(initial = null)


    val lat = remember{ mutableStateOf("") }
    val lon = remember{ mutableStateOf("") }
    val id = remember{ mutableStateOf("") }

    Column() {
        Spacer(modifier = Modifier.height(10.dp))
        SearchBox(hint = "id", text = id)
        Spacer(modifier = Modifier.height(20.dp))
        //SearchBox(hint = "lat", text = lat)
        //Spacer(modifier = Modifier.height(20.dp))
        //SearchBox(hint = "lon", text = lon)
        //Spacer(modifier = Modifier.height(20.dp))
        CommitButton(title = "Search",{
            try {
                mainVm.performFethSingleCharacter(id.value.toInt())
            }catch (e:java.lang.Exception){}
        })
        Spacer(modifier = Modifier.height(20.dp))
        if(character != null){
            Text(text = character.toString())
        }
    }
}


@Composable
fun SearchBox(
    hint:String,
    text: MutableState<String>,
    modifier: Modifier=Modifier
){
    OutlinedTextField(
        value = text.value,
        onValueChange = {text.value=it},
        placeholder = { Text(text = hint)},
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
    )

}

@Composable
fun CommitButton(
    title:String,
    onClickSearch: () -> Unit = {},
    modifier: Modifier=Modifier
){
    Button(
        onClick = onClickSearch,
        Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
    ) {
        Text(text=title)
    }
}