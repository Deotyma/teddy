<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';  // Assurez-vous que axios est importé
import "leaflet/dist/leaflet.css";
import * as L from 'leaflet';
import { MarkerClusterGroup } from 'leaflet.markercluster';
import favicon from '../assets/favicon-32x32.png';

const initialMap = ref(null);

const myIcon = L.icon({
    iconUrl: favicon,
    iconSize: [30, 30],
    iconAnchor: [15, 30],
    popupAnchor: [-3, -76]
});

// Fonction asynchrone pour récupérer et afficher les données des annonces
const fetchAnnonceData = async () => {
    try {
        const annoncesResponse = await axios.get('http://localhost:8080/annonces');
        const annonces = annoncesResponse.data;

        annonces.forEach(async annonce => {
            if (annonce.localityId) {
                const localityResponse = await axios.get(`http://localhost:8080/localities/${annonce.localityId}`);
                const locality = localityResponse.data;

                const marker = L.marker([locality.latitude, locality.longitude], { icon: myIcon })
                    .bindPopup(annonce.title);
                
                initialMap.value.addLayer(marker);  // Ajoutez directement chaque marqueur à la carte
            }
        });
    } catch (error) {
        console.error("Error fetching annonce data:", error);
    }
};

onMounted(() => {
    initialMap.value = L.map('map', {
        zoomControl: true,
        zoom: 9,
        zoomAnimation: false,
        fadeAnimation: true,
        markerZoomAnimation: true
    }).setView([48.8566, 2.3522], 9);  // Coordonnées centrées sur la France

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '© <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(initialMap.value);

    fetchAnnonceData();  // Appel de la fonction pour charger les données après l'initialisation de la carte
});
</script>

<template>
    <div>
        <h3>Les Annonces</h3>
        <div id="map" style="height: 90vh;"></div>
    </div>
</template>
