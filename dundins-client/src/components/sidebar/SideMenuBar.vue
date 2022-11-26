<template>
  <b-card class="no-paddings">
    <side-move-bar></side-move-bar>
    <hr class="my-2" />
    <side-search-bar class="mt-3"></side-search-bar>
    <side-select-search-bar class="mt-3"></side-select-search-bar>
  </b-card>
</template>

<script>
import SideMoveBar from "@/components/sidebar/SideMoveBar.vue";
import SideSearchBar from "@/components/sidebar/SideSearchBar.vue";
import SideSelectSearchBar from "@/components/sidebar/SideSelectSearchBar.vue";

export default {
  data() {
    return {};
  },
  components: { SideMoveBar, SideSearchBar, SideSelectSearchBar },
};
</script>

<style scoped></style>
