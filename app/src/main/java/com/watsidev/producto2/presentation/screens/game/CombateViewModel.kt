package com.watsidev.producto2.presentation.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CombatViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CombatUiState())
    val uiState: StateFlow<CombatUiState> = _uiState.asStateFlow()

    fun iniciarCombate(playerId: Int) {
        val myPokemon = PokemonList.firstOrNull { it.id == playerId } ?: return
        val enemyPokemon = PokemonList.filter { it.id != playerId }.shuffled().first()

        _uiState.value = CombatUiState(
            myPokemon = myPokemon,
            enemyPokemon = enemyPokemon,
            myHp = myPokemon.hp,
            myHpMax = myPokemon.hp,
            enemyHp = enemyPokemon.hp,
            enemyHpMax = enemyPokemon.hp,
            isAttacking = false,
            winnerMessage = null
        )
    }

    fun atacar(move: String) {
        val current = _uiState.value
        if (current.isAttacking || current.winnerMessage != null) return

        _uiState.update { it.copy(isAttacking = true) }

        val damageToEnemy = (5..10).random()
        val newEnemyHp = (current.enemyHp - damageToEnemy).coerceAtLeast(0)

        _uiState.update {
            it.copy(enemyHp = newEnemyHp)
        }

        if (newEnemyHp == 0) {
            _uiState.update { it.copy(winnerMessage = "¡Ganaste!", isAttacking = false) }
            return
        }

        viewModelScope.launch {
            delay(1000)
            val damageToMe = (7..10).random()
            val newMyHp = (current.myHp - damageToMe).coerceAtLeast(0)

            val winner = if (newMyHp == 0) "¡Perdiste!" else null

            _uiState.update {
                it.copy(
                    myHp = newMyHp,
                    isAttacking = false,
                    winnerMessage = winner
                )
            }
        }
    }

    fun reset() {
        _uiState.value = CombatUiState()
    }
}