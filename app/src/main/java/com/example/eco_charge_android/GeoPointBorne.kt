package com.example.eco_charge_android

import java.io.Serializable
/**
 * Classe représentant un point géographique avec des coordonnées de longitude et latitude.
 * Implémente l'interface [Serializable] pour permettre la sérialisation de l'objet,
 * facilitant son passage entre activités ou fragments.
 *
 * @property lon Longitude du point géographique.
 * @property lat Latitude du point géographique.
 */

data class GeoPointBorne(
    val lon: Double,
    val lat: Double
) : Serializable
