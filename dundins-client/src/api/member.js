import { apiInstance } from "./index.js";

const api = apiInstance();

async function regist(user, success, fail) {
  await api
    .post(`/user/regist`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function idCheck(userId, success, fail) {
  await api.get(`/user/${userId}`).then(success).catch(fail);
}

async function update(user, success, fail) {
  await api
    .post(`/user/update`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, regist, idCheck, update };
