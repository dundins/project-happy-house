<template>
  <b-container class="bv-example-row">
    <div class="col-xs-12" style="height: 10px"></div>
    <hr class="my-2" />
    <div class="col-xs-12" style="height: 10px"></div>
    <div>
      <b-col>
        <b-form-input
          v-model="searchCode"
          @keyup.enter="searchBarApt"
          label="원하시는 지역을 검색하세요👋🏻"
          placeholder="아파트, 지역으로 검색해보세요!"
        ></b-form-input>
      </b-col>
    </div>
    <div class="col-xs-12" style="height: 10px"></div>

    <div class="col-xs-12" style="height: 10px"></div>
    <b-row>
      <b-col cols="4">
        <fieldset>
          <select
            v-model="sidoCode"
            @change="gugunList"
            class="array-select form-control form-select"
            aria-label="example"
          >
            <option value="null" disabled selected>시 선택</option>
            <option
              v-for="(item, index) in sidos"
              :key="index"
              :value="item.value"
            >
              {{ item.text }}
            </option>
          </select>
        </fieldset></b-col
      >
      <b-col cols="4">
        <fieldset>
          <select
            v-model="gugunCode"
            @change="dongList"
            class="array-select form-control form-select"
            aria-label="example"
          >
            <option value="null" disabled selected>구 선택</option>
            <option
              v-for="(item, index) in guguns"
              :key="index"
              :value="item.value"
            >
              {{ item.text }}
            </option>
          </select>
        </fieldset></b-col
      >
      <b-col cols="4">
        <fieldset>
          <select
            v-model="dongCode"
            @change="searchApt"
            class="array-select form-control form-select"
            aria-label="example"
          >
            <option value="null" disabled selected>동 선택</option>
            <option
              v-for="(item, index) in dongs"
              :key="index"
              :value="item.value"
            >
              {{ item.text }}
            </option>
          </select>
        </fieldset></b-col
      >
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
      console.log(this.dongCode);
      if (this.dongCode) this.getHouseList(this.dongCode);
    },
    searchBarApt() {
      if (this.searchCode) this.getSearchList(this.searchCode);
    },
  },
};
</script>

<style></style>
