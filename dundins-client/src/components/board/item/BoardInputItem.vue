<template>
  <v-row justify="end">
    <v-col cols="2" />
    <v-col cols="8">
      <v-card class="pa-3">
        <v-form @submit="onSubmit">
          <v-card-text>
            <v-col cols="12" sm="6">
              <v-text-field
                id="userid"
                v-model="article.userid"
                value="ssafy"
                label="작성자"
                hide-details
                solo
              ></v-text-field>
              <!--readonly-->
            </v-col>
            <v-col cols="12">
              <v-text-field
                id="subject"
                v-model="article.subject"
                label="제목"
                single-line
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-textarea id="content" v-model="article.content" single-line>
                <template v-slot:label>
                  <div>내용</div>
                </template>
              </v-textarea>
            </v-col>
          </v-card-text>
          <v-divider class="mt-12"></v-divider>
          <v-card-actions>
            <v-col cols="12">
              <v-btn
                color="#42A5F5"
                type="submit"
                v-if="this.type === 'register'"
                >작성</v-btn
              >
              <v-btn type="submit" color="#42A5F5" v-else>수정</v-btn>
              <v-btn @click="onReset($event)" color="#EF5350">취소</v-btn>
            </v-col>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import { writeArticle, modifyArticle, getArticle } from "@/api/board";

export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      let param = this.$route.params.articleno;
      getArticle(
        param,
        ({ data }) => {
          this.article.articleno = data.article.articleno;
          this.article.userid = data.article.userid;
          this.article.subject = data.article.subject;
          this.article.content = data.article.content;
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      err &&
        !this.article.userid &&
        ((msg = "작성자를 입력하세요"), (err = false));
      err &&
        !this.article.subject &&
        ((msg = "제목을 입력하세요"), (err = false));
      err &&
        !this.article.content &&
        ((msg = "내용을 입력하세요"), (err = false));

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.moveList();
    },
    registArticle() {
      let param = {
        userid: this.article.userid,
        subject: this.article.subject,
        content: this.article.content,
      };
      writeArticle(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyArticle() {
      let param = {
        articleno: this.article.articleno,
        userid: this.article.userid,
        subject: this.article.subject,
        content: this.article.content,
      };
      modifyArticle(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style scoped>
button {
  margin: 0 10px;
}
</style>
