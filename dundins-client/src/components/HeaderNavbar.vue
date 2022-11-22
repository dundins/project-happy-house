<template>
  <div>
    <b-navbar toggleable="md" variant="info" class="bg-white">
      <router-link class="navbar-brand" to="/home">
        <img style="width: 64px" src="@/assets/img/dundins-logo.png" />
      </router-link>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <router-link class="nav-link" to="/">실거래가</router-link>
          <router-link class="nav-link" to="/house">부동산 매물</router-link>
          <router-link class="nav-link" to="/board">공지사항</router-link>
        </b-navbar-nav>

        <b-navbar-nav v-if="!userInfo" class="ml-auto">
          <router-link class="nav-link" to="/user/login">로그인</router-link>
          <router-link class="nav-link" to="/user/join">회원가입</router-link>
        </b-navbar-nav>
        <b-navbar-nav v-else class="ml-auto">
          <button @click="onClickLogout()">로그아웃</button>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
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

<style></style>
