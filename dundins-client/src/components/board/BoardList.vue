<template>
  <b-container id="bc" class="bv-example-row mt-5">
    <b-row>
      <b-col>
        <b-col
          v-if="userInfo && userInfo.userid == 'admin'"
          class="d-flex justify-content-end mb-4"
          style="float: right"
        >
          <b-button variant="primary" @click="moveWrite()">글작성</b-button>
        </b-col>
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
            <div class="col-xs-12" style="height: 7px"></div>
            {{ data.item.subject }}
            <div class="col-xs-12" style="height: 7px"></div>
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
        <b-pagination
          pills
          align="center"
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="tboard"
        ></b-pagination>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listArticle } from "@/api/board";
import { mapState, mapGetters } from "vuex";
const memberStore = "memberStore";

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
    console.log(this.currentPage);
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
    rows() {
      return this.articles.length;
    },
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
      console.log(this.currentPage);
    },
    totalPage() {
      return Math.ceil(this.rows / this.perPage);
    },
  },
};
</script>

<style scope>
#bc {
  width: 65%;
}
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
