<script setup>
import { ref, onBeforeMount } from 'vue';
import axios from 'axios';
import { RouterLink, useRouter, useRoute } from 'vue-router';
import LastestAnnonces from './LastestAnnonces.vue';

const route = useRoute();
const router = useRouter();
const annonceId = route.params.id;
const annonce = ref({});
const lastestAnnonces = ref([]);
const isAuthenticated = ref(false);
const isCreator = ref(false); // New ref to check if the logged-in user is the creator
const userId = localStorage.getItem('userId');

async function initAnnonce() {
    try {
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/annonces/${annonceId}`);
        annonce.value = response.data;
        console.log('Annonce data:', annonce.value); // Log fetched annonce data
        checkCreator();
    } catch (error) {
        console.error('Error fetching annonce details:', error);
    }
}

async function initLastAnnonce() {
    try {
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/annonces/lastest`);
        lastestAnnonces.value = response.data;
    } catch (error) {
        console.error('Error fetching last annonces details:', error);
    }
}

function getToken() {
    return localStorage.getItem('accessToken');
}

async function checkAuthentication() {
    const token = getToken();
    if (token) {
        isAuthenticated.value = true;
    } else {
        isAuthenticated.value = false;
    }
}

function checkCreator() {
    console.log('Logged-in user ID:', userId); // Log the user ID from local storage
    console.log('Annonce creator ID:', annonce.value.userId); // Log the user ID from the fetched annonce
    if (userId && annonce.value.userId && userId === annonce.value.userId.toString()) {
        isCreator.value = true;
    } else {
        isCreator.value = false;
    }
    console.log('Is creator:', isCreator.value); // Log whether the user is the creator
}

function imgSrc(photoLink) {
    return `${import.meta.env.VITE_IMG_BASE_URL}/${photoLink}`;
}

async function deleteAnnonce() {
    const token = getToken();
    if (!token) {
        console.error('No token found');
        return;
    }
    try {
        await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/annonces/delete/${annonceId}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        router.push({ name: 'user', params: { id: userId } });
    } catch (error) {
        console.error('Error deleting annonce:', error.response ? error.response.data : error);
    }
}

onBeforeMount(() => {
    initAnnonce();
    initLastAnnonce();
    checkAuthentication();
});
</script>

<template>
    <main class="py-4">
        <div class="container">
            <div class="row">
                <div class="my-5 col-lg-9 order-lg-last">
                    <div class="row d-flex justify-content-around">
                        <div class="imgProd py-4 col-lg-5">
                            <img :src="imgSrc(annonce.photoLink)" alt="" class="img-fluid rounded-3 shadow-sm">
                        </div>
                        <div class="py-4 col-lg-5">
                            <h2 class="name fw-bold">{{ annonce.title }}</h2>
                            <div class="my-2">
                                <span class="badge bg-primary me-1">{{ annonce.categoryName }}</span>
                                <span class="badge bg-primary">{{ annonce.sharingMethodName }}</span>
                            </div>
                            <p class="description text-muted">{{ annonce.textAnnonce }}</p>
                            <div v-if="isAuthenticated && isCreator" class="my-3">
                                <RouterLink :to="{ name: 'update', params: { id: annonceId }}" class="btn btn-warning text-light me-2">Update</RouterLink>
                                <button @click="deleteAnnonce" type="button" class="btn btn-danger text-light me-2">Delete</button>
                            </div>
                            <a :href="`mailto:${annonce.userEmail}`" class="btn btn-secondary text-light"><i class="fas fa-envelope"></i> Contacter l'annonceur</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 order-lg-first">
                    <h2 class="m-3 text-center">Nos suggestions</h2>
                    <div class="col-12 mb-3" v-for="lastAnnonce in lastestAnnonces" :key="lastAnnonce.id">
                        <LastestAnnonces :annonceData="lastAnnonce" :imgSrc="imgSrc" />
                    </div>
                </div>
            </div>
        </div>
    </main>
</template>

<style>
.name {
    color: #2c3e50;
}
.description {
    font-size: 1.1rem;
}
.btn {
    transition: background-color 0.3s ease;
}
.btn:hover {
    background-color: #1a1a1a;
}
.imgProd img {
    transition: transform 0.3s ease;
}
.imgProd img:hover {
    transform: scale(1.05);
}
</style>

