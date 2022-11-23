<template>
  <div>
    <BasicHeader name="마이 페이지" />
    <div>
      <div class="block-header pt-4 pt-lg-5 pb-0">
        <div class="container">
          <div class="row mb-3">
            <div class="col">
              <ul class="breadcrumb bg-transparent mb-0">
                <li class="breadcrumb-item">
                  <router-link to="/" class="text-decoration-none"
                    >Home</router-link
                  >
                </li>
                <li class="breadcrumb-item active">내 정보</li>
              </ul>
            </div>
          </div>
          <div class="row">
            <div class="col-12">
              <div class="card">
                <div class="card-body">
                  <div
                    class="d-flex align-items-center flex-column flex-md-row"
                  >
                    <img
                      src="../../assets/img/account.png"
                      alt="기본 프로필 이미지"
                      class="rounded-circle"
                      style="width: 70px"
                    />
                    <div
                      class="media-body ms-md-5 m-0 mt-4 mt-md-0 text-md-start text-center"
                    >
                      <h5 class="font-weight-bold d-inline-block me-2">
                        {{ userInfo.username }}
                      </h5>
                      님
                      <div class="text-muted mb-4">
                        <span class="text-dark">가입일</span> :
                        {{ userInfo.joindate }}
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div>
              <div class="col-xs-12" style="height: 20px"></div>
              <b-col
                class="d-flex justify-content-end"
                style="margin-left:10px: right"
              >
                <b-button v-b-toggle.collapse-1 variant="secondary"
                  >회원 정보 수정</b-button
                >
              </b-col>

              <b-collapse id="collapse-1" class="mt-2">
                <b-card
                  ><div class="container my-5">
                    <div class="d-flex flex-column align-items-center">
                      <form class="row g-1 p-0 p-md-4 w-100">
                        <table>
                          <tr>
                            <td
                              style="width: 15%"
                              class="px-4 border-top border-dark"
                            >
                              <label class="mb-4 form-label" for="userName"
                                >이름 <span class="text-danger">*</span></label
                              >
                            </td>
                            <td class="px-4">
                              <input
                                v-model="userName"
                                id="userName"
                                type="text"
                                class="mb-4 form-control form-control-lg"
                              />
                            </td>
                          </tr>

                          <tr>
                            <td class="px-4 border-top border-dark">
                              <label class="mb-4 form-label" for="userPassword"
                                >비밀번호
                                <span class="text-danger">*</span></label
                              >
                            </td>
                            <td v-if="userPwdChk" class="px-4">
                              <input
                                v-model="userPassword"
                                id="userPassword"
                                type="password"
                                class="mb-4 form-control form-control-lg"
                              />
                            </td>
                            <td v-else class="px-4 form-floating">
                              <input
                                v-model="userPassword"
                                id="userPassword"
                                type="password"
                                class="mb-4 form-control is-invalid form-control-lg"
                              />
                              <label for="userPassword" class="ps-5 text-danger"
                                >비밀번호가 일치하지 않습니다.
                              </label>
                            </td>
                          </tr>
                          <tr>
                            <td class="px-4 border-top border-dark">
                              <label
                                class="mb-4 form-label"
                                for="userRePassword"
                                >비밀번호 확인
                                <span class="text-danger">*</span></label
                              >
                            </td>
                            <td v-if="userPwdChk" class="px-4">
                              <input
                                v-model="userRePassword"
                                @change="passwordcheck()"
                                id="userRePassword"
                                type="password"
                                class="mb-4 form-control form-control-lg"
                              />
                            </td>
                            <td v-else class="px-4 form-floating">
                              <input
                                v-model="userRePassword"
                                @change="passwordcheck()"
                                id="userRePassword"
                                type="password"
                                class="mb-4 form-control is-invalid form-control-lg"
                              />
                              <label
                                for="userRePassword"
                                class="ps-5 text-danger"
                                >비밀번호가 일치하지 않습니다.
                              </label>
                            </td>
                          </tr>
                          <tr>
                            <td class="px-4 border-top border-dark">
                              <label class="mb-4 form-label" for="userEmail"
                                >이메일
                                <span class="text-danger">*</span></label
                              >
                            </td>
                            <td class="px-4">
                              <input
                                v-model="userEmail"
                                id="userEmail"
                                type="email"
                                class="mb-4 form-control form-control-lg"
                              />
                            </td>
                          </tr>
                        </table>
                        <div class="col-12 text-center mt-4">
                          <b-button variant="secondary" @click="updateInfo()"
                            >저장</b-button
                          >
                        </div>
                      </form>
                    </div>
                  </div></b-card
                >
              </b-collapse>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { update } from "@/api/member.js";
import BasicHeader from "../layout/BasicHeader.vue";

const memberStore = "memberStore";

export default {
  name: "UserAccount",
  data() {
    return {
      userPassword: "",
      userRePassword: "",
      userId: "",
      userName: "",
      userEmail: "",
      userPwdChk: true,
    };
  },
  components: { BasicHeader },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    passwordcheck() {
      if (
        this.userPassword === this.userRePassword &&
        this.userPassword != ""
      ) {
        this.userPwdChk = true;
        return true;
      } else if (this.userPassword != this.userRePassword) {
        this.userPwdChk = false;
        return false;
      }
    },
    validateForm() {
      console.log("비번 체크");
      console.log(this.userPwdChk);
      if (!this.passwordcheck()) {
        return false;
      }
      return true;
    },
    updateInfo() {
      console.log("??");
      if (!this.validateForm()) return;
      console.log("!!");
      const user = {
        userid: this.userId,
        email: this.userEmail,
        username: this.userName,
        userpwd: this.userPassword,
      };
      update(
        user,
        () => {
          console.log("회원 정보 수정 완료!");
          this.$router.push({ name: "home" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  created() {
    this.userId = this.userInfo.userid;
    this.userName = this.userInfo.username;
    this.userEmail = this.userInfo.email;
    console.log(this.userInfo);
  },
};
</script>

<style></style>
