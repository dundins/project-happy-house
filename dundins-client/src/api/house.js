import { apiInstance } from "./index.js";
// import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
// const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`/map/apt?dong=${params.dongCode}`).then(success).catch(fail);
}

function searchList(params, success, fail) {
  api.post(`/map/search`, params).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, houseList, searchList };
