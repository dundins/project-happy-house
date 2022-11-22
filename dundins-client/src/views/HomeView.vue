<template>
  <div id="homeview">
    <header-navbar></header-navbar>
    <home-center></home-center>
    <div>
      <home-news></home-news>
      <home-board></home-board>
    </div>
  </div>
</template>

<script>
import HeaderNavbar from "@/components/layout/HeaderNavbar";
import HomeCenter from "@/components/home/HomeCenter";
// import HomeNews from "@/components/home/HomeNews";
// import HomeBoard from "@/components/home/HomeBoard";

export default {
  name: "HomeView",
  components: {
    HeaderNavbar,
    HomeCenter,
    // HomeNews,
    // HomeBoard,
  },
};
</script>

<style>
.bg {
  background-color: rgba(0, 0, 0, 0.3); /*살짝 투명한 검정으로 배경색*/
  width: 100%;
  height: 500px;
  position: absolute; /*다른 요소들 위로 겹쳐질 수 있게함*/
  top: 0px;
}
section {
  background-image: url(https://cdn.pixabay.com/photo/2022/04/04/13/54/city-7111380_960_720.jpg);
  background-size: cover;
  display: block;
  width: 100%;
  height: 500px;
  color: #ffffff;
  text-align: center;
  padding-top: 150px;
  position: absolute; /*다른 요소들 위로 겹쳐질 수 있게함*/
}

section h1 {
  font-size: 2.5em;
}
section p {
  margin-bottom: 30px;
}

section h1,
p,
a {
  position: relative;
  z-index: 1000; /*맨 앞으로 나오도록 함*/
}
</style>
