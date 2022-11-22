<template>
  <div>
    <house-kakao-map class="map"></house-kakao-map>
    <side-menu-bar class="dashboard"></side-menu-bar>
  </div>
</template>

<script>
import HouseKakaoMap from "@/components/house/HouseKakaoMap.vue";
import SideMenuBar from "@/components/sidebar/SideMenuBar.vue";

export default {
  name: "ApartView",
  components: { HouseKakaoMap, SideMenuBar },
};
</script>

<style scoped>
.map {
  position: absolute;
  z-index: -1;
  width: 100%;
  height: 100%;
}
.dashboard {
  position: absolute;
  /* z-index: 2; */
  height: auto;
  width: 500px;
  border-radius: 20px;
}
</style>
