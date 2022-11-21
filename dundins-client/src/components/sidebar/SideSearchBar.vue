<template>
  <b-container class="bv-example-row">
    <b-row>
      <!-- <span class="material-icons"> search </span>
        원하시는 지역을 선택해주세요. -->
      <b-col md="10">
        <b-form-input
          v-model="searchCode"
          @keyup.enter="searchBarApt"
          label="원하시는 지역을 검색하세요👋🏻"
          placeholder="아파트, 지역으로 검색해보세요!"
        ></b-form-input>
      </b-col>
      <b-col md="2">
        <b-button elevation="0" rounded @click="searchBarApt">
          <span class="material-icons">search</span>
        </b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col style="display: flex" class="d-flex">
        <b-form-select
          v-model="sidoCode"
          :items="sidos"
          label="시 선택"
          @change="gugunList"
          dense
        ></b-form-select>
        <b-form-select
          v-model="gugunCode"
          :items="guguns"
          label="구 선택"
          @change="dongList"
          dense
        ></b-form-select>
        <b-form-select
          v-model="dongCode"
          :items="dongs"
          label="동 선택"
          @change="searchApt"
          dense
        ></b-form-select>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "SideSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      searchCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },
  methods: {
    // eslint-disable-next-line prettier/prettier
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getSearchList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_APT_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      if (this.dongCode) this.getHouseList(this.dongCode);
    },
    searchBarApt() {
      console.log(this.searchCode);
      if (this.searchCode) this.getSearchList(this.searchCode);
    },
  },
};
</script>

<style></style>
