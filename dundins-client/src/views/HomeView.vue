<template>
  <div id="homeview">
    <header-navbar></header-navbar>
    <home-center></home-center>
    <div class="mt-550"></div>
    <b-row class="justify-content-md-center">
      <b-col cols="4"><home-news-list></home-news-list></b-col>
      <b-col cols="4"><home-board-list></home-board-list></b-col>
    </b-row>
  </div>
</template>

<script>
import HeaderNavbar from "@/components/layout/HeaderNavbar";
import HomeCenter from "@/components/home/HomeCenter";
import HomeNewsList from "@/components/home/HomeNewsList";
import HomeBoardList from "@/components/home/HomeBoardList";

export default {
  name: "HomeView",
  components: {
    HeaderNavbar,
    HomeCenter,
    HomeNewsList,
    HomeBoardList,
  },
};
</script>

<style scoped>
.mt-550 {
  margin-top: 550px;
}
</style>
