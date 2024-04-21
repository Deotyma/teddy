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
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
    </div>
</template>
