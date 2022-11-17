<template>
  <v-card class="d-flex pa-2" outlined tile>
    <div>
      <kakao-map></kakao-map>
    </div>
    <div>
      <map-search class="-"></map-search>
    </div>
  </v-card>
</template>

<script>
import KakaoMap from "@/components/Main/KakaoMap.vue";
import MapSearch from "@/components/Main/MapSearch.vue";

export default {
  components: { KakaoMap, MapSearch },
  name: "AppMain",
  props: {
    msg: String,
  },
  comments: {
    KakaoMap,
    MapSearch,
  },
};
</script>

<style scoped>
.floatmenu {
  position: absolute;
  top: 0px;
  left: 0px;
}
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
