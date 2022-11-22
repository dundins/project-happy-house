<template>
  <b-container class="bv-example-row mt-3">
    <!-- <b-row style="height: 130px">
      <div style="height: 30px"></div>
      <b-row id="board-header"
        ><b-col class="d-flex justify-content-start"
          ><span style="font-weight: bold; color: #254baa">Home /</span>
          <div style="width: 10px"></div>
          <span>공지 사항</span>
        </b-col></b-row
      >
      <div style="height: 30px"></div>
      <b-row>
        <b-col class="d-flex justify-content-start"> <h4>공지 사항</h4></b-col>
      </b-row>
      <div class="col-xs-12" style="height: 10px"></div>
    </b-row> -->
    <b-row>
      <b-col>
        <b-table
          id="tboard"
          hover
          :per-page="perPage"
          :current-page="currentPage"
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
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
        <!-- <b-col class="d-flex justify-content-end" style="float: right">
          <b-button variant="outline-primary" @click="moveWrite()"
            >글 작성</b-button
          >
        </b-col> -->
        <b-pagination
          id="bpage"
          align="center"
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="tbarticle"
        ></b-pagination>
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
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boarddetail",
        params: { articleno: article.articleno },
      });
    },
    totalPage() {
      return Math.ceil(this.rows / this.perPage);
    },
  },
  computed: {
    rows() {
      return this.articles.length;
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
#title {
  text-decoration: none;
  color: gray;
}
#title:hover {
  color: #254baa;
}
#type {
  color: #254baa;
  font-weight: bold;
}
</style>
