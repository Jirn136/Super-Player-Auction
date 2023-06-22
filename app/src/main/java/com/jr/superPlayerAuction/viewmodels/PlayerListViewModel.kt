package com.jr.superPlayerAuction.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jr.superPlayerAuction.model.Player
import com.jr.superPlayerAuction.repositories.interfaces.PlayerListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerListViewModel @Inject constructor(private val playerListRepository: PlayerListRepository) :
    ViewModel() {

    val retrievePlayerList: LiveData<ArrayList<Player>> get() = mRetrievePlayList
    private val mRetrievePlayList = MutableLiveData<ArrayList<Player>>()

    fun retrievePlayerList(teamName: String) {
        viewModelScope.launch {
            mRetrievePlayList.postValue(playerListRepository.retrievePlayersList(teamName))
        }
    }
}
