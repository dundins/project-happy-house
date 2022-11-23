<template>
  <div id="newsview">
    <header-navbar></header-navbar>
    <BasicHeader name="부동산 최신 뉴스" />
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
</style>
