<template>
  <div class="container">
    <side-move-bar></side-move-bar>
    <side-search-bar></side-search-bar>
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

<style scoped>
.container {
  padding: auto;
}
</style>
