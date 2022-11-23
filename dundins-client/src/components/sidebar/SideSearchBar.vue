<template>
  <b-container class="bv-example-row">
    <b-row>
      <b-input-group>
        <b-form-input
          v-model="searchCode"
          @keyup.enter="searchBarApt"
          label="원하시는 지역을 검색하세요👋🏻"
          placeholder="아파트, 지역으로 검색해보세요!"
          b-popover-light
        ></b-form-input>
        <b-button size="sm" variant="primary" class="mb-0">
          <b-icon icon="search" aria-hidden="true"></b-icon>
        </b-button>
      </b-input-group>
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
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getHouseList", "getSearchList"]),
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
