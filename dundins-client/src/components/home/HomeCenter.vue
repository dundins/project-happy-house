<template>
  <div>
    <section>
      <div class="bg"></div>
      <h1>어떤 집을 찾고 계세요?</h1>
      <p>
        안녕하세요, 주택 실거래가 조회, 부동산 매물 조회 사이트 입니다. 둘러보고
        싶은 지역이나 아파트를 구경해 보세요!
      </p>
      <div class="sb">
        <side-search-bar></side-search-bar>
      </div>
    </section>
  </div>
</template>

<script>
import SideSearchBar from "@/components/sidebar/SideSearchBar.vue";

export default {
  data() {
    return {};
  },
  components: { SideSearchBar },
};
</script>

<style scoped>
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
.sb {
  width: 500px;
  margin: auto;
}
</style>
