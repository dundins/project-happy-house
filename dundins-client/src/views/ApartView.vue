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
  height: auto;
  width: 530px;
}
</style>
