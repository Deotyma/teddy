<script setup>
    import UserInfo from '../components/UserInfo.vue';
    import { RouterLink } from 'vue-router';
</script>
<template>
     <main class="py-4">
        <div class="container">
            <UserInfo/>
            <div class="pt-3">
                <RouterLink to="create" class="btn btn-primary" role="button">{{ $t('user.createAnnonce') }}</RouterLink>
            </div>
        </div>
     </main>
</template>