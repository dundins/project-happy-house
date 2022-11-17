<template>
  <div>
    <kakao-map class="map"></kakao-map>
    <div>
      <h1 class="dashboard">asdasd</h1>
    </div>
  </div>
</template>

<script>
import KakaoMap from "@/components/Main/KakaoMap.vue";

export default {
  components: { KakaoMap },
  name: "AppMain",
  props: {
    msg: String,
  },
  comments: {
    KakaoMap,
  },
};
</script>

<style scoped>
.map {
  position: absolute;
  z-index: 1;
  width: 100%;
}
.dashboard {
  z-index: 2;
  position: absolute;
  border-radius: 10px;
  border-block-color: black;
  width: 400px;
  height: 50%;
}
</style>
