package com.example.kotlinandroiddaggerhilt.ui

import androidx.lifecycle.*
import com.example.kotlinandroiddaggerhilt.SuccessResult
import com.example.kotlinandroiddaggerhilt.models.GithubRepositoryModel
import com.example.kotlinandroiddaggerhilt.repsitory.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

    private val _repo = MutableLiveData<SuccessResult<List<GithubRepositoryModel>>>()

    val repo : LiveData<SuccessResult<List<GithubRepositoryModel>>>
        get() = _repo

    init {
        getAllRepo()
    }

    fun getAllRepo(){

        viewModelScope.launch {

            mainRepository.getRepo().let {
                if (it.isSuccessful){
                    _repo.postValue(SuccessResult.success(it.body()))
                }
                else{
                    _repo.postValue(SuccessResult.error(it.errorBody().toString(), null))
                }
            }

        }

    }

}