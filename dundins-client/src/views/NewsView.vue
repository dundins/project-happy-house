<template>
  <div id="newsview">
    <header-navbar></header-navbar>
    <basic-header id="bh-n" name="부동산 최신 뉴스" />
    <news-list></news-list>
  </div>
</template>

<script>
import HeaderNavbar from "@/components/layout/HeaderNavbar";
import BasicHeader from "@/components/layout/BasicHeader";
import NewsList from "@/components/home/NewsList";

export default {
  name: "NewsView",
  components: { HeaderNavbar, BasicHeader, NewsList },
};
</script>

<style scoped>
#newsview {
  width: 100%;
}
#bh-n {
  width: 100%;
  height: 100%;
  text-align: center;
  position: relative;
  z-index: 1;
}
#bh-n::after {
  width: 100%;
  height: 100%;
  content: "";
  background-image: url(https://cdn.pixabay.com/photo/2015/03/13/14/12/newspaper-671689_960_720.jpg);
  background-size: cover;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
  opacity: 0.2;
}
</style>
