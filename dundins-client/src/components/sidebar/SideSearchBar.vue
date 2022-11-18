<template>
  <v-col>
    <v-row>
      <v-col>
        <span class="material-icons"> search </span>
        원하시는 지역을 선택해주세요.
      </v-col>
    </v-row>
    <v-row>
      <v-col style="display: flex" class="d-flex">
        <v-select
          v-model="sidoCode"
          :items="sidos"
          label="시 선택"
          @change="gugunList"
          dense
        ></v-select>
        <v-select
          v-model="gugunCode"
          :items="guguns"
          label="구 선택"
          @change="dongList"
          dense
        ></v-select>
        <v-select
          v-model="dongCode"
          :items="dongs"
          label="동 선택"
          @change="searchApt"
          dense
        ></v-select>
      </v-col>
    </v-row>
  </v-col>
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
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
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
      // if (this.dongCode) this.getHouseList(this.dongCode);
      if (this.gugunCode) this.getHouseList(this.gugunCode);
    },
  },
};
</script>

<style></style>
