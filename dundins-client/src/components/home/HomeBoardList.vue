<template>
  <b-container class="bv-example-row">
    <b-row>
      <b-col>
        <div class="list-group">
          <b-row class="mb-2">
            <b-col cols="6"
              ><strong style="font-size: 1.25rem">공지사항</strong></b-col
            >
            <b-col cols="6" style="text-align: right">
              <router-link to="/board"> 더보기 </router-link>
            </b-col>
          </b-row>
          <div
            class="list-group-item list-group-item-action"
            v-for="(article, index) in $options.filters.limitArray(articles, 4)"
            :key="index"
            :value="article.value"
            @click="go(article.url)"
          >
            <b-row class="justify-content-md-center" align-v="center">
              <b-col cols="3">
                <div class="list-group-item-thumbnail">
                  <img :src="article.img" alt="noImage" />
                </div>
                <div class="list-group-item-writer mt-1">
                  <span>{{ article.company }}</span>
                  <small>{{ article.createdDate }}</small>
                </div>
              </b-col>
              <b-col cols="9">
                <h5 class="list-group-item-title text-left">
                  {{ article.title }}
                </h5>
                <p class="list-group-item-content text-left mb-0">
                  {{ article.content }}
                </p>
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
      isOne: true,
      isTwo: false,
      isThree: false,
      articles: [],
    };
  },
  created() {
    listNewsArticle(
      ({ data }) => {
        this.articles = data;
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
  filters: {
    limitArray(arr, length = 3) {
      if (arr && arr.length) {
        if (length == -1) {
          return arr;
        }
        if (length > arr.length) {
          return arr;
        }
        return arr.slice(0, length);
      }
      return null;
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
