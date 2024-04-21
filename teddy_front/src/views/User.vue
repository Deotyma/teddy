<script setup>
import UserInfo from '../components/UserInfo.vue';
import { RouterLink } from 'vue-router';
import Annonce from '../components/Annonce.vue';
import { ref, onMounted } from 'vue';
import axios from 'axios';

const annonces = ref([]);

function getToken() {
    return localStorage.getItem('accessToken');
}

onMounted(async () => {
    const token = getToken();
    const userId = localStorage.getItem('userId');  // Assurez-vous que l'userId est stocké lors de l'authentification

    if (!token) {
        console.error('No token found');
        return;
    }
    if (!userId) {
        console.error('No user ID found');
        return;
    }
    
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    
    try {
        const response = await axios.get(`http://localhost:8080/annonces/by-user/${userId}`);
        annonces.value = response.data;
    } catch (error) {
        console.error('Error fetching annonces:', error);
    }
});
</script>

<template>
     <main class="py-4">
        <div class="container">
            <UserInfo/>
            <div class="pt-3">
                <RouterLink to="/create" class="btn btn-primary" role="button">{{ $t('user.createAnnonce') }}</RouterLink>
            </div>
            <div class="col-md-6 d-flex justify-content-center" v-for="annonce in annonces" :key="annonce.id">
                <Annonce :annonceData="annonce" />
            </div>
        </div>
     </main>
</template>
