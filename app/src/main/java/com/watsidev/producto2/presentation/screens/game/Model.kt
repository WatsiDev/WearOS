package com.watsidev.producto2.presentation.screens.game

import com.watsidev.producto2.R

data class Pokemon(
    val id: Int,
    val name: String,
    val hp: Int,
    val spriteFront: Int,
    val spriteBack: Int,
    val moves: List<String>,
    val cry: Int
)

val PokemonList = listOf(
    Pokemon(
        1,
        "Bulbasaur",
        45,
        R.drawable.bulbasaur_front,
        R.drawable.bulbasaur_back,
        listOf("Tackle", "Growl", "Vine Whip", "Razor Leaf"),
        R.raw.bulbasaur_cry
    ),
    Pokemon(
        2,
        "Ivysaur",
        60,
        R.drawable.ivysaur_front,
        R.drawable.ivysaur_back,
        listOf("Tackle", "Growl", "Vine Whip", "Sleep Powder"),
        R.raw.ivysaur_cry
    ),
    Pokemon(
        3,
        "Venusaur",
        80,
        R.drawable.venusaur_front,
        R.drawable.venusaur_back,
        listOf("Tackle", "Vine Whip", "Razor Leaf", "Solar Beam"),
        R.raw.venusaur_cry
    ),
    Pokemon(
        4,
        "Charmander",
        39,
        R.drawable.charmander_front,
        R.drawable.charmander_back,
        listOf("Scratch", "Growl", "Ember", "Smokescreen"),
        R.raw.charmander_cry
    ),
    Pokemon(
        5,
        "Charmeleon",
        58,
        R.drawable.charmeleon_front,
        R.drawable.charmeleon_back,
        listOf("Scratch", "Ember", "Dragon Rage", "Scary Face"),
        R.raw.charmeleon_cry
    ),
    Pokemon(
        6,
        "Charizard",
        78,
        R.drawable.charizard_front,
        R.drawable.charizard_back,
        listOf("Flamethrower", "Fly", "Slash", "Fire Spin"),
        R.raw.charizard_cry
    ),
    Pokemon(
        7,
        "Squirtle",
        44,
        R.drawable.squirtle_front,
        R.drawable.squirtle_back,
        listOf("Tackle", "Tail Whip", "Water Gun", "Withdraw"),
        R.raw.squirtle_cry
    ),
    Pokemon(
        8,
        "Wartortle",
        59,
        R.drawable.wartortle_front,
        R.drawable.wartortle_back,
        listOf("Tackle", "Water Gun", "Bite", "Rapid Spin"),
        R.raw.wartortle_cry
    ),
    Pokemon(
        9,
        "Blastoise",
        79,
        R.drawable.blastoise_front,
        R.drawable.blastoise_back,
        listOf("Hydro Pump", "Skull Bash", "Bite", "Rain Dance"),
        R.raw.blastoise_cry
    ),
    Pokemon(
        10,
        "Caterpie",
        45,
        R.drawable.caterpie_front,
        R.drawable.caterpie_back,
        listOf("Tackle", "String Shot"),
        R.raw.caterpie_cry
    )
)