<template>
  <div>
    <header-navbar></header-navbar>
    <BasicHeader name="로그인" />
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderNavbar from "@/components/HeaderNavbar";
import BasicHeader from "@/components/layout/BasicHeader.vue";

export default {
  name: "UserView",
  components: { HeaderNavbar, BasicHeader },
};
</script>

<style></style>
