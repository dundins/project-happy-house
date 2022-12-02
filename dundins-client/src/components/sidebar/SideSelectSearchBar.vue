<template>
  <b-container class="bv-example-row">
    <b-row class="no-gutters">
      <b-col cols="4" class="p-1">
        <fieldset>
          <select
            v-model="sidoCode"
            @change="gugunList"
            class="array-select form-control form-select"
            aria-label="example"
          >
            <option value="null" disabled selected>-- 시/도 --</option>
            <option
              v-for="(item, index) in sidos"
              :key="index"
              :value="item.value"
            >
              {{ item.text }}
            </option>
          </select>
        </fieldset>
      </b-col>
      <b-col cols="4" class="p-1">
        <fieldset>
          <select
            v-model="gugunCode"
            @change="dongList"
            class="array-select form-control form-select"
            aria-label="example"
          >
            <option value="null" disabled selected>-- 구/군 --</option>
            <option
              v-for="(item, index) in guguns"
              :key="index"
              :value="item.value"
            >
              {{ item.text }}
            </option>
          </select>
        </fieldset>
      </b-col>
      <b-col cols="4" class="p-1">
        <fieldset>
          <select
            v-model="dongCode"
            @change="searchApt"
            class="array-select form-control form-select"
            aria-label="example"
          >
            <option value="null" disabled selected>-- 동 --</option>
            <option
              v-for="(item, index) in dongs"
              :key="index"
              :value="item.value"
            >
              {{ item.text }}
            </option>
          </select>
        </fieldset>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "SideSelectSearchBar",
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
