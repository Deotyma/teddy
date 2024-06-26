import { mount } from '@vue/test-utils';
import FormSignUp from '../src/components/FormSignUp.vue';
import { createRouter, createWebHistory } from 'vue-router';
import flushPromises from 'flush-promises';
import i18n from '../src/i18n/i18n.js';

jest.mock('axios');

const routes = [
  {
    path: '/authenticate',
    name: 'authenticate',
    component: { template: '<div>Authenication Page</div>' },
  },
  {
    path: '/register',
    name: 'register',
    component: FormSignUp,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

const mountComponent = async (routePath = '/register') => {
  router.push(routePath);
  await router.isReady();

  return mount(FormSignUp, {
    global: {
      plugins: [router, i18n],
    },
  });
};

describe('FormSignUp', () => {
  beforeEach(() => {
    jest.clearAllMocks();
    localStorage.clear();
  });

  test('should exist', async () => {
    const wrapper = await mountComponent();
    expect(wrapper.exists()).toBe(true);
  });

  test('should display form in English', async () => {
    i18n.global.locale = 'en';
    const wrapper = await mountComponent();
    await flushPromises();

    expect(wrapper.find('label[for="firstName"]').text()).toBe('First name');
    expect(wrapper.find('label[for="lastName"]').text()).toBe('Last name');
    expect(wrapper.find('label[for="email"]').text()).toBe('Email');
    expect(wrapper.find('label[for="password"]').text()).toBe('Password');
    expect(wrapper.find('label[for="nickName"]').text()).toBe('Nick name');
  });

  test('should display form in French', async () => {
    i18n.global.locale = 'fr';
    const wrapper = await mountComponent();
    await flushPromises();

    expect(wrapper.find('label[for="firstName"]').text()).toBe('Prènom');
    expect(wrapper.find('label[for="lastName"]').text()).toBe('Nom');
    expect(wrapper.find('label[for="email"]').text()).toBe('Email');
    expect(wrapper.find('label[for="password"]').text()).toBe('Mot de passe');
    expect(wrapper.find('label[for="nickName"]').text()).toBe('Pseudonyme');
  });
});
