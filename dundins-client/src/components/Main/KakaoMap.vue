<template>
  <v-main v-if="houses && houses.length != 0">
    <div id="map"></div>
  </v-main>
  <v-main v-else>
    <div id="map"></div>
  </v-main>
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
      markerOnMap: [],
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
    // addMarkerWithAddress(content, bounds, address, index) {
    //   // 주소-좌표 변환 객체를 생성합니다
    //   var geocoder = new kakao.maps.services.Geocoder();
    //   const $this = this;

    //   // 주소로 좌표를 검색합니다
    //   geocoder.addressSearch(address, function (result, status) {
    //     // 정상적으로 검색이 완료됐으면
    //     if (status === kakao.maps.services.Status.OK) {
    //       let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    //       let price = $this.removeComma($this.houses[index].거래금액);
    //       let scale = $this.houses[index].전용면적;
    //       let aptName = $this.houses[index].아파트;

    //       var content = document.createElement("div");
    //       content.className = "customoverlay";
    //       if (price > 100000) {
    //         content.innerHTML = `<span class="title1"><div id="scale">${$this.calcSize(
    //           scale
    //         )}</div><div>${$this.priceFormatting(price)}</div>
    //         </span>`;
    //       } else {
    //         content.innerHTML = `<span class="title2"><div id="scale">${$this.calcSize(
    //           scale
    //         )}</div><div>${$this.priceFormatting(price)}</div>
    //         </span>`;
    //       }

    //       let marker = null;
    //       if (!$this.markerOnMap.includes(aptName)) {
    //         $this.markerOnMap.push(aptName);
    //         marker = new kakao.maps.CustomOverlay({
    //           map: $this.map,
    //           position: coords,
    //           content: content,
    //           yAnchor: 1,
    //         });

    //         marker.setMap($this.map);

    //         $this.markers.push(marker);
    //       }

    //       content.addEventListener("click", function () {
    //         $this.showHouseDetail(coords, index);
    //       });

    //       bounds.extend(coords);
    //       // $this.map.setBounds(bounds);

    //       // console.log();
    //       // 인포윈도우로 장소에 대한 설명을 표시합니다
    //       // var infowindow = new kakao.maps.InfoWindow({
    //       //   content:
    //       //     '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>',
    //       // });
    //       // infowindow.open($map, marker);
    //       // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    //       // $map.setCenter(coords);
    //     }
    //   });
    // },
    calcSize(size) {
      return Math.floor((size / 3) * 10) / 10 + "평";
    },
    removeComma(num) {
      return num.replace(",", "");
    },
    priceFormatting(price) {
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
    addMarkerWithAddress2(coords, index) {
      let price = this.removeComma(this.houses[index].거래금액);
      let scale = this.houses[index].전용면적;
      let aptName = this.houses[index].아파트;
      let $this = this;
      let content = document.createElement("div");
      content.className = "customoverlay";
      if (price > 100000) {
        content.innerHTML = `<span class="title1"><div id="scale">${this.calcSize(
          scale
        )}</div><div>${this.priceFormatting(price)}</div>
            </span>`;
      } else {
        content.innerHTML = `<span class="title2"><div id="scale">${this.calcSize(
          scale
        )}</div><div>${this.priceFormatting(price)}</div>
            </span>`;
      }

      let marker = null;
      if (!this.markerOnMap.includes(aptName)) {
        this.markerOnMap.push(aptName);
        marker = new kakao.maps.CustomOverlay({
          map: this.map,
          position: coords,
          content: content,
          yAnchor: 1,
        });

        marker.setMap(this.map);

        this.markers.push(marker);
      }

      content.addEventListener("click", function () {
        $this.showHouseDetail(coords, index);
      });
      // $this.map.setBounds(bounds);
    },
    addMarkers(list) {
      // let bounds = new kakao.maps.LatLngBounds();

      // const content = `<div id="customoverlay"></div>`;

      // list.forEach((data, index) => {
      //   const address = `${data.도로명} ${data.도로명건물본번호코드}`;
      //   this.addMarkerWithAddress(content, bounds, address, index);
      // });
      // console.log(this.markerOnMap);
      // this.map.setBounds(bounds);

      let bounds = new kakao.maps.LatLngBounds();
      var geocoder = new kakao.maps.services.Geocoder();
      let $this = this;
      const addressSearch = (address) => {
        return new Promise((resolve) => {
          geocoder.addressSearch(address, function (result, status) {
            console.log("결과스");
            console.log(result);
            if (status === kakao.maps.services.Status.OK) {
              resolve({ lat: result[0].y, lng: result[0].x });
            } else {
              console.log("키키" + result);
            }
          });
        });
      };

      // async-await
      (async () => {
        try {
          const positions = [];

          for (const address of list) {
            const addr = `${address.도로명} ${address.도로명건물본번호코드}`;
            const result = await addressSearch(addr);
            positions.push(result);
          }

          positions.map(function (position, index) {
            let coords = new kakao.maps.LatLng(position.lat, position.lng);
            $this.addMarkerWithAddress2(coords, index);
            bounds.extend(coords);
          });
          $this.map.setBounds(bounds);
        } catch (e) {
          console.log(e);
        }
      })();
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
      this.markerOnMap = [];
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
      // console.log("정보 갱신!");
      // console.log(this.houses);
      this.removeMarkers();
      if (this.houses.length) {
        this.addMarkers(this.houses);
      }
    },
  },
};
</script>

<style>
#map {
  width: 100%;
  height: 100%;
}
.customoverlay {
  position: relative;
  bottom: 85px;
  border-radius: 10px;
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
#scale {
  font-size: 12px;
}
.customoverlay .title1 {
  display: block;
  text-align: center;
  background: #d32f2f;
  border-radius: 10px;
  /* margin-right: 35px;*/
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
  color: white;
}
.title2 {
  display: block;
  text-align: center;
  background: #1e88e5;
  border-radius: 10px;
  /* margin-right: 35px;*/
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
  color: white;
}

/* .customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -10px;
  width: 22px;
  height: 12px;
  background: url("../../assets/vertex_D32F2F.png");
} */
.title1:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -10px;
  width: 22px;
  height: 12px;
  background: url("../../assets/vertex_D32F2F.png");
}
.title2:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -9px;
  width: 22px;
  height: 12px;
  background: url("../../assets/vertex_1E88E5.png");
}
</style>
