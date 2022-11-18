<template>
  <v-container>
    <v-row>
      <v-col>
        <h3>글목록</h3>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="10" />
      <v-col cols="2">
        <v-btn @click="moveWrite()">글쓰기</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <!-- <v-data-able
          striped
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
        >
        </v-data-able> -->
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { listArticle } from "@/api/board";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
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
        console.log(data);
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
        name: "boardview",
        params: { articleno: article.articleno },
      });
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
</style>
