<script setup>
import { RouterLink, RouterView } from 'vue-router'
import Navebar from './components/Navebar.vue';
</script>

<template>
  <header>
    <Navebar/>
  </header>

  <RouterView />
</template>

<style scoped>

</style>
