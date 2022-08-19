import axios from 'axios';

export const SETTINGS = {
  DOMAIN: '/api'
};

export default {
  request(settings) {
    const GET_URL = settings.url;
    if (!GET_URL.match('http')) {
      settings.url = SETTINGS.DOMAIN + GET_URL;
    }

    return axios(settings)
      .then(result => result)
      .catch(err => {
        throw err;
      });
  },
}
