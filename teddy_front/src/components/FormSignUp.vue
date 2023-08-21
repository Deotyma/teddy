<script setup>
    import { ref } from 'vue';
    import { useVuelidate } from '@vuelidate/core';
    import { required, minLength, maxLength, email,sameAs, helpers} from '@vuelidate/validators'
const createdUser= ref({
        firstName:"",
        lastName:"",
        email:"",
        password:"",
        repeatPassword:"",
        address:"",
        zipCode:"",
        town:""
    })

const rules = {
    firstName:{ 
        required, 
        minLength:(1)
    },
    lastName:{ 
        required, 
        minLength:(1)
    },
    email: { 
        required, 
        email 
    },
    password: { 
        required, 
        minLength: minLength(8), 
        maxLength:maxLength(20),
        hasUppercase: (value) => /[A-Z]/.test(value),
        hasLowercase: (value) => /[a-z]/.test(value),
        hasNumber: (value) => /\d/.test(value),
        hasSymbol: (value) => /[$@$!%*?&]/.test(value), 
    },
    //repeatPassword:{
    //    required,
    //    sameAsRef: sameAs(createdUser.password)
    //},
    address: {
        required
    },
    zipCode: {
        required
    },
    town: {
        required
    }
    };

const v$ = useVuelidate(rules, createdUser);

function SignUpUser() {
    console.log("password - ", createdUser.password)
    console.log("données: ",createdUser.value);
        v$.value.$touch();
    if (!v$.value.$error) {
        console.log(createdUser.value);
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
                            <input v-model="createdUser.firstName" type="text" class="form-control py-3" id="firstName" :class="{'is-invalid' : v$.firstName.$error}"/>
                            <div v-if="v$.firstName.$error">
                                <p v-for="error of v$.firstName.$errors" :key="error.$uid" class="text-danger">
                                    {{ $t(`errorMessages.${error.$message}`) }}
                                </p>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 mb-3">
                            <label for="lastName" class="form-label text-light fw-bolder fs-5">{{ $t('userForm.lastName') }}</label>
                            <input  v-model="createdUser.lastName" type="text" class="form-control py-3" id="lastName" :class="{'is-invalid' : v$.lastName.$error}"/>
                            <div v-if="v$.lastName.$error">
                                <p v-for="error of v$.lastName.$errors" :key="error.$uid" class="text-danger">
                                    {{ $t(`errorMessages.${error.$message}`) }}
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label text-light fw-bolder fs-5">{{ $t('userForm.email') }}</label>
                        <input v-model="createdUser.email" type="email" class="form-control py-3" id="email" placeholder="name@example.com" :class="{'is-invalid' : v$.email.$error}">
                        <div v-if="v$.email.$error">
                            <p v-for="error of v$.email.$errors" :key="error.$uid" class="text-danger">
                                {{ error.$message }}
                            </p>
                        </div>
                    </div>
                    <div class="col-12 mb-3">
                        <label for="address" class="form-label text-light fw-bolder fs-5">{{ $t('userForm.address') }}</label>
                        <input v-model="createdUser.address" type="text" class="form-control py-3" id="address" placeholder="1, rue Saint Sevrin" :class="{'is-invalid' : v$.address.$error}"/>
                        <div v-if="v$.address.$error">
                            <p v-for="error of v$.address.$errors" :key="error.$uid" class="text-danger">
                                {{ error.$message }}
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 mb-3">
                            <label for="zipCode" class="form-label text-light fw-bolder fs-5">{{ $t('userForm.zipCode') }}</label>
                            <input v-model="createdUser.zipCode" type="text" class="form-control py-3" id="zipCode" placeholder="75009" :class="{'is-invalid' : v$.zipCode.$error}"/>
                            <div v-if="v$.zipCode.$error">
                                <p v-for="error of v$.zipCode.$errors" :key="error.$uid" class="text-danger">
                                    {{ error.$message }}
                                </p>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 mb-3">
                            <label for="town" class="form-label text-light fw-bolder fs-5">{{ $t('userForm.town') }}</label>
                            <input v-model="createdUser.town" type="text" class="form-control py-3" id="town" placeholder="Paris" :class="{'is-invalid' : v$.town.$error}"/>
                            <div v-if="v$.town.$error">
                                <p v-for="error of v$.town.$errors" :key="error.$uid" class="text-danger">
                                    {{ error.$message }}
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 mb-3">
                            <label for="password" class="form-label text-light fw-bolder fs-5">{{ $t('userForm.password') }}</label>
                            <input v-model="createdUser.password" type="password" class="form-control py-3" id="password" :class="{'is-invalid' : v$.password.$error}"/>
                            <div v-if="v$.password.$errors">
                                <p v-for="error of v$.password.$errors" :key="error.$uid" class="text-danger">
                                    {{ error.$message }}
                                </p>
                            </div>
                        </div>
                       <!--<div class="col-12 col-md-6 mb-3">
                            <label for="repeatPassword" class="form-label text-light fw-bolder fs-5">Confirmez le mot de passe</label>
                            <input v-model="createdUser.repeatPassword" type="repeatPassword" class="form-control py-3" id="repeatPassword" :class="{'is-invalid' : v$.repeatPassword.$error}"/>
                            <div v-if="v$.repeatPassword.$errors">
                                <p v-for="error of v$.repeatPassword.$errors" :key="error.$uid" class="text-danger">
                                    {{ error.$message }}
                                </p>
                            </div>
                        </div>-->
                    </div>
                    <div>
                        <button type="submit" class="btn search-button border-0 w-100 py-3 bg-primary text-light fw-bolder">{{ $t('home.register') }}</button>
                    </div>
                </form>
            </div>
        </div>
     </div>
</template>