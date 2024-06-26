import { createI18n } from "vue-i18n"
import enMessages from "./en";
import frMessages from "./fr";

const messages = {
    en: enMessages,
    fr: frMessages,
  };

const i18n = createI18n({
    locale: 'fr',
    fallbackLocale: 'fr',
    messages
})

export default i18n