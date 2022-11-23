<template>
  <div>
    <BasicHeader name="로그인" />
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-card class="text-center mt-3" style="width: 30rem">
            <form class="row g-1 p-0 p-md-1">
              <div class="col-12">
                <div class="mb-2">
                  <label class="form-label" for="userId"></label>
                  <input
                    style="height: 80px"
                    v-model="user.userid"
                    required
                    id="userId"
                    type="text"
                    class="form-control form-control-lg"
                    placeholder="아이디"
                    @keyup.enter="confirm"
                  />
                </div>
              </div>
              <div class="col-12">
                <div class="mb-2">
                  <div class="form-label" for="userpwd"></div>
                  <input
                    style="height: 80px"
                    v-model="user.userpwd"
                    required
                    id="userpwd"
                    type="password"
                    class="form-control form-control-lg"
                    placeholder="비밀번호"
                    @keyup.enter="confirm"
                  />
                </div>
              </div>
              <p class="text-md-left" v-if="isLoginError" id="le">
                * 아이디 또는 비밀번호를 확인하세요.
              </p>
              <div class="col-12 text-center mt-4">
                <a
                  @click="confirm"
                  class="w-100 btn btn-lg btn-block btn-primary lift text-uppercase"
                  >로그인</a
                >
              </div>
              <div class="d-flex col-12 text-center mt-2">
                <router-link
                  :to="{ name: 'regist' }"
                  class="flex-grow-1 btn btn-lg btn-block btn-secondary lift fs-6 text-uppercase"
                  >회원가입</router-link
                >
                &nbsp;&nbsp;
                <router-link
                  to="/user/password"
                  class="flex-grow-1 btn btn-lg btn-block btn-secondary lift fs-6 text-uppercase"
                  >비밀번호 찾기</router-link
                >
              </div>
            </form>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import BasicHeader from "@/components/layout/BasicHeader.vue";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  components: {
    BasicHeader,
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>

<style scoped>
#userId::-webkit-input-placeholder {
  background-image: url(https://cdn2.iconfinder.com/data/icons/e-commerce-line-4-1/1024/user4-512.png);
  background-size: contain;
  background-position: 1px center;
  background-repeat: no-repeat;
  text-align: center;
  text-indent: 0;
}
#userpwd::-webkit-input-placeholder {
  background-image: url(https://cdn2.iconfinder.com/data/icons/e-commerce-line-4-1/1024/lock4-512.png);
  background-size: contain;
  background-position: 1px center;
  background-repeat: no-repeat;
  text-align: center;
  text-indent: 0;
}
#le {
  color: #aa3939;
}
</style>
