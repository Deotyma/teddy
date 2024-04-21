<script setup>
import { RouterLink } from 'vue-router'
</script>

<template>
<div class="wrapper">
        <nav class="navbar navbar-dark navbar-expand-lg bg-primary">
        <div class="container-fluid">
            <RouterLink class="nav-link" to="/"> <img src="../assets/favicon-32x32.png"> <span class="pacifico">Teddy Blue</span></RouterLink>
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
              </ul>
            </div>
        </div>
    </nav>   
</div>
</template>