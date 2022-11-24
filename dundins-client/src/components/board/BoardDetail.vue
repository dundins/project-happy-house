<template>
  <b-container id="bc" class="bv-example-row mt-5">
    <b-row class="mb-1">
      <b-col>
        <b-card class="mb-2" no-body header-tag="nav">
          <template #header>
            <b-row align-v="center">
              <b-col>
                <div
                  style="text-align: left; font-weight: bold; font-size: large"
                  class="text-primary"
                >
                  {{ article.articleno }}. {{ article.subject }}
                </div>
              </b-col>
              <b-col>
                <div style="text-align: right">
                  {{ article.userid }} [{{ article.hit }}]
                </div>
                <div style="text-align: right">{{ article.regtime }}</div>
              </b-col>
            </b-row>
          </template>
          <b-card-body class="p-4">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-button variant="primary" @click="moveList">목록</b-button>
        <b-button
          v-if="userInfo.userid === article.userid"
          class="mx-3"
          variant="outline-info"
          @click="moveModifyArticle"
          >수정</b-button
        >
        <b-button
          v-if="userInfo.userid === article.userid"
          variant="outline-danger"
          @click="deleteArticle"
          >삭제</b-button
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { getArticle } from "@/api/board";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content) {
        let str;
        str = this.article.content.split("\n").join("<br>");
        str = "<div style='text-align: left'>" + str + "</div>";
        return str;
      }
      return "";
    },
  },
  created() {
    let param = this.$route.params.articleno;
    getArticle(
      param,
      ({ data }) => {
        this.article = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { articleno: this.article.articleno },
      });
      this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { articleno: this.article.articleno },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style scoped>
#bc {
  width: 65%;
}
</style>
