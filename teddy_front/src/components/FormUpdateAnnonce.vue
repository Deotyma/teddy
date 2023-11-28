<script setup>
import { ref, reactive, onMounted } from 'vue';
import axios from 'axios';

// Extract user ID and annonce ID from URL
const pathParts = window.location.pathname.split('/');
const userId = pathParts[2];
const annonceId = pathParts[4];

// Initialize categories, sharing methods, and form inputs
const categories = ref([]);
const sharingMethods = ref([]);
const inputs = reactive({
    title: '',
    textAnnonce: '',
    categoriesId: { id: null },
    sharingMethodsId: { id: null },
    file: null
});

// Fetch existing annonce data
const fetchAnnonceData = async () => {
    try {
        const response = await axios.get(`http://localhost:8080/annonces/${annonceId}`);
        const data = response.data;
        inputs.title = data.title;
        inputs.textAnnonce = data.textAnnonce;
        inputs.categoriesId.id = data.categoryId;
        inputs.sharingMethodsId.id = data.sharingMethodId;
        inputs.file = data.photoLink;
        // Handle file if necessary
    } catch (error) {
        console.error("Error fetching annonce data:", error);
    }
};

// Function to update annonce
const updateAnnonce = async () => {
    try {
        const formData = new FormData();
        formData.append('title', inputs.title);
        formData.append('textAnnonce', inputs.textAnnonce);
        formData.append('categoryId', inputs.categoriesId.id);
        formData.append('sharingMethodId', inputs.sharingMethodsId.id);
        formData.append('userId', userId);
        if (inputs.file) {
            formData.append('photoLink', inputs.file);
        }

        const response = await axios.patch(`http://localhost:8080/annonces/update/${annonceId}`, formData, {
            headers: { 'Content-Type': 'multipart/form-data' },
        });

        console.log('Annonce updated:', response.data);
        // Handle success (e.g., redirecting, showing a success message, etc.)
    } catch (error) {
        console.error('Error updating annonce:', error);
    }
};

// File selected handler
const fileSelected = (event) => {
    inputs.file = event.target.files[0];
};

// Fetch categories and sharing methods
const fetchCategories = async () => {
    try {
        const response = await axios.get('http://localhost:8080/categories');
        categories.value = response.data;
    } catch (error) {
        console.error("Error fetching categories:", error);
    }
};

const fetchSharingMethods = async () => {
    try {
        const response = await axios.get('http://localhost:8080/sharingMethods');
        sharingMethods.value = response.data;
    } catch (error) {
        console.error("Error fetching sharing methods:", error);
    }
};

// On component mount
onMounted(() => {
    fetchAnnonceData();
    fetchCategories();
    fetchSharingMethods();
});
</script>

<template>
     <div class="container-fluid justify-content-center" style="padding: 35px;">
        <div class="col-12 pt-4">
            <p class="text-center w-100 fs-1">{{ $t('formAnnonce.create') }}</p>
        </div>
        <div class="row d-flex justify-content-around">
            <div class="bg-secondary col-sm-12 col-md-8 p-5 rounded-3">
                <form @submit.prevent="updateAnnonce">
                    <div class="row">
                        <div class="col-12 mb-3">
                            <label for="title" class="form-label text-light fw-bolder fs-5">{{ $t('formAnnonce.title') }}</label>
                            <input type="text" class="form-control py-3" id="title" v-model.trim="inputs.title"/>
                        </div>
                        <div class="col-12 mb-3">
                            <label for="textAnnonce" class="form-label text-light fw-bolder fs-5">{{ $t('formAnnonce.text') }}</label>
                            <textarea class="form-control" id="textAnnonce" rows="3" v-model.trim="inputs.textAnnonce"></textarea>
                        </div>
                        <div>
                            <label for="photoLink" class="form-label text-light fw-bolder fs-5">{{ $t('formAnnonce.photo') }}</label>
                            <input class="form-control form-control-lg" type="file" accept="image/png,image/gif,image/jpeg" id="photoLink" @change="fileSelected">
                            <div class="form-text text-light">{{ $t('formAnnonce.photoInstructions') }}</div>
                            <img :src="`/teddyblueImg/${inputs.file}`" class="img-thumbnail rounded float-start small-image" alt="Annonce image">
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="categories" class="form-label text-light fw-bolder fs-5">Categorie</label>
                            <select v-model.number="inputs.categoriesId.id" id="categories" name="categories" class="form-select py-3">
                                <option selected disabled value="0">Categorie</option>
                                <option v-for="category in categories" :value="category.id" :key="category.id">
                                    {{ category.categoryName }}
                                </option>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="sharingMethods" class="form-label text-light fw-bolder fs-5">Sharing Methods</label>
                            <select v-model.number="inputs.sharingMethodsId.id" id="sharingMethods" name="sharingMethods" class="form-select py-3">
                                <option selected disabled value="0">Sharing Methods</option>
                                <option v-for="sharingMethod in sharingMethods" :value="sharingMethod.id" :key="sharingMethod.id">
                                    {{ sharingMethod.sharingMethodName }}
                                </option>
                            </select>
                        </div>
                        <button type="submit" class="btn search-button border-0 w-100 py-3 bg-primary text-light fw-bolder">
                            Mettre à jour l'annonce
                        </button>
                    </div>    
                </form>
            </div>
        </div>
     </div>
</template>

<style>
    .small-image{
    width: 100px;
    height: auto;
}
</style>