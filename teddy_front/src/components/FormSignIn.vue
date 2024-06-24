<script setup>
    import { RouterLink, useRouter  } from 'vue-router';
    import { reactive } from 'vue';
    import { useVuelidate } from '@vuelidate/core';
    import { required, minLength, maxLength, email, helpers} from '@vuelidate/validators';
    import axios from 'axios';

    const router = useRouter();

    const inputs = reactive({
      email: "",
      password: ""
    });

    const rules = {
        email: { 
            required: helpers.withMessage('required', required),  
            email: helpers.withMessage('email', email)
        },
        password: { 
            required: helpers.withMessage('required', required), 
            minLength:helpers.withMessage('minLength', minLength(8)), 
            maxLength:helpers.withMessage('maxLength', maxLength(20))
        }
    };

    const v$ = useVuelidate(rules, inputs);

    const logInUser = async() => {
        v$.value.$touch();
        if (!v$.value.$error) {
            const user = {
                email: inputs.email,
                password: inputs.password,
            };

            try {
                const response = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/auth/authenticate`, user);
                localStorage.setItem('accessToken', response.data.accessToken);
                localStorage.setItem('userId', response.data.userId);
                console.log("Les données sont valides et l'utilisateur a été connecté:", response.data);
                router.push({ name: 'user', params: { id: response.data.userId } });
            } catch (error) {
                console.error("Erreur lors de l'authentication de l'utilisateur :", error.response ? error.response.data : error.message);
            }
        } else {
            console.log("Des erreurs de authentication sont présentes", v$.value);
            console.log("Données saisies :", inputs);
        }
    }

</script>

<template>
    
    <div class="container-fluid py-2" style="padding: 35px;">
        <div class="col-12 pt-4">
            <p class="text-center w-100 fs-1">Connectez-vous sur</p>
            <p class="pacifico text-primary text-center w-100 fs-1">Teddy Blue</p>
        </div>
        <div class="row d-flex justify-content-around">
            <div class="col-md-5 d-none d-md-block">
                <img class="img-fluid d-flex justify-content-end px-0 mx-0 rounded-3" src="../assets/images/bear2.png" alt="two teddy-bears">
            </div>
            <div class="bg-secondary col-md-5 p-5 rounded-3">
                <form @submit.prevent="logInUser">
                    <div class="mb-3">
                        <label for="email" class="form-label text-light fw-bolder fs-5">Email</label>
                        <input v-model="inputs.email" type="email" class="form-control py-3" id="email" placeholder="name@example.com" :class="{'is-invalid' : v$.email.$error}">
                        <div v-if="v$.email.$errors">
                            <p v-for="error of v$.email.$errors" :key="error.$uid" class="text-danger">
                                {{ $t(`errorMessages.${error.$message}`) }}
                            </p>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label text-light fw-bolder fs-5">Mot de passe</label>
                        <input v-model="inputs.password" type="password" class="form-control py-3" id="password" :class="{'is-invalid' : v$.password.$error}">
                        <div v-if="v$.password.$errors">
                            <p v-for="error of v$.password.$errors" :key="error.$uid" class="text-danger">
                                {{ $t(`errorMessages.${error.$message}`) }}
                            </p>
                        </div>
                    </div>

                    <div class="my-3 d-flex justify-content-end">
                        <a href="#" class="text-primary fw-bolder fs-5">Mot de passe oublié?</a>
                    </div>

                    <div>
                        <button type="submit" class="btn search-button border-0 w-100 py-3 bg-primary text-light fw-bolder">Se connecter</button>
                    </div>
                </form>
                <div class="row py-3 d-flex justify-content-center">
                    <div>
                        <p class="text-center text-light fw-bolder fs-6">Vous découvrez <span class="pacifico">Teddy Blue</span></p>
                        </div>
                    <div class="d-flex justify-content-center">
                        <RouterLink to="register" class="text-center text-primary fw-bolder fs-6 text-decoration-none">Inscrivez Vous</RouterLink>
                    </div>
                </div>
            </div>
        </div> 
    </div>
</template>