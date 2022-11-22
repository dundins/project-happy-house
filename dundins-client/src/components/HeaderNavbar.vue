<template>
  <div class="navbar navbar-expand-lg sticky-top py-md-2 border-bottom">
    <div class="container">
      <div class="left d-flex">
        <router-link class="navbar-brand" to="/home">
          <img style="width: 64px" src="../assets/img/dundins-logo.png" />
        </router-link>
      </div>
      <div
        class="collapse navbar-collapse justify-content-between"
        id="navbar-collapse"
      >
        <ul class="navbar-nav">
          <li class="ms-lg-2 ms-md-1 nav-item">
            <router-link class="nav-link" to="/board">공지사항</router-link>
          </li>
          <li class="ms-lg-2 ms-md-1 nav-item">
            <router-link class="nav-link" to="/">실거래가</router-link>
          </li>
          <li class="ms-lg-2 ms-md-1 nav-item">
            <router-link class="nav-link" to="/house">매물</router-link>
          </li>
        </ul>
      </div>

      <div class="right d-flex">
        <div v-if="!userInfo">
          <ul class="navbar-nav">
            <li class="ms-lg-2 ms-md-1 nav-item">
              <router-link class="nav-link" to="/user/login"
                >로그인</router-link
              >
            </li>
            <li class="ms-lg-2 ms-md-1 nav-item">
              <router-link class="nav-link" to="/user/join"
                >회원가입</router-link
              >
            </li>
          </ul>
        </div>
        <div v-else>
          <button @click="onClickLogout()">로그아웃</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
const memberStore = "memberStore";

export default {
  name: "HeaderNavbar",
  data() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      // console.log(this.userInfo.userid);
      // //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      // //+ satate에 isLogin, userInfo 정보 변경)
      // // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      // if (this.$route.path != "/home") this.$router.push({ name: "home" });
      this.$router.push({ name: "user" });
    },
  },
  mounted() {
    console.log(this.isLogin);
  },
};
</script>

<style scoped>
.link {
  text-decoration: none;
}

#header {
  background-color: #fafaf8;
  height: 80px;
  box-shadow: 0px 0.1px 1px 1px #deddd6;
}
#dd {
  width: 50px;
}
</style>
