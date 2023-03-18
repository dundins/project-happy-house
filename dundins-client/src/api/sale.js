import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/sale`, { params: param }).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/sale`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(articleno, success, fail) {
  api.get(`/sale/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/sale`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  api.delete(`/sale/${articleno}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
