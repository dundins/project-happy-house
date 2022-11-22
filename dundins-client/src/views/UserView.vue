<template>
  <div>
    <header-navbar></header-navbar>
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderNavbar from "@/components/layout/HeaderNavbar";

export default {
  name: "UserView",
  components: { HeaderNavbar },
};
</script>

<style></style>
