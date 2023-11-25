<script setup>
import { ref, onBeforeMount } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';

const route = useRoute();
const annonceId = route.params.id;
const annonce = ref({});

async function initAnnonce() {
    try {
        const response = await axios.get(`http://localhost:8080/annonces/${annonceId}`);
        annonce.value = response.data;
    } catch (error) {
        console.error('Error fetching sticker details:', error);
    }
}
onBeforeMount(() => {
    initAnnonce();
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
                        <h2 class="name ">{{ annonce.title }} </h2>
                        <span class="badge text-bg-primary">{{ annonce.categoryName }}</span>
                        <span class="badge text-bg-primary">{{ annonce.sharingMethodName }}</span>
                        <p class="description">{{ annonce.textAnnonce }}</p>
                        <a class="btn btn-dark bg-secondary" href ="mailto:${annonce.userEmail}"><i class="fas fa-cart-arrow-down"></i>Contacter l'annonceur</a>
                    </div>
                </div>
                <div class="col-lg-3 order-lg-first justify-content-center">
                    <h2 class="text-center m-3">Nos suggestions </h2>
                        <ul>
                            <li class="mt-5">
                                Titre
                                <a href="#"><img src="../assets/images/bear2.png" alt="savon" class="col-12 suggestions"></a>
                            </li>
                            <li class="mt-5">
                                <a href="#"><img src="../assets/images/bear2.png" alt="savon" class="col-12 suggestions"></a>
                            </li>
                            <li class="mt-5">
                                <a href="#"><img src="../assets/images/bear2.png" alt="savon" class="col-12 suggestions"></a>
                            </li>
                        </ul>
                </div>
            </div>
        </div>
    </main>

    
</template>