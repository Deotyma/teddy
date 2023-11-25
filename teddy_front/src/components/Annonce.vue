<script setup>
const props = defineProps({
  annonceData: Object
});

</script>
<template>
    <div class="card my-3 " style="max-width: 540px;">
      <div class="row g-0">
        <div class="col-md-4">
          <img :src="`/teddyblueImg/${annonceData.photoLink}`"  class="img-fluid rounded-start" alt=${annonceData.photo.title} >
        </div>
        <div class="col-md-8">
          <div class="card-body">
            <h5 class="card-title">{{ annonceData.title }}</h5>
            <p class="card-text">{{ annonceData.textAnnonce}}</p>
            <RouterLink :to="{ name: 'annonceDetail', params: { id: annonceData.id } }" class="btn search-button py-3 px-5 me-3 animated fadeIn bg-primary text-light fw-bolder fs-6">
 {{ $t('annonce.see') }}
</RouterLink>
          </div>
        </div>
      </div>
    </div>
</template>