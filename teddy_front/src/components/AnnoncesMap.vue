<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import "leaflet/dist/leaflet.css";
import * as L from 'leaflet';
import favicon from '../assets/favicon-32x32.png';

function navigateToAnnonce(id) {
    router.push({ name: 'annonceDetail', params: { id: id } });
}

const initialMap = ref(null);

const myIcon = L.icon({
    iconUrl: favicon,
    iconSize: [30, 30],
    iconAnchor: [15, 30],
    popupAnchor: [-3, -76]
});

const fetchAnnonceData = async () => {
    try {
        const annoncesResponse = await axios.get('http://localhost:8080/annonces');
        const annonces = annoncesResponse.data;

        annonces.forEach(async annonce => {
            if (annonce.localityId) {
                const localityResponse = await axios.get(`http://localhost:8080/localities/${annonce.localityId}`);
                const locality = localityResponse.data;

                const popupContent = `
            <div>
                <h4>${annonce.title}</h4>
                <a href="#" onclick="navigateToAnnonce(${annonce.id})">Voir les détails</a>
            </div>
        `;

                const marker = L.marker([locality.latitude, locality.longitude], { icon: myIcon })
                    .bindPopup(popupContent);
                
                initialMap.value.addLayer(marker);
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
    }).setView([48.8566, 2.3522], 9);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '© <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(initialMap.value);

    fetchAnnonceData();
});
</script>

<template>
    <div>
        <h3>Les Annonces</h3>
        <div id="map" style="height: 80vh;"></div>
    </div>
</template>