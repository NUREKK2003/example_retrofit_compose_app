package com.example.exampleretrofitcomposeapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.exampleretrofitcomposeapp.data.models.SerialCharacter
import com.example.exampleretrofitcomposeapp.repositories.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repo = Repository(application)

    private val _character = MutableStateFlow<SerialCharacter?>(null)
    val character = _character.asStateFlow()

    fun performFethSingleCharacter(id:Int) = viewModelScope.launch {
        val local = repo.getCharacter(id).first()

        if(local != null){
            Log.d("PROCESS_D","Local source")
            _character.update { local }
            return@launch
        }

        val remote = repo.loadCharacter(id)

        if(remote.isSuccessful){
            val data = remote.body()

            if(data != null){
                _character.update { data }
                repo.insertAll(listOf(data))
            }
        }
    }

}