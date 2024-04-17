<script setup>
    import { ref, reactive } from 'vue';
    import { useVuelidate } from '@vuelidate/core';
    import { required, minLength, maxLength, email, sameAs, helpers} from '@vuelidate/validators';
    import axios from 'axios';

const inputs= reactive({
        firstName:"",
        lastName:"",
        email:"",
        password:"",
        nickName:""
    })

const rules = {
    firstName:{ 
        required: helpers.withMessage('required', required),  
        minLength:helpers.withMessage('minLength', minLength(1))
    },
    lastName:{ 
        required: helpers.withMessage('required', required),  
        minLength:helpers.withMessage('minLength', minLength(1))
    },
    email: { 
        required: helpers.withMessage('required', required),  
        email: helpers.withMessage('email', email)
    },
    nickName: {
        required: helpers.withMessage('required', required),  
        minLength:helpers.withMessage('minLength', minLength(1))
    },
    password: { 
        required: helpers.withMessage('required', required), 
        minLength:helpers.withMessage('minLength', minLength(8)), 
        maxLength:helpers.withMessage('maxLength', maxLength(20)),
        hasUppercase: helpers.withMessage('hasUppercase', (value) => /[A-Z]/.test(value)),
        hasLowercase: helpers.withMessage('hasLowercase', (value) => /[a-z]/.test(value)),
        hasNumber:  helpers.withMessage('hasNumber', (value) =>/\d/.test(value)),
        hasSymbol: helpers.withMessage('hasSymbol', (value) =>/[$@$!%*?&]/.test(value))
    },
    /* repeatPassword:{
        required: helpers.withMessage('required', required),
        sameAs: helpers.withMessage('repeatPassword', sameAs(() => inputs.value.password))
    } */
    };

const v$ = useVuelidate(rules, inputs);

const SignUpUser = async () => {
    v$.value.$touch();
    if (!v$.value.$error) {
        const user = {
            firstName: inputs.firstName,
            lastName: inputs.lastName,
            email: inputs.email,
            password: inputs.password,
            nickName: inputs.nickName
        };

        try {
            const response = await axios.post('http://localhost:8080/auth/register', user);
            console.log("Les données sont valides et l'utilisateur a été créé :", response.data);
        } catch (error) {
            console.error("Erreur lors de la création de l'utilisateur :", error.response ? error.response.data : error.message);
        }
    } else {
        console.log("Des erreurs de validation sont présentes", v$.value);
        console.log("Données saisies :", inputs);
    }
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
                            <label for="firstName" class="form-label text-light fw-bolder fs-5">{{ $t('userForm.firstName') }}</label>
                            <input v-model="inputs.firstName" type="text" class="form-control py-3" id="firstName" :class="{'is-invalid' : v$.firstName.$error}"/>
                            <div v-if="v$.firstName.$errors">
                                <p v-for="error of v$.firstName.$errors" :key="error.$uid" class="text-danger">
                                    {{ $t(`errorMessages.${error.$message}`) }}
                                </p>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 mb-3">
                            <label for="lastName" class="form-label text-light fw-bolder fs-5">{{ $t('userForm.lastName') }}</label>
                            <input  v-model="inputs.lastName" type="text" class="form-control py-3" id="lastName" :class="{'is-invalid' : v$.lastName.$error}"/>
                            <div v-if="v$.lastName.$errors">
                                <p v-for="error of v$.lastName.$errors" :key="error.$uid" class="text-danger">
                                    {{ $t(`errorMessages.${error.$message}`) }}
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 mb-3">
                            <label for="nickName" class="form-label text-light fw-bolder fs-5">{{ $t('userForm.nickName') }}</label>
                            <input  v-model="inputs.nickName" type="text" class="form-control py-3" id="nickName" :class="{'is-invalid' : v$.nickName.$error}"/>
                            <div v-if="v$.nickName.$errors">
                                <p v-for="error of v$.nickName.$errors" :key="error.$uid" class="text-danger">
                                    {{ $t(`errorMessages.${error.$message}`) }}
                                </p>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 mb-3">
                            <label for="email" class="form-label text-light fw-bolder fs-5">{{ $t('userForm.email') }}</label>
                            <input v-model="inputs.email" type="email" class="form-control py-3" id="email" placeholder="name@example.com" :class="{'is-invalid' : v$.email.$error}">
                            <div v-if="v$.email.$error">
                                <p v-for="error of v$.email.$errors" :key="error.$uid" class="text-danger">
                                    {{ $t(`errorMessages.${error.$message}`) }}
                                </p>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 mb-3">
                            <label for="password" class="form-label text-light fw-bolder fs-5">{{ $t('userForm.password') }}</label>
                            <input v-model="inputs.password" type="password" class="form-control py-3" id="password" :class="{'is-invalid' : v$.password.$error}"/>
                            <div v-if="v$.password.$errors">
                                <p v-for="error of v$.password.$errors" :key="error.$uid" class="text-danger">
                                    {{ $t(`errorMessages.${error.$message}`) }}
                                </p>
                            </div>
                        </div>
                      <!--  <div class="col-12 col-md-6 mb-3">
                            <label for="repeatPassword" class="form-label text-light fw-bolder fs-5">Confirmez le mot de passe</label>
                            <input v-model="inputs.repeatPassword" type="repeatPassword" class="form-control py-3" id="repeatPassword" :class="{'is-invalid' : v$.repeatPassword.$error}"/>
                            <div v-if="v$.repeatPassword.$errors">
                                <p v-for="error of v$.repeatPassword.$errors" :key="error.$uid" class="text-danger">
                                    {{ $t(`errorMessages.${error.$message}`) }}
                                </p>
                            </div>
                        </div> -->
                    </div>
                    <div>
                        <button type="submit" class="btn search-button border-0 w-100 py-3 bg-primary text-light fw-bolder">{{ $t('home.register') }}</button>
                    </div>
                </form>
            </div>
        </div>
     </div>
</template>