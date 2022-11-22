<template>
  <div>
    <kakao-map class="map"></kakao-map>
    <side-menu-bar class="dashboard"></side-menu-bar>
  </div>
</template>

<script>
import KakaoMap from "@/components/apt/KakaoMap.vue";
import SideMenuBar from "@/components/sidebar/SideMenuBar.vue";

export default {
  name: "ApartView",
  components: { KakaoMap, SideMenuBar },
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
