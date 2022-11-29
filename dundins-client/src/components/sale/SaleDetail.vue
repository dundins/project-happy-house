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
                  {{ article.house_sale_id }}. {{ article.title }}
                </div>
              </b-col>
              <b-col>
                <div style="text-align: right">
                  [{{ article.floor }}층] [방 {{ article.room }}개]
                </div>
                <div style="text-align: right">{{ article.dealAmount }} 원</div>
                <div style="text-align: right">{{ article.area }}</div>
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
          v-if="userInfo && userInfo.userid === article.userid"
          class="mx-3"
          variant="outline-info"
          @click="moveModifyArticle"
          >수정</b-button
        >
        <b-button
          v-if="userInfo && userInfo.userid === article.userid"
          variant="outline-danger"
          @click="deleteArticle"
          >삭제</b-button
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { getArticle } from "@/api/sale";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "SaleDetail",
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
    let param = this.$route.params.house_sale_id;
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
        name: "salemodify",
        params: { house_sale_id: this.article.house_sale_id },
      });
      this.$router.push({ path: `/sale/modify/${this.article.house_sale_id}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "saledelete",
          params: { house_sale_id: this.article.house_sale_id },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "salelist" });
    },
  },
};
</script>

<style scoped>
#bc {
  width: 65%;
}
</style>
