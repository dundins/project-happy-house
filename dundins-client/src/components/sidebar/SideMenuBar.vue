<template>
  <v-card class="bar" elevation="10">
    <v-container data-app>
      <v-col></v-col>
      <side-move-bar></side-move-bar>
      <v-col></v-col>
      <v-divider></v-divider>
      <v-col></v-col>
      <side-search-bar></side-search-bar>
    </v-container>
  </v-card>
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
