<template>
  <b-card class="no-paddings">
    <side-move-bar></side-move-bar>
    <side-search-bar></side-search-bar>
  </b-card>
</template>

<script>
import SideMoveBar from "@/components/sidebar/SideMoveBar.vue";
import SideSearchBar from "@/components/sidebar/SideSearchBar.vue";

export default {
  data() {
    return {};
  },
  components: { SideMoveBar, SideSearchBar },
};
</script>

<style scoped></style>
