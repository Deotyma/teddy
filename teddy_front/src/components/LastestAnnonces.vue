<script setup>
const props = defineProps({
  annonceData: Object
});
</script>
<template>
    <div>
        <div class="card" style="width: 18rem;">
            <img :src="`/teddyblueImg/${annonceData.photoLink}`" class="img-fluid rounded-start" :alt="annonceData.title">
            <div class="card-body">
                <h5 class="card-title">{{ annonceData.title }}</h5>
                <RouterLink :to="{ name: 'annonceDetail', params: { id: annonceData.id } }" class="btn search-button py-3 px-5 me-3 animated fadeIn bg-primary text-light fw-bolder fs-6">
                    {{ $t('annonce.see') }}
                </RouterLink>
            </div>
        </div>
    </div>
</template>
