<template>
  <div>
    <house-kakao-map class="map"></house-kakao-map>
    <side-menu-bar v-show="!selected" class="dashboard"></side-menu-bar>
    <side-near-check class="near"></side-near-check>
    <!-- <house-deal-info></house-deal-info> -->
  </div>
</template>

<script>
import HouseKakaoMap from "@/components/house/HouseKakaoMap.vue";
import SideMenuBar from "@/components/sidebar/SideMenuBar.vue";
import SideNearCheck from "@/components/sidebar/SideNearCheck.vue";
import { mapState } from "vuex";
// import HouseDealInfo from "@/components/house/HouseDealInfo.vue";

const houseStore = "houseStore";
export default {
  name: "ApartView",
  components: { HouseKakaoMap, SideMenuBar, SideNearCheck },
  data() {
    return {
      houseSelected: true,
    };
  },
  computed: {
    ...mapState(houseStore, ["selected"]),
  },
};
</script>

<style scoped>
.map {
  position: absolute;
  z-index: 1;
  width: 100%;
  height: 100%;
}
.dashboard {
  margin-top: 10px;
  margin-left: 10px;
  position: absolute;
  height: auto;
  z-index: 2;
  width: 500px;
  border-radius: 20px;
}
.near {
  position: absolute;
  z-index: 2;
  padding: 10px;

  margin-top: 10px;
  margin-left: 540px;

  width: 300px;
  height: 100px;
  border-radius: 20px;
}
</style>
