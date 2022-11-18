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
          dense
        ></v-select>
        <v-select
          v-model="dongCode"
          :items="dongs"
          label="동 선택"
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
    ...mapState(houseStore, ["sidos", "guguns", "houses"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getHouseList"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_APT_LIST",
    ]),
    gugunList() {
      // console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt() {
      if (this.gugunCode) this.getHouseList(this.gugunCode);
    },
  },
};
</script>

<style></style>
