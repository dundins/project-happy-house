<template>
  <div v-if="houses && houses.length != 0">
    <div id="map"></div>
    <div><h1>굳</h1></div>
  </div>
  <div v-else>
    <div class="purple darken-2 text-center">
      <span class="white--text">Lorem ipsum</span>
    </div>
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

    showHouseDetail(coords, index) {
      console.log(this.houses[index].아파트);
      // this.curIndex = index;
      // const houseNo = this.houseList[index].houseNo;
      // this.getHouseDeal(houseNo);
      // this.getOngoingList(houseNo);
      // this.getHouseReview(houseNo);
      // if (!this.listVisible) this.listVisible = true;
      // console.log("showHouseDetail" + index);
      if (this.map.getLevel() >= 5) {
        this.map.setLevel(3);
      }
      this.map.panTo(coords);
    },

    // 지도 관련 메소드
    addMarkerHandler(marker, coords, index) {
      let $this = this;
      kakao.maps.event.addListener(marker, "click", function () {
        $this.showHouseDetail(coords, index);
      });
    },
    test() {
      console.log("click!");
    },
    addMarkerWithAddress(image, bounds, address, index) {
      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();
      const $this = this;
      const $map = this.map;
      let $marker = this.markers;

      // 주소로 좌표를 검색합니다
      geocoder.addressSearch(address, function (result, status) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          // 결과값으로 받은 위치를 마커로 표시합니다
          // var marker = new kakao.maps.Marker({
          //   map: $map,
          //   position: coords,
          //   image: image,
          // });

          const content =
            `<div id="customoverlay">` +
            // `<div id="customoverlay" @click="${$this.showHouseDetail(coords, index)}">` +
            `  <a href="javascript:void(0);" onclick="${$this.showHouseDetail(
              coords,
              index
            )}">` +
            `    <span class="title">${$this.priceFormatting(
              $this.houses[index].거래금액
            )}</span>` +
            "  </a>" +
            "</div>";

          let marker = new kakao.maps.CustomOverlay({
            map: $map,
            position: coords,
            content: content,
            yAnchor: 1,
          });

          // $this.addMarkerHandler(marker, coords, index);
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
    priceFormatting(price) {
      price = price.replace(",", "");
      let inputNumber = price < 0 ? false : price;
      let unitWords = ["천", "억", "조", "경"];
      let splitUnit = 10000;
      let splitCount = unitWords.length;
      let resultArray = [];
      let resultString = "";

      for (let i = 0; i < splitCount; i++) {
        let unitResult =
          (inputNumber % Math.pow(splitUnit, i + 1)) / Math.pow(splitUnit, i);
        unitResult = Math.floor(unitResult);
        if (unitResult > 0) {
          resultArray[i] = unitResult;
        }
      }
      for (let i = resultArray.length - 1; i >= 0; i--) {
        if (!resultArray[i]) continue;

        if (i == 0) {
          resultString += String(resultArray[i]).substring(0, 1) + unitWords[i];
        } else {
          resultString += String(resultArray[i]) + unitWords[i];
        }
      }
      return resultString;
    },
    addMarkers(list) {
      let bounds = new kakao.maps.LatLngBounds();
      const markerImageSrc = require("../../assets/home-address.png");
      // "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png";
      const imageSize = new kakao.maps.Size(44, 44),
        imageOptions = { offset: new kakao.maps.Point(27, 69) };
      list.forEach((data, index) => {
        // 마커이미지와 마커를 생성합니다
        const markerImage = this.createMarkerImage(
          markerImageSrc,
          imageSize,
          imageOptions
        );
        const address = `${data.도로명} ${data.도로명건물본번호코드}`;
        this.addMarkerWithAddress(markerImage, bounds, address, index);
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
    // 마커 이미지 생성
    createMarkerImage(src, size, options) {
      var markerImage = new kakao.maps.MarkerImage(src, size, options);
      return markerImage;
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
<style>
#map {
  width: 100%;
  height: 100%;
}
.customoverlay {
  position: relative;
  bottom: 15px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
/* .customoverlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
} */
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  /* margin-right: 35px;*/
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>
