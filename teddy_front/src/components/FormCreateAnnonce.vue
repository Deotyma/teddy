<script setup>
import { ref, reactive } from 'vue';
import axios from 'axios';

// Initialize categories, sharing methods, and inputs
const categories = ref([]);
const sharingMethods = ref([]);
const inputs = reactive({
    title: '', // Add title
    textAnnonce: '', // Add textAnnonce
    categoriesId: { id: null },
    sharingMethodsId: { id: null },
    file: null
});

const userId = window.location.pathname.split('/')[2];

const createAnnonce = async () => {
    try {
        const formData = new FormData();
        formData.append('title', inputs.title);
        formData.append('textAnnonce', inputs.textAnnonce);
        formData.append('categoryId', inputs.categoriesId.id);
        formData.append('sharingMethodId', inputs.sharingMethodsId.id);
        formData.append('userId', userId);
        formData.append('photoLink', inputs.file);

        if (inputs.file) {
            formData.append('photo', inputs.file);
        }

        const response = await axios.post('http://localhost:8080/annonces', formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });

        console.log('Annonce created:', response.data);
        // Handle success (e.g., redirecting, showing a success message, etc.)
    } catch (error) {
        console.error('Error creating annonce:', error);
        
    }
};

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

fetchCategories();
fetchSharingMethods();
</script>


<template>
     <div class="container-fluid justify-content-center" style="padding: 35px;">
        <div class="col-12 pt-4">
            <p class="text-center w-100 fs-1">{{ $t('formAnnonce.create') }}</p>
        </div>
        <div class="row d-flex justify-content-around">
            <div class="bg-secondary col-sm-12 col-md-8 p-5 rounded-3">
                <form @submit.prevent="createAnnonce">
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
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="cathegories" class="form-label text-light fw-bolder fs-5">Categorie</label>
                            <select v-model.number="inputs.categoriesId.id" id="categories" name="categories" class="form-select py-3">
                                <option selected disabled value="0">Categorie</option>
                                <option v-for="category in categories" :value="category.id" :key="category.id">
                                    {{ category.categoryName }}
                                </option>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="sharingMethods" class="form-label text-light fw-bolder fs-5">sharing Methods</label>
                            <select v-model.number="inputs.sharingMethodsId.id" id="sharingMethods" name="sharingMethods" class="form-select py-3">
                                <option selected disabled value="0">Sharing Methods</option>
                                <option v-for="sharingMethod in sharingMethods" :value="sharingMethod.id" :key="sharingMethod.id">
                                    {{ sharingMethod.sharingMethodName }}
                                </option>
                            </select>
                        </div>
                        <button type="submit" class="btn search-button border-0 w-100 py-3 bg-primary text-light fw-bolder">Créer l'annonce</button>
                    </div>    
                </form>
            </div>
        </div>
     </div>
</template>