<template>
  <div id="boardview">
    <header-navbar></header-navbar>
    <BasicHeader name="공지사항" />
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderNavbar from "@/components/HeaderNavbar";
import BasicHeader from "@/components/layout/BasicHeader.vue";

export default {
  name: "BoardView",
  components: { HeaderNavbar, BasicHeader },
};
</script>

<style>
#boardview {
  background-color: white;
}
</style>
