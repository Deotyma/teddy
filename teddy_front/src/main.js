import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import i18n from './i18n/i18n.js'
import axios from './plugins/axios';

const app = createApp(App)

window.navigateToAnnonce = id => {
    router.push({ name: 'annonceDetail', params: { id } });
};

app.use(router)
app.use(axios);
app.use(i18n)

app.mount('#app')

console.log(import.meta.env.VITE_API_BASE_URL);
