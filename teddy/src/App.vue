<script setup>
import { RouterLink, RouterView } from 'vue-router'
import Navebar from './components/Navebar.vue';
import SearchBar from './components/SearchBar.vue'
</script>

<template>
  <header>
    <Navebar/>
  </header>
  <SearchBar/>

  <RouterView />
</template>

<style scoped>

</style>
