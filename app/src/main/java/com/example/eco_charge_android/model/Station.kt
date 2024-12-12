package com.example.eco_charge_android.model

import java.io.Serializable

/**
 * Classe de données représentant une station de recharge.
 * Implémente [Serializable] pour permettre la sérialisation lors des transferts d'objets.
 */
data class Station(
    val n_operateur: String,        // Nom de l'opérateur de la station.
    val n_enseigne: String,         // Nom de l'enseigne de la station.
    val id_station: String,         // Identifiant unique de la station.
    val n_station: String,          // Nom de la station.
    val ad_station: String,         // Adresse complète de la station.
    val code_insee: Int,            // Code INSEE de la commune où se trouve la station.
    val nbre_pdc: Int,              // Nombre de points de charge disponibles.
    val puiss_max: Double,          // Puissance maximale de charge (en kW).
    val type_prise: String,         // Type de prise de recharge disponible.
    val acces_recharge: String,     // Informations sur l'accès à la recharge.
    val accessibilite: String,      // Accessibilité de la station.
    val geo_point_borne: GeoPointBorne, // Coordonnées géographiques de la station (latitude/longitude).
    val code_insee_commune: String, // Code INSEE de la commune associée.
    val region: String,             // Région administrative où se trouve la station.
    val departement: String,        // Département où se situe la station.
    var favorite: Boolean           // Indique si la station est marquée comme favorite par l'utilisateur.
) : Serializable

