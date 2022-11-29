<template>
  <b-container class="bv-example-row">
    <b-row>
      <b-col>
        <div class="list-group">
          <b-row class="mb-3">
            <b-col cols="6"
              ><strong style="font-size: 1.25rem">공지사항</strong></b-col
            >
            <b-col cols="5" style="text-align: right">
              <router-link to="/board"> 더보기 </router-link>
            </b-col>
          </b-row>
          <div v-if="articles.length == 0">작성된 글이 없습니다.</div>
          <b-table
            id="tboard"
            hover
            :per-page="perPage"
            :current-page="currentPage"
            :items="articles"
            :fields="fields"
            @row-clicked="viewArticle"
            thead-class="d-none"
          >
            <template #cell(subject)="data">
              <router-link
                id="title"
                :to="{
                  name: 'boarddetail',
                  params: { articleno: data.item.articleno },
                }"
              >
                <div class="col-xs-12" style="height: 7px"></div>
                {{ data.item.subject }}
                <div class="col-xs-12" style="height: 7px"></div>
              </router-link>
            </template>
            <template #cell(articleno)>
              <div class="col-xs-12" style="height: 7px"></div>
              <span id="type">공지</span>
              <div class="col-xs-12" style="height: 7px"></div>
            </template>
            <template #cell(regtime)="data">
              <div class="col-xs-12" style="height: 7px"></div>
              {{ data.item.regtime }}
              <div class="col-xs-12" style="height: 7px"></div>
            </template>
          </b-table>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listArticle } from "@/api/board";

export default {
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      articles: [],
      fields: [
        { key: "articleno", label: "분류", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      ({ data }) => {
        this.articles = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    viewArticle(article) {
      this.$router.push({
        name: "boarddetail",
        params: { articleno: article.articleno },
      });
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
