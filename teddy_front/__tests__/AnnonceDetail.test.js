import { mount } from '@vue/test-utils';
import AnnonceDetail from '../src/components/AnnonceDetail.vue';
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/annonce-detail/:id',
    name: 'annonceDetail',
    component: AnnonceDetail,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

describe('AnnonceDetail', () => {
  test('should exist', async () => {
    router.push('/annonce-detail/14'); 
    await router.isReady();

    const wrapper = mount(AnnonceDetail, {
      global: {
        plugins: [router],
      },
    });

    expect(wrapper.exists()).toBe(true);
  });
});
