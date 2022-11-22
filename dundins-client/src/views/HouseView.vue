<template>
  <div>
    <header-navbar></header-navbar>
    <BasicHeader name="매물 관리" />
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderNavbar from "@/components/layout/HeaderNavbar";
import BasicHeader from "@/components/layout/BasicHeader.vue";

export default {
  name: "HouseView",
  components: { HeaderNavbar, BasicHeader },
};
</script>

<style></style>
