<template>
  <div v-if="houses && houses.length != 0">
    <div id="map"></div>
    <div><h1>굳</h1></div>
  </div>
  <div v-else>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      mapInfo: "",
      markers: [],
    };
  },
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
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=20eae81b3a2499aad461a35f363d719b&libraries=services";
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

      // const markerPositions = new kakao.maps.LatLng(37.5013, 127.0397);
      // const marker = new kakao.maps.Marker({
      //   position: markerPositions,
      // });
      // marker.setMap(this.map);
    },
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

    showHouseDetail(index) {
      // this.curIndex = index;
      // const houseNo = this.houseList[index].houseNo;
      // this.getHouseDeal(houseNo);
      // this.getOngoingList(houseNo);
      // this.getHouseReview(houseNo);
      // if (!this.listVisible) this.listVisible = true;
      console.log("showHouseDetail" + index);
    },

    // 지도 관련 메소드
    addMarkerHandler(marker, markerPosition, index) {
      // let marker = new kakao.maps.Marker({
      //   position: markerPosition,
      // });
      let $this = this;
      kakao.maps.event.addListener(marker, "click", function () {
        console.log("??");
        $this.showHouseDetail(index);
      });
    },
    addMarkerWithAddress(bounds, address, index) {
      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();
      const $this = this;
      const $map = this.map;
      let $marker = this.markers;

      // 주소로 좌표를 검색합니다
      geocoder.addressSearch(address, function (result, status) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          // console.log($map);
          let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
            map: $map,
            position: coords,
          });
          $this.addMarkerHandler(marker, coords, index);
          marker.setMap($map);
          bounds.extend(coords);

          $map.setBounds(bounds);
          $marker.push(marker);

          // console.log();
          // 인포윈도우로 장소에 대한 설명을 표시합니다
          // var infowindow = new kakao.maps.InfoWindow({
          //   content:
          //     '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>',
          // });
          // infowindow.open($map, marker);
          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          // $map.setCenter(coords);
        }
      });
    },
    addMarkers(list) {
      let bounds = new kakao.maps.LatLngBounds();

      list.forEach((data, index) => {
        const address = `${data.도로명} ${data.도로명건물본번호코드}`;
        this.addMarkerWithAddress(bounds, address, index);
      });
      // list.forEach(({ lat, lng }, index) => {
      //   console.log(`forEach ${index}`);
      //   let markerPosition = new kakao.maps.LatLng(lat, lng);
      //   this.addMarkerByOne(markerPosition, index);
      //   bounds.extend(markerPosition);
      // });
      // this.addInfoWindow();
    },
    addInfoWindow() {
      // console.log("addiw");
      // this.markers.forEach((marker) => {
      //   // let item = this.houses[index];
      //   let infoContents = `<div style="width:150px;text-align:center;padding:6px 0;">zzz</div>`;
      //   // let infoContents = `<div style="width:150px;text-align:center;padding:6px 0;">${item.aptName}</div>`;
      //   let infoWindow = new kakao.maps.InfoWindow({
      //     content: infoContents,
      //   });
      //   let $this = this;
      //   kakao.maps.event.addListener(marker, "mouseover", function () {
      //     infoWindow.open($this.map, marker);
      //   });
      //   kakao.maps.event.addListener(marker, "mouseout", function () {
      //     infoWindow.close();
      //   });
      // });
    },
    removeMarkers() {
      this.markers.forEach((m) => m.setMap(null));
      this.markers = [];
    },
  },
  created() {},
  watch: {
    houses: function () {
      console.log("정보 갱신!");
      console.log(this.houses);
      this.removeMarkers();
      if (this.houses.length) {
        this.addMarkers(this.houses);
      }
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
</style>
