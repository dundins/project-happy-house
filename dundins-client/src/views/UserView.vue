<template>
  <div>
    <header-navbar></header-navbar>
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderNavbar from "@/components/HeaderNavbar";

export default {
  name: "UserView",
  components: { HeaderNavbar },
};
</script>

<style></style>
