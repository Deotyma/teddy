<script setup>

const createdUser= {
        firstName:"",
        lastName:"",
        email:"",
        password:"",
        address:"",
        zipCode:"",
        town:""
    }

    function SignUpUser() {
        console.log(createdUser)
    }

</script>

<template>
     <div class="container-fluid justify-content-center" style="padding: 35px;">
        <div class="col-12 pt-4">
            <p class="text-center w-100 fs-1">Rejoignez <span class="pacifico text-primary fs-1">Teddy Blue</span></p>
        </div>
        <div class="row d-flex justify-content-around">
            <div class="bg-secondary col-sm-12 col-md-8 p-5 rounded-3">
                <form @submit.prevent="SignUpUser">
                    <div class="row">
                        <div class="col-12 col-md-6 mb-3">
                            <label for="firstName" class="form-label text-light fw-bolder fs-5">Prènom</label>
                            <input v-model="createdUser.firstName" type="text" class="form-control py-3" id="firstName"/>
                        </div>
                        <div class="col-12 col-md-6 mb-3">
                            <label for="lastName" class="form-label text-light fw-bolder fs-5">Nom</label>
                            <input  v-model="createdUser.lastName" type="text" class="form-control py-3" id="lastName"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label text-light fw-bolder fs-5">Email</label>
                        <input v-model="createdUser.email" type="email" class="form-control py-3" id="email" placeholder="name@example.com">
                    </div>
                    <div class="col-12 mb-3">
                        <label for="address" class="form-label text-light fw-bolder fs-5">Adresse</label>
                        <input v-model="createdUser.address" type="text" class="form-control py-3" id="address" placeholder="1, rue Saint Sevrin"/>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 mb-3">
                            <label for="zipCode" class="form-label text-light fw-bolder fs-5">Code postal</label>
                            <input v-model="createdUser.zipCode" type="text" class="form-control py-3" id="zipCode" placeholder="75009"/>
                        </div>
                        <div class="col-12 col-md-6 mb-3">
                            <label for="town" class="form-label text-light fw-bolder fs-5">Ville</label>
                            <input v-model="createdUser.town" type="text" class="form-control py-3" id="town" placeholder="Paris"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 mb-3">
                            <label for="password" class="form-label text-light fw-bolder fs-5">Mot de passe</label>
                            <input v-model="createdUser.password" type="password" class="form-control py-3" id="password"/>
                        </div>
                        <div class="col-12 col-md-6 mb-3">
                            <label for="repetedPasswprd" class="form-label text-light fw-bolder fs-5">Mot de passe répété</label>
                            <input type="password" class="form-control py-3" id="repetedPasswprd"/>
                        </div>
                    </div>
                    <div>
                        <button type="submit" class="btn search-button border-0 w-100 py-3 bg-primary text-light fw-bolder">S'inscrire</button>
                    </div>
                </form>
            </div>
        </div>
     </div>
</template>