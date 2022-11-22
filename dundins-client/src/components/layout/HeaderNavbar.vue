<template>
  <div
    id="header"
    class="navbar navbar-expand-lg sticky-top py-md-2 border-bottom"
  >
    <div class="container">
      <div class="left d-flex">
        <router-link class="navbar-brand" to="/home">
          <img style="width: 64px" src="../../assets/img/dundins-logo.png" />
        </router-link>
      </div>
      <div
        class="collapse navbar-collapse justify-content-between"
        id="navbar-collapse"
      >
        <ul class="navbar-nav">
          <li class="ms-lg-2 ms-md-1 nav-item">
            <router-link class="nav-link" to="/">실거래가</router-link>
          </li>
          <li class="ms-lg-2 ms-md-1 nav-item">
            <router-link class="nav-link" to="/board">공지사항</router-link>
          </li>
          <li class="ms-lg-2 ms-md-1 nav-item">
            <router-link class="nav-link" to="/house">매물</router-link>
          </li>
        </ul>
      </div>

      <div class="right d-flex">
        <div v-if="userInfo">
          <ul class="navbar-nav">
            <b-nav-item-dropdown right>
              <template #button-content>
                {{ userInfo.username }} 님
                <b-icon icon="people" font-scale="2"></b-icon>
              </template>
              <b-dropdown-item>
                <span @click="onClickAccount()">
                  <b-icon icon="person-circle"></b-icon> 마이 페이지
                </span>
              </b-dropdown-item>
              <b-dropdown-item>
                <span @click="onClickLogout()">
                  <b-icon icon="key"></b-icon> 로그아웃
                </span>
              </b-dropdown-item>
            </b-nav-item-dropdown>
          </ul>
        </div>
        <div v-else>
          <ul class="navbar-nav">
            <li class="ms-lg-2 ms-md-1 nav-item">
              <router-link class="nav-link" :to="{ name: 'login' }"
                >로그인</router-link
              >
            </li>
            <li class="ms-lg-2 ms-md-1 nav-item">
              <router-link class="nav-link" :to="{ name: 'regist' }"
                >회원가입</router-link
              >
            </li>
          </ul>
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
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      this.$router.push({ name: "login" });
    },
    onClickAccount() {
      this.$router.push({ name: "account" });
    },
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
#header {
  background-color: white;
}
</style>
