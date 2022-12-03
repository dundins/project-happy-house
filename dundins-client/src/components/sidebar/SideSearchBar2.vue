<template>
  <div>
    <b-input-group class="px-1 mt-3">
      <b-form-input
        v-model="searchCode"
        @keyup.enter="searchPlace"
        placeholder="원하시는 지역을 검색하세요👋🏻"
        b-popover-light
      ></b-form-input>
      <b-button size="sm" variant="primary" class="mb-0" @click="searchPlace">
        <b-icon icon="search" aria-hidden="true"></b-icon>
      </b-button>
    </b-input-group>
    <div class="results mt-3">
      <div
        class="place"
        v-for="rs in search.results"
        :key="rs.id"
        @click="showPlace(rs)"
      >
        <div class="pname">{{ rs.place_name }}</div>
        <div class="addr">{{ rs.address_name }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchCode: null,
      search: {
        keyword: null,
        pgn: null,
        results: [],
      },
    };
  },
  methods: {
    searchPlace(e) {
      let keyword = "";
      if (e.target._value == null) {
        // 검색 아이콘 클릭한 경우
        keyword = e.path[2].children[0]._value.trim();
      } else {
        // Enter 키 누른 경우
        keyword = e.target._value.trim();
      }
      console.log(keyword);
      if (keyword.length === 0) {
        return;
      }
      const ps = new window.kakao.maps.services.Places();
      ps.keywordSearch(keyword, (data, status, pgn) => {
        this.search.keyword = keyword;
        this.search.pgn = pgn;
        this.search.results = data;
        // console.log(data);
      });
      // console.log("searchCode : " + this.searchCode);
    },
    showPlace(place) {
      console.log(place);
      // this.mapOption.center = {
      //   lat: place.y,
      //   lng: place.x,
      // };
      // console.log("place " + this.mapOption.center.lat);
    },
  },
};
</script>

<style scoped>
.results {
  max-height: 400px;
  overflow: auto;
}
.place {
  text-align: left;
  padding: 5px 20px;
  cursor: pointer;
}
.results .place:hover {
  background-color: rgb(218, 236, 251);
}
.pname {
  font-weight: bold;
}
.addr {
  font-size: small;
}
</style>
