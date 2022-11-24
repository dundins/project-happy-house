<template>
  <div>
    <header-navbar></header-navbar>
    <basic-header id="bh-h" name="매물 관리" />
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

<style>
#bh-h {
  width: 100%;
  height: 100%;
  text-align: center;
  position: relative;
  z-index: 1;
}
#bh-h::after {
  width: 100%;
  height: 100%;
  content: "";
  background-image: url(https://cdn.pixabay.com/photo/2014/09/18/11/56/skyscrapers-450793_960_720.jpg);
  background-size: cover;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
  opacity: 0.2;
}
</style>
