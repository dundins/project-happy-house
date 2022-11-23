import { apiInstance } from "./index.js";

const api = apiInstance();

function listNewsArticle(success, fail) {
  api.get(`/news/`).then(success).catch(fail);
}

export { listNewsArticle };
