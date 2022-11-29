<template>
  <div>
    <basic-header id="bh-user" name="회원 가입" />
    <div id="bc-user" class="container mt-3">
      <b-card class="text-center mt-5">
        <form class="row g-1 p-0 p-md-4 w-100">
          <table>
            <tr>
              <td class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="userName"
                  >이름 <span class="text-danger">*</span></label
                >
              </td>
              <td class="px-4">
                <input
                  v-model="userName"
                  id="userName"
                  type="text"
                  class="mb-4 form-control"
                />
              </td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="userId"
                  >아이디 <span class="text-danger">*</span></label
                >
              </td>
              <td v-if="userIdChk" class="px-4">
                <input
                  v-model="userId"
                  @change="checkIdDuplicated"
                  id="userId"
                  type="text"
                  class="mb-4 form-control"
                />
              </td>
              <td v-else class="px-4 form-floating">
                <input
                  v-model="userId"
                  @change="checkIdDuplicated"
                  id="userId"
                  type="text"
                  class="mb-4 form-control is-invalid"
                />
                <label for="userId" class="ps-5 text-danger"
                  >중복된 아이디가 존재합니다. 다른 아이디를 입력하세요.</label
                >
              </td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="userPassword"
                  >비밀번호 <span class="text-danger">*</span></label
                >
              </td>
              <td v-if="userPwdChk" class="px-4">
                <input
                  v-model="userPassword"
                  id="userPassword"
                  type="password"
                  class="mb-4 form-control"
                />
              </td>
              <td v-else class="px-4 form-floating">
                <input
                  v-model="userPassword"
                  id="userPassword"
                  type="password"
                  class="mb-4 form-control is-invalid"
                />
                <label for="userPassword" class="ps-5 text-danger"
                  >비밀번호가 일치하지 않습니다.
                </label>
              </td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="userRePassword"
                  >비밀번호 확인 <span class="text-danger">*</span></label
                >
              </td>
              <td v-if="userPwdChk" class="px-4">
                <input
                  v-model="userRePassword"
                  @change="passwordcheck"
                  id="userRePassword"
                  type="password"
                  class="mb-4 form-control"
                />
              </td>
              <td v-else class="px-4 form-floating">
                <input
                  v-model="userRePassword"
                  @change="passwordcheck"
                  id="userRePassword"
                  type="password"
                  class="mb-4 form-control is-invalid"
                />
                <label for="userRePassword" class="ps-5 text-danger"
                  >비밀번호가 일치하지 않습니다.
                </label>
              </td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="userEmail"
                  >이메일 <span class="text-danger">*</span></label
                >
              </td>
              <td class="px-4">
                <input
                  v-model="userEmail"
                  id="userEmail"
                  type="email"
                  class="mb-4 form-control"
                />
              </td>
            </tr>
          </table>
          <div class="col-12 text-center mt-3">
            <a
              @click="join"
              class="btn px-4 btn-block btn-primary lift text-uppercase"
              >회원가입</a
            >&nbsp;
            <router-link
              :to="{ name: 'login' }"
              class="btn px-4 btn-block btn-secondary lift text-uppercase"
              >로그인 이동</router-link
            >
          </div>
        </form>
      </b-card>
    </div>
  </div>
</template>

<script>
import BasicHeader from "../layout/BasicHeader.vue";
import { regist, idCheck } from "@/api/member.js";

export default {
  name: "UserRegist",
  data() {
    return {
      userType: "common",
      userId: "",
      userPassword: "",
      userRePassword: "",
      userName: "",
      userEmail: "",
      userIdChk: true,
      userPwdChk: true,
    };
  },
  components: { BasicHeader },
  methods: {
    checkIdDuplicated() {
      if (this.userId == "" || this.userId == null) {
        this.userIdChk = false;
        return;
      }

      if (this.userType == "common") {
        idCheck(
          this.userId,
          ({ data }) => {
            if (data == 1) {
              this.userIdChk = false;
            } else if (data == 0) {
              this.userIdChk = true;
            }
          },
          (error) => {
            console.log("id Check Error");
            console.log(error);
            // this.$swal("서버에 문제가 발생하였습니다.", { icon: "error" })
          }
        );
      }
    },
    passwordcheck() {
      if (this.userPassword === this.userRePassword) {
        this.userPwdChk = true;
      } else if (this.userPassword != this.userRePassword) {
        this.userPwdChk = false;
      }
    },
    validateForm() {
      console.log(this.userPassword);
      console.log(this.userRePassword);
      console.log(this.userPwdChk);

      if (this.userPassword == "") {
        return false;
      }
      if (this.userPassword !== this.userRePassword) {
        return false;
      }
      if (!this.userIdChk) {
        return false;
      }
      return true;
    },
    join() {
      let msg = "입력한 내용을 다시 확인해주세요.";
      if (!this.validateForm()) {
        alert(msg);
        return;
      }
      if (this.userType == "common") {
        const user = {
          email: this.userEmail,
          userid: this.userId,
          username: this.userName,
          userpwd: this.userPassword,
        };
        regist(
          user,
          () => {
            this.$router.push({ name: "login" });
            msg = "회원 가입에 성공하였습니다.";
            alert(msg);
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
};
</script>

<style>
.input_box button {
  float: left;
  margin-left: 1.04712%;
  width: 10.471204%;
  height: 55px;
  font-size: 16px;
  color: #fff;
  text-align: center;
  background: #6e7c8c;
}
</style>
>
