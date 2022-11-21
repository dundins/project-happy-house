<template>
  <div id="boardview">
    <header-navbar></header-navbar>
    <b-container class="bv-example-row mt-3 text-center">
      <router-view></router-view>
    </b-container>
  </div>
</template>

<script>
import HeaderNavbar from "@/components/HeaderNavbar";

export default {
  name: "HeaderNavber",
  components: { HeaderNavbar },
};
</script>

<style>
#boardview {
  background-color: white;
}
</style>
