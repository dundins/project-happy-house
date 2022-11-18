<template>
  <div>
    <div id="map"></div>
    <div>
      <h2 v-if="house">zz{{ house.아파트 }}</h2>
      <h2 v-else>qq</h2>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "KakaoMap",
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=20eae81b3a2499aad461a35f363d719b";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const mapContainer = document.getElementById("map");
      const mapOption = {
        center: new kakao.maps.LatLng(37.5013, 127.0397),
        level: 3,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(mapContainer, mapOption);

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      const mapTypeControl = new kakao.maps.MapTypeControl();

      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      const zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      const markerPositions = new kakao.maps.LatLng(37.5013, 127.0397);
      const marker = new kakao.maps.Marker({
        position: markerPositions,
      });
      marker.setMap(this.map);
    },
    // displayMarker(markerPositions) {
    //   if (this.markers.length > 0) {
    //     this.markers.forEach((marker) => marker.setMap(null));
    //   }

    //   const positions = markerPositions.map(
    //     (position) => new kakao.maps.LatLng(...position)
    //   );

    //   if (positions.length > 0) {
    //     this.markers = positions.map(
    //       (position) =>
    //         new kakao.maps.Marker({
    //           map: this.map,
    //           position,
    //         })
    //     );

    //     const bounds = positions.reduce(
    //       (bounds, latlng) => bounds.extend(latlng),
    //       new kakao.maps.LatLngBounds()
    //     );

    //     this.map.setBounds(bounds);
    //   }
    // },
    displayTraffic() {
      this.btnClick1++;

      if (this.btnClick1 % 2 == 1) {
        // 지도에 교통정보를 표시하도록 지도타입을 추가합니다
        this.map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
      } else {
        // 아래 코드는 위에서 추가한 교통정보 지도타입을 제거합니다
        this.map.removeOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
      }
    },
    markHouse(value) {
      console.log(value);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 100%;
}
#tt {
  color: red;
}
</style>
