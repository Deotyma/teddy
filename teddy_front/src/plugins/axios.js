import axios from 'axios';

const ACCEPTED_STATUS = [200, 201, 202, 204, 400];
export default {
    install: (app) => {
        const http = axios.create({
            // Recuperation URL de base de l'API depuis le fichier des variables d'environnement
            baseURL: import.meta.env.VITE_API_BASE_URL,
            validateStatus: (status) => {
                // Fonction de callback appelee par axios pour valider ou non une reponse
                return ACCEPTED_STATUS.includes(status);
            }
        });
        http.interceptors.response.use((response) => {
            const status = response.status;
            const data = response.data;
            // Remplacement de chaine vide par null si pas de body
            const body = data != '' ? data : null;
            return { status: status, body: body };
        }, (error) => {
            return Promise.reject(error);
        });
        app.config.globalProperties.$axios = http;
    }
};