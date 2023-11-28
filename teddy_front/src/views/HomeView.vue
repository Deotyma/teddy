<script setup>
import { RouterLink } from 'vue-router';
import Annonce from '../components/Annonce.vue';
import { ref, onMounted } from 'vue';
import axios from 'axios';

const annonces = ref([]);

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/annonces');
    annonces.value = response.data;
  } catch (error) {
    console.error('Error fetching annonces:', error);
  }
});
</script>

<template>
<div class="container-fluid px-0 mx-0 row">
    <div class="row g-0 align-items-center flex-column flex-md-row d-flex justify-content-center px-0 mx-0 my-2">
        <div class="col-md-6 mx-2 d-flex justify-content-center row">
            <h1 class="display-5 mb-4">{{ $t('home.find') }} <span class="pacifico text-primary">{{ $t('home.toy') }}</span> {{ $t('home.for') }} </h1>
            <p class="pb-2">{{  $t('home.description') }}</p>
            <RouterLink to="sign-up" class="btn search-button py-3 px-5 me-3 animated fadeIn bg-primary text-light fw-bolder fs-6">{{ $t('home.register') }}</RouterLink>
        </div>
        <div class="col-md-5">
            <img class="img-fluid d-flex justify-content-end px-0 mx-0" src="../assets/images/bear1.png" alt="two teddy-bears">
        </div>
        <h2 class="pacifico text-primary text-center mt-3" >{{  $t('home.new') }}</h2>
        <div class="row d-flex justify-content-around">
          <div class="col-md-6 d-flex justify-content-center"  v-for="annonce in annonces" :key="annonce.id" >
            <Annonce :annonceData="annonce" />
          </div>
        </div>
    </div>
</div>
</template>
