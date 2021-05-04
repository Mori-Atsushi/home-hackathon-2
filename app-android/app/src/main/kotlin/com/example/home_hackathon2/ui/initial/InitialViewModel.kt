package com.example.home_hackathon2.ui.initial

import com.example.home_hackathon2.repository.UserRepository
import com.example.home_hackathon2.ui.tools.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InitialViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    fun handleChangeName(name: String) {
        _name.value = name
    }

    fun submit() {
        if (_name.value.isNotBlank()) {
            viewModelScope.launch {
                userRepository.createUser(_name.value)
            }
        }
    }
}
