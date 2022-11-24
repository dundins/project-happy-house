<template>
  <div id="boardview">
    <header-navbar></header-navbar>
    <basic-header id="bh-b" name="공지사항" />
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderNavbar from "@/components/layout/HeaderNavbar";
import BasicHeader from "@/components/layout/BasicHeader.vue";

export default {
  name: "BoardView",
  components: { HeaderNavbar, BasicHeader },
};
</script>

<style>
#bh-b {
  width: 100%;
  height: 100%;
  text-align: center;
  position: relative;
  z-index: 1;
}
#bh-b::after {
  width: 100%;
  height: 100%;
  content: "";
  background-image: url(https://cdn.pixabay.com/photo/2016/03/27/23/11/post-it-notes-1284667_960_720.jpg);
  background-size: cover;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
  opacity: 0.2;
}
</style>
