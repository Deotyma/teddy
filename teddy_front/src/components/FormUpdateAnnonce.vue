<script setup>
import { ref, reactive, onMounted, computed, watch } from 'vue';
import axios from 'axios';

// Extract annonce ID from URL
const pathParts = window.location.pathname.split('/');
const annonceId = pathParts[pathParts.length - 1];

// Reactive state for categories, sharing methods, and form inputs
const categories = ref([]);
const sharingMethods = ref([]);
const inputs = reactive({
    title: '',
    textAnnonce: '',
    zipCode: '',
    cityCode: '',
    categoriesId: null,
    sharingMethodsId: null,
    file: null
});

const token = localStorage.getItem('accessToken');

if (token) {
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
} else {
    console.error('No token found');
}

const userId = localStorage.getItem('userId');

// API calls for fetching data
const fetchAnnonceData = async () => {
    try {
        const annonceResponse = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/annonces/${annonceId}`);
        const annonceData = annonceResponse.data;
        inputs.title = annonceData.title;
        inputs.textAnnonce = annonceData.textAnnonce;
        inputs.categoriesId = annonceData.categoryId;
        inputs.sharingMethodsId = annonceData.sharingMethodId;

        if (annonceData.localityId) {
            const localityResponse = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/localities/${annonceData.localityId}`);
            const localityData = localityResponse.data;
            inputs.zipCode = localityData.zipCode;
            inputs.cityCode = localityData.cityCode;
        }
    } catch (error) {
        console.error("Error fetching annonce data:", error);
    }
};

const updateAnnonce = async () => {
    try {
        const formData = new FormData();
        formData.append('title', inputs.title);
        formData.append('textAnnonce', inputs.textAnnonce);
        formData.append('zipCode', inputs.zipCode);
        formData.append('cityCode', inputs.cityCode);
        formData.append('categoryId', inputs.categoriesId);
        formData.append('sharingMethodId', inputs.sharingMethodsId);
        formData.append('photoLink', inputs.file);
        formData.append('userId', userId);
        if (inputs.file) {
            formData.append('photo', inputs.file);
        }

        const response = await axios.patch(`${import.meta.env.VITE_API_BASE_URL}/annonces/update/${annonceId}`, formData,{
            headers: {
                'Content-Type': 'multipart/form-data',
                Authorization: `Bearer ${token}`
            },
        });
        console.log('Annonce updated:', response.data);
    } catch (error) {
        console.error('Error updating annonce:', error.response ? error.response.data : error);
    }
};

// Fetch categories and sharing methods data
const fetchCategoriesAndMethods = async () => {
    try {
        const [catResponse, methodResponse] = await Promise.all([
            axios.get(`${import.meta.env.VITE_API_BASE_URL}/categories`),
            axios.get(`${import.meta.env.VITE_API_BASE_URL}/sharingMethods`)
        ]);
        categories.value = catResponse.data;
        sharingMethods.value = methodResponse.data;
    } catch (error) {
        console.error("Error fetching setup data:", error);
    }
};

// File selection handler
const fileSelected = (event) => {
    inputs.file = event.target.files[0];
};

const suggestions = ref([]);
const selectedCity = ref('');

const searchZipCode = computed(() => {
    if (inputs.zipCode === '') {
        return [];
    }

    let matches = 0;

    return suggestions.value.filter(suggestion => {
        if (suggestion.zipCode.includes(inputs.zipCode) && matches < 10) {
            matches++;
            return suggestion;
        }
    });
});

const fetchLocationInfo = async (zipCode) => {
    if (zipCode) {
        try {
            const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/localities/search?zipCode=${zipCode}`);
            console.log('API response:', response.data);
            if (response.data && response.data.length > 0) {
                suggestions.value = response.data;
            } else {
                console.error('No locality data available');
            }
        } catch (error) {
            console.error('Error fetching location info:', error);
        }
    }
};

const selectSuggestion = (suggestion) => {
    inputs.zipCode = suggestion.zipCode;
    inputs.cityCode = suggestion.cityCode;
    selectedCity.value = suggestion.cityCode;
    suggestions.value = [];
};

watch(() => inputs.zipCode, (newZipCode) => {
    if (newZipCode) {
        fetchLocationInfo(newZipCode);
    }
});

// Initialization on component mount
onMounted(() => {
    fetchAnnonceData();
    fetchCategoriesAndMethods();
    updateAnnonce()
});
</script>

<template>
    <div class="container-fluid justify-content-center" style="padding: 35px;">
        <div class="col-12 pt-4">
            <p class="text-center w-100 fs-1">Mettre à jour l'annonce</p>
        </div>
        <div class="row d-flex justify-content-around">
            <div class="bg-secondary col-sm-12 col-md-8 p-5 rounded-3">
                <form @submit.prevent="updateAnnonce">
                    <div class="row">
                        <!-- Form fields for annonce data -->
                        <div class="col-12 mb-3">
                            <label for="title" class="form-label text-light fw-bolder fs-5">Titre</label>
                            <input type="text" class="form-control py-3" id="title" v-model.trim="inputs.title"/>
                        </div>
                        <div class="col-12 mb-3">
                            <label for="textAnnonce" class="form-label text-light fw-bolder fs-5">Description</label>
                            <textarea class="form-control" id="textAnnonce" rows="3" v-model.trim="inputs.textAnnonce"></textarea>
                        </div>
                        <div class="col-12 mb-3">
                            <label for="photoLink" class="form-label text-light fw-bolder fs-5">Photo</label>
                            <input class="form-control form-control-lg" type="file" accept="image/png,image/gif,image/jpeg" id="photoLink" @change="fileSelected">
                            <img :src="`/teddyblueImg/${inputs.file}`" class="img-thumbnail rounded float-start small-image" alt="Current annonce image">
                        </div>
                        <!-- More input fields -->
                        <div class="col-6 mb-3">
                           <label for="zipCode" class="form-label text-light fw-bolder fs-5">{{ $t('formAnnonce.zipCode') }}</label>
                           <input type="text" class="form-control py-3" id="zipCode" v-model.trim="inputs.zipCode"/>
                           <ul v-if="searchZipCode.length" class="autocomplete-results">
                               <li v-for="suggestion in searchZipCode" :key="suggestion.zipCode" @click="selectSuggestion(suggestion)">
                                   {{ suggestion.zipCode }}
                               </li>
                           </ul>
                       </div>
                        <div class="col-6 mb-3">
                            <label for="cityCode" class="form-label text-light fw-bolder fs-5">Code Ville</label>
                            <input type="text" class="form-control py-3" id="cityCode" v-model.trim="inputs.cityCode"/>
                        </div>
                        <!-- Dropdowns for categories and sharing methods -->
                        <div class="col-md-6 mb-3">
                            <label for="categories" class="form-label text-light fw-bolder fs-5">Catégorie</label>
                            <select v-model.number="inputs.categoriesId" id="categories" class="form-select py-3">
                                <option selected disabled value="0">Choisir une catégorie</option>
                                <option v-for="category in categories" :value="category.id" :key="category.id">{{ category.categoryName }}</option>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="sharingMethods" class="form-label text-light fw-bolder fs-5">Méthode de partage</label>
                            <select v-model.number="inputs.sharingMethodsId" id="sharingMethods" class="form-select py-3">
                                <option selected disabled value="0">Choisir une méthode de partage</option>
                                <option v-for="sharingMethod in sharingMethods" :value="sharingMethod.id" :key="sharingMethod.id">{{ sharingMethod.sharingMethodName }}</option>
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
    .small-image {
        width: 100px;
        height: auto;
    }
</style>