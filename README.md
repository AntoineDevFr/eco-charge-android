# EcoCharge Android

EcoCharge Android est une application mobile qui permet aux utilisateurs de localiser des stations de recharge pour véhicules électriques, de consulter des informations détaillées sur celles-ci et de les visualiser sur une carte interactive. L'application utilise l'API Google Maps pour la visualisation des stations et communique avec un backend REST pour récupérer les données des stations.

---

## Fonctionnalités principales

### 1. **Affichage de la liste des stations**
- Permet de visualiser les stations de recharge sous forme de liste.
- Les informations incluent le nom, l'adresse et l'accessibilité de la station.

### 2. **Carte interactive**
- Les stations de recharge sont affichées sur une carte Google Maps.
- Chaque station est représentée par un marqueur avec des informations supplémentaires visibles en cliquant dessus.

### 3. **Détails des stations**
- Possibilité de consulter des détails approfondis sur une station sélectionnée.

### 4. **Rafraîchissement des données**
- Fonctionnalité permettant de recharger la liste des stations depuis le backend.

### 5. **Mise en favoris**
- Fonctionnalité permettant de mettre en favoris des stations.

### 6. **Rechercher une station selon son nom**
- Fonctionnalité permettant recherche une station en fonction de son nom.

---

## Technologies utilisées

### Frontend :
- **Langage :** Kotlin
- **UI :** Activity, Fragments, Toolbar
- **Cartographie :** Google Maps SDK pour Android

### Backend :
- **API REST :** Interaction avec un serveur distant utilisant Retrofit.
- **Base URL :** `https://eco-charge.cleverapps.io`

---

## Structure du projet

### **MainActivity**
- Point d'entrée de l'application.
- Gère la navigation entre les fragments :
    - Liste des stations.
    - Carte.
    - Informations supplémentaires.
- Initialise Retrofit et récupère la liste des stations via des appels API.

### **Fragments**
- `FragmentListe` : Affiche la liste des stations.
- `SupportMapFragment` : Affiche la carte des stations.
- `FragmentInfo` : Affiche des informations complémentaires.

### **Services**
- `StationService` : Interface définissant les appels API pour :
    - Récupérer toutes les stations.

### **Modèles**
- `Station` : Représente une station de recharge avec des attributs comme `id_station`, `geo_point_borne`, `n_station`, etc.
- `StationShelf` : Stocke localement les données des stations récupérées depuis le backend.

---

## Installation

### Prérequis
- Android Studio (version 2022 ou ultérieure)
- Clé API Google Maps (voir la documentation Google pour obtenir une clé API valide)

### Étapes d'installation
1. Clonez le dépôt sur votre machine locale :
    ```bash
    git clone https://github.com/votre-repo/eco_charge_android.git
    ```

2. Ouvrez le projet dans Android Studio.

3. Ajoutez votre clé API Google Maps dans le fichier `secrets.properties` :
    ```properties
    GOOGLE_API_KEY=your_google_maps_api_key
    ```

4. Synchronisez le projet avec Gradle.

5. Exécutez l'application sur un émulateur ou un appareil physique Android.

---

## Utilisation

### Navigation entre les écrans
- **Liste des stations** : Affichée par défaut au démarrage de l'application.
- **Carte** : Accédez à la carte en cliquant sur le bouton "Carte".
- **Détails** : Sélectionnez une station dans la liste ou sur la carte pour voir les détails.

### Rafraîchissement des données
- Utilisez l'icône de rafraîchissement dans la barre d'action pour recharger les stations depuis le serveur.

### Mise en favoris d'une station
- Sélectionner une station dans la liste.
- Cliquez sur l'icône en forme de cœur/bouton en base de la page pour ajouter ou supprimer une station de vos favoris.

---

## Auteur

- **Antoine Banchet**
- **Elliot Galaor**

