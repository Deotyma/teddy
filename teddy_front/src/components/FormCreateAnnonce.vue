<script setup>
import { ref, reactive, computed, watch } from 'vue';
import axios from 'axios';

// Initialize categories, sharing methods, and inputs
const categories = ref([]);
const sharingMethods = ref([]);
const inputs = reactive({
    title: '',
    textAnnonce: '',
    zipCode: '',
    cityCode: '',
    categoriesId: { id: null },
    sharingMethodsId: { id: null },
    file: null
});

const userId = localStorage.getItem('userId');

function getToken() {
    console.log(localStorage.getItem('accessToken')); 
    return localStorage.getItem('accessToken');
}

const createAnnonce = async () => {
    const token = getToken(); 
    console.log("token de createAnnonce: ", token); 
    if (!token) {
        console.error('No token found');
        return;
    }
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    try {
        const formData = new FormData();
        formData.append('title', inputs.title);
        formData.append('textAnnonce', inputs.textAnnonce);
        formData.append('zipCode', inputs.zipCode);  
        formData.append('cityCode', inputs.cityCode);
        formData.append('categoryId', inputs.categoriesId.id);
        formData.append('sharingMethodId', inputs.sharingMethodsId.id);
        formData.append('userId', userId);
        formData.append('photoLink', inputs.file);
        if (inputs.file) {
            formData.append('photo', inputs.file);
        }
        const response = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/annonces`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
                Authorization: `Bearer ${token}`
            },
        });
        console.log('Annonce created:', response.data);
    } catch (error) {
        console.error('Error creating annonce:', error);
    }
};

const fileSelected = (event) => {
    inputs.file = event.target.files[0];
};

// Fetch categories and sharing methods
const fetchCategories = async () => {
    const token = getToken();  
    if (!token) {
        console.error('No token found');
        return;
    }
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    try {
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/categories`);
        categories.value = response.data;
    } catch (error) {
        console.error("Error fetching categories:", error);
    }
};

const fetchSharingMethods = async () => {
    const token = getToken();  
    if (!token) {
        console.error('No token found');
        return;
    }
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    try {
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/sharingMethods`);
        sharingMethods.value = response.data;
    } catch (error) {
        console.error("Error fetching sharing methods:", error);
    }
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
                           <label for="cityCode" class="form-label text-light fw-bolder fs-5">{{ $t('formAnnonce.cityCode') }}</label>
                           <input type="text" class="form-control py-3" id="cityCode" v-model.trim="inputs.cityCode" readonly/>
                           <li v-for="suggestion in searchZipCode" :key="suggestion.zipCode" @click="selectSuggestion(suggestion)">
                                {{ suggestion.cityCode }}
                            </li>
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
                       <button type="submit" class="btn search-button border-0 w-100 py-3 bg-primary text-light fw-bolder">Créer l'annonce</button>
                   </div>    
               </form>
           </div>
       </div>
    </div>
</template>