<template>
  <b-card class="no-paddings">
    <side-move-bar></side-move-bar>
    <hr class="my-2" />
    <!-- <side-search-bar class="mt-3"></side-search-bar> -->
    <div class="map-area mt-3">
      <side-select-search-bar class="mt-3"></side-select-search-bar>
      <side-search-bar-2></side-search-bar-2>
    </div>
  </b-card>
</template>

<script>
import SideMoveBar from "@/components/sidebar/SideMoveBar.vue";
// import SideSearchBar from "@/components/sidebar/SideSearchBar.vue";
import SideSelectSearchBar from "@/components/sidebar/SideSelectSearchBar.vue";
import SideSearchBar2 from "@/components/sidebar/SideSearchBar2.vue";

export default {
  components: {
    SideMoveBar,
    // SideSearchBar,
    SideSelectSearchBar,
    SideSearchBar2,
  },
};
</script>

<style scoped></style>
