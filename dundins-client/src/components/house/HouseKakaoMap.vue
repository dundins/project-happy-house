<template>
  <div v-if="houses && houses.length != 0">
    <div id="map">
      <b-card v-show="selected" id="showList" class="no-paddings card p-0">
        <b-row id="detail-title">
          <b-col cols="10">
            <div v-if="detail">
              <h5 style="text-align: left; font-weight: bold">
                {{ detail.aptName }}
              </h5>
              <h6 style="text-align: left">{{ curAddr }}</h6>
            </div>
          </b-col>
          <b-col cols="2">
            <b-icon
              icon="backspace-fill"
              id="xbtn"
              @click="SET_HOUSE_SELECTED(false)"
              font-scale="2"
            ></b-icon>
          </b-col>
        </b-row>
        <div class="col-xs-12" style="height: 30px"></div>
        <b-row>
          <b-col>
            <div id="roadview" style="height: 230px"></div>
          </b-col>
        </b-row>
        <div class="col-xs-12" style="height: 20px"></div>
        <hr class="my-2" />
        <div class="col-xs-12" style="height: 20px"></div>
        <b-row>
          <div v-if="detail">
            <table class="tb1" style="align-items: center">
              <colgroup>
                <col style="width: 100px" />
                <col style="width: 100px" />
                <col style="width: 100px" />
                <col style="width: 100px" />
              </colgroup>
              <thead>
                <tr>
                  <th>시도</th>
                  <th>구군</th>
                  <th>읍면동</th>
                  <th>거래 금액</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>{{ detail.sidoName }}</td>
                  <td>
                    <div>{{ detail.gugunName }}</div>
                  </td>
                  <td>{{ detail.dongName }}</td>
                  <td>{{ calcFormattingPrice(detail.recentPrice) }}</td>
                </tr>
              </tbody>
            </table>
            <div class="col-xs-12" style="height: 15px"></div>
            <table class="tb1" style="align-items: center">
              <colgroup>
                <col style="width: 100px" />
                <col style="width: 100px" />
                <col style="width: 100px" />
                <col style="width: 100px" />
              </colgroup>
              <thead>
                <tr>
                  <th>건축연도</th>
                  <th>면적</th>
                  <th>거래일자</th>
                  <th>지번</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>{{ detail.buildYear }}년</td>
                  <td>
                    <div>{{ detail.area }}㎡</div>
                    <div>{{ calcSize(detail.area) }}</div>
                  </td>
                  <td>{{ detail.dealYear }}년 {{ detail.dealMonth }}월</td>
                  <td>{{ detail.jibun }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </b-row>
        <div class="col-xs-12" style="height: 20px"></div>
        <hr class="my-2" />
        <div class="col-xs-12" style="height: 20px"></div>
        <b-row>
          <div v-if="detail">
            <div>최근 1년 평균 거래 금액</div>
            <line-chart
              style="height: 200px"
              :data="this.chartData"
            ></line-chart>
          </div>
        </b-row>
        <div class="col-xs-12" style="height: 20px"></div>
        <hr class="my-2" />
        <div class="col-xs-12" style="height: 20px"></div>
        <b-row>
          <b-col>
            <router-link :to="{ name: 'salewrite' }">
              <b-button
                style="width: 80%"
                v-b-toggle.collapse-1
                variant="outline-secondary"
                >매물 등록 하기</b-button
              >
            </router-link>
          </b-col>
        </b-row>
        <div class="bg-white mb-2">
          <div class="d-flex justify-content-between align-items-center">
            <h6 class="p-3 m-0">주거 환경 리뷰</h6>
            <i class="bi bi-plus-circle px-3 cursor-pointer"></i>
          </div>
          <div class="p-3 border-top">
            <div>등록된 리뷰가 없습니다.</div>
          </div>
        </div>
        <div class="bg-white mb-2">
          <div class="d-flex justify-content-between align-items-center">
            <h6 class="p-3 m-0">관심 현황</h6>
            <i class="bi bi-plus-circle px-3 cursor-pointer"></i>
          </div>
          <div class="p-3 border-top">
            <div>현재 0명의 이용자와 함께 보는 중</div>
          </div>
        </div>
      </b-card>
    </div>
  </div>
  <div v-else>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import Vue from "vue";
import Chartkick from "vue-chartkick";
import Chart from "chart.js";

Vue.use(Chartkick.use(Chart));

const houseStore = "houseStore";
const kakaoStore = "kakaoStore";

export default {
  name: "HouseKakaoMap",
  data() {
    return {
      chartDatas: [],
      curAddr: "",
      detail: null,
      houseSelected: true,
      curIndex: -1,
      markers: [],
      polygonMarkers: [],
      markerOnMap: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["selected", "houses", "type", "avgList"]),
    ...mapState(kakaoStore, ["sigs", "emds"]),
  },
  created() {
    this.SET_HOUSE_SELECTED(false); // test용 false로 바꾸기!!
    this.CLEAR_SIG_LIST();
    this.CLEAR_EMD_LIST();
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=be099b89e17f279641425236a83dc97a&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    // ...mapActions(kakaoStore, ["getSig"]),
    ...mapMutations(kakaoStore, [
      "CLEAR_SIG_LIST",
      "CLEAR_EMD_LIST",
      "SET_SIG_LIST",
      "SET_EMD_LIST",
    ]),
    ...mapMutations(houseStore, ["SET_HOUSE_SELECTED"]),
    ...mapActions(houseStore, ["getAvgList"]),
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
    },

    searchDetailAddrFromCoords(coords, callback) {
      var geocoder = new kakao.maps.services.Geocoder();
      // 좌표로 법정동 상세 주소 정보를 요청합니다
      geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
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
      this.detail = this.houses[index];
      this.SET_HOUSE_SELECTED(true);
      let $this = this;
      let cds = new kakao.maps.LatLng(
        this.houses[index].lat,
        this.houses[index].lng
      );
      this.getAvgList(this.houses[index].aptCode);
      this.searchDetailAddrFromCoords(cds, function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
          if (result[0].road_address) {
            $this.curAddr = result[0].address.address_name;
          } else {
            $this.curAddr = this.houses[index].dongName;
          }
        }
      });
      if (this.avgList.length >= 5)
        this.chartData = {
          2017: this.avgList[0],
          2018: this.avgList[1],
          2019: this.avgList[3],
          2020: this.avgList[4],
          2021: this.avgList[5],
        };
      else {
        this.chartData = {
          2017: 5,
          2018: 6,
          2019: 6,
          2020: 7,
          2021: 11,
        };
      }
      this.createRoadView(cds);
      this.curIndex = index;
      console.log(this.houses[index].aptName);
      // this.curIndex = index;
      // const houseNo = this.houseList[index].houseNo;
      // this.getHouseDeal(houseNo);
      // this.getOngoingList(houseNo);
      // this.getHouseReview(houseNo);
      // if (!this.listVisible) this.listVisible = true;
      // console.log("showHouseDetail" + index);
      if (this.map.getLevel() >= 4) {
        this.map.setLevel(2);
      }
      this.map.panTo(coords);
    },
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
        // eslint-disable-next-line prettier/prettier
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
    calcFormattingPrice(recentPrice) {
      let price = this.removeComma(recentPrice);
      return this.priceFormatting(price);
    },
    addMarkerWithAddress(coords, index) {
      let price = this.removeComma(this.houses[index].recentPrice);
      let scale = this.houses[index].area;
      let aptName = this.houses[index].aptName;
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
      let bounds = new kakao.maps.LatLngBounds();

      list.forEach((li, index) => {
        let coords = new kakao.maps.LatLng(li.lat, li.lng);
        this.addMarkerWithAddress(coords, index);
        bounds.extend(coords);
      });

      this.map.setBounds(bounds);
    },
    removeMarkers() {
      this.markers.forEach((m) => m.setMap(null));
      this.polygonMarkers.forEach((m) => m.setMap(null));
      this.markers = [];
      this.markerOnMap = [];
    },
    // 마커 이미지 생성
    createMarkerImage(src, size, options) {
      var markerImage = new kakao.maps.MarkerImage(src, size, options);
      return markerImage;
    },
    displayAreas(searchType) {
      let code = this.houses[0].dongCode;
      let path = [];
      if (searchType == "gugun") {
        code = code.substring(0, 5);
        this.CLEAR_SIG_LIST();
        this.SET_SIG_LIST(code);
        this.sigs[0].forEach((v) => {
          path.push(new kakao.maps.LatLng(v[1], v[0]));
        });
      } else {
        this.CLEAR_EMD_LIST();
        this.SET_EMD_LIST(code);
        this.emds[0].forEach((v) => {
          path.push(new kakao.maps.LatLng(v[1], v[0]));
        });
      }

      let area = { code: code, path: path };
      this.displayArea(area);
      return;
    },
    // 다각형을 생상하고 이벤트를 등록하는 함수입니다
    displayArea(area) {
      // 다각형을 생성합니다
      let polygon = new kakao.maps.Polygon({
        map: this.map, // 다각형을 표시할 지도 객체
        path: area.path,
        strokeWeight: 2,
        strokeColor: "black",
        strokeOpacity: 0.5,
        fillColor: "#f5e4e2",
        fillOpacity: 0.4,
      });
      this.polygonMarkers.push(polygon);
    },
    createRoadView(cc) {
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(cc, 150, function (panoId) {
        roadview.setPanoId(panoId, cc); //panoId와 중심좌표를 통해 로드뷰 실행
      });
    },
  },
  watch: {
    houses: function () {
      this.removeMarkers();
      if (this.houses.length) {
        this.displayAreas(this.type);
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
.title1:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -10px;
  width: 22px;
  height: 12px;
  background: url("../../assets/img/vertex_D32F2F.png");
}
.title2:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -9px;
  width: 22px;
  height: 12px;
  background: url("../../assets/img/vertex_1E88E5.png");
}
#showList {
  position: absolute;
  padding: 10px;

  width: 400px;
  height: 100%;
  z-index: 2;
  border-radius: 10px;
  /* background-color:rgba(255, 244, 244, 0.8); */
  /* opacity: 0.5; */

  overflow: auto;
}
#xbtn {
  float: right;
}
#xbtn:hover {
  cursor: pointer;
}
td {
  text-align: center;
  margin: auto;
  table-layout: fixed;
}
tr {
  font-size: small;
}
</style>
