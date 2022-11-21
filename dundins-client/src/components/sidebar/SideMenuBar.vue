<template>
  <div class="bar" elevation="10">
    <div class="container">
      <b-col></b-col>
      <side-move-bar></side-move-bar>
      <b-col></b-col>
      <b-col></b-col>
      <side-search-bar></side-search-bar>
    </div>
  </div>
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
