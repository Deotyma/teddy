<script setup>
const props = defineProps({
  annonceData: Object,
  imgSrc: Function
});
</script>
<template>
    <div class="d-flex justify-content-center">
        <div class="card" style="width: 16rem;">
            <img :src="imgSrc(annonceData.photoLink)" class="img-fluid rounded-start" :alt="annonceData.title">
            <div class="card-body">
                <h5 class="card-title">{{ annonceData.title }}</h5>
                <RouterLink :to="{ name: 'annonceDetail', params: { id: annonceData.id } }" class="btn text-light me-25 me-2 bg-primary text-light fw-bolder fs-6">
                    {{ $t('annonce.see') }}
                </RouterLink>
            </div>
        </div>
    </div>
</template>
