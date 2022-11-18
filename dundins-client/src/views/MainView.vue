<template>
  <div>
    <kakao-map class="map"></kakao-map>
    <side-menu-bar class="dashboard"></side-menu-bar>
  </div>
</template>

<script>
import KakaoMap from "@/components/main/KakaoMap.vue";
import SideMenuBar from "@/components/sidebar/SideMenuBar.vue";

export default {
  components: { KakaoMap, SideMenuBar },
  name: "AppMain",
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
  z-index: 2;
  position: absolute;
  margin: 1%;
  height: auto;
  width: 375px;
}
</style>
