<script setup>
import { ref, onBeforeMount } from 'vue';
import axios from 'axios';
import { RouterLink, useRouter, useRoute} from 'vue-router';
import LastestAnnonces from './LastestAnnonces.vue';

const route = useRoute();
const router = useRouter();
const annonceId = route.params.id;
const annonce = ref({});
const lastestAnnonces = ref([]);
const isAuthenticated = ref(false)
const userId = localStorage.getItem('userId');

async function initAnnonce() {
    try {
        const response = await axios.get(`http://localhost:8080/annonces/${annonceId}`);
        annonce.value = response.data;
    } catch (error) {
        console.error('Error fetching annonce details:', error);
    }
}

async function initLastAnnonce() {
    try {
        const response = await axios.get(`http://localhost:8080/annonces/lastest`);
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
     if (token){
        isAuthenticated.value = true
    } else {
        isAuthenticated.value = false;
    }
}

async function deleteAnnonce() {
    const token = getToken();
    if (!token) {
        console.error('No token found');
        return;
    }
    try {
        await axios.delete(`http://localhost:8080/annonces/delete/${annonceId}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        router.push({ name: 'user', params: { id:userId } });
    } catch (error) {
        console.error('Error deleting annonce:', error.response ? error.response.data : error);
    }
}

onBeforeMount(() => {
    initAnnonce();
    initLastAnnonce();
    checkAuthentication()
});
</script>

<template>
    <main class="py-4">
        <div class="container">
            <div class="row">
                <div class="col-lg-9">
                    <div class="row justify-content-center">
                        <div class="imgProd col-lg-12 py-4">
                            <img :src="`/teddyblueImg/${annonce.photoLink}`" alt="" class="image col-lg-8 offset-lg-2">
                        </div>
                    </div>
                    <div class="col-lg-12 text-center">
                        <h2 class="name">{{ annonce.title }}</h2>
                        <span class="badge text-bg-primary">{{ annonce.categoryName }}</span>
                        <span class="badge text-bg-primary">{{ annonce.sharingMethodName }}</span>
                        <p class="description">{{ annonce.textAnnonce }}</p>
                        <div v-if="isAuthenticated">
                            <RouterLink :to="{ name: 'update', params: { id: annonceId }}" class="btn search-button py-3 px-5 me-3 animated fadeIn bg-warning text-light fw-bolder fs-6">Update</RouterLink>
                            <button @click="deleteAnnonce" type="button" class="btn search-button py-3 px-5 me-3 animated fadeIn bg-danger text-light fw-bolder fs-6">Delete</button>
                        </div>
                        <a :href="`mailto:${annonce.userEmail}`" class="btn btn-dark bg-secondary"><i class="fas fa-cart-arrow-down"></i>Contacter l'annonceur</a>

                    </div>
                </div>
                <div class="col-lg-3 order-lg-first justify-content-center">
                    <h2 class="m-3">Nos suggestions</h2>
                    <div class="col-md-6 d-flex justify-content-center" v-for="lastAnnonce in lastestAnnonces" :key="lastAnnonce.id">
                        <LastestAnnonces :annonceData="lastAnnonce" />
                    </div>
                </div>
            </div>
        </div>
    </main>
</template>