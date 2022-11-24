<template>
  <b-container id="bc" class="bv-example-row mt-5">
    <b-row>
      <b-col>
        <b-col class="d-flex justify-content-end mb-4" style="float: right">
          <b-button variant="primary" @click="moveWrite()">매물 등록</b-button>
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
          <template #cell(house_sale_id)="data">
            <div class="col-xs-12" style="height: 7px"></div>
            {{ data.item.house_sale_id }}
            <div class="col-xs-12" style="height: 7px"></div>
          </template>
          <template #cell(title)="data">
            <div class="col-xs-12" style="height: 7px"></div>
            {{ data.item.title }}
            <div class="col-xs-12" style="height: 7px"></div>
          </template>
          <template #cell(dealAmount)="data">
            <div class="col-xs-12" style="height: 7px"></div>
            {{ data.item.dealAmount }}
            <div class="col-xs-12" style="height: 7px"></div>
          </template>
          <template #cell(floor)="data">
            <div class="col-xs-12" style="height: 7px"></div>
            {{ data.item.floor }}
            <div class="col-xs-12" style="height: 7px"></div>
          </template>
          <template #cell(area)="data">
            <div class="col-xs-12" style="height: 7px"></div>
            {{ data.item.area }}
            <div class="col-xs-12" style="height: 7px"></div>
          </template>
          <template #cell(room)="data">
            <div class="col-xs-12" style="height: 7px"></div>
            {{ data.item.room }}
            <div class="col-xs-12" style="height: 7px"></div>
          </template>
        </b-table>
        <div v-if="articles.length == 0">등록된 매물이 없습니다.</div>
        <b-pagination
          v-else
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
        { key: "house_sale_id", label: "등록번호", tdClass: "tdClass" },
        { key: "title", label: "매물", tdClass: "tdSubject" },
        { key: "dealAmount", label: "가격", tdClass: "tdClass" },
        { key: "floor", label: "층", tdClass: "tdClass" },
        { key: "area", label: "지역", tdClass: "tdClass" },
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
        params: { articleno: article.house_sale_id },
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
