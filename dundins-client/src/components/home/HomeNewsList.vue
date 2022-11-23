<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div class="list-group">
          <div
            class="list-group-item list-group-item-action"
            v-for="article in articles"
            v-bind:key="article.title"
            @click="go(article.url)"
          >
            <b-row class="justify-content-md-center">
              <b-col cols="3">
                <div class="list-group-item-thumbnail">
                  <img :src="article.img" alt="" />
                </div>
                <div class="list-group-item-writer mt-1">
                  <span>{{ article.company }}</span>
                  <small>{{ article.createdDate }}</small>
                </div>
              </b-col>
              <b-col cols="9">
                <div class="list-group-item-article">
                  <h5 class="list-group-item-title">
                    {{ article.title }}
                  </h5>
                  <p class="list-group-item-content">{{ article.content }}</p>
                </div>
              </b-col>
            </b-row>
          </div>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listNewsArticle } from "@/api/news";

export default {
  data() {
    return {
      articles: [],
      fields: [
        { key: "title", label: "제목", tdClass: "tdClass" },
        { key: "content", label: "요약", tdClass: "tdClass" },
        { key: "img", label: "이미지", tdClass: "tdClass" },
        { key: "url", label: "링크", tdClass: "tdClass" },
        { key: "company", label: "신문사", tdClass: "tdClass" },
        { key: "createdDate", label: "등록일", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    listNewsArticle(
      ({ data }) => {
        this.articles = data;
        console.log(this.articles);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    go(url) {
      window.open(url, "_blank");
    },
  },
};
</script>

<style scoped>
.list-group-item-writer > span {
  display: block;
  font-size: 14px;
}
.list-group-item-writer > small {
  display: block;
  font-size: 11px;
}
.list-group-item-title {
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: 1.4em;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  font-size: 16px;
  font-weight: 600;
}
.list-group-item-content {
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: 1.4em;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  font-size: 13px;
}
.list-group-item-thumbnail > img {
  width: 100%;
}
</style>
