package com.watsidev.producto2.presentation.screens.game

data class CombatUiState(
    val myPokemon: Pokemon? = null,
    val enemyPokemon: Pokemon? = null,
    val myHp: Int = 0,
    val myHpMax: Int = 0,
    val enemyHp: Int = 0,
    val enemyHpMax: Int = 0,
    val isAttacking: Boolean = false,
    val winnerMessage: String? = null
)
