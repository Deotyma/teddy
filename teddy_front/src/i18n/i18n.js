import { createI18n } from "vue-i18n"
import enMessages from "./en";
import frMessages from "./fr";

const i18n = createI18n({
    locale: 'fr',
    fallbackLocale: 'en',
    messages: {
        en: enMessages,
        fr: frMessages
    }
})

export default i18n