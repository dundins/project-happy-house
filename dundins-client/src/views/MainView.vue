<template>
  <div class="mainview">
    <kakao-map class="map"></kakao-map>
    <side-menu-bar class="dashboard"></side-menu-bar>
  </div>
</template>

<script>
import KakaoMap from "@/components/Main/KakaoMap.vue";
import SideMenuBar from "@/components/Main/SideMenuBar.vue";

export default {
  components: { KakaoMap, SideMenuBar },
  name: "AppMain",
};
</script>

<style scoped>
.mapview {
  width: 100%;
  height: 100%;
}
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
  height: 90%;
  width: 400px;
}
</style>
