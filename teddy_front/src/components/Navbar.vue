<script setup>
import { RouterLink, useRouter } from 'vue-router'
import { ref, computed } from 'vue'

// Function to get user ID from local storage
const userId = ref(localStorage.getItem('userId'))
const router = useRouter()

// Function to generate user account link
const getUserAccountLink = () => {
  return userId.value ? { name: 'user', params: { id: userId.value } } : { name: 'authenticate' }
}

// Function to handle logout
const handleLogout = () => {
  localStorage.removeItem('userId')
  localStorage.removeItem('accessToken')
  router.push('/')
}

// Computed property to check if user is logged in
const isLoggedIn = computed(() => !!userId.value)
</script>

<template>
  <div class="wrapper">
    <nav class="navbar navbar-dark navbar-expand-lg bg-primary">
      <div class="container-fluid">
        <RouterLink class="nav-link" to="/"> 
          <img src="../assets/favicon-32x32.png"> 
          <span class="pacifico">Teddy Blue</span>
        </RouterLink>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <RouterLink class="nav-link active" to="/">Home</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" to="authenticate">Se connecter</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" to="register">S'inscrire</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" to="annonces-map">Annonces</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" :to="getUserAccountLink()">Mon Compte</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link " v-if="isLoggedIn" @click.native="handleLogout" to="/">Log Out</RouterLink>
              <RouterLink class="nav-link " v-else to="authenticate">Log Out</RouterLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>   
  </div>
</template>
