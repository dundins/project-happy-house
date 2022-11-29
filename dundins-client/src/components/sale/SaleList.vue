<template>
  <b-container id="bc" class="bv-example-row mt-5">
    <div class="py-5">
      <div class="container" style="text-align: center">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3">
          <b-card-group
            deck
            v-for="article in articles"
            v-bind:key="article.house_sale_id"
            :per-page="perPage"
            :current-page="currentPage"
            style="margin: 15px 0"
          >
            <b-card
              border-variant="info"
              header="info"
              header-bg-variant="transparent"
              align="center"
            >
              <template #header>
                <div style="text-align: left">
                  <b-card-title style="font-weight: bold; font-size: large">{{
                    article.title
                  }}</b-card-title>

                  <b-card-text
                    style="font-size: medium; padding-top: 10px; margin: 0"
                    >{{ article.area }}</b-card-text
                  >
                  <b-card-text style="font-size: medium">{{
                    article.dealAmount
                  }}</b-card-text>
                </div>
              </template>
              <div class="mb-3">
                <img
                  src="https://post-phinf.pstatic.net/MjAxOTExMThfMTk3/MDAxNTc0MDYzODE1Nzc3.8gtaRbg1njFRrqIumPZyH9VZO8wAYOsMlN5kG4DrNNog.59q7quubaw3zC2e2ezro2PMZeF999wYEDhyKZC69gJEg.JPEG/cm280018503.jpg?type=w1200"
                  alt="noImg"
                  style="width: 100%"
                />
              </div>
              <button
                type="button"
                class="btn btn-sm btn-outline-secondary"
                @click="viewArticle(article)"
              >
                보기
              </button>
            </b-card>
          </b-card-group>
        </div>
      </div>
      <b-row>
        <b-col>
          <b-col class="d-flex justify-content-end mb-4" style="float: right">
            <b-button variant="primary" @click="moveWrite()"
              >매물 등록</b-button
            >
          </b-col>
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>

<script>
import { listArticle } from "@/api/sale";
import { mapState, mapGetters } from "vuex";
const memberStore = "memberStore";

export default {
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      articles: [],
      fields: [
        // { key: "house_sale_id", label: "등록번호", tdClass: "tdClass" },
        { key: "title", label: "매물", tdClass: "tdSubject" },
        { key: "dealAmount", label: "가격", tdClass: "tdClass" },
        { key: "floor", label: "층", tdClass: "tdClass" },
        { key: "area", label: "지역", tdClass: "tdSubject" },
        { key: "room", label: "방 개수", tdClass: "tdClass" },
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
      this.$router.push({ name: "salewrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "saledetail",
        params: { house_sale_id: article.house_sale_id },
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
  width: 70%;
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
