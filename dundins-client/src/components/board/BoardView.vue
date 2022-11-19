<template>
  <v-container>
    <v-row>
      <v-col>
        <h3>글보기</h3>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card>
          <v-card-text>
            <v-row>
              <v-avatar color="grey"></v-avatar>
              <strong class="text-h6">Title</strong>
            </v-row>
            <v-row class="text-left">
              <p v-text="message"></p>
            </v-row>
          </v-card-text>
        </v-card>
        <!-- <v-card
          :elevation="30"
          :header-html="`<h3>${article.articleno}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <v-card-text class="text-left">
            <div v-html="message"></div>
          </v-card-text>
        </v-card> -->
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-btn color="success" @click="moveList">목록</v-btn>
      </v-col>
      <v-col v-if="userInfo.userid === article.userid">
        <v-btn color="primary" @click="moveModifyArticle">글수정</v-btn>
        <v-btn color="error" @click="deleteArticle">글삭제</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
// import moment from "moment";
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
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    let param = this.$route.params.articleno;
    getArticle(
      param,
      ({ data }) => {
        console.log(data);
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
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
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
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
