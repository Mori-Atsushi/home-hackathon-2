package com.example.home_hackathon2.ui.initial

import com.example.home_hackathon2.ui.tools.ViewModel
import com.example.home_hackathon2.usecase.CreateUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InitialViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    fun handleChangeName(name: String) {
        _name.value = name
    }

    fun submit() {
        viewModelScope.launch {
            val input = CreateUserUseCase.Input(_name.value)
            createUserUseCase.invoke(input)
        }
    }
}
