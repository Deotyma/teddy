import { mount } from '@vue/test-utils';
import AnnonceDetail from '../src/components/AnnonceDetail.vue';
import { createRouter, createWebHistory } from 'vue-router';
import axios from 'axios';
import flushPromises from 'flush-promises';

jest.mock('axios');

// Mock VITE_IMG_BASE_URL
process.env.VITE_IMG_BASE_URL = '/teddyblueImg';

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

const mountComponent = async (routePath = '/annonce-detail/14') => {
  router.push(routePath);
  await router.isReady();

  return mount(AnnonceDetail, {
    global: {
      plugins: [router],
    },
  });
};

const mockAnnonceData = {
  title: 'Test Title',
  categoryName: 'Category',
  sharingMethodName: 'Sharing Method',
  textAnnonce: 'Description',
  photoLink: 'test.jpg',
  userEmail: 'test@example.com',
};

const mockAxiosGet = () => {
  axios.get.mockResolvedValueOnce({ data: mockAnnonceData });
};

describe('AnnonceDetail', () => {
  beforeEach(() => {
    jest.clearAllMocks();
    localStorage.clear();
  });

  test('should exist', async () => {
    const wrapper = await mountComponent();
    expect(wrapper.exists()).toBe(true);
  });

  test('should display title, correctly', async () => {
    mockAxiosGet();

    const wrapper = await mountComponent();
    await flushPromises();

    expect(wrapper.find('.name').text()).toBe(mockAnnonceData.title);
  });

  test('should display categoryName, correctly', async () => {
    mockAxiosGet();

    const wrapper = await mountComponent();
    await flushPromises();

    expect(wrapper.find('.badge').text()).toBe(mockAnnonceData.categoryName);
  });

  test('should display textAnnonce, correctly', async () => {
    mockAxiosGet();

    const wrapper = await mountComponent();
    await flushPromises();

    expect(wrapper.find('.description').text()).toBe(mockAnnonceData.textAnnonce);
  });

  test('should display photoLink, correctly', async () => {
    mockAxiosGet();

    const wrapper = await mountComponent();
    await flushPromises();

    expect(wrapper.find('img').attributes('src')).toBe(`${process.env.VITE_IMG_BASE_URL}/${mockAnnonceData.photoLink}`);
  });

  test('should display userEmail, correctly', async () => {
    mockAxiosGet();

    const wrapper = await mountComponent();
    await flushPromises();
    
    expect(wrapper.find('a.btn-secondary').attributes('href')).toBe(`mailto:${mockAnnonceData.userEmail}`);
  });
});

