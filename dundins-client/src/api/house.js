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

// function houseList(params, success, fail) {
//   // api.get(`/map/aptlist`, { params: params }).then(success).catch(fail);
//   api
//     .get(`/map/aptlist/${params.LAWD_CD}/${params.DEAL_YMD}`)
//     .then(success)
//     .catch(fail);
// }

function houseList(params, success, fail) {
  // api.get(`/map/aptlist`, { params: params }).then(success).catch(fail);
  // console.log(params.dongCode);
  // api.get(`/map/apt?dong=${params.dongCode}`).then(success).catch(fail);
  api.get(`/map/apt?dong=${params.dongCode}`).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, houseList };
