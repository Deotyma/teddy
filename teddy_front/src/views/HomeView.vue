<script setup>
import { RouterLink } from 'vue-router'
import SearchBar from '../components/SearchBar.vue'
</script>

<template>
 <div class="container-fluid px-0 mx-0 row">
    <SearchBar/>
    <div class="row g-0 align-items-center flex-column-reverse flex-md-row d-flex justify-content-center px-0 mx-0 my-2">
        <div class="col-md-6 mx-2 d-flex justify-content-center row">
            <h1 class="display-5 mb-4">{{ $t('home.find') }} <span class="pacifico text-primary">{{ $t('home.toy') }}</span> {{ $t('home.for') }} </h1>
            <p class="pb-2">{{  $t('home.description') }}</p>
            <RouterLink to="sign-up" class="btn search-button py-3 px-5 me-3 animated fadeIn bg-primary text-light fw-bolder fs-6">{{ $t('home.register') }}</RouterLink>
        </div>
        <div class="col-md-5">
            <img class="img-fluid d-flex justify-content-end px-0 mx-0" src="../assets/images/bear1.png" alt="two teddy-bears">
        </div>
        </div>
  </div>

</template>
