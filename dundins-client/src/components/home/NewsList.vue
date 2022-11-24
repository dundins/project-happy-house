<template>
  <b-container class="bv-example-row mt-5">
    <b-row class="justify-content-md-center">
      <b-col cols="10">
        <div class="list-group">
          <div
            class="list-group-item list-group-item-action"
            v-for="(article, index) in articles"
            :key="index"
            :value="article.value"
            @click="go(article.url)"
          >
            <div v-if="isOne">
              <b-row class="justify-content-md-center" align-v="center">
                <b-col cols="2">
                  <div class="list-group-item-thumbnail">
                    <img :src="article.img" alt="noImage" />
                  </div>
                  <div class="list-group-item-writer mt-1">
                    <span>{{ article.company }}</span>
                    <small>{{ article.createdDate }}</small>
                  </div>
                </b-col>
                <b-col cols="10">
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
        </div>
      </b-col>
    </b-row>
    <br />
    <div class="text-center">
      <a
        href="https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=101&sid2=260"
        >더보기</a
      >
    </div>
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
  font-size: 18px;
  font-weight: 600;
}
.list-group-item-content {
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: 1.4em;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  font-size: 15px;
}
.list-group-item-thumbnail > img {
  width: 100%;
}
</style>
