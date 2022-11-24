<template>
  <div id="homeview">
    <header-navbar></header-navbar>
    <home-center></home-center>
    <div class="mt-530"></div>
    <b-row class="justify-content-md-center no-gutters mx-1">
      <b-col cols="5">
        <home-news-list class="mt-1"></home-news-list>
      </b-col>
      <b-col cols="5">
        <home-board-list class="mt-1"></home-board-list>
      </b-col>
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
#homeview {
  width: 100%;
}
.mt-530 {
  margin-top: 530px;
}
</style>
