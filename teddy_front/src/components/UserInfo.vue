<script setup>
    import { ref, onBeforeMount } from 'vue';
    import axios from 'axios';
    import { useRoute } from 'vue-router';

    const route = useRoute();
    const userId = route.params.id;
    const user = ref({});

    function getToken() {
        // Assurez-vous que le nom utilisé ici correspond à celui utilisé lors du stockage du token
        console.log(localStorage.getItem('accessToken')); // Correction de la faute de frappe
        return localStorage.getItem('accessToken'); // Correction de la faute de frappe
    }

    async function initUser() {
        const token = getToken();  // Utiliser 'token' qui est la valeur récupérée
        if (!token) {  // Correction: utiliser 'token' au lieu de 'accessToken'
            console.error('No token found');
            return;
        }
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`; // Utiliser 'token'

        try {
            const response = await axios.get(`http://localhost:8080/users/${userId}`);
            user.value = response.data;  // Assurez-vous d'assigner les données au bon référant
        } catch (error) {
            console.error('Error fetching user details:', error);
        }
    }

    onBeforeMount(() => {
        initUser();
    });
</script>

<template>
    <h1>{{ $t('user.hello') }} {{ user.firstName }}</h1>
    <div class="card" style="width: 40rem;">
    <div class="card-header bg-primary">
        {{ $t('user.info') }}
    </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">{{ $t('user.firstName') }}: {{ user.firstName }}</li>
            <li class="list-group-item">{{ $t('user.lastName') }}: {{ user.lastName }}</li>
            <li class="list-group-item">{{ $t('user.nickName') }}: {{ user.nickName }}</li>
            <li class="list-group-item">{{ $t('user.email') }}: {{ user.email }}</li>
        </ul>
        <div class="card-footer">
        Card footer
        </div>
    </div>
</template>