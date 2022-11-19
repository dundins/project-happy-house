<template>
  <v-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-hotpink">
      <v-icon icon="journals"></v-icon> 공지사항
    </h3>
    <div class="purple darken-2 text-center">
      <span class="white--text">Lorem ipsum</span>
    </div>
    <router-view></router-view>
  </v-container>
</template>
<script>
export default {
  name: "AppBoard",
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
